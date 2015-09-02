package Core;
/**
 * Created by ZainH on 31/08/2015.
 */
public class UWOpenDataAPI {
    // the base url for each API call
    public final static String URL_BASE = "https://api.uwaterloo.ca/v2/";

    /* Params:
    *       endPoint: the endpoint URL specific to each call, and JSON Data requested
    *       apiKey: a registered, valid API key that can be requested from https://api.uwaterloo.ca/#!/keygen
    * */
    public static String buildURL(final String endPoint, final String apiKey){
        return URL_BASE + endPoint + ".json?key=" + apiKey;
    }
}
