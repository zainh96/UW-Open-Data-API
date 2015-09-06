# ResourcesParser.java
The ResourcesParser class is responsible for parsing JSON data from these End Points:
* [/resources/tutors](https://github.com/uWaterloo/api-documentation/blob/master/v2/resources/tutors.md)
* [/resources/printers](https://github.com/uWaterloo/api-documentation/blob/master/v2/resources/printers.md)
* [/resources/infosessions](https://github.com/uWaterloo/api-documentation/blob/master/v2/resources/infosessions.md)
* [/resources/goosewatch](https://github.com/uWaterloo/api-documentation/blob/master/v2/resources/goosewatch.md)

## ParseTypes Enumerations
The table below shows which parse types correspond to which end point, and getter method.

| Name  | End Point | Getter Method(s) |
| ------------- | ------------- |------------- |
| TUTORS  | /resources/tutors  | getTutors()|
| PRINTERS  | /resources/printers  | getPrinters()|
| INFOSESSIONS  | /resources/infosessions  | getInfoSessions()|
| GOOSEWATCH  | /resources/goosewatch  | getGooseLocations()|

## Public Getter Methods
| Method Name  | Return Type | Description |
| ------------- | ------------- | ------------- |
|`getAPIResult()`|`APIResult`|returns the apiResult associated with the instance of the parser|
|`getMeta()`|`MetaData`|returns the Meta Data associated with the given APIResult|
|`getEndPoint()`|String|returns the required end point string for building a URL|
|`getParseTypes()`|ServerParser.ParseType[]|returns an array of all Parse Types for this parser|
|`getTutors()`|ArrayList of type `Tutor`|returns a list of tutors on campus|
|`getPrinters()`|ArrayList of type `CampusPrinter`|returns a list of printers on campus|
|`getInfoSessions()`|ArrayList of type `InfoSession`|returns a list of scheduled info sessions|
|`getGooseLocations()`|ArrayList of type `GooseLocation`|returns a list of reported goose sightings|

## Public Setter Methods
| Method Name  | Parameters | Description |
| ------------- | ------------- | ------------- |
|`setAPIResult(...)`| `APIResult` |Sets the apiResult object for this parser|
|`setParseType(...)`| `ServerParser.ParseType` | sets the parse type for this parser |

## Public Void Methods
| Method Name  | Description |
| ------------- | ------------- | ------------- |
|`parseJSON()`| parses the JSON data in it's given APIResult |
