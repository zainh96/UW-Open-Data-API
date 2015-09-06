package Weather;
import Core.APIResult;
import Core.MetaData;
import Core.MetaDataParser;
import Core.UWParser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ZainH on 06/09/2015.
 */
public class WeatherParser extends UWParser {
    // endpoints
    private static final String CURRENT_ENDPOINT = "weather/current";

    // JSON object leaf node tags
    private static final String LATITUDE_TAG= "latitude";
    private static final String LONGITUDE_TAG = "longitude";
    private static final String ELEVATION_M_TAG = "elevation_m";
    private static final String OBSERVATION_TIME_TAG = "observation_time";
    private static final String TEMPERATURE_CURRENT_C_TAG = "temperature_current_c";
    private static final String HUMIDEX_C_TAG = "humidex_c";
    private static final String WINDCHILL_TAG = "windchill";
    private static final String TEMPERATURE_24HR_MAX_C_TAG = "temperature_24hr_max_c";
    private static final String TEMPERATURE_24HR_MIN_C_TAG = "temperature_24hr_min_c";
    private static final String PRECIPITATION_15MIN_MM_TAG = "precipitation_15min_mm";
    private static final String PRECIPITATION_1HR_MM_TAG = "precipitation_1hr_mm";
    private static final String PRECIPITATION_24HR_MM_TAG = "precipitation_24hr_mm";
    private static final String RELATIVE_HUMIDITY_PERCENT_TAG = "relative_humidity_percent";
    private static final String DEW_POINT_C_TAG = "dew_point_c";
    private static final String WIND_SPEED_KPH_TAG = "wind_speed_kph";
    private static final String WIND_DIRECTION_DEGREES_TAG = "wind_direction_degrees";
    private static final String PRESSURE_KPA_TAG = "pressure_kpa";
    private static final String PRESSURE_TREND_TAG = "pressure_trend";
    private static final String INCOMING_SHORTWAVE_RADIATION_WM2_TAG = "incoming_shortwave_radiation_wm2";

    // JSON object tags
    private static final String DATA_TAG = "data";

    // contains all JSON information
    private APIResult apiResult = null;

    // although this end point only has 1 parse type, (and so creating a ParseType
    // Enumeration is redundant, it may get more urls in the future so this structure is already
    // set up for that, if/when it happens. It will make upgrading it easier.
    public enum ParseType {
        CURRENT
    }

    private ParseType parseType = ParseType.CURRENT;

    // /weather/current variables
    private double latitude = 0;
    private double longitude = 0;
    private double elevation = 0;
    private String observationTime = null;
    private double currentTemperature = 0;
    private double humidex = 0;
    private double windchill = 0;
    private double temperature24hrMax = 0;
    private double temperature24hrMin = 0;
    private double precipitation15min = 0;
    private double precipitation1hr = 0;
    private double precipitation24hr = 0;
    private double relativeHumidityPercent = 0;
    private double dewPoint = 0;
    private double windSpeed = 0;
    private double windDirection = 0;
    private double pressureKpa = 0;
    private String pressureTrend = null;
    private double incomingShortwaveRadiationWm2 = 0;

    @Override
    public void parseJSON() {
        if(apiResult == null || apiResult.getResultJSON() == null) return;

        switch (parseType){
            case CURRENT:
                parseCurrentJSON();
                break;
        }
    }

