# Overview
The Core API classes are responsible for downloading and packaging JSON data from the UW Open Data servers.
In general, these classes <b>do not</b> parse the JSON data, with the exception of [MetaDataParser.java](MetaDataParser.java).
There are _ general steps required to retrieve data:

1. Build a valid URL using the static method `buildURL(...)` contained in [UWOpenDataAPI.java](UWOpenDataAPI.java).
