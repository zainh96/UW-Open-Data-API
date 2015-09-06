package Server;
import Core.APIResult;
import Core.MetaData;
import Core.MetaDataParser;
import Core.UWParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by ZainH on 01/09/2015.
 *
 * A /server/codes call back's JSON object looks like
 * {
 *    "meta":{
 *        [See MetaDataParser.java]
 *    },
 *    "data":[
 *      {
 *          "code": (int)
 *          "message": (String)Name
 *      },
 *      ...
 *    ]
 * }
 *
 * A /server/time call back's JSON object looks like
 * {
 *     "meta":{
 *         [See MetaDataParser.java]
 *     },
 *     "data":{
 *         "timestamp": (int)
 *         "datetime": (String, ISO8601 time stamp)
 *         "timezone": (String)
 *         "key_reset_time": (int)
 *     }
 * }
 *
 *
 *
 *  Proper Use:
 *  1) call setParseType(ParseType type) before calling getEndPoint() or parseJSON().
 *  2) call getEndPoint() to build the proper URL with UWOpenDataAPI.buildURL(String endPoint, String apiLey)
 *  3) Once an APIResult is received from JSONDownloader.java, give the APIResult object to this class with setAPIResult(APIResult apiResult)
 *  4) call parseJSON()
 *  5) the proper data has now been parsed, and can be requested through
 *         * getErrorCodes()
 *         * getTimeStamp() ... getKey_reset_time()
 */
public class ServerParser extends UWParser {
    // end points for building the URL
    private static final String TIME_END_POINT = "server/time";
    private static final String CODES_END_POINT = "server/codes";

    // wrapper that contains all the JSON information
    private APIResult apiResult = null;

    // Types for different endpoints
    public enum ParseType {
        TIME,
        CODES
    }
    private ParseType parseType = ParseType.TIME;

    // JSONArray Node Tags
    private static final String DATA_TAG = "data"; // also used as JSONObject tag

    // JSONObject End Point Tags
    private static final String CODE_TAG = "code";
    private static final String MESSAGE_TAG = "message";
    private static final String TIMESTAMP_TAG = "timestamp";
    private static final String DATETIME_TAG = "datetime";
    private static final String TIMEZONE_TAG = "timezone";
    private static final String KEY_RESET_TIME_TAG = "key_reset_time";

    // List to hold ErrorCodes
    ArrayList<ServerErrorCode> errorCodes = new ArrayList<>();

    // Variables to hold server time information
    private int timestamp = 0;
    private String datetime = null;
    private String timezone = null;
    private int key_reset_time = 0;

    @Override
    public void parseJSON() {
        switch (parseType){
            case TIME:
                parseTimeJSON();
                break;
            case CODES:
                parseCodesJSON();
                break;
        }
    }

    private void parseCodesJSON(){
        if(apiResult == null) return;

        try {
            JSONArray dataArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int arrayLength = dataArray.length();

            for(int i = 0; i < arrayLength; i++){
                JSONObject errorCodeJSON = dataArray.getJSONObject(i);
                ServerErrorCode errorCode = new ServerErrorCode();

                if(!errorCodeJSON.isNull(CODE_TAG))
                    errorCode.setCode(errorCodeJSON.getInt(CODE_TAG));

                if(!errorCodeJSON.isNull(MESSAGE_TAG))
                    errorCode.setMessage(errorCodeJSON.getString(MESSAGE_TAG));

                errorCodes.add(errorCode);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public ArrayList<ServerErrorCode> getErrorCodes(){
        return errorCodes;
    }

    private void parseTimeJSON(){
        if(apiResult == null) return;

        try {
            JSONObject dataObject = apiResult.getResultJSON().getJSONObject(DATA_TAG);

            if(!dataObject.isNull(TIMESTAMP_TAG))
                timestamp = dataObject.getInt(TIMESTAMP_TAG);

            if(!dataObject.isNull(DATETIME_TAG))
                datetime = dataObject.getString(DATETIME_TAG);

            if(!dataObject.isNull(TIMEZONE_TAG))
                timezone = dataObject.getString(TIMEZONE_TAG);

            if(!dataObject.isNull(KEY_RESET_TIME_TAG))
                key_reset_time = dataObject.getInt(KEY_RESET_TIME_TAG);

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getTimezone() {
        return timezone;
    }

    public int getKey_reset_time() {
        return key_reset_time;
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
        MetaDataParser parser = new MetaDataParser();
        parser.setAPIResult(apiResult);
        parser.parseJSON();
        return parser.getMeta();
    }

    public void setParseType(ParseType type){
        parseType = type;
    }

    public String getEndPoint(){
        switch (parseType){
            case TIME:
                return TIME_END_POINT;
            case CODES:
                return CODES_END_POINT;
            default:
                return TIME_END_POINT;
        }
    }

    public ParseType[] getParseTypes(){
        return new ParseType[]{ParseType.TIME, ParseType.CODES};
    }
}
