package com.mprojection.weather.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SnowInfo {

    @JsonProperty("3h")
    private Double h3;

    public Double getH3() {
        return h3;
    }

    public void setH3(Double h3) {
        this.h3 = h3;
    }
}
