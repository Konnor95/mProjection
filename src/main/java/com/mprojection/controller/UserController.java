package com.mprojection.controller;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.PublicUserInfo;
import com.mprojection.exception.DAOException;
import com.mprojection.service.UserService;
import com.mprojection.util.ErrorInfo;
import com.mprojection.util.measureunit.MeasureUnit;
import com.mprojection.weather.Weather;
import com.mprojection.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    @Autowired
    @Qualifier("OpenWeatherService")
    private WeatherService weatherService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "updateAbilities", method = RequestMethod.POST)
    public Weather updateAbilities(double lat, double lng, Integer measureUnit, String timeZone) {
        return weatherService.getCurrentWeather(lat, lng, measureUnit, timeZone);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public PublicUserInfo get(@PathVariable long id) {
        return userService.getPublicInfo(id);
    }

    @RequestMapping(value = "{id}/nearest/", method = RequestMethod.GET)
    public List<PublicUserInfo> getNearest(@PathVariable long id) {
        return userService.getNearest(id);
    }

    @RequestMapping(value = "full/{id}/", method = RequestMethod.GET)
    public FullUserInfo getFullInfo(@PathVariable long id, Integer measureUnit) {
        return userService.get(id, MeasureUnit.define(measureUnit));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public FullUserInfo add(@RequestBody FullUserInfo user) {
        return userService.add(user);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public FullUserInfo update(@RequestBody FullUserInfo user, Integer measureUnit) {
        return userService.updateAndReturn(user, MeasureUnit.define(measureUnit));
    }

    @RequestMapping(value = "full/{id}/ability/{abilityId}/", method = RequestMethod.GET)
    public FullUserInfo addAbility(@PathVariable long id, @PathVariable String abilityId, Integer measureUnit) {
        return userService.addAbility(id, MeasureUnit.define(measureUnit), abilityId);
    }

    @ExceptionHandler({DAOException.class})
    public ErrorInfo handleError(HttpServletRequest request, DAOException exception) {
        return new ErrorInfo(request.getRequestURL().toString(), exception.getCause());
    }

}
