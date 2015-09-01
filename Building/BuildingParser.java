import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ZainH on 01/09/2015.
 *
 * A /building/list call back's JSON Object looks like
 * {
 *     "meta":{
 *         [See MetaDataParser.java]
 *     },
 *     "data":[
 *          {
 *              "building_id": (String)
 *              "building_code": (String)
 *              "building_name": (String)
 *              "alternate_names": [
 *                  (String array)
 *              ]
 *              "latitude":(double)
 *              "longitude": (double)
 *              "building_sections":[
 *                  {
 *                      "section_name": (String)
 *                      "latitude": (double)
 *                      "longitude": (double)
 *                  },
 *                  ...
 *              ]
 *          },
 *          ...
 *     ]
 * }
 *
 * A /buildings/{building_code} call back's JSON Object looks like
 * {
 *     "meta":{
 *         [See MetaDataParser.java]
 *     },
 *     "data":{
 *              "building_id": (String)
 *              "building_code": (String)
 *              "building_name": (String)
 *              "alternate_names": [
 *                  (String array)
 *              ]
 *              "latitude":(double)
 *              "longitude": (double)
 *              "building_sections":[
 *                  {
 *                      "section_name": (String)
 *                      "latitude": (double)
 *                      "longitude": (double)
 *                  },
 *                  ...
 *              ]
 *          }
 * }
 *
 *
 * A /buildings/{building}/{room}/courses call back's JSON Object looks like
 *
 *  {
 *      "meta":{
 *          [See MetaDataParser.java]
 *      },
 *      "data":[
 *          {
 *              "class_number": (int)
 *              "subject": (String)
 *              "title": (String)
 *              "section": (String)
 *              "weekdays": (String)
 *              "start_time": (String)
 *              "end_time": (String)
 *              "start_date": (String)
 *              "end_date": (String)
 *              "enrollment_total": (int)
 *              "instructors":[
 *                  (String array)
 *              ]
 *              "building": (String)
 *              "room": (String)
 *              "term": (int)
 *              "last_updated": (String)
 *          },
 *          ...
 *      ]
 *  }
 *
 *
 * Proper Use:
 *  1) call setParseType(ParseType type) before calling getEndPoint(...)/ or parseJSON().
 *  2) call getEndPoint(...) to build the proper URL with UWOpenDataAPI.buildURL(String endPoint, String apiLey).
 *  3) Once an APIResult is received from JSONDownloader.java, give the APIResult object to this class with setAPIResult(APIResult apiResult)
 *  4) call parseJSON()
 *  5) the proper data has now been parsed, and can be requested through
 *         * getUwBuildings()
 *         * getBuildingCodeBuilding()
 *         * getUwClasses() (Note, some variables in each UWClass may be 0 or null, because the call does not return enough data to fill a UWClass)
 */
public class BuildingParser extends UWParser {
    // endpoints
    private static final String LIST_END_POINT = "buildings/list";
    private static final String BUILDING_CODE_END_POINT = "buildings/%s"; // /buildings/{building_code}
    private static final String COURSES_END_POINT = "buildings/%s/%s/courses"; // buildings/{building}/{room}/courses

    // JSONObject End Point Node Tags
    private static final String BUILDING_ID_TAG = "building_id";
    private static final String BUILDING_CODE_TAG = "building_code";
    private static final String BUILDING_NAME_TAG = "building_name";
    private static final String LATITUDE_TAG = "latitude";
    private static final String LONGITUDE_TAG = "longitude_tag";
    private static final String SECTION_NAME_TAG = "section_name";

    private final String SUBJECT_TAG = "subject";
    private final String CATALOG_NUMBER_TAG = "catalog_number";
    private final String TITLE_TAG = "title";
    private final String CLASS_NUMBER_TAG = "class_number";
    private final String SECTION_TAG = "section";
    private final String ENROLLMENT_TOTAL_TAG = "enrollment_total";
    private final String TERM_TAG = "term";
    private final String LAST_UPDATED_TAG = "last_updated";
    private final String START_TIME_TAG = "start_time";
    private final String END_TIME_TAG = "end_time";
    private final String WEEKDAYS_TAG = "weekdays";
    private final String START_DATE_TAG = "start_date";
    private final String END_DATE_TAG = "end_date";
    private final String BUILDING_TAG = "building";
    private final String ROOM_TAG = "room";

