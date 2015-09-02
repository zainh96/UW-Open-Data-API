package Core;
/**
 * Created by ZainH on 31/08/2015.
 *
 * Abstract:
 *      The UWParser class is the template for other parsers. Every parser in the Client Library extends UWParser and implements
 *      these methods
 */
public abstract class UWParser {
    // attempts to parse the data give to it from setAPIResult. if APIResult is null, parsing terminates
    public abstract void parseJSON();

    public abstract void setAPIResult(APIResult apiResult);
    public abstract APIResult getAPIResult();

    public abstract MetaData getMeta();
}
