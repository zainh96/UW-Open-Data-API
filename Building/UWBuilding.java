import java.util.ArrayList;

/**
 * Created by ZainH on 01/09/2015.
 */
public class UWBuilding {
    private String building_id = null;
    private String building_code = null;
    private String building_name = null;
    private ArrayList<String> alternate_names = new ArrayList<>();
    private double latitude = 0.0;
    private double longitude = 0.0;
    private ArrayList<UWBuildingSection> building_sections = new ArrayList<>();

    public String getBuildingId() {
        return building_id;
    }

    public void setBuildingId(String building_id) {
        this.building_id = building_id;
    }

    public String getBuildingCode() {
        return building_code;
    }

    public void setBuildingCode(String building_code) {
        this.building_code = building_code;
    }

    public ArrayList<String> getAlternateNames() {
        return alternate_names;
    }

    public void setAlternateNames(ArrayList<String> alternateNames) {
        this.alternate_names = alternateNames;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<UWBuildingSection> getBuildingSections() {
        return building_sections;
    }

    public void setBuildingSections(ArrayList<UWBuildingSection> building_sections) {
        this.building_sections = building_sections;
    }

    public String getBuildingName() {
        return building_name;
    }

    public void setBuildingName(String building_name) {
        this.building_name = building_name;
    }
}
