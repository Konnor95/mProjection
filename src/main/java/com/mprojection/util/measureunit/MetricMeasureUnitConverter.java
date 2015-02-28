package com.mprojection.util.measureunit;

public final class MetricMeasureUnitConverter implements MeasureUnitConverter {

    @Override
    public int convertTemperature(int temperature) {
        return (int) (temperature - 273.0);
    }

    @Override
    public int convertDistance(int distance) {
        return distance;
    }

}
