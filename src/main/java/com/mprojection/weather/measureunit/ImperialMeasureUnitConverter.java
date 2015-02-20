package com.mprojection.weather.measureunit;

public final class ImperialMeasureUnitConverter implements MeasureUnitConverter{

    @Override
    public int convertTemperature(int temperature) {
        return (int) (temperature * 1.8 - 459.67);
    }

}
