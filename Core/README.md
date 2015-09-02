# Overview
The Core API classes are responsible for downloading and packaging JSON data from the UW Open Data servers.
In general, these classes <b>do not</b> parse the JSON data, with the exception of [MetaDataParser.java](MetaDataParser.java).
There are 5 general steps required to retrieve data:

1. Instaniate your parser(s), and set their parse type.
2. Build and number of valid URLs using the static method `buildURL(...)` contained in [UWOpenDataAPI.java](UWOpenDataAPI.java).
3. Create an instance of [JSONDownloader.java](JSONDownloader.java). Pass in all your URLs into it's constructor. Implement `JSONDownlaoder.onDownloadListener` into your class, and call `setOnDownloadListener(onDownloadListener callBack` with your JSONDownloader object. 
4. In `onDownloadComplete(APIResult apiResult)` give your parsers `apiResult` and call `parseJSON()` on them.
5. You can now request data from your parsers
