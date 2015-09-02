package API;
import java.util.ArrayList;

/**
 * Created by ZainH on 01/09/2015.
 */
public class APIService {
    private int service_id = 0;
    private String service_name = null;
    private String service_url = null;
    private ArrayList<APIMethod> methods = new ArrayList<>();

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_url() {
        return service_url;
    }

    public void setService_url(String service_url) {
        this.service_url = service_url;
    }

    public ArrayList<APIMethod> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<APIMethod> methods) {
        this.methods = methods;
    }
}
