package com.mprojection.controller;

import com.mprojection.entity.User;
import com.mprojection.service.UserService;
import com.mprojection.util.ErrorInfo;
import com.mprojection.weather.Weather;
import com.mprojection.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    @Qualifier("OpenWeatherService")
    private WeatherService weatherService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "updateAbilities", method = RequestMethod.POST)
    public Weather updateAbilities(double lat, double lng, int measureUnit, String timeZone) {
        return weatherService.getCurrentWeather(lat, lng, measureUnit, timeZone);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public User get(@PathVariable int id) {
        return userService.get(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User add(@RequestBody User user) {
        return userService.add(user);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public User update(@RequestBody User user) {
        return userService.updateAndReturn(user);
    }

    @ExceptionHandler({Exception.class})
    public ErrorInfo handleError(HttpServletRequest request, Exception exception) {
        return new ErrorInfo(request.getRequestURL().toString(), exception);
    }

}
