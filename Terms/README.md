# TermsParser.java
The TermsParser class is responsible for parsing JSON data from these End Points:
* [/terms/{term}/examschedule](https://github.com/uWaterloo/api-documentation/blob/master/v2/terms/term_examschedule.md)
* [/terms/{term}/{subject}/schedule](https://github.com/uWaterloo/api-documentation/blob/master/v2/terms/term_subject_schedule.md)
* [/terms/{term}/{subject}/{catalog_number}/schedule](https://github.com/uWaterloo/api-documentation/blob/master/v2/terms/term_subject_catalog_number_schedule.md)
* [/terms/{term}/infosessions](https://github.com/uWaterloo/api-documentation/blob/master/v2/terms/term_infosessions.md)

## ParseTypes Enumerations
The table below shows which parse types correspond to which end point, and getter method.

| Name  | End Point | Getter Method(s) |
| ------------- | ------------- |------------- |
| EXAM_SCHEDULE  | /terms/{term}/examschedule  | getExamSchedules()|
| SUBJECT_SCHEDULE  | /terms/{term}/{subject}/schedule  | getSubjectClasses()|
| CATALOG_SCHEDULE  | /terms/{term}/{subject}/{catalog_number}/schedule  | getCatalogNumberClasses()|
| INFO_SESSIONS  | /terms/{term}/infosessions  | getInfoSessions()|

## Public Getter Methods
| Method Name  | Return Type | Description |
| ------------- | ------------- | ------------- |
|`getAPIResult()`|`APIResult`|returns the apiResult associated with the instance of the parser|
|`getMeta()`|`MetaData`|returns the Meta Data associated with the given APIResult|
|`getEndPoint()`|String|returns the required end point string for building a URL|
|`getParseType`|`TermsParser.ParseType`|returns the parse type associated with this instance|
|`getExamSchedules()`|ArrayList of type `CourseExamSchedule`|returns a the exam schedule for a given term|
|`getSubjectClasses()`|ArrayList of type `UWClass`|returns a list of classes from a given {term} for a specific {subject}|
|`getCatalogNumberClasses()`|ArrayList of type `UWClass`|returns a list of classes from a given {term} for a specific {subject} and {catalog_number}|
|`getInfoSessions()`|ArrayList of type `InfoSession`|returns a list of info sessions from a given {term}|

## Public Setter Methods
| Method Name  | Parameters | Description |
| ------------- | ------------- | ------------- |
|`setAPIResult(...)`| `APIResult` |Sets the apiResult object for this parser|
|`setParseType(...)`| `TermsParser.ParseType` | sets the parse type for this parser |

## Public Void Methods
| Method Name  | Description |
| ------------- | ------------- | ------------- |
|`parseJSON()`| parses the JSON data in it's given APIResult |
