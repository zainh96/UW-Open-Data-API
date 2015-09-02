# APIParser.java
The APIParser class is responsible for parsing JSON data from the following end points:
* [/api/usage](https://github.com/uWaterloo/api-documentation/blob/master/v2/api/usage.md)
* [/api/services](https://github.com/uWaterloo/api-documentation/blob/master/v2/api/services.md)
* [/api/methods](https://github.com/uWaterloo/api-documentation/blob/master/v2/api/methods.md)
* [/api/versions](https://github.com/uWaterloo/api-documentation/blob/master/v2/api/versions.md)
* [/api/changelog](https://github.com/uWaterloo/api-documentation/blob/master/v2/api/changelog.md)

## ParseType Enumerations
The table below shows which parse types correspond to which end point, and getter method(s).

| Name  | End Point | Getter Method(s) |
| ------------- | ------------- | ------------- |
| USAGE  | /api/usage  | getName(), getApi_key(), getMonthly_calls(), getTotal_calls() |
| SERVICES | /api/services | getServices() |
| METHODS| /api/methods| getMethods()|
| VERSIONS| /api/versions| getVersions()|
| CHANGELOG| /api/changelog| getChangelogs()|

## Public Getter Methods

| Method Name | Return Type | Description |
| ------------- | ------------- | ------------- |
| `getTotal_calls()` | int  | returns number of total calls by your api key |
| `getName()`  | String | returns the name registered with your api key|
|`getApi_key()`| String | returns the api key|
|`getMonthly_calls()`|int| returns number of monthly calls by your api key|
|`getServices()`|ArrayList of type `APIService`| returns a list of services offered by the API |
|`getMethods()`|ArrayList of type `APIMethod`|returns a list of methods offered by the API|
|`getVersions()`|ArrayList of type `APIVersion`|returns a list of previous versions of the API|
|`getChangeLogs()`|ArrayList of type `APIChangeLog`|returns a list of changelogs for the API|
|`getAPIResult()`|`APIResult`|returns the APIResult associated with this instance of the parser|
|`getMeta()`|`MetaData`|returns the meta data associated with this instances apiResult object|
|`getEndPoint()`|String|returns the required end point string for building a URL|
|`getParseTypes`|`APIParser.ParseType[]`|returns the parse types of this parser|
|`getParseType()`|`APIParser.ParseType`|returns the current parse type|

## Public Setter Methods

| Method Name | Parameters | Description |
| ------------- | ------------- | ------------- |
| `setParseType(...)`  | `APIParser.ParseType`  |sets that parse type for this instance|
| `setAPIResult(...)`  |  `APIResult` |sets the APIResult object for this parser  |

## Public Void Methods
| Method Name  | Description |
| ------------- | ------------- |
| `parseJSON()`  | parses the JSON data in it's given APIResult  |

