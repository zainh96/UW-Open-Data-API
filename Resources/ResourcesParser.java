package Resources;
import Core.APIResult;
import Core.MetaData;
import Core.MetaDataParser;
import Core.UWParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by ZainH on 02/09/2015.
 *
 * A /resources/tutors call back's JSON Object looks like
 * {
 *     ...
 *     "data":[
 *          {
 *              "subject": (String)
 *              "catalog_number": (String)
 *              "title": (String)
 *              "tutors_count": (int)
 *              "contact_url": (String)
 *          },
 *          ...
 *     ]
 * }
 *
 * A /resources/printers call back's JSON Object looks like
 * {
 *     ...
 *     "data":[
 *          {
 *              "printer": (String)
 *              "ad": (String)
 *              "server": (String)
 *              "comment": (String)
 *              "driver": (String)
 *              "room": (String)
 *              "faculty": (String)
 *          },
 *          ...
 *     ]
 * }
 *
 * A /resources/infosession call back's JSON Object looks like
 * {
 *     ...
 *     "data":[
 *          {
 *              "id": (String)
 *              "employer": (String)
 *              "date": (String)
 *              "start_time": (String)
 *              "end_time": (String)
 *              "location": (String)
 *              "website": (String)
 *              "audience": (String)
 *              "programs": (String, comma separated values)
 *              "description": (String)
 *          },
 *          ...
 *     ]
 * }
 *
 *
 * A /resources/goosewatch call back's JSON Object looks like
 * {
 *     ...
 *     "data":[
 *          {
 *             "id": (int)
 *             "location": (String)
 *             "latitude": (double)
 *             "longitude": (double)
 *             "updated": (String)
 *          },
 *          ...
 *     ]
 * }
 *
 * Proper Use:
 * 1) call setParseType()
 * 2) call getEndPoint() and use it to build a URL with UWOpenDataAPI.buildURL(...)
 * 3) once an APIResult is received in onDownloadComplete(APIResult apiResult), call setAPIResult, then call parseJSON()
 * 4) the proper data is now parsed, and can be retrieved. (This depends on your parseType). Data can be requested through
 *      * getTutors()
 *      * getPrinters()
 *      * getInfoSessions()
 *      * getGooseLocations()
 */
public class ResourcesParser extends UWParser {
    // end points
    private static final String TUTORS_END_POINT = "resources/tutors";
    private static final String PRINTERS_END_POINT = "resources/printers";
    private static final String INFOSESSIONS_END_POINT = "resources/infosessions";
    private static final String GOOSEWATCH_END_POINT = "resources/goosewatch";

    // JSON Object leaf node tags
    private static final String SUBJECT_TAG = "subject";
    private static final String CATALOG_NUMBER_TAG = "catalog_number";
    private static final String TITLE_TAG = "title";
    private static final String TUTORS_COUNT_TAG = "tutors_count";
    private static final String CONTACT_URL_TAG = "contact_url";
    private static final String PRINTER_TAG = "printer";
    private static final String AD_TAG = "ad";
    private static final String SERVER_TAG = "server";
    private static final String COMMENT_TAG = "comment";
    private static final String DRIVER_TAG = "driver";
    private static final String ROOM_TAG = "room";
    private static final String FACULTY_TAG = "faculty";
    private static final String ID_TAG = "id";
    private static final String EMPLOYER_TAG = "employer";
    private static final String DATE_TAG = "date";
    private static final String START_TIME_TAG = "start_time";
    private static final String END_TIME_TAG = "end_time";
    private static final String LOCATION_TAG = "location";
    private static final String WEBSITE_TAG = "website";
    private static final String AUDIENCE_TAG = "audience";
    private static final String PROGRAMS_TAG = "programs";
    private static final String DESCRIPTION_TAG = "description";
    private static final String LATITUDE_TAG = "latitude";
    private static final String LONGITUDE_TAG = "longitude";
    private static final String UPDATED_TAG = "updated";

    // JSON Array tags
    private static final String DATA_TAG = "data";

