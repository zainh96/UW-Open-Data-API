package Terms;
import Core.APIResult;
import Core.MetaData;
import Core.MetaDataParser;
import Core.UWParser;
import Course.Reserve;
import Course.ScheduleData;
import Course.UWClass;
import Resources.InfoSession;
import Resources.ResourcesParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by ZainH on 06/09/2015.
 */
public class TermsParser extends UWParser {
    // end point strings
    private static final String EXAM_END_POINT = "terms/%s/examschedule"; // /terms/{term}/examschedule
    private static final String SUBJECT_END_POINT = "terms/%s/%s/schedule"; // /terms/{term}/{subject}/schedule
    private static final String CATALOG_END_POINT = "terms/%s/%s/%s/schedule"; // /terms/{term}/{subject}/{catalog_number}/schedule
    private static final String INFO_SESSION_END_POINT = "terms/%s/infosessions"; // /terms/{term}/infosessions

    // JSON Object leaf node tags
    private static final String COURSE_TAG = "course";
    private static final String SECTION_TAG = "section";
    private static final String DAY_TAG = "day";
    private static final String DATE_TAG = "date";
    private static final String LOCATION_TAG = "location";
    private static final String NOTES_TAG = "notes";

    private static final String SUBJECT_TAG = "subject";
    private static final String CATALOG_NUMBER_TAG = "catalog_number";
    private static final String UNITS_TAG = "units";
    private static final String TITLE_TAG = "title";
    private static final String NOTE_TAG = "note";
    private static final String CLASS_NUMBER_TAG = "class_number";
    private static final String CAMPUS_TAG = "campus";
    private static final String ASSOCIATED_CLASS_TAG = "associated_class";
    private static final String RELATED_COMPONENT_1_TAG = "related_component_1";
    private static final String RELATED_COMPONENT_2_TAG = "related_component_2";
    private static final String ENROLLMENT_CAPACITY_TAG = "enrollment_capacity";
    private static final String ENROLLMENT_TOTAL_TAG = "enrollment_total";
    private static final String WAITING_CAPACITY_TAG = "waiting_capacity";
    private static final String WAITING_TOTAL_TAG = "waiting_total";
    private static final String TOPIC_TAG = "topic";
    private static final String RESERVES_TAG = "reserves";
    private static final String TERM_TAG = "term";
    private static final String ACADEMIC_LEVEL_TAG = "academic_level";
    private static final String LAST_UPDATED_TAG = "last_updated";
    private static final String START_TIME_TAG = "start_time";
    private static final String END_TIME_TAG = "end_time";
    private static final String WEEKDAYS_TAG = "weekdays";
    private static final String START_DATE_TAG = "start_date";
    private static final String END_DATE_TAG = "end_date";
    private static final String BUILDING_TAG = "building";
    private static final String ROOM_TAG = "room";
    private static final String RESERVE_GROUP_TAG = "reserve_group";
    private static final String IS_TBA_TAG = "is_tba";
    private static final String IS_CANCELLED_TAG = "is_cancelled";
    private static final String IS_CLOSED_TAG = "is_closed";

    // JSON Array/Object Tags
    private static final String DATA_TAG = "data";
    private static final String SECTIONS_TAG = "sections";
    private static final String CLASSES_TAG = "classes";
    private static final String DATES_TAG = "dates";
    private static final String INSTRUCTORS_TAG = "instructors";


    // contains all JSON information
    APIResult apiResult = null;

    public enum ParseType {
        EXAM_SCHEDULE,
        SUBJECT_SCHEDULE,
        CATALOG_SCHEDULE,
        INFO_SESSIONS
    }

    // /terms/{term}/examschedule variables
    private ArrayList<CourseExamSchedule> examSchedules = new ArrayList<>();

    // /terms/{term}/{subject}/schedule variables
    private ArrayList<UWClass> subjectClasses = new ArrayList<>();

    // /terms/{term}/{subject}/{catalog_number}/schedule variables
    private ArrayList<UWClass> catalogNumberClasses = new ArrayList<>();

    // /terms/{term}/infosessions variables
    private ArrayList<InfoSession> infoSessions = new ArrayList<>();

