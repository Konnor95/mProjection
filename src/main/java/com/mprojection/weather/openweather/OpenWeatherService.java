package com.mprojection.weather.openweather;

import com.luckycatlabs.sunrisesunset.dto.Location;
import com.mprojection.serializer.JSONSerializer;
import com.mprojection.serializer.StreamSerializer;
import com.mprojection.util.ArrayUtil;
import com.mprojection.util.URLHelper;
import com.mprojection.weather.SunInfo;
import com.mprojection.weather.Weather;
import com.mprojection.weather.WeatherConfig;
import com.mprojection.weather.WeatherService;
import com.mprojection.util.measureunit.MeasureUnit;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component("OpenWeatherService")
public final class OpenWeatherService implements WeatherService {

    private static final String API_KEY = WeatherConfig.getProperty("openweathermap.key");
    private static final StreamSerializer serializer = new JSONSerializer();

    @Override
    public Weather getCurrentWeather(double lat, double lng, int measureUnit, String timeZone) {
        MeasureUnit unit = MeasureUnit.define(measureUnit);
        String url = prepareURL("openweathermap.current.by.coordiantes", lat, lng);
        InputStream stream = URLHelper.getInputStream(url);
        return serializer.deserialize(stream, CurrentOpenWeatherWrapper.class).toWeather(MeasureUnit.METRIC);
    }

    @Override
    public SunInfo getSunInfo(Location location, String timeZoneIdentifier) {
        SunInfo sunInfo = new SunInfo();
        sunInfo.setSunCycle(location, timeZoneIdentifier);
        return sunInfo;
    }

    private String prepareURL(String url, Object... args) {
        Object[] args2 = new Object[]{API_KEY, LocaleContextHolder.getLocale().getLanguage()};
        return String.format(WeatherConfig.getProperty(url), ArrayUtil.merge(args, args2));
    }
}
