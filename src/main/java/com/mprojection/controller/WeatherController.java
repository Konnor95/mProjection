package com.mprojection.controller;

import com.mprojection.util.measureunit.MeasureUnit;
import com.mprojection.weather.Weather;
import com.mprojection.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather/")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Weather get(double lat, double lng, Integer measureUnit, String timeZone) {
        return weatherService.getCurrentWeather(lat, lng, MeasureUnit.define(measureUnit), timeZone);
    }

}
