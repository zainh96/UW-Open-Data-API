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

## Public Methods
| Method Name  | Return Type | Description |
| ------------- | ------------- | ------------- |
| `getTimeStamp()`| int | returns Current UNIX timestamp of server|
| `getDatetime()`  | String | returns ISO8601 compatible current server timestamp |
| `getTimezone()` | String | returns server timezone |
| `getKey_reset_time()` | int | returns UNIX timestamp of when the api call quota will reset |
| `getErrorCodes()` | ArrayList<ServerErrorCode> | returns a list of Server error codes, each wrapped as a ServerErrorCode|
