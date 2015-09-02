# Overview
The Core API classes are responsible for downloading and packaging JSON data from the UW Open Data servers.
In general, these classes <b>do not</b> parse the JSON data, with the exception of [MetaDataParser.java](MetaDataParser.java).
There are 5 general steps required to retrieve data:

1. Instaniate your parser(s), and set their parse type.
2. Build and number of valid URLs using the static method `buildURL(...)` contained in [UWOpenDataAPI.java](UWOpenDataAPI.java).
3. Create an instance of [JSONDownloader.java](JSONDownloader.java). Pass in all your URLs into it's constructor. Implement `JSONDownlaoder.onDownloadListener` into your class, and call `setOnDownloadListener(onDownloadListener callBack)` with your JSONDownloader object. 
4. In `onDownloadComplete(APIResult apiResult)` give your parsers `apiResult` and call `parseJSON()` on them.
5. You can now request data from your parsers

### Example

```java
public class Test extends JFrame implements JSONDownloader.onDownloadListener {
    ...
    // Step 1
    String apiKey = "MY_API_KEY";
    BuildingParser parser = new BuildingParser();
    
    public Test() {
        // Step 1 continued
        // This is for the endpoint /building/{building}/{room}/courses
        parser.setParseType(BuildingParser.ParseType.COURSES); 
        
        // Step 2
        String url = UWOpenDataAPI.buildURL(parser.getEndPoint("MC", "2038"), apiKey);
        
        // Step 3
        JSONDownloader downloader = new JSONDownloader(url); // you can give any number of arguments, or a String array
        downloader.setOnDownloadListener(this);
    }
    
    ...
    
    @Override
    public void onDownloadFail(String givenURL, int index) {
        // this method is called if the download fails (No internet connection, timeout, etc). 
        System.out.print("Download failed.. url = " + givenURL);
    }

    @Override
    public void onDownloadComplete(APIResult apiResult) {
        // prints out the raw JSON downloaded
        System.out.println(apiResult.getRawJSON());
        
        // Step 4
        // parseJSON() will do different types of parsing depending on what ParseType you give it.
        // Each Parser has their own ParseTypes
        parser.setAPIResult(apiResult);
        parser.parseJSON();
        
        // Step 5
        ArrayList<UWClass> classes = parser.getUwClasses();
    }
}
```
