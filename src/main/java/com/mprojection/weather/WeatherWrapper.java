package com.mprojection.weather;

import com.mprojection.weather.measureunit.MeasureUnit;

public interface WeatherWrapper {

    Weather toWeather(MeasureUnit measureUnit);

}
