package Course;

import java.util.ArrayList;

/**
 * Created by ZainH on 01/09/2015.
 */
public class UWClass {
    private String subject = null;
    private String catalog_number = null;
    private String units = null;
    private String title = null;
    private String notes = null;
    private int class_number;
    private String section = null;
    private String campus = null;
    private int enroll_cap;
    private int enroll_total;
    private int waiting_cap;
    private int waiting_total;
    private String topic = null;

    // [held_with]
    private String academic_level = null;
    private String last_updated = null;
    private int term = 0;

    // date data
    private String start_time = null;
    private String end_time = null;
    private String weekdays = "";
    private String start_date = null;
    private String end_date = null;
    private boolean is_tba = false;
    private boolean is_cancelled = false;
    private boolean is_closed = false;

    // location data
    private String building = null;
    private String room = null;

    // instructor data
    private ArrayList<String> instructors = new ArrayList<>();

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCatalog_number() {
        return catalog_number;
    }

    public void setCatalog_number(String catalog_number) {
        this.catalog_number = catalog_number;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getClass_number() {
        return class_number;
    }

    public void setClass_number(int class_number) {
        this.class_number = class_number;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getEnroll_cap() {
        return enroll_cap;
    }

    public void setEnroll_cap(int enroll_cap) {
        this.enroll_cap = enroll_cap;
    }

    public int getEnroll_total() {
        return enroll_total;
    }

    public void setEnroll_total(int enroll_total) {
        this.enroll_total = enroll_total;
    }

    public int getWaiting_cap() {
        return waiting_cap;
    }

    public void setWaiting_cap(int waiting_cap) {
        this.waiting_cap = waiting_cap;
    }

    public int getWaiting_total() {
        return waiting_total;
    }

    public void setWaiting_total(int waiting_total) {
        this.waiting_total = waiting_total;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAcademic_level() {
        return academic_level;
    }

    public void setAcademic_level(String academic_level) {
        this.academic_level = academic_level;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getWeekdays() {
        return weekdays;
    }

    public void setWeekdays(String weekdays) {
        this.weekdays = weekdays;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public boolean is_tba() {
        return is_tba;
    }

    public void setIs_tba(boolean is_tba) {
        this.is_tba = is_tba;
    }

    public boolean is_cancelled() {
        return is_cancelled;
    }

    public void setIs_cancelled(boolean is_cancelled) {
        this.is_cancelled = is_cancelled;
    }

    public boolean is_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public ArrayList<String> getInstructors() {
        return instructors;
    }

    public void setInstructors(ArrayList<String> instructors) {
        this.instructors = instructors;
    }
}
