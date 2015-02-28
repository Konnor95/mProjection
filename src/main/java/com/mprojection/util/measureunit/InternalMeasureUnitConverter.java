package com.mprojection.util.measureunit;

public final class InternalMeasureUnitConverter implements MeasureUnitConverter {

    @Override
    public int convertTemperature(int temperature) {
        return temperature;
    }

    @Override
    public int convertDistance(int distance) {
        return distance;
    }

}
