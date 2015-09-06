# BuildingParser.java
The BuildingParser class is responsible for parsing JSON data from the following end points:
* [/building/list](https://github.com/uWaterloo/api-documentation/blob/master/v2/buildings/list.md)
* [/building/{building_code}](https://github.com/uWaterloo/api-documentation/blob/master/v2/buildings/building_acronym.md)
* [/building/{building}/{room}/courses](https://github.com/uWaterloo/api-documentation/blob/master/v2/buildings/building_acronym_room_number_courses.md)

## ParseType Enumerations
The table below shows which parse types correspond to which end point, and getter method(s).

|  Name  | End Point | Getter Methods |
| ------------- | ------------- | ------------- |
| LIST  | /building/list  | getUWBuildings()|
|BUILDING_CODE| /building/{building_code}|getBuildingCodeBuilding()|
|COURSES|/building/{building}/{room}/courses|getUwClasses()|

## Public Getter Methods

|  Method Name  | Return Type | Description |
| ------------- | ------------- | ------------- |
| `getUwBuildings()`  | ArrayList of type `UWBuilding`  | returns a list of buildings on campus|
|`getBuildingCodeBuilding()`|`UWBuilding`|returns a single UWBuilding for a particular {building_code}|
|`getUwClasses()`|ArrayList of Type `UWClass`|returns a list of classes for a particular {building} and {room}|
|`getAPIResult()`|`APIResult`|returns the APIResult object associated with this instance|
|`getMeta()`|`MetaData`|returns the MetaData object associated with this instance|
|`getParseType()`|`BuildingParser.ParseType`|returns the BuildingParser.ParseType enum associated with this instance|
|`getEndPoint()`|String|returns end point for enum LIST|
|`getEndPoint(String buildingCode)`|String|returns end point for enum BUILDING_CODE|
|`getEndPoint(String building, String room)`|String|returns end point for enum COURSES|

## Public Setter Methods
|  Method Name  | Parameters | Description |
| ------------- | ------------- | ------------- |
| `setAPIResult(...)`  | `APIResult`  | sets APIResult object for this instance|
|`setParseType(...)`|`BuildingParser.ParseType`|sets the BuildingParser.ParseType enum for this instance|

## Public Void Methods
|  Method Name   | Description |
| ------------- | ------------- |
| `parseJSON()`  | parses the JSON data in it's given APIResult | 



