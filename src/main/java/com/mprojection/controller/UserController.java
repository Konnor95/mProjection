package com.mprojection.controller;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.PublicUserInfo;
import com.mprojection.exception.DAOException;
import com.mprojection.service.UserService;
import com.mprojection.util.ErrorInfo;
import com.mprojection.util.PushManagerUtil;
import com.mprojection.util.measureunit.MeasureUnit;
import com.mprojection.weather.SunInfo;
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

    @Autowired
    private PushManagerUtil pushManagerUtil;

    @RequestMapping(value = "{id}/abilities/", method = RequestMethod.GET)
    public FullUserInfo updateAbilities(@PathVariable int id, Integer measureUnit, String timeZone) {
        MeasureUnit unit = MeasureUnit.define(measureUnit);
        FullUserInfo user = userService.get(id, unit);
        Weather weather = weatherService.getCurrentWeather(user.getLat(), user.getLng(), unit, timeZone);
        weather.convert(MeasureUnit.METRIC);
        SunInfo sunInfo = weatherService.getSunInfo(user.getLat(), user.getLng(), timeZone);
        user.getType().applyWeatherCondition(user, weather, sunInfo);
        return userService.updateWithFactors(user, unit);
    }

    @RequestMapping(value = "{id}/public/", method = RequestMethod.GET)
    public PublicUserInfo get(@PathVariable long id) {
        return userService.getPublicInfo(id);
    }

    @RequestMapping(value = "{id}/public/nearest/", method = RequestMethod.GET)
    public List<PublicUserInfo> getNearest(@PathVariable long id) {
        return userService.getNearest(id);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.GET)
    public FullUserInfo getFullInfo(@PathVariable long id, Integer measureUnit) {
        return userService.get(id, MeasureUnit.define(measureUnit));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public FullUserInfo add(@RequestBody FullUserInfo user) {
        return userService.add(user);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public FullUserInfo update(@RequestBody FullUserInfo user, Integer measureUnit) {
        FullUserInfo existingUser = userService.get(user.getId(), MeasureUnit.INTERNAL);

        return userService.updateAndReturn(user, MeasureUnit.define(measureUnit));
    }

    @RequestMapping(value = "/{id}/ability/{abilityId}/", method = RequestMethod.PUT)
    public FullUserInfo addAbility(@PathVariable long id, @PathVariable String abilityId, Integer measureUnit) {
        return userService.addAbility(id, MeasureUnit.define(measureUnit), abilityId);
    }

    @RequestMapping(value = "/{id}/attack/{targetId}", method = RequestMethod.GET)
    public PublicUserInfo attack(@PathVariable long id, @PathVariable long targetId) {
        PublicUserInfo userInfo1 = userService.getPublicInfo(id);
        PublicUserInfo userInfo2 = userService.getPublicInfo(targetId);
        PublicUserInfo user2 = userService.attack(userInfo1, userInfo2);
        sendPushToAttackedUser(user2);
        return user2;
    }

    @ExceptionHandler({DAOException.class})
    public ErrorInfo handleError(HttpServletRequest request, DAOException exception) {
        return new ErrorInfo(request.getRequestURL().toString(), exception.getCause());
    }

    private void sendPushToAttackedUser(PublicUserInfo user) {
        pushManagerUtil.send("", "");
    }

}
