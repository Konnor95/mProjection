package com.mprojection.weather;

import com.mprojection.util.measureunit.MeasureUnit;

/**
 * Provides access for weather data.
 */
public interface WeatherService {

    /**
     * Gets current weather by coordinates.
     *
     * @param lat         latitude
     * @param lng         longitude
     * @param measureUnit measure unit type
     * @return current weather
     */
    Weather getCurrentWeather(double lat, double lng, MeasureUnit measureUnit, String timeZone);

    SunInfo getSunInfo(double lat, double lng, String timeZoneIdentifier);

}
