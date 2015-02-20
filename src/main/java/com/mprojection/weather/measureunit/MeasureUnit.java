package com.mprojection.weather.measureunit;

public enum MeasureUnit {

    INTERNAL(new InternalMeasureUnitConverter()),
    METRIC(new MetricMeasureUnitConverter()),
    IMPERIAL(new ImperialMeasureUnitConverter());
    private MeasureUnitConverter converter;

    MeasureUnit(MeasureUnitConverter converter) {
        this.converter = converter;
    }

    public static MeasureUnit define(int type) {
        MeasureUnit[] roles = values();
        if (type >= roles.length || type < 0) {
            return roles[0];
        }
        return roles[type];
    }

    public MeasureUnitConverter getConverter() {
        return converter;
    }
}
