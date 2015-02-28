package com.mprojection.util.measureunit;

public final class ImperialMeasureUnitConverter implements MeasureUnitConverter {

    @Override
    public int convertTemperature(int temperature) {
        return (int) (temperature * 1.8 - 459.67);
    }

    @Override
    public int convertDistance(int distance) {
        return (int) (distance * 0.9144);
    }

}
