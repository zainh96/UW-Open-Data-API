# Overview
The Core API classes are responsible for downloading and packaging JSON data from the UW Open Data servers.
In general, these classes <b>do not</b> parse the JSON data, with the exception of [MetaDataParser.java](MetaDataParser.java).
There are 5 general steps required to retrieve data:

1. Instaniate your parser(s), and set their parse type.
2. Build any number of valid URLs using the static method `buildURL(...)` contained in [UWOpenDataAPI.java](UWOpenDataAPI.java).
3. Create an instance of [JSONDownloader.java](JSONDownloader.java). Pass in all your URLs into it's constructor. Implement `JSONDownlaoder.onDownloadListener` into your class, and call `setOnDownloadListener(onDownloadListener callBack)` with your JSONDownloader object. 
4. In `onDownloadComplete(APIResult apiResult)` give your parsers `apiResult` and call `parseJSON()` on them. 
**If you gave more than one URL to the JSONDownloader constructor, you will need to differentiate each apiResult**. You can distinguish each apiResult with it's url or index (the order you gave the urls into the constructor). An Example on using more than one url at a time is given below. 
5. You can now request data from your parsers

## Android
### Updating the Manifest
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest 
    xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.myapp" >
    
    <!-- These permissions are required to download data from the UW Open Data Servers-->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    
    ...
    
    <application>
        ...
    </application>
</manifest>
```
### Example in Android
```java
public class MyActivity extends AppCompatActivity implements JSONDownloader.onDownloadListener {
    
    String apiKey = null;
    final String LOGCAT_TAG = "My Activity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);
        ...
        // Step 1, do not redownload on screen rotation
        if(savedInstance == null) {
            ResourcesParser parser = new ResourcesParser();
            parser.setParseType(ResourcesParser.ParseType.INFOSESSIONS);
            
            String apiKey = getString(R.string.api_key); // store your key in strings.xml
            
            // Step 2
            String url = UWOpenDataAPI.buildURL(parser.getEndPoint(), apiKey);
            
            // Step 3
            JSONDownloader downloader = new JSONDownloader(url);
            downloader.setOnDownloadListener(this);
            downloader.start(); // starts download in seperate thread
        }
    }
    
    ... 
    
      @Override
    public void onDownloadFail(String givenURL, int index) {
        // this method is called if the download fails (No internet connection, timeout, bad url, missing permission etc). 
        Log.i(LOGCAT_TAG, "Download failed.. url = " + givenURL);
    }

    @Override
    public void onDownloadComplete(APIResult apiResult) {
        // Step 4
        // parseJSON() will do different types of parsing depending on what ParseType you give it.
        // Each Parser has their own ParseTypes
        parser.setAPIResult(apiResult);
        parser.parseJSON();
        
        // Step 5
        ArrayList<InfoSession> sessions = parser.getInfoSessions();
    }
}
```

### Examples
#### Single Url
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
        downloader.start(); // starts the download in a seperate thread
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
#### Multiple Urls
```java
public class Test extends JFrame implements JSONDownloader.onDownloadListener {
    ...
    // Step 1
    String apiKey = "MY_API_KEY";
    BuildingParser buildingParser = new BuildingParser();
    ServerParser serverParser = new ServerParser();
    
    String buildingUrl;
    String serverUrl;
    
    public Test() {
        // Step 1 continued
        // This is for the endpoint /building/{building}/{room}/courses
        buildingParser.setParseType(BuildingParser.ParseType.COURSES); 
        
        // This is for endpoint /server/time
        serverParser.setParseTpye(ServerParser.ParseType.TIME);
        
        // Step 2
        buildingUrl = UWOpenDataAPI.buildURL(buildingParser.getEndPoint("MC", "2038"), apiKey);
        serverUrl = UWOpenDataAPI.buildURL(serverParser.getEndPoint(), apiKey);
        
        // Step 3
        JSONDownloader downloader = new JSONDownloader(buildingUrl, serverUrl); // you can give any number of arguments, or a String array
        downloader.setOnDownloadListener(this);
        downloader.start(); // starts the download in a seperate thread
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
        String givenUrl = apiResult.getUrl(); 
        if(givenUrl.equals(buildingUrl)){
            buildingParser.setAPIResult(apiResult);
            buildingParser.parseJSON();
            
            // Step 5
            ArrayList<UWClass> classes = buildingParser.getUwClasses();
            
        } else if(givenUrl.equals(serverUrl)){
            serverParser.setAPIResult(apiResult);
            serverParser.parseJSON();
            
            // Step 5
            int timestamp = serverParser.getTimeStamp();
        }
    }
}
```

