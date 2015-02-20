package com.mprojection.weather.measureunit;

public final class MetricMeasureUnitConverter implements MeasureUnitConverter {

    @Override
    public int convertTemperature(int temperature) {
        return (int) (temperature - 273.0);
    }

}
