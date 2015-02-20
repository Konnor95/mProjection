package com.mprojection.controller;

import com.luckycatlabs.sunrisesunset.dto.Location;
import com.mprojection.weather.SunInfo;
import com.mprojection.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather/")
public class WeatherController {

    @Autowired
    @Qualifier("OpenWeatherService")
    private WeatherService weatherService;

    @RequestMapping(value = "sun", method = RequestMethod.GET)
    public SunInfo getSunInfo(double lat, double lng, String timeZone) {
        return weatherService.getSunInfo(new Location(lat, lng), timeZone);
    }

}
