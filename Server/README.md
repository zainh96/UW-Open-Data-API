# ServerParser.java
The ServerParser class is responsible for parsing JSON data from these End Points:
* [/server/codes](https://github.com/uWaterloo/api-documentation/blob/master/v2/server/codes.md)
* [/server/time](https://github.com/uWaterloo/api-documentation/blob/master/v2/server/time.md)

## ParseTypes Enumerations
The table below shows which parse types correspond to which end point, and getter method.

| Name  | End Point | Getter Method(s) |
| ------------- | ------------- |------------- |
| TIME  | /server/time  | getTimeStamp(), getDatetime(), getTimeZone(), getKey_reset_time()|
| CODES  | /server/codes  | getErrorCodes()|

## Public Getter Methods
| Method Name  | Return Type | Description |
| ------------- | ------------- | ------------- |
|`getAPIResult()`|`APIResult`|returns the apiResult associated with the instance of the parser|
|`getMeta()`|`MetaData`|returns the Meta Data associated with the given APIResult|
|`getEndPoint()`|String|returns the required end point string for building a URL|
|`getParseTypes()`|ServerParser.ParseType[]|returns an array of all Parse Types for this parser|
| `getTimeStamp()`| int | returns Current UNIX timestamp of server|
| `getDatetime()`  | String | returns ISO8601 compatible current server timestamp |
| `getTimezone()` | String | returns server timezone |
| `getKey_reset_time()` | int | returns UNIX timestamp of when the api call quota will reset |
| `getErrorCodes()` | ArrayList of Type `ServerErrorCode` | returns a list of Server error codes, each wrapped as a ServerErrorCode|

## Public Setter Methods
| Method Name  | Parameters | Description |
| ------------- | ------------- | ------------- |
|`setAPIResult(...)`| `APIResult` |Sets the apiResult object for this parser|
|`setParseType(...)`| `ServerParser.ParseType` | sets the parse type for this parser |

## Public Void Methods
| Method Name  | Description |
| ------------- | ------------- | ------------- |
|`parseJSON()`| parses the JSON data in it's given APIResult |

# ServerErrorCode.java
The java wrapper for server error codes for the /server/codes endpoint.

## Public Getter Methods
| Method Name  | Return Type | Description |
| ------------- | ------------- | ------------- |
| `getMessage()`| String | returns the message associated with the code|
| `getCode()`  | int | returns error code number |
