import java.util.ArrayList;

/**
 * Created by ZainH on 01/09/2015.
 */
public class APIChangeLog {
    private String date = null;
    private ArrayList<String> changes = new ArrayList<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getChanges() {
        return changes;
    }

    public void setChanges(ArrayList<String> changes) {
        this.changes = changes;
    }
}
