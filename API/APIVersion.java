package API;
/**
 * Created by ZainH on 01/09/2015.
 */
public class APIVersion {
    private double version = 0.0;
    private String release_date = null;

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