    // JSONArray End Point Tags
    private static final String ALTERNATE_NAMES_TAG = "alternate_names";
    private static final String DATA_TAG = "data";
    private static final String BUILDING_SECTIONS_TAG = "building_sections";
    private static final String INSTRUCTORS_TAG = "instructors";

    // contains all JSON information
    private APIResult apiResult = null;

    // Parse Types
    public enum ParseType{
        LIST,
        BUILDING_CODE,
        COURSES
    }
    private ParseType parseType = ParseType.LIST;

    // /buildings/list variables
    ArrayList<UWBuilding> uwBuildings = new ArrayList<>();

    // /buildings/{building_code} variables
    UWBuilding buildingCodeBuilding = null;

    // buildings/{building}/{room}/courses variables
    ArrayList<UWClass> uwClasses = new ArrayList<>();

    @Override
    public void parseJSON() {
        if(apiResult == null || apiResult.getResultJSON() == null) return;
        switch (parseType){
            case LIST:
                parseListJSON();
                break;
            case BUILDING_CODE:
                parseBuildingCodeJSON();
                break;
            case COURSES:
                parseCoursesJSON();
                break;
        }
    }

    // apiResult, and it's containing JSONObject are not null
    private void parseListJSON(){
        try {
            JSONArray buildingArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int buildingArrayLength = buildingArray.length();

            for(int i = 0; i < buildingArrayLength; i++){
                JSONObject buildingObject = buildingArray.getJSONObject(i);
                UWBuilding building = parseSingleBuilding(buildingObject);
                uwBuildings.add(building);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseBuildingCodeJSON(){
        // there is only one building object
        try {
            JSONObject buildingObject = apiResult.getResultJSON().getJSONObject(DATA_TAG);
            buildingCodeBuilding = parseSingleBuilding(buildingObject);
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseCoursesJSON(){
        try {
            JSONArray classArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int classArrayLength = classArray.length();

            for (int i = 0; i < classArrayLength; i++) {
                JSONObject classObject = classArray.getJSONObject(i);
                UWClass uwClass = new UWClass();

                if (!classObject.isNull(SUBJECT_TAG))
                    uwClass.setSubject(classObject.getString(SUBJECT_TAG));

                if (!classObject.isNull(CATALOG_NUMBER_TAG))
                    uwClass.setCatalog_number(classObject.getString(CATALOG_NUMBER_TAG));

                if (!classObject.isNull(TITLE_TAG))
                    uwClass.setTitle(classObject.getString(TITLE_TAG));

                if (!classObject.isNull(CLASS_NUMBER_TAG))
                    uwClass.setClass_number(classObject.getInt(CLASS_NUMBER_TAG));

                if (!classObject.isNull(SECTION_TAG))
                    uwClass.setSection(classObject.getString(SECTION_TAG));

                if (!classObject.isNull(ENROLLMENT_TOTAL_TAG))
                    uwClass.setEnroll_total(classObject.getInt(ENROLLMENT_TOTAL_TAG));

                if(!classObject.isNull(WEEKDAYS_TAG))
                    uwClass.setWeekdays(classObject.getString(WEEKDAYS_TAG));

                if(!classObject.isNull(START_TIME_TAG))
                    uwClass.setStart_time(classObject.getString(START_TIME_TAG));

                if(!classObject.isNull(END_TIME_TAG))
                    uwClass.setEnd_time(classObject.getString(END_TIME_TAG));

                if(!classObject.isNull(START_DATE_TAG))
                    uwClass.setStart_date(classObject.getString(START_DATE_TAG));

                if(!classObject.isNull(END_DATE_TAG))
                    uwClass.setEnd_date(classObject.getString(END_DATE_TAG));

                // load instructor data
                JSONArray offeringInstructors = classObject.getJSONArray(INSTRUCTORS_TAG);
                ArrayList<String> instructors = new ArrayList<>();
                int num_instructors = offeringInstructors.length();

                for (int j = 0; j < num_instructors; j++) {
                    instructors.add(offeringInstructors.getString(j));
                }

                if (num_instructors > 0) {
                    uwClass.setInstructors(instructors);
                }

                if(!classObject.isNull(BUILDING_TAG))
                    uwClass.setBuilding(classObject.getString(BUILDING_TAG));

                if(!classObject.isNull(ROOM_TAG))
                    uwClass.setRoom(classObject.getString(ROOM_TAG));

                if (!classObject.isNull(LAST_UPDATED_TAG))
                    uwClass.setLast_updated(classObject.getString(LAST_UPDATED_TAG));

                if (!classObject.isNull(TERM_TAG))
                    uwClass.setTerm(classObject.getInt(TERM_TAG));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public ArrayList<UWBuilding> getUwBuildings() {
        return uwBuildings;
    }

    public UWBuilding getBuildingCodeBuilding() {
        return buildingCodeBuilding;
    }

    public ArrayList<UWClass> getUwClasses() {
        return uwClasses;
    }

    private UWBuilding parseSingleBuilding (JSONObject buildingObject) throws JSONException {
        UWBuilding building = new UWBuilding();

        if(!buildingObject.isNull(BUILDING_ID_TAG))
            building.setBuildingId(buildingObject.getString(BUILDING_ID_TAG));

        if(!buildingObject.isNull(BUILDING_CODE_TAG))
            building.setBuildingCode(buildingObject.getString(BUILDING_CODE_TAG));

        if(!buildingObject.isNull(BUILDING_NAME_TAG))
            building.setBuildingName(buildingObject.getString(BUILDING_NAME_TAG));

        // alternate names
        JSONArray alternateNameArray = buildingObject.getJSONArray(ALTERNATE_NAMES_TAG);
        int alternateNameArrayLength = alternateNameArray.length();

        ArrayList<String> alternateNames = new ArrayList<>();
        for(int j = 0; j < alternateNameArrayLength; j++){
            alternateNames.add(alternateNameArray.getString(j));
        }

        building.setAlternateNames(alternateNames);

        if(!buildingObject.isNull(LATITUDE_TAG))
            building.setLatitude(buildingObject.getDouble(LATITUDE_TAG));

        if(!buildingObject.isNull(LONGITUDE_TAG))
            building.setLongitude(buildingObject.getDouble(LONGITUDE_TAG));

        // sections
        JSONArray sectionArray = buildingObject.getJSONArray(BUILDING_SECTIONS_TAG);
        ArrayList<UWBuildingSection> sections = new ArrayList<>();
        int sectionArrayLength = sectionArray.length();

        for(int j = 0; j < sectionArrayLength; j++){
            UWBuildingSection section = new UWBuildingSection();
            JSONObject sectionObject = sectionArray.getJSONObject(j);

            if(!sectionObject.isNull(SECTION_NAME_TAG))
                section.setSection_name(sectionObject.getString(SECTION_NAME_TAG));

            if(!sectionObject.isNull(LATITUDE_TAG))
                section.setLatitude(sectionObject.getDouble(LATITUDE_TAG));

            if(!sectionObject.isNull(LONGITUDE_TAG))
                section.setLongitude(sectionObject.getDouble(LONGITUDE_TAG));

            sections.add(section);
        }
        building.setBuildingSections(sections);
        return building;
    }

    @Override
    public void setAPIResult(APIResult apiResult) {
        this.apiResult = apiResult;
    }

    @Override
    public APIResult getAPIResult() {
        return apiResult;
    }

    @Override
    public MetaData getMeta() {
        MetaDataParser parser = new MetaDataParser();
        parser.setAPIResult(apiResult);
        parser.parseJSON();
        return parser.getMeta();
    }

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }

    public String getEndPoint(){
        return LIST_END_POINT;
    }

    public String getEndPoint(String buildingCode){
        return String.format(BUILDING_CODE_END_POINT, buildingCode);
    }

    public String getEndPoint(String building, String room){
        return String.format(COURSES_END_POINT, building, room);
    }
}
