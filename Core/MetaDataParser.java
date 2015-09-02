package Core;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by ZainH on 31/08/2015.
 *
 * Abstract:
 *      The MetaDataParser class can take in any APIResult and parse it to capture the "meta" attributes from a callback.
 *      Every call to the UW Open Data API contains a "meta" JSON object
 *
 * JSON Object structure for "meta":
 *
 * "meta":{
 *    "requests": (int),
 *    "timestamp": (long),
 *    "status": (int),
 *    "message": (String),
 *    "method_id": (int),
 *    "version": (double),
 *    "method":{
 *        (int array)
 *    }
 * }
 */
public class MetaDataParser extends UWParser {
    private APIResult apiResult = null;

    // JSON Object tags
    private static final String REQUESTS_TAG = "requests";
    private static final String TIMESTAMP_TAG = "timestamp";
    private static final String STATUS_TAG = "status";
    private static final String MESSAGE_TAG = "message";
    private static final String METHOD_ID_TAG = "method_id";
    private static final String VERSION_TAG = "version";

    // JSON Object end node tags
    private static final String META_TAG = "meta";

    // JSON Object Array tags
    private static final String METHOD_TAG = "method";

    private MetaData metaData = null;

    @Override
    public void parseJSON() {
        if(apiResult == null) return;

        try {
            JSONObject metaObject = apiResult.getResultJSON().getJSONObject(META_TAG);

            if(!metaObject.isNull(REQUESTS_TAG))
                metaData.setRequests(metaObject.getInt(REQUESTS_TAG));

            if(!metaObject.isNull(TIMESTAMP_TAG))
                metaData.setTimestamp(metaObject.getLong(TIMESTAMP_TAG));

            if(!metaObject.isNull(STATUS_TAG))
                metaData.setStatus(metaObject.getInt(STATUS_TAG));

            if(!metaObject.isNull(MESSAGE_TAG))
                metaData.setMessage(metaObject.getString(MESSAGE_TAG));

            if(!metaObject.isNull(METHOD_ID_TAG))
                metaData.setMethod_id(metaObject.getInt(METHOD_ID_TAG));

            if(!metaObject.isNull(VERSION_TAG))
                metaData.setVersion(metaObject.getDouble(VERSION_TAG));

        } catch (JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    public void setAPIResult(APIResult apiResult) {
        this.apiResult = apiResult;
    }

    @Override
    public APIResult getAPIResult() {
        return apiResult;
    }

    @Override
    public MetaData getMeta() {
        return metaData;
    }
}
