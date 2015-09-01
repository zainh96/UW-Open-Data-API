import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by ZainH on 01/09/2015.
 *
 * A /api/usage call back's JSON Object looks like
 * {
 *  "meta":{
 *      [See MetaDataParser.java]
 *  },
 *      "data":{
 *      "name": (String)
 *      "api_key": (String)
 *      "monthly_calls": (int)
 *      "total_calls": (int)
 *      }
 *  }
 *
 * A /api/services call back's JSON Object looks like
 * {
 *  "meta":{
 *      [See MetaDataParser.java]
 *  },
 *  "data":[
 *      {
 *          "service_id": (int)
 *          "service_name": (String)
 *          "service_url": (String)
 *          "methods":[
 *               {
 *                  "method_id": (int)
 *                  "method_url": (String)
 *              },
 *              ...
 *           ]
 *      },
 *      ...
 *   ]
 * }
 *
 * A /api/methods call back's JSON Object looks like
 * {
 *      "meta":{
 *          [See MetaDataParser.java]
 *      },
 *      "data":[
 *          {
 *              "method_id": (int)
 *              "method_url": (String)
 *              "service_id": (int)
 *              "service_name": (String)
 *              "parameters":[
 *              (String Array)
 *              ...
 *              ]
 *     },
 *     ...
 *    ]
 * }
 *
 * A /api/versions call back's JSON Object looks like
 * {
 *    "meta":{
 *      [See MetaDataParser.java]
 *  },
 *    "data":[
 *       {
 *          "version": (double)
 *          "release_date": (String)
 *      },
 *      ...
 *   ]
 * }
 *
 * Proper Use:
 * 1) call setParseType()
 * 2) call getEndPoint() and use it to build a URL with UWOpenDataAPI.buildURL(...)
 * 3) once an APIResult is received in onDownloadComplete(APIResult apiResult), call setAPIResult, then call parseJSON()
 * 4) the proper data is now parsed, and can be retrieved. (This depends on your parseType). Data can be requested through
 *      * getName(), getApi_key(), getMonthly_calls(), getTotal_calls()
 *      * getServices()
 *      * getMethods()
 *      * getVersions()
 *      * getChangelogs()
 */
public class APIParser extends UWParser {
    // endpoints
    private static final String USAGE_END_POINT = "api/usage";
    private static final String SERVICES_END_POINT = "api/services";
    private static final String METHODS_END_POINT = "api/methods";
    private static final String VERSIONS_END_POINT = "api/versions";
    private static final String CHANGELOG_END_POINT = "api/changelog";

    // wrapper that contains all the JSON information
    private APIResult apiResult = null;

    public enum ParseType {
        USAGE,
        SERVICES,
        METHODS,
        VERSIONS,
        CHANGELOG
    }

    private ParseType parseType = ParseType.USAGE;

    // JSONObject End Point tags
    private static final String NAME_TAG = "name";
    private static final String API_KEY_TAG = "api_key";
    private static final String MONTHLY_CALLS_TAG = "monthly_calls";
    private static final String TOTAL_CALLS = "total_calls";

    private static final String SERVICE_ID_TAG = "service_id";
    private static final String SERVICE_NAME_TAG = "service_name";
    private static final String SERVICE_URL_TAG = "service_url";
    private static final String METHOD_ID_TAG = "method_id";
    private static final String METHOD_URL_TAG = "method_url";

    private static final String VERSION_TAG = "version";
    private static final String RELEASE_DATE_TAG = "release_date";

    private static final String DATE_TAG = "date";

    // JSONArray Node Tags
    private static final String DATA_TAG = "data"; // also used for JSONObject
    private static final String METHODS_TAG = "methods";
    private static final String PARAMETERS_TAG = "parameters";
    private static final String CHANGES_TAG = "changes";

    // /api/usage variables
    private String name = null;
    private String api_key = null;
    private int monthly_calls = 0;
    private int total_calls = 0;

    // /api/services variables
    private ArrayList<APIService> services = new ArrayList<>();

    // /api/methods variables
    private ArrayList<APIMethod> methods = new ArrayList<>();

    // /api/versions variables
    private ArrayList<APIVersion> versions = new ArrayList<>();

    // /api/changelog variables
    private ArrayList<APIChangeLog> changeLogs = new ArrayList<>();

    @Override
    public void parseJSON() {
        if (apiResult == null) return;

        switch (parseType) {
            case USAGE:
                parseUsageJSON();
                break;
            case SERVICES:
                parseServicesJSON();
                break;
            case METHODS:
                parseMethodsJSON();
                break;
            case VERSIONS:
                parseVersionsJSON();
                break;
            case CHANGELOG:
                parseChangeLogJSON();
                break;
        }
    }

