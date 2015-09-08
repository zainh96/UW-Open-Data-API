package Events;

import Core.APIResult;
import Core.MetaData;
import Core.MetaDataParser;
import Core.UWParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ZainH on 08/09/2015.
 */
public class EventsParser extends UWParser {
    // end points
    private static final String EVENTS_ENDPOINT = "events";
    private static final String EVENTS_SITE_END_POINT = "events/%s"; // /events/{site}
    private static final String EVENTS_SITE_ID_END_POINT = "events/%s/%s"; // /events/{site}/{id}
    private static final String EVENTS_HOLIDAYS_END_POINT = "events/holidays"; // /events/holidays

    // JSON object leaf node tags
    private static final String ID_TAG = "id";
    private static final String SITE_TAG = "site";
    private static final String SITE_NAME_TAG = "site_name";
    private static final String TITLE_TAG = "title";
    private static final String START_TAG = "start";
    private static final String END_TAG = "end";
    private static final String LINK_TAG = "link";
    private static final String UPDATED_TAG = "updated";
    private static final String DESCRIPTION_TAG = "description";
    private static final String DESCRIPTION_RAW_TAG = "description_raw";
    private static final String START_DAY_TAG = "start_day";
    private static final String END_DAY_TAG = "end_day";
    private static final String START_DATE_TAG = "start_date";
    private static final String START_TIME_TAG = "start_time";
    private static final String END_DATE_TAG = "end_date";
    private static final String END_TIME_TAG = "end_time";
    private static final String COST_TAG = "cost";
    private static final String URL_TAG = "url";
    private static final String FILE_TAG = "file";
    private static final String ALT_TAG = "alt";
    private static final String MIME_TAG = "mime";
    private static final String SIZE_TAG = "size";
    private static final String WIDTH_TAG = "width";
    private static final String HEIGHT_TAG = "height";
    private static final String NAME_TAG = "name";
    private static final String STREET_TAG = "street";
    private static final String ADDITIONAL_TAG = "additional";
    private static final String CITY_TAG = "city";
    private static final String PROVINCE_TAG = "province";
    private static final String POSTAL_CODE_TAG = "postal_code";
    private static final String COUNTRY_TAG = "country";
    private static final String LATITUDE_TAG = "latitude";
    private static final String LONGITUDE_TAG = "longitude";
    private static final String SITE_ID_TAG = "site_id";
    private static final String REVISION_ID_TAG = "revision_id";
    private static final String LINK_CALENDAR_TAG = "link_calendar";
    private static final String DATE_TAG = "date";

    // JSON object / array tags
    private static final String DATA_TAG = "data";
    private static final String TIMES_TAG = "times";
    private static final String TYPES_TAG = "type";
    private static final String TAG = "tag";
    private static final String AUDIENCE_TAG = "audience";
    private static final String WEBSITE_TAG = "website";
    private static final String HOST_TAG = "host";
    private static final String IMAGE_TAG = "image";
    private static final String LOCATION_TAG = "location";


    // contains all JSON information
    private APIResult apiResult = null;

    // parse types
    public enum ParseType{
        EVENTS,
        EVENTS_SITE,
        EVENTS_SITE_ID,
        EVENTS_HOLIDAYS
    }

    private ParseType parseType = ParseType.EVENTS;

    // for /events and /events/{site}
    private ArrayList<Event> events = new ArrayList<>();

    // for /events/{site}/{id}
    private Event specificEvent = new Event();

    // for /events/holidays
    private ArrayList<Holiday> holidays = new ArrayList<>();

    @Override
    public void parseJSON() {
        if(apiResult == null || apiResult.getResultJSON() == null) return;

        switch (parseType){
            case EVENTS:
            case EVENTS_SITE:
                parseEventsJSON();
                break;
            case EVENTS_SITE_ID:
                parseIdJSON();
                break;
            case EVENTS_HOLIDAYS:
                parseHolidaysJSON();
                break;
        }
    }