    private ParseType parseType = ParseType.EXAM_SCHEDULE;

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }

    @Override
    public void parseJSON() {
        if(apiResult == null || apiResult.getResultJSON() == null) return;

        switch (parseType){
            case EXAM_SCHEDULE:
                parseExamScheduleJSON();
                break;
            case SUBJECT_SCHEDULE:
                parseSubjectScheduleJSON();
                break;
            case CATALOG_SCHEDULE:
                parseCatalogScheduleJSON();
                break;
            case INFO_SESSIONS:
                parseInfoSessionJSON();
        }
    }

    private void parseExamScheduleJSON(){
        try {
            JSONArray scheduleArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int scheduleArrayLength = scheduleArray.length();

            for(int i = 0; i < scheduleArrayLength; i++){
                JSONObject scheduleObject = scheduleArray.getJSONObject(i);
                CourseExamSchedule schedule = new CourseExamSchedule();

                if(!scheduleObject.isNull(COURSE_TAG))
                    schedule.setCourse(scheduleObject.getString(COURSE_TAG));

                JSONArray sectionArray = scheduleObject.getJSONArray(SECTIONS_TAG);
                int sectionArrayLength = sectionArray.length();
                ArrayList<Section> sections = new ArrayList<>();

                for(int j = 0; j < sectionArrayLength; j++){
                    JSONObject sectionObject = sectionArray.getJSONObject(j);
                    Section section = new Section();

                    if(!sectionObject.isNull(SECTION_TAG))
                        section.setSection(sectionObject.getString(SECTION_TAG));

                    if(!sectionObject.isNull(DAY_TAG))
                        section.setDay(sectionObject.getString(DAY_TAG));

                    if(!sectionObject.isNull(DATE_TAG))
                        section.setDate(sectionObject.getString(DATE_TAG));

                    if(!sectionObject.isNull(START_TIME_TAG))
                        section.setStartTime(sectionObject.getString(START_TIME_TAG));

                    if(!sectionObject.isNull(END_TIME_TAG))
                        section.setEndTime(sectionObject.getString(END_TIME_TAG));

                    if(!sectionObject.isNull(LOCATION_TAG))
                        section.setLocation(sectionObject.getString(LOCATION_TAG));

                    if(!sectionObject.isNull(NOTES_TAG))
                        section.setNotes(sectionObject.getString(NOTES_TAG));

                    sections.add(section);
                }

                schedule.setSections(sections);
                examSchedules.add(schedule);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseSubjectScheduleJSON(){
        try {
            JSONArray classArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int classArrayLength = classArray.length();

            for (int i = 0; i < classArrayLength; i++) {
                JSONObject classObject = classArray.getJSONObject(i);
                UWClass uwClass = parseSingleClass(classObject);
                subjectClasses.add(uwClass);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseCatalogScheduleJSON(){
        try {
            JSONArray classArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int classArrayLength = classArray.length();

            for (int i = 0; i < classArrayLength; i++) {
                JSONObject classObject = classArray.getJSONObject(i);
                UWClass uwClass = parseSingleClass(classObject);
                catalogNumberClasses.add(uwClass);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseInfoSessionJSON(){
        ResourcesParser parser = new ResourcesParser();
        parser.setParseType(ResourcesParser.ParseType.INFOSESSIONS);
        parser.setAPIResult(apiResult);
        parser.parseJSON();
        infoSessions = parser.getInfoSessions();
    }

    public ArrayList<CourseExamSchedule> getExamSchedules() {
        return examSchedules;
    }

    public ArrayList<UWClass> getSubjectClasses() {
        return subjectClasses;
    }

    public ArrayList<UWClass> getCatalogNumberClasses() {
        return catalogNumberClasses;
    }

    public ArrayList<InfoSession> getInfoSessions() {
        return infoSessions;
    }

    private UWClass parseSingleClass(JSONObject classObject) throws JSONException {
        UWClass uwClass = new UWClass();

        if (!classObject.isNull(SUBJECT_TAG))
            uwClass.setSubject(classObject.getString(SUBJECT_TAG));

        if (!classObject.isNull(CATALOG_NUMBER_TAG))
            uwClass.setCatalog_number(classObject.getString(CATALOG_NUMBER_TAG));

        if(!classObject.isNull(UNITS_TAG))
            uwClass.setUnits(classObject.getDouble(UNITS_TAG));

        if (!classObject.isNull(TITLE_TAG))
            uwClass.setTitle(classObject.getString(TITLE_TAG));

        if(!classObject.isNull(NOTE_TAG))
            uwClass.setNotes(classObject.getString(NOTE_TAG));

        if (!classObject.isNull(CLASS_NUMBER_TAG))
            uwClass.setClass_number(classObject.getInt(CLASS_NUMBER_TAG));

        if (!classObject.isNull(SECTION_TAG))
            uwClass.setSection(classObject.getString(SECTION_TAG));

        if(!classObject.isNull(CAMPUS_TAG))
            uwClass.setCampus(classObject.getString(CAMPUS_TAG));

        if(!classObject.isNull(ASSOCIATED_CLASS_TAG))
            uwClass.setAssociatedClass(classObject.getInt(ASSOCIATED_CLASS_TAG));

        if(!classObject.isNull(RELATED_COMPONENT_1_TAG))
            uwClass.setRelatedComponent1(classObject.getString(RELATED_COMPONENT_1_TAG));

        if(!classObject.isNull(RELATED_COMPONENT_2_TAG))
            uwClass.setRelatedComponent2(classObject.getString(RELATED_COMPONENT_2_TAG));

        if(!classObject.isNull(ENROLLMENT_CAPACITY_TAG))
            uwClass.setEnroll_cap(classObject.getInt(ENROLLMENT_CAPACITY_TAG));

        if (!classObject.isNull(ENROLLMENT_TOTAL_TAG))
            uwClass.setEnroll_total(classObject.getInt(ENROLLMENT_TOTAL_TAG));

        if(!classObject.isNull(WAITING_CAPACITY_TAG))
            uwClass.setWaiting_cap(classObject.getInt(WAITING_CAPACITY_TAG));

        if(!classObject.isNull(WAITING_TOTAL_TAG))
            uwClass.setWaiting_total(classObject.getInt(WAITING_TOTAL_TAG));

        if(!classObject.isNull(TOPIC_TAG))
            uwClass.setTopic(classObject.getString(TOPIC_TAG));

        JSONArray reserveArray = classObject.getJSONArray(RESERVES_TAG);
        int reserveArrayLength = reserveArray.length();
        ArrayList<Reserve> reserves = new ArrayList<>();

        for(int j = 0; j < reserveArrayLength; j++){
            Reserve reserve = new Reserve();
            JSONObject reserveObject = reserveArray.getJSONObject(j);

            if(!reserveObject.isNull(RESERVE_GROUP_TAG))
                reserve.setReserveGroup(reserveObject.getString(RESERVE_GROUP_TAG));

            if(!reserveObject.isNull(ENROLLMENT_CAPACITY_TAG))
                reserve.setEnrollmentCapacity(reserveObject.getInt(ENROLLMENT_CAPACITY_TAG));

            if(!reserveObject.isNull(ENROLLMENT_TOTAL_TAG))
                reserve.setEnrollmentTotal(reserveObject.getInt(ENROLLMENT_TOTAL_TAG));

            reserves.add(reserve);
        }

        uwClass.setReserves(reserves);

        ArrayList<ScheduleData> SDArray = new ArrayList<>();
        JSONArray classesArray = classObject.getJSONArray(CLASSES_TAG);

        for(int j = 0; j < classesArray.length(); j++) {
            ScheduleData SD = new ScheduleData();
            JSONObject innerClassObject = classesArray.getJSONObject(j);
            JSONObject datesObject = innerClassObject.getJSONObject(DATES_TAG);
            JSONObject locationObject = innerClassObject.getJSONObject(LOCATION_TAG);

            if (!datesObject.isNull(WEEKDAYS_TAG))
                SD.setWeekdays(datesObject.getString(WEEKDAYS_TAG));

            if (!datesObject.isNull(START_TIME_TAG))
                SD.setStart_time(datesObject.getString(START_TIME_TAG));

            if (!datesObject.isNull(END_TIME_TAG))
                SD.setEnd_time(datesObject.getString(END_TIME_TAG));

            if (!datesObject.isNull(START_DATE_TAG))
                SD.setStart_date(datesObject.getString(START_DATE_TAG));

            if (!datesObject.isNull(END_DATE_TAG))
                SD.setEnd_date(datesObject.getString(END_DATE_TAG));

            if(!datesObject.isNull(IS_TBA_TAG))
                SD.setIs_tba(datesObject.getBoolean(IS_TBA_TAG));

            if(!datesObject.isNull(IS_CANCELLED_TAG))
                SD.setIs_cancelled(datesObject.getBoolean(IS_CANCELLED_TAG));

            if(!datesObject.isNull(IS_CLOSED_TAG))
                SD.setIs_closed(datesObject.getBoolean(IS_CLOSED_TAG));

            // load instructor data
            JSONArray offeringInstructors = innerClassObject.getJSONArray(INSTRUCTORS_TAG);
            ArrayList<String> instructors = new ArrayList<>();
            int num_instructors = offeringInstructors.length();

            for (int k = 0; k < num_instructors; k++) {
                instructors.add(offeringInstructors.getString(k));
            }

            if (num_instructors > 0) {
                SD.setInstructors(instructors);
            }

            if (!locationObject.isNull(BUILDING_TAG))
                SD.setBuilding(locationObject.getString(BUILDING_TAG));

            if (!classObject.isNull(ROOM_TAG))
                SD.setRoom(classObject.getString(ROOM_TAG));

            SDArray.add(SD);
        }

        uwClass.setScheduleData(SDArray);

        if (!classObject.isNull(LAST_UPDATED_TAG))
            uwClass.setLast_updated(classObject.getString(LAST_UPDATED_TAG));

        if (!classObject.isNull(TERM_TAG))
            uwClass.setTerm(classObject.getInt(TERM_TAG));

        if(!classObject.isNull(ACADEMIC_LEVEL_TAG))
            uwClass.setAcademic_level(classObject.getString(ACADEMIC_LEVEL_TAG));

        return uwClass;
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

    public String getEndPoint(String term){
        switch (parseType){
            case EXAM_SCHEDULE:
                return String.format(EXAM_END_POINT, term);
            case INFO_SESSIONS:
                return String.format(INFO_SESSION_END_POINT, term);
            default:
                return String.format(EXAM_END_POINT, term);
        }
    }

    public String getEndPoint(String term, String subject){
        return String.format(SUBJECT_END_POINT, term, subject);
    }

    public String getEndPoint(String term, String subject, String catalogNumber){
        return String.format(CATALOG_END_POINT, term, subject, catalogNumber);
    }
}