    // apiResult is guaranteed to be not null from parseJSON()
    private void parseUsageJSON() {
        try {
            JSONObject dataObject = apiResult.getResultJSON().getJSONObject(DATA_TAG);

            if (!dataObject.isNull(NAME_TAG))
                name = dataObject.getString(NAME_TAG);

            if (!dataObject.isNull(API_KEY_TAG))
                api_key = dataObject.getString(API_KEY_TAG);

            if (!dataObject.isNull(MONTHLY_CALLS_TAG))
                monthly_calls = dataObject.getInt(MONTHLY_CALLS_TAG);

            if (!dataObject.isNull(TOTAL_CALLS))
                total_calls = dataObject.getInt(TOTAL_CALLS);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // /api/usage getters
    public int getTotal_calls() {
        return total_calls;
    }

    public String getName() {
        return name;
    }

    public String getApi_key() {
        return api_key;
    }

    public int getMonthly_calls() {
        return monthly_calls;
    }

    private void parseServicesJSON() {
        try {
            JSONArray serviceArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int serviceArrayLength = serviceArray.length();

            for (int i = 0; i < serviceArrayLength; i++) {
                JSONObject serviceObject = serviceArray.getJSONObject(i);
                APIService service = new APIService();

                if (!serviceObject.isNull(SERVICE_ID_TAG))
                    service.setService_id(serviceObject.getInt(SERVICE_ID_TAG));

                if (!serviceObject.isNull(SERVICE_NAME_TAG))
                    service.setService_name(serviceObject.getString(SERVICE_NAME_TAG));

                if (!serviceObject.isNull(SERVICE_URL_TAG))
                    service.setService_url(serviceObject.getString(SERVICE_URL_TAG));

                ArrayList<APIMethod> methods = new ArrayList<>();
                JSONArray methodArray = serviceObject.getJSONArray(METHODS_TAG);
                int methodArrayLength = methodArray.length();

                for (int j = 0; j < methodArrayLength; j++) {
                    APIMethod method = new APIMethod();
                    JSONObject methodObject = methodArray.getJSONObject(j);

                    if (!methodObject.isNull(METHOD_ID_TAG))
                        method.setMethod_id(methodObject.getInt(METHOD_ID_TAG));

                    if (!methodObject.isNull(METHOD_URL_TAG))
                        method.setMethod_url(methodObject.getString(METHOD_URL_TAG));

                    methods.add(method);
                }

                service.setMethods(methods);
                services.add(service);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<APIService> getServices() {
        return services;
    }

    private void parseMethodsJSON() {
        try {
            JSONArray methodArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int methodArrayLength = methodArray.length();

            for (int i = 0; i < methodArrayLength; i++) {
                APIMethod method = new APIMethod();
                JSONObject methodObject = methodArray.getJSONObject(i);

                if (!methodObject.isNull(METHOD_ID_TAG))
                    method.setMethod_id(methodObject.getInt(METHOD_ID_TAG));

                if (!methodObject.isNull(METHOD_URL_TAG))
                    method.setMethod_url(methodObject.getString(METHOD_URL_TAG));

                if (!methodObject.isNull(SERVICE_ID_TAG))
                    method.setService_id(methodObject.getInt(SERVICE_ID_TAG));

                if (!methodObject.isNull(SERVICE_NAME_TAG))
                    method.setService_name(methodObject.getString(SERVICE_NAME_TAG));

                ArrayList<String> parameters = new ArrayList<>();
                JSONArray parameterArray = methodObject.getJSONArray(PARAMETERS_TAG);
                int parameterArrayLength = parameterArray.length();

                for (int j = 0; j < parameterArrayLength; j++) {
                    parameters.add(parameterArray.getString(j));
                }

                method.setParameters(parameters);
                methods.add(method);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<APIMethod> getMethods() {
        return methods;
    }

    private void parseVersionsJSON() {
        try {
            JSONArray versionArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int versionArrayLength = versionArray.length();

            for(int i = 0; i < versionArrayLength; i++){
                JSONObject versionObject = versionArray.getJSONObject(i);
                APIVersion version = new APIVersion();

                if(!versionObject.isNull(VERSION_TAG))
                    version.setVersion(versionObject.getDouble(VERSION_TAG));

                if(!versionObject.isNull(RELEASE_DATE_TAG))
                    version.setRelease_date(versionObject.getString(RELEASE_DATE_TAG));

                versions.add(version);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public ArrayList<APIVersion> getVersions() {
        return versions;
    }

    private void parseChangeLogJSON() {
        try {
            JSONArray changelogArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int changelogArrayLength = changelogArray.length();

            for(int i = 0; i < changelogArrayLength; i++){
                JSONObject changelogObject = changelogArray.getJSONObject(i);
                APIChangeLog changeLog = new APIChangeLog();

                if(!changelogObject.isNull(DATE_TAG))
                    changeLog.setDate(changelogObject.getString(DATE_TAG));

                JSONArray changesArray = changelogObject.getJSONArray(CHANGES_TAG);
                ArrayList<String> changes = new ArrayList<>();
                int changesArrayLength = changesArray.length();

                for(int j = 0; j < changesArrayLength; j++){
                    changes.add(changesArray.getString(j));
                }

                changeLog.setChanges(changes);
                changeLogs.add(changeLog);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public ArrayList<APIChangeLog> getChangeLogs() {
        return changeLogs;
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

    public String getEndPoint() {
        switch (parseType) {
            case USAGE:
                return USAGE_END_POINT;
            case SERVICES:
                return SERVICES_END_POINT;
            case METHODS:
                return METHODS_END_POINT;
            case VERSIONS:
                return VERSIONS_END_POINT;
            case CHANGELOG:
                return CHANGELOG_END_POINT;
            default:
                return USAGE_END_POINT;
        }
    }

    public ParseType[] getParseTypes() {
        return new ParseType[]{
                ParseType.USAGE,
                ParseType.SERVICES,
                ParseType.METHODS,
                ParseType.VERSIONS,
                ParseType.CHANGELOG
        };
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }

    public ParseType getParseType(){
        return parseType;
    }
}
