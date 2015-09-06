package Course;

import java.util.ArrayList;

/**
 * Created by ZainH on 01/09/2015.
 */
public class UWClass {
    private String subject = null;
    private String catalog_number = null;
    private double units = 0;
    private String title = null;
    private String notes = null;
    private int class_number;
    private String section = null;
    private String campus = null;
    private int associatedClass = 0;
    private String relatedComponent1 = null;
    private String relatedComponent2 = null;
    private int enroll_cap;
    private int enroll_total;
    private int waiting_cap;
    private int waiting_total;
    private String topic = null;

    // [held_with]
    private String academic_level = null;
    private String last_updated = null;
    private int term = 0;

    ArrayList<Reserve> reserves = new ArrayList<>();

    ArrayList<ScheduleData> scheduleData = new ArrayList<>();

    public ArrayList<Reserve> getReserves() {
        return reserves;
    }

    public int getAssociatedClass() {
        return associatedClass;
    }

    public void setAssociatedClass(int associatedClass) {
        this.associatedClass = associatedClass;
    }

    public String getRelatedComponent1() {
        return relatedComponent1;
    }

    public void setRelatedComponent1(String relatedComponent1) {
        this.relatedComponent1 = relatedComponent1;
    }

    public String getRelatedComponent2() {
        return relatedComponent2;
    }

    public void setRelatedComponent2(String relatedComponent2) {
        this.relatedComponent2 = relatedComponent2;
    }

    public void setReserves(ArrayList<Reserve> reserves) {
        this.reserves = reserves;
    }

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

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
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

    public ArrayList<ScheduleData> getScheduleData() {
        return scheduleData;
    }

    public void setScheduleData(ArrayList<ScheduleData> scheduleData) {
        this.scheduleData = scheduleData;
    }
}
