package com.mprojection.weather;

import com.mprojection.weather.measureunit.MeasureUnit;
import com.mprojection.weather.measureunit.MeasureUnitConverter;

public class Weather {

    private int temperature;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public Weather convert(MeasureUnit measureUnit) {
        MeasureUnitConverter converter = measureUnit.getConverter();
        Weather weather = new Weather();
        weather.setTemperature(converter.convertTemperature(temperature));
        return weather;
    }
}
