# WeatherParser.java
The WeatherParser class is responsible for parsing JSON data from these End Points:
* [/weather/current](https://github.com/uWaterloo/api-documentation/blob/master/v2/weather/current.md)

## ParseTypes Enumerations
The table below shows which parse types correspond to which end point, and getter method.

| Name  | End Point | Getter Method(s) |
| ------------- | ------------- |------------- |
| CURRENT  | /weather/current  | See below |

## Public Getter Methods
| Method Name  | Return Type | Description |
| ------------- | ------------- | ------------- |
|`getAPIResult()`|`APIResult`|returns the apiResult associated with the instance of the parser|
|`getMeta()`|`MetaData`|returns the Meta Data associated with the given APIResult|
|`getEndPoint()`|String|returns the required end point string for building a URL|
|`getParseType()`|`WeatherParser.ParseType`|returns the parse type associated with this instance|
|`getLatitude()`|double|returns the latitude co-ordinate for the weather sighting|
|`getLongitude()`|double|returns the longitude co-ordinate for the weather sighting|
|`getElevation()`|double|returns the elevation height in metres for the weather sighting|
|`getObservationTime()`|String|returns the time the weather was observed|
|`getCurrentTemperature()`|double|returns the current temperature in celsius|
|`getHumidex()`|double|returns the humidex|
|`getWindchill()`|double|returns the windchill in celsius|
|`getTemperature24hrMax()`|double|returns the 24 hour maximum temperature in celsius|
|`getTemperature24hrMin()`|double|returns the 24 hour minimum temperature in celsius|
|`getPrecipitation15min()`|double|returns the precipitation in the next 15 minutes in mm|
|`getPrecipitation1hr()`|double|returns the precipitation in the next 1 hour in mm|
|`getPrecipitation24hr()`|double|returns the precipitation in the next 24 hours in mm|
|`getRelativeHumidityPercent()`|double|returns the humidity percentage|
|`getDewPoint()`|double|returns dew point|
|`getWindSpeed()`|double|returns the wind speed in kph|
|`getWindDirection()`|double|returns the wind direction in degrees|
|`getPressureKpa()`|double|returns the pressure in kilpascals|
|`getPressureTrend()`|String|returns the pressure trend|
|`getIncomingShortwaveRadiationWm2()`|double|returns the incoming short wave radiation in wm2|

## Public Setter Methods
| Method Name  | Parameters | Description |
| ------------- | ------------- | ------------- |
|`setAPIResult(...)`| `APIResult` |Sets the apiResult object for this parser|
|`setParseType(...)`| `WeatherParser.ParseType` | sets the parse type for this parser |

## Public Void Methods
| Method Name  | Description |
| ------------- | ------------- | ------------- |
|`parseJSON()`| parses the JSON data in it's given APIResult |
