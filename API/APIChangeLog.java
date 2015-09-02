package API;
import java.util.ArrayList;

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
