package Core;
import org.json.JSONObject;

/**
 * Created by ZainH on 31/08/2015.
 */    public class APIResult {

    // Object that holds the downloaded result as a JSONObject. JSONParsers
    // use this to parse the result
    JSONObject resultJSON = null;

    // String that holds the raw JSON String downloaded. Can be used to repackage a JSONObject
    String rawJSON = null;

    // The given URL, for re-download if something goes wrong
    String url = null;

    // Index in which the url was given to distinguish between different APIResults
    int index = -1;

    public JSONObject getResultJSON() {
        return resultJSON;
    }

    public void setResultJSON(JSONObject resultJSON) {
        this.resultJSON = resultJSON;
    }

    public String getRawJSON() {
        return rawJSON;
    }

    public void setRawJSON(String rawJSON) {
        this.rawJSON = rawJSON;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
