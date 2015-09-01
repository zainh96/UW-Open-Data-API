import java.util.ArrayList;

/**
 * Created by ZainH on 01/09/2015.
 */
public class APIMethod {
    private int method_id = 0;
    private String method_url = null;
    private int service_id = 0;
    private String service_name = null;
    private ArrayList<String> parameters = new ArrayList<>();

    public int getMethod_id() {
        return method_id;
    }

    public void setMethod_id(int method_id) {
        this.method_id = method_id;
    }

    public String getMethod_url() {
        return method_url;
    }

    public void setMethod_url(String method_url) {
        this.method_url = method_url;
    }

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

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<String> parameters) {
        this.parameters = parameters;
    }
}