    private void parseEventsJSON(){
        try {
            JSONArray eventsArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int eventsArrayLength = eventsArray.length();
            events.clear();

            for(int i = 0; i < eventsArrayLength; i++) {
                events.add(parseSmallEvent(eventsArray.getJSONObject(i)));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseIdJSON(){
        try {
            JSONObject eventObject = apiResult.getResultJSON().getJSONObject(DATA_TAG);

            if(!eventObject.isNull(ID_TAG))
                specificEvent.setEventId(eventObject.getInt(ID_TAG));

            if(!eventObject.isNull(TITLE_TAG))
                specificEvent.setEventTitle(eventObject.getString(TITLE_TAG));

            if(!eventObject.isNull(DESCRIPTION_TAG))
                specificEvent.setEventDescriptionRaw(eventObject.getString(DESCRIPTION_TAG));

            if(!eventObject.isNull(DESCRIPTION_RAW_TAG))
                specificEvent.setEventDescriptionRaw(eventObject.getString(DESCRIPTION_RAW_TAG));

            JSONArray eventTimesArray = eventObject.getJSONArray(TIMES_TAG);
            ArrayList<EventTime> times = new ArrayList<>();
            for(int i = 0; i < eventTimesArray.length(); i++){
                EventTime time = new EventTime();
                JSONObject timeObject = eventTimesArray.getJSONObject(i);

                if(!timeObject.isNull(START_TAG))
                    time.setStart(timeObject.getString(START_TAG));

                if(!timeObject.isNull(END_TAG))
                    time.setEnd(timeObject.getString(END_TAG));

                if(!timeObject.isNull(START_DAY_TAG))
                    time.setStartDay(timeObject.getString(START_DAY_TAG));

                if(!timeObject.isNull(START_DATE_TAG))
                    time.setStartDate(timeObject.getString(START_DATE_TAG));

                if(!timeObject.isNull(START_TIME_TAG))
                    time.setStartTime(timeObject.getString(START_TIME_TAG));

                if(!timeObject.isNull(END_DATE_TAG))
                    time.setEndDate(timeObject.getString(END_DATE_TAG));

                if(!timeObject.isNull(END_DAY_TAG))
                    time.setEndDay(timeObject.getString(END_DAY_TAG));

                if(!timeObject.isNull(END_TIME_TAG))
                    time.setEndTime(timeObject.getString(END_TIME_TAG));

                times.add(time);
            }

            specificEvent.setTimes(times);

            if(!eventObject.isNull(COST_TAG))
                specificEvent.setCost(eventObject.getString(COST_TAG));

            if(!eventObject.isNull(TYPES_TAG))
                specificEvent.setTypes(getArrayFromObject(eventObject.getJSONArray(TYPES_TAG)));

            if(!eventObject.isNull(AUDIENCE_TAG))
                specificEvent.setAudience(getArrayFromObject(eventObject.getJSONArray(AUDIENCE_TAG)));

            if(!eventObject.isNull(TAG))
                specificEvent.setTags(getArrayFromObject(eventObject.getJSONArray(TAG)));

            JSONObject websiteObject = eventObject.getJSONObject(WEBSITE_TAG);

            if(!websiteObject.isNull(TITLE_TAG))
                specificEvent.setWebsiteTitle(websiteObject.getString(TITLE_TAG));

            if(!websiteObject.isNull(URL_TAG))
                specificEvent.setWebsiteUrl(websiteObject.getString(URL_TAG));

            JSONObject hostObject = eventObject.getJSONObject(HOST_TAG);

            if(!hostObject.isNull(TITLE_TAG))
                specificEvent.setHostTitle(hostObject.getString(TITLE_TAG));

            if(!hostObject.isNull(URL_TAG))
                specificEvent.setHostUrl(hostObject.getString(URL_TAG));

            JSONObject imageObject = eventObject.getJSONObject(IMAGE_TAG);

            if(!imageObject.isNull(ID_TAG))
                specificEvent.setImageId(imageObject.getInt(ID_TAG));

            if(!imageObject.isNull(FILE_TAG))
                specificEvent.setImageFile(imageObject.getString(FILE_TAG));

            if(!imageObject.isNull(ALT_TAG))
                specificEvent.setImageAlt(imageObject.getString(ALT_TAG));

            if(!imageObject.isNull(MIME_TAG))
                specificEvent.setImageMime(imageObject.getString(MIME_TAG));

            if(!imageObject.isNull(SIZE_TAG))
                specificEvent.setImageSize(imageObject.getInt(SIZE_TAG));

            if(!imageObject.isNull(WIDTH_TAG))
                specificEvent.setImageWidth(imageObject.getInt(WIDTH_TAG));

            if(!imageObject.isNull(HEIGHT_TAG))
                specificEvent.setImageHeight(imageObject.getInt(HEIGHT_TAG));

            if(!imageObject.isNull(URL_TAG))
                specificEvent.setImageUrl(imageObject.getString(URL_TAG));

            JSONObject locationObject = eventObject.getJSONObject(LOCATION_TAG);

            if(!locationObject.isNull(ID_TAG))
                specificEvent.setLocationId(locationObject.getInt(ID_TAG));

            if(!locationObject.isNull(NAME_TAG))
                specificEvent.setLocationName(locationObject.getString(NAME_TAG));

            if(!locationObject.isNull(STREET_TAG))
                specificEvent.setStreet(locationObject.getString(STREET_TAG));

            if(!locationObject.isNull(ADDITIONAL_TAG))
                specificEvent.setAdditional(locationObject.getString(ADDITIONAL_TAG));

            if(!locationObject.isNull(CITY_TAG))
                specificEvent.setCity(locationObject.getString(CITY_TAG));

            if(!locationObject.isNull(PROVINCE_TAG))
                specificEvent.setProvince(locationObject.getString(PROVINCE_TAG));

            if(!locationObject.isNull(POSTAL_CODE_TAG))
                specificEvent.setPostalCode(locationObject.getString(POSTAL_CODE_TAG));

            if(!locationObject.isNull(COUNTRY_TAG))
                specificEvent.setCountry(locationObject.getString(COUNTRY_TAG));

            if(!locationObject.isNull(LATITUDE_TAG))
                specificEvent.setLatitude(locationObject.getDouble(LATITUDE_TAG));

            if(!locationObject.isNull(LONGITUDE_TAG))
                specificEvent.setLongitude(locationObject.getDouble(LONGITUDE_TAG));

            if(!eventObject.isNull(SITE_NAME_TAG))
                specificEvent.setSiteName(eventObject.getString(SITE_NAME_TAG));

            if(!eventObject.isNull(SITE_ID_TAG))
                specificEvent.setSiteId(eventObject.getString(SITE_ID_TAG));

            if(!eventObject.isNull(REVISION_ID_TAG))
                specificEvent.setRevisionId(eventObject.getInt(REVISION_ID_TAG));

            if(!eventObject.isNull(LINK_CALENDAR_TAG))
                specificEvent.setCalendarLink(eventObject.getString(LINK_CALENDAR_TAG));

            if(!eventObject.isNull(LINK_TAG))
                specificEvent.setLink(eventObject.getString(LINK_TAG));

            if(!eventObject.isNull(UPDATED_TAG))
                specificEvent.setUpdated(eventObject.getString(UPDATED_TAG));

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseHolidaysJSON(){
        try {
            JSONArray holidayArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int holidayArrayLength = holidayArray.length();

            for(int i = 0; i < holidayArrayLength; i++){
                Holiday holiday = new Holiday();
                JSONObject holidayObject = holidayArray.getJSONObject(i);

                if(!holidayObject.isNull(NAME_TAG))
                    holiday.setName(holidayObject.getString(NAME_TAG));

                if(!holidayObject.isNull(DATE_TAG))
                    holiday.setDate(holidayObject.getString(DATE_TAG));

                holidays.add(holiday);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private Event parseSmallEvent(JSONObject eventObject) throws JSONException {
        Event event = new Event();

        if(!eventObject.isNull(ID_TAG))
            event.setEventId(eventObject.getInt(ID_TAG));

        if(!eventObject.isNull(SITE_TAG))
            event.setSite(eventObject.getString(SITE_TAG));

        if(!eventObject.isNull(SITE_NAME_TAG))
            event.setSiteName(eventObject.getString(SITE_NAME_TAG));

        if(!eventObject.isNull(TITLE_TAG))
            event.setEventTitle(eventObject.getString(TITLE_TAG));

        JSONArray eventTimesArray = eventObject.getJSONArray(TIMES_TAG);
        ArrayList<EventTime> times = new ArrayList<>();
        for(int i = 0; i < eventTimesArray.length(); i++){
            EventTime time = new EventTime();
            JSONObject timeObject = eventTimesArray.getJSONObject(i);

            if(!timeObject.isNull(START_TAG))
                time.setStart(timeObject.getString(START_TAG));

            if(!timeObject.isNull(END_TAG))
                time.setEnd(timeObject.getString(END_TAG));

            times.add(time);
        }

        event.setTimes(times);

        if(!eventObject.isNull(TYPES_TAG))
            event.setTypes(getArrayFromObject(eventObject.getJSONArray(TYPES_TAG)));

        if(!eventObject.isNull(LINK_TAG))
            event.setLink(eventObject.getString(LINK_TAG));

        if(!eventObject.isNull(UPDATED_TAG))
            event.setUpdated(eventObject.getString(UPDATED_TAG));

        return event;
    }

    private ArrayList<String> getArrayFromObject(JSONArray object) throws JSONException {
        ArrayList<String> strings = new ArrayList<>();

        for(int i = 0; i < object.length(); i++){
            strings.add(object.getString(i));
        }

        return strings;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Holiday> getHolidays() {
        return holidays;
    }

    public Event getSpecificEvent() {
        return specificEvent;
    }

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
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

    public String getEndPoint(){
        switch (parseType){
            case EVENTS:
                return EVENTS_ENDPOINT;
            case EVENTS_HOLIDAYS:
                return EVENTS_HOLIDAYS_END_POINT;
            default:
                return EVENTS_ENDPOINT;
        }
    }

    public String getEndPoint(String site){
        return String.format(EVENTS_SITE_END_POINT, site);
    }

    public String getEndPoint(String site, String id){
        return String.format(EVENTS_SITE_ID_END_POINT, site, id);
    }
}