    private void parseCurrentJSON(){
        try {
            JSONObject weatherObject = apiResult.getResultJSON().getJSONObject(DATA_TAG);

            if(!weatherObject.isNull(LATITUDE_TAG))
                latitude = weatherObject.getDouble(LATITUDE_TAG);

            if(!weatherObject.isNull(LONGITUDE_TAG))
                longitude = weatherObject.getDouble(LONGITUDE_TAG);

            if(!weatherObject.isNull(ELEVATION_M_TAG))
                elevation = weatherObject.getDouble(ELEVATION_M_TAG);

            if(!weatherObject.isNull(OBSERVATION_TIME_TAG))
                observationTime = weatherObject.getString(OBSERVATION_TIME_TAG);

            if(!weatherObject.isNull(TEMPERATURE_CURRENT_C_TAG))
                currentTemperature = weatherObject.getDouble(TEMPERATURE_CURRENT_C_TAG);

            if(!weatherObject.isNull(TEMPERATURE_24HR_MAX_C_TAG))
                temperature24hrMax = weatherObject.getDouble(TEMPERATURE_24HR_MAX_C_TAG);

            if(!weatherObject.isNull(TEMPERATURE_24HR_MIN_C_TAG))
                temperature24hrMin = weatherObject.getDouble(TEMPERATURE_24HR_MIN_C_TAG);

            if(!weatherObject.isNull(HUMIDEX_C_TAG))
                humidex = weatherObject.getDouble(HUMIDEX_C_TAG);

            if(!weatherObject.isNull(WINDCHILL_TAG))
                windchill = weatherObject.getDouble(WINDCHILL_TAG);

            if(!weatherObject.isNull(PRECIPITATION_15MIN_MM_TAG))
                precipitation15min = weatherObject.getDouble(PRECIPITATION_15MIN_MM_TAG);

            if(!weatherObject.isNull(PRECIPITATION_1HR_MM_TAG))
                precipitation1hr = weatherObject.getDouble(PRECIPITATION_1HR_MM_TAG);

            if(!weatherObject.isNull(PRECIPITATION_24HR_MM_TAG))
                precipitation24hr = weatherObject.getDouble(PRECIPITATION_24HR_MM_TAG);

            if(!weatherObject.isNull(RELATIVE_HUMIDITY_PERCENT_TAG))
                relativeHumidityPercent = weatherObject.getDouble(RELATIVE_HUMIDITY_PERCENT_TAG);

            if(!weatherObject.isNull(DEW_POINT_C_TAG))
                dewPoint = weatherObject.getDouble(DEW_POINT_C_TAG);

            if(!weatherObject.isNull(WIND_SPEED_KPH_TAG))
                windSpeed = weatherObject.getDouble(WIND_SPEED_KPH_TAG);

            if(!weatherObject.isNull(WIND_DIRECTION_DEGREES_TAG))
                windDirection = weatherObject.getDouble(WIND_DIRECTION_DEGREES_TAG);

            if(!weatherObject.isNull(PRESSURE_KPA_TAG))
                pressureKpa = weatherObject.getDouble(PRESSURE_KPA_TAG);

            if(!weatherObject.isNull(PRESSURE_TREND_TAG))
                pressureTrend = weatherObject.getString(PRESSURE_TREND_TAG);

            if(!weatherObject.isNull(INCOMING_SHORTWAVE_RADIATION_WM2_TAG))
                incomingShortwaveRadiationWm2 = weatherObject.getDouble(INCOMING_SHORTWAVE_RADIATION_WM2_TAG);

        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public String getObservationTime() {
        return observationTime;
    }

    public double getCurrentTemperature() {
        return currentTemperature;
    }

    public double getHumidex() {
        return humidex;
    }

    public double getWindchill() {
        return windchill;
    }

    public double getTemperature24hrMax() {
        return temperature24hrMax;
    }

    public double getTemperature24hrMin() {
        return temperature24hrMin;
    }

    public double getPrecipitation15min() {
        return precipitation15min;
    }

    public double getPrecipitation1hr() {
        return precipitation1hr;
    }

    public double getPrecipitation24hr() {
        return precipitation24hr;
    }

    public double getRelativeHumidityPercent() {
        return relativeHumidityPercent;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public double getPressureKpa() {
        return pressureKpa;
    }

    public String getPressureTrend() {
        return pressureTrend;
    }

    public double getIncomingShortwaveRadiationWm2() {
        return incomingShortwaveRadiationWm2;
    }

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }

    @Override
    public void setAPIResult(APIResult apiResult) {
        this.apiResult = apiResult;
    }

    @Override
    public APIResult getAPIResult() {
        return apiResult;
    }

    @Override
    public MetaData getMeta() {
        MetaDataParser parser = new MetaDataParser();
        parser.setAPIResult(apiResult);
        parser.parseJSON();
        return parser.getMeta();
    }

    public String getEndPoint(){
        switch (parseType){
            case CURRENT:
                return CURRENT_ENDPOINT;
            default:
                return CURRENT_ENDPOINT;
        }
    }
}