    // contains all JSON data
    private APIResult apiResult = null;

    // parse type
    public enum ParseType {
        TUTORS,
        PRINTERS,
        INFOSESSIONS,
        GOOSEWATCH
    }

    private ParseType parseType = ParseType.TUTORS;

    // /resources/tutors variables
    private ArrayList<Tutor> tutors = new ArrayList<>();

    // /resources/printers variables
    private ArrayList<CampusPrinter> printers = new ArrayList<>();

    // /resources/infosessions variables
    private ArrayList<InfoSession> infoSessions = new ArrayList<>();

    // /resources/goosewatch variables
    private ArrayList<GooseLocation> gooseLocations = new ArrayList<>();

    @Override
    public void parseJSON() {
        if(apiResult == null || apiResult.getResultJSON() == null) return;
        switch (parseType){
            case TUTORS:
                parseTutorsJSON();
                break;
            case PRINTERS:
                parsePrintersJSON();
                break;
            case INFOSESSIONS:
                parseInfoSessionsJSON();
                break;
            case GOOSEWATCH:
                parseGooseWatchJSON();
                break;
        }
    }

    private void parseTutorsJSON(){
        try {
            JSONArray tutorArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int tutorArrayLength = tutorArray.length();

            for(int i = 0; i < tutorArrayLength; i++){
                JSONObject tutorObject = tutorArray.getJSONObject(i);
                Tutor tutor = new Tutor();

                if(!tutorObject.isNull(SUBJECT_TAG))
                    tutor.setSubject(tutorObject.getString(SUBJECT_TAG));

                if(!tutorObject.isNull(CATALOG_NUMBER_TAG))
                    tutor.setCatalogNumber(tutorObject.getString(CATALOG_NUMBER_TAG));

                if(!tutorObject.isNull(TITLE_TAG))
                    tutor.setTitle(tutorObject.getString(TITLE_TAG));

                if(!tutorObject.isNull(TUTORS_COUNT_TAG))
                    tutor.setTutorsCount(tutorObject.getInt(TUTORS_COUNT_TAG));

                if(!tutorObject.isNull(CONTACT_URL_TAG))
                    tutor.setContactUrl(tutorObject.getString(CONTACT_URL_TAG));

                tutors.add(tutor);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parsePrintersJSON(){
        try {
            JSONArray printerArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int printerArrayLength = printerArray.length();

            for(int i = 0; i < printerArrayLength; i++){
                JSONObject printerObject = printerArray.getJSONObject(i);
                CampusPrinter printer = new CampusPrinter();

                if(!printerObject.isNull(PRINTER_TAG))
                    printer.setPrinter(printerObject.getString(PRINTER_TAG));

                if(!printerObject.isNull(AD_TAG))
                    printer.setAd(printerObject.getString(AD_TAG));

                if(!printerObject.isNull(SERVER_TAG))
                    printer.setServer(printerObject.getString(SERVER_TAG));

                if(!printerObject.isNull(COMMENT_TAG))
                    printer.setComment(printerObject.getString(COMMENT_TAG));

                if(!printerObject.isNull(DRIVER_TAG))
                    printer.setDriver(printerObject.getString(DRIVER_TAG));

                if(!printerObject.isNull(ROOM_TAG))
                    printer.setRoom(printerObject.getString(ROOM_TAG));

                if(!printerObject.isNull(FACULTY_TAG))
                    printer.setFaculty(printerObject.getString(FACULTY_TAG));

                printers.add(printer);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseInfoSessionsJSON(){

        try
        {
            JSONArray infosessionArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int infosessionArrayLength = infosessionArray.length();

            for(int i = 0; i < infosessionArrayLength; i++){
                JSONObject jsonInfoSessionLocation = infosessionArray.getJSONObject(i);
                InfoSession location = new InfoSession();

                if(!jsonInfoSessionLocation.isNull(ID_TAG))
                    location.setId(Integer.parseInt(jsonInfoSessionLocation.getString(ID_TAG)));

                if(!jsonInfoSessionLocation.isNull(EMPLOYER_TAG))
                    location.setEmployer(jsonInfoSessionLocation.getString(EMPLOYER_TAG));

                if(!jsonInfoSessionLocation.isNull(DATE_TAG))
                    location.setDate(jsonInfoSessionLocation.getString(DATE_TAG));

                if(!jsonInfoSessionLocation.isNull(START_TIME_TAG))
                    location.setStart_time(jsonInfoSessionLocation.getString(START_TIME_TAG));

                if(!jsonInfoSessionLocation.isNull(END_TIME_TAG))
                    location.setEnd_time(jsonInfoSessionLocation.getString(END_TIME_TAG));

                if(!jsonInfoSessionLocation.isNull(LOCATION_TAG))
                    location.setLocation(jsonInfoSessionLocation.getString(LOCATION_TAG));

                if(!jsonInfoSessionLocation.isNull(WEBSITE_TAG))
                    location.setWebsite(jsonInfoSessionLocation.getString(WEBSITE_TAG));

                if(!jsonInfoSessionLocation.isNull(AUDIENCE_TAG))
                    location.setAudience(jsonInfoSessionLocation.getString(AUDIENCE_TAG));

                if(!jsonInfoSessionLocation.isNull(PROGRAMS_TAG))
                    location.setPrograms(jsonInfoSessionLocation.getString(PROGRAMS_TAG));

                if(!jsonInfoSessionLocation.isNull(DESCRIPTION_TAG))
                    location.setDescription(jsonInfoSessionLocation.getString(DESCRIPTION_TAG));

                infoSessions.add(location);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseGooseWatchJSON(){
        try
        {
            JSONArray gooseLocationArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int gooseLocationArrayLength = gooseLocationArray.length();

            for(int i = 0; i < gooseLocationArrayLength; i++){
                JSONObject gooseLocationObject = gooseLocationArray.getJSONObject(i);
                GooseLocation goose = new GooseLocation();

                if(!gooseLocationObject.isNull(ID_TAG))
                    goose.setId(gooseLocationObject.getInt(ID_TAG));

                if(!gooseLocationObject.isNull(LOCATION_TAG))
                    goose.setLocation(gooseLocationObject.getString(LOCATION_TAG));

                if(!gooseLocationObject.isNull(LATITUDE_TAG))
                    goose.setLatitude(gooseLocationObject.getDouble(LATITUDE_TAG));

                if(!gooseLocationObject.isNull(LONGITUDE_TAG))
                    goose.setLongitude(gooseLocationObject.getDouble(LONGITUDE_TAG));

                if(!gooseLocationObject.isNull(UPDATED_TAG))
                    goose.setTimeUpdated(gooseLocationObject.getString(UPDATED_TAG));

                gooseLocations.add(goose);
            }

        } catch(JSONException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Tutor> getTutors() {
        return tutors;
    }

    public ArrayList<CampusPrinter> getPrinters() {
        return printers;
    }

    public ArrayList<InfoSession> getInfoSessions() {
        return infoSessions;
    }

    public ArrayList<GooseLocation> getGooseLocations() {
        return gooseLocations;
    }

    @Override
    public void setAPIResult(APIResult apiResult) {
        this.apiResult = apiResult;
    }

    @Override
    public APIResult getAPIResult() {
        return apiResult;
    }

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }

    @Override
    public MetaData getMeta() {
        MetaDataParser parser = new MetaDataParser();
        parser.setAPIResult(apiResult);
        parser.parseJSON();
        return parser.getMeta();
    }

    public String getEndPoint(){
        switch (parseType){
            case TUTORS:
                return TUTORS_END_POINT;
            case PRINTERS:
                return PRINTERS_END_POINT;
            case INFOSESSIONS:
                return INFOSESSIONS_END_POINT;
            case GOOSEWATCH:
                return GOOSEWATCH_END_POINT;
            default:
                return TUTORS_END_POINT;
        }
    }
}
