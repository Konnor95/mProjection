package com.mprojection.weather;

import com.luckycatlabs.sunrisesunset.dto.Location;

/**
 * Provides access for weather data.
 */
public interface WeatherService {

    /**
     * Gets current weather by coordinates.
     *
     * @param lat latitude
     * @param lng longitude
     * @param measureUnit measure unit type
     * @return current weather
     */
    Weather getCurrentWeather(double lat, double lng, int measureUnit, String timeZone);

    SunInfo getSunInfo(Location location, String timeZoneIdentifier);

}
