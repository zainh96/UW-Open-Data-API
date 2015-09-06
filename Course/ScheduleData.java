package Course;

import java.util.ArrayList;

/**
 * Created by ZainH on 06/09/2015.
 */
public class ScheduleData {
    private String start_time = null;
    private String end_time = null;
    private String weekdays = "";
    private String start_date = null;
    private String end_date = null;
    private boolean is_tba = false;
    private boolean is_cancelled = false;
    private boolean is_closed = false;

    private String building = null;
    private String room = null;

    private ArrayList<String> instructors = new ArrayList<>();

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
