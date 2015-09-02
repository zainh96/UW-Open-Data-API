package Server;
/**
 * Created by ZainH on 01/09/2015.
 *
 * Wrapper for /server/codes JSON Data
 */
public class ServerErrorCode {
    private int code = 0;
    private String message = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
