package com.mprojection.weather;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import java.util.Calendar;
import java.util.TimeZone;

public class SunInfo {

    private String sunrise;
    private String sunset;
    private boolean isNight;
    private boolean isDay;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public boolean isNight() {
        return isNight;
    }

    public void setNight(boolean isNight) {
        this.isNight = isNight;
    }

    public boolean isDay() {
        return isDay;
    }

    public void setDay(boolean isDay) {
        this.isDay = isDay;
    }

    public void setSunCycle(Location location, String timeZoneIdentifier) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneIdentifier);
        Calendar calendar = Calendar.getInstance(timeZone);
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, timeZone);
        Calendar sunriseDate = calculator.getCivilSunriseCalendarForDate(calendar);
        Calendar sunsetDate = calculator.getCivilSunsetCalendarForDate(calendar);
        isDay = calendar.after(sunriseDate) && calendar.before(sunsetDate);
        isNight = !isDay;
        sunrise = calculator.getCivilSunriseForDate(calendar);
        sunset = calculator.getCivilSunsetForDate(calendar);
    }

}
