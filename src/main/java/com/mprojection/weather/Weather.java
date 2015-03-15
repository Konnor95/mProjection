package com.mprojection.weather;

import com.mprojection.util.measureunit.MeasureUnit;
import com.mprojection.util.measureunit.MeasureUnitConverter;

public class Weather {

    private int temperature;
    private boolean isLowVisibility;
    private boolean isRain;
    private boolean isSnow;
    private boolean isCloudy;

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

    public boolean isLowVisibility() {
        return isLowVisibility;
    }

    public void setLowVisibility(boolean isLowVisibility) {
        this.isLowVisibility = isLowVisibility;
    }

    public boolean isRain() {
        return isRain;
    }

    public void setRain(boolean isRain) {
        this.isRain = isRain;
    }

    public boolean isSnow() {
        return isSnow;
    }

    public void setSnow(boolean isSnow) {
        this.isSnow = isSnow;
    }

    public boolean isCloudy() {
        return isCloudy;
    }

    public void setCloudy(boolean isCloudy) {
        this.isCloudy = isCloudy;
    }
}
