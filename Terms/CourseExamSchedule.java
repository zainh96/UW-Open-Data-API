package Terms;

import java.util.ArrayList;

/**
 * Created by ZainH on 06/09/2015.
 */
public class CourseExamSchedule {
    private String course = null;
    private ArrayList<Section> sections = new ArrayList<>();

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }
}
