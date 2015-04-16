package com.mprojection.controller;

import com.mprojection.entity.AttackInfo;
import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.PublicUserInfo;
import com.mprojection.exception.DAOException;
import com.mprojection.serializer.JSONSerializer;
import com.mprojection.serializer.ObjectSerializer;
import com.mprojection.service.UserService;
import com.mprojection.util.ErrorInfo;
import com.mprojection.util.PushManagerUtil;
import com.mprojection.util.Translator;
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

    @Autowired
    private Translator translator;

    private ObjectSerializer serializer = new JSONSerializer();

    @RequestMapping(value = "{id}/abilities/", method = RequestMethod.PUT)
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
    public FullUserInfo add(FullUserInfo user) {
        return userService.add(user);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public FullUserInfo update(FullUserInfo user, Integer measureUnit) {
        return userService.updateAndReturn(user, MeasureUnit.define(measureUnit));
    }

    @RequestMapping(value = "/{id}/ability/{abilityId}/", method = RequestMethod.PUT)
    public FullUserInfo addAbility(@PathVariable long id, @PathVariable String abilityId, Integer measureUnit) {
        return userService.addAbility(id, MeasureUnit.define(measureUnit), abilityId);
    }

    @RequestMapping(value = "/{id}/attack/{targetId}/", method = RequestMethod.POST)
    public PublicUserInfo attack(@PathVariable long id, @PathVariable long targetId) {
        PublicUserInfo attacker = userService.getPublicInfo(id);
        PublicUserInfo target = userService.getPublicInfo(targetId);
        PublicUserInfo victim = userService.attack(attacker, target);
        sendPushToAttackedUser(victim, attacker);
        return victim;
    }

    @RequestMapping(value = "{id}/random/{count}/", method = RequestMethod.POST)
    public List<PublicUserInfo> generateRandomUsers(@PathVariable long id, @PathVariable int count) {
        PublicUserInfo user = userService.getPublicInfo(id);
        return userService.generateRandomUsers(user.getLat(), user.getLng(), 400, count);
    }

    @RequestMapping(value = "{id}/coordinates/{lat}-{lng}/", method = RequestMethod.PUT)
    public FullUserInfo updateCoordinates(@PathVariable long id, @PathVariable double lat, @PathVariable double lng) {
        FullUserInfo user = userService.get(id, MeasureUnit.METRIC);
        user.setLat(lat);
        user.setLng(lng);
        userService.update(user);
        return user;
    }

    @ExceptionHandler({DAOException.class})
    public ErrorInfo handleError(HttpServletRequest request, DAOException exception) {
        return new ErrorInfo(request.getRequestURL().toString(), exception.getCause());
    }

    private void sendPushToAttackedUser(PublicUserInfo victim, PublicUserInfo attacker) {
        String messageBody = serializer.serialize(new AttackInfo(victim, attacker));
        String messageTitle = translator.translate("push.youWasAttacked.title", victim.getLang());
        pushManagerUtil.send(victim.getAppleToken(), messageTitle, messageBody);
    }

}
