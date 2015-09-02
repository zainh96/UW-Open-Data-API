package Core;
/**
 * Created by ZainH on 31/08/2015.
 */
public class MetaData {
    private int requests = 0;
    private long timestamp = 0;
    private int status = 0;
    private String message = null;
    private int method_id = 0;
    private int method[] = null;
    private double version = 0.0;

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMethod_id() {
        return method_id;
    }

    public void setMethod_id(int method_id) {
        this.method_id = method_id;
    }

    public int[] getMethod() {
        return method;
    }

    public void setMethod(int[] method) {
        this.method = method;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }
}
