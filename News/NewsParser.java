package News;

import Core.APIResult;
import Core.MetaData;
import Core.MetaDataParser;
import Core.UWParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ZainH on 07/09/2015.
 */
public class NewsParser  extends UWParser {
    private static final String NEWS_END_POINT = "news";
    private static final String NEWS_SITE_END_POINT = "news/%s";
    private static final String NEWS_SITE_ID_END_POINT = "news/%s/%s";

    // JSON Object leaf node tags
    private static final String ID_TAG = "id";
    private static final String TITLE_TAG = "title";
    private static final String DESCRIPTION_TAG = "description";
    private static final String DESCRIPTION_RAW_TAG = "description_raw";
    private static final String FILE_TAG = "file";
    private static final String ALT_TAG = "alt";
    private static final String MIME_TAG = "mime";
    private static final String SIZE_TAG = "size";
    private static final String WIDTH_TAG = "width";
    private static final String HEIGHT_TAG = "height";
    private static final String URL_TAG = "url";
    private static final String SITE_TAG = "site";
    private static final String SITE_ID = "site_id";
    private static final String SITE_NAME_TAG = "site_name";
    private static final String REVISION_ID_TAG = "revision_id";
    private static final String PUBLISHED_TAG = "published";
    private static final String UPDATED_TAG = "updated";
    private static final String LINK_TAG = "link";

    // JSON Array / Object tags
    private static final String DATA_TAG = "data";
    private static final String AUDIENCE_TAG = "audience";
    private static final String IMAGE_TAG = "image";

    //contains all json information
    private APIResult apiResult = null;

    public enum ParseType{
        NEWS,
        SITE,
        SITE_ID
    }

    private ParseType parseType = ParseType.NEWS;

    // /news & /news/{site} variables
    private ArrayList<NewsItem> newsItems = new ArrayList<>();

    // /news/{site}/{id} variable
    private NewsItem specificNewsItem = new NewsItem();

    @Override
    public void parseJSON() {
        if(apiResult == null || apiResult.getResultJSON() == null) return;

        switch (parseType){
            case NEWS:
                parseNewsJSON();
                break;
            case SITE:
                parseNewsJSON();
                break;
            case SITE_ID:
                parseNewsSiteIdJSON();
                break;
        }
    }

    private void parseNewsJSON(){
        try {
            JSONArray newsArray = apiResult.getResultJSON().getJSONArray(DATA_TAG);
            int newsArrayLength = newsArray.length();
            newsItems.clear();

            for(int i = 0; i < newsArrayLength; i++){
                JSONObject newsObject = newsArray.getJSONObject(i);
                NewsItem item = new NewsItem();

                if(!newsObject.isNull(ID_TAG))
                    item.setId(newsObject.getInt(ID_TAG));

                if(!newsObject.isNull(TITLE_TAG))
                    item.setTitle(newsObject.getString(TITLE_TAG));

                if(!newsObject.isNull(SITE_TAG))
                    item.setSiteName(newsObject.getString(SITE_TAG));

                if(!newsObject.isNull(LINK_TAG))
                    item.setLink(newsObject.getString(LINK_TAG));

                if(!newsObject.isNull(PUBLISHED_TAG))
                    item.setPublished(newsObject.getString(PUBLISHED_TAG));

                if(!newsObject.isNull(UPDATED_TAG))
                    item.setUpdated(newsObject.getString(UPDATED_TAG));

                newsItems.add(item);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void parseNewsSiteIdJSON(){
        try {
            JSONObject newsObject = apiResult.getResultJSON().getJSONObject(DATA_TAG);

            if(!newsObject.isNull(ID_TAG))
                specificNewsItem.setId(newsObject.getInt(ID_TAG));

            if(!newsObject.isNull(TITLE_TAG))
                specificNewsItem.setTitle(newsObject.getString(TITLE_TAG));

            if(!newsObject.isNull(DESCRIPTION_TAG))
                specificNewsItem.setDescription(newsObject.getString(DESCRIPTION_TAG));

            if(!newsObject.isNull(DESCRIPTION_RAW_TAG))
                specificNewsItem.setRawDescription(newsObject.getString(DESCRIPTION_RAW_TAG));

            JSONArray audienceArray = newsObject.getJSONArray(AUDIENCE_TAG);
            ArrayList<String> audiences = new ArrayList<>();

            for(int i = 0; i < audienceArray.length(); i++){
                audiences.add(audienceArray.getString(i));
            }

            JSONObject imageObject = newsObject.getJSONObject(IMAGE_TAG);

            if(!imageObject.isNull(ID_TAG))
                specificNewsItem.setImageId(imageObject.getInt(ID_TAG));

            if(!imageObject.isNull(FILE_TAG))
                specificNewsItem.setFile(imageObject.getString(FILE_TAG));

            if(!imageObject.isNull(ALT_TAG))
                specificNewsItem.setAlt(imageObject.getString(ALT_TAG));

            if(!imageObject.isNull(MIME_TAG))
                specificNewsItem.setMime(imageObject.getString(MIME_TAG));

            if(!imageObject.isNull(SIZE_TAG))
                specificNewsItem.setImageSize(imageObject.getInt(SIZE_TAG));

            if(!imageObject.isNull(WIDTH_TAG))
                specificNewsItem.setImageWidth(imageObject.getInt(WIDTH_TAG));

            if(!imageObject.isNull(HEIGHT_TAG))
                specificNewsItem.setImageHeight(imageObject.getInt(HEIGHT_TAG));

            if(!imageObject.isNull(URL_TAG))
                specificNewsItem.setImageUrl(imageObject.getString(URL_TAG));

            if(!newsObject.isNull(SITE_ID))
                specificNewsItem.setSiteId(newsObject.getString(SITE_ID));

            if(!newsObject.isNull(SITE_NAME_TAG))
                specificNewsItem.setSiteName(newsObject.getString(SITE_NAME_TAG));

            if(!newsObject.isNull(REVISION_ID_TAG))
                specificNewsItem.setRevisionId(newsObject.getInt(REVISION_ID_TAG));

            if(!newsObject.isNull(PUBLISHED_TAG))
                specificNewsItem.setPublished(newsObject.getString(PUBLISHED_TAG));

            if(!newsObject.isNull(UPDATED_TAG))
                specificNewsItem.setUpdated(newsObject.getString(UPDATED_TAG));

            if(!newsObject.isNull(LINK_TAG))
                specificNewsItem.setLink(newsObject.getString(LINK_TAG));

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }

    public NewsItem getSpecificNewsItem() {
        return specificNewsItem;
    }

    public ArrayList<NewsItem> getNewsItems() {
        return newsItems;
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

    public String getEndPoint(){
        return NEWS_END_POINT;
    }

    public String getEndPoint(String site){
        return String.format(NEWS_SITE_END_POINT, site);
    }

    public String getEndPoint(String site, String id){
        return String.format(NEWS_SITE_ID_END_POINT, site, id);
    }
}
