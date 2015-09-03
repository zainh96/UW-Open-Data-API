# CodesParser
The APIParser class is responsible for parsing JSON data from the following end points:
* [/codes/untis](https://github.com/uWaterloo/api-documentation/blob/master/v2/codes/units.md)
* [/codes/terms](https://github.com/uWaterloo/api-documentation/blob/master/v2/codes/terms.md)
* [/codes/groups](https://github.com/uWaterloo/api-documentation/blob/master/v2/codes/groups.md)
* [/codes/subjects](https://github.com/uWaterloo/api-documentation/blob/master/v2/codes/subjects.md)
* [/codes/instructions](https://github.com/uWaterloo/api-documentation/blob/master/v2/codes/instructions.md)

## ParseType Enumerations
The table below shows which parse types correspond to which end point, and getter method(s).

| Name  | End Point | Getter Method(s) |
| ------------- | ------------- | ------------- |
|UNITS|/codes/units|getUnits()|
|TERMS|/codes/terms|getTerms()|
|GROUPS|/codes/groups|getGroups()|
|SUBJECTS|/codes/subjects|getSubjects()|
|INSTRUCTIONS|/codes/instructions|getInstructions()|

## Public Getter Methods

| Method Name | Return Type | Description |
| ------------- | ------------- | ------------- |
|`getUnits()`|ArrayList of type `Unit`|returns a list of units used by the API|
|`getTerms()`|ArrayList of type `Term`|returns a list of terms used by the API|
|`getGroups()`|ArrayList of type of `Group`|returns a list of groups used by the API|
|`getSubjects()`|ArrayList of type `Subject`|returns a list of subjects used by the API|
|`getInstructions()`|ArrayList of type `Instruction`|returns a list of instructions used by the API|
|`getAPIResult()`|`APIResult`|returns the APIResult associated with this instance|
|`getParseType()`|`CodesParser.ParseType`|returns the CodesParser.ParseType associated with this instance|
|`getMeta()`|`MetaData`|returns the meta data associated with this instance's APIResult|
|`getEndPoint()`|String|returns the required end point string for building a URL|

## Public Setter Methods
| Method Name | Parameters | Description |
| ------------- | ------------- | ------------- |
| `setParseType(...)`  | `APIParser.ParseType`  |sets that parse type for this instance|
| `setAPIResult(...)`  |  `APIResult` |sets the APIResult object for this parser  |

## Public Void Methods
| Method Name  | Description |
| ------------- | ------------- |
| `parseJSON()`  | parses the JSON data in it's given APIResult  |
