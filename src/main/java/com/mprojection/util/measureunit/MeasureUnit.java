package com.mprojection.util.measureunit;

import com.mprojection.util.Translator;

public enum MeasureUnit {

    INTERNAL(new InternalMeasureUnitConverter(), "units.internal.distance"),
    METRIC(new MetricMeasureUnitConverter(), "units.metric.distance"),
    IMPERIAL(new ImperialMeasureUnitConverter(), "units.imperial.distance");

    private MeasureUnitConverter converter;
    private String distanceUnit;

    MeasureUnit(MeasureUnitConverter converter, String distanceUnit) {
        this.converter = converter;
        this.distanceUnit = distanceUnit;
    }

    public static MeasureUnit define(Integer type) {
        if (type == null) {
            return MeasureUnit.METRIC;
        }
        MeasureUnit[] roles = values();
        if (type >= roles.length || type < 0) {
            return roles[0];
        }
        return roles[type];
    }

    public MeasureUnitConverter getConverter() {
        return converter;
    }

    public String getDistanceUnit(Translator translator) {
        return translator.translate(distanceUnit);
    }
}
