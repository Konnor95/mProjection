package com.mprojection.weather;

import com.mprojection.util.measureunit.MeasureUnit;

public interface WeatherWrapper {

    Weather toWeather(MeasureUnit measureUnit);

}
