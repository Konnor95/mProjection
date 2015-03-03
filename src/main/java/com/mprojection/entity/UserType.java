package com.mprojection.entity;

import com.mprojection.weather.SunInfo;
import com.mprojection.weather.Weather;

public enum UserType {

    SOLDIER(50, 100) {
        @Override
        public void applyWeatherCondition(FullUserInfo user, Weather weather, SunInfo sunInfo) {
            if (sunInfo.isNight()) {
                user.setSunDefenseFactor(0.75f);
            }
            if (sunInfo.isDay()) {
                user.setSunDefenseFactor(1f);
            }
            if (weather.getTemperature() < -20) {
                user.setTemperatureAttackFactor(0.85f);
                user.setTemperatureDefenseFactor(0.65f);
            } else {
                user.setTemperatureAttackFactor(1f);
                user.setTemperatureDefenseFactor(DEFAULT_DEFENSE);
            }
        }
    },
    SCIENTIST(25, 100) {
        @Override
        public void applyWeatherCondition(FullUserInfo user, Weather weather, SunInfo sunInfo) {
            if (sunInfo.isNight()) {
                user.setSunDefenseFactor(0.75f);
            }
            if (sunInfo.isDay()) {
                user.setSunDefenseFactor(1f);
            }
            if (weather.getTemperature() < -20) {
                user.setTemperatureAttackFactor(0.85f);
                user.setTemperatureDefenseFactor(0.65f);
            } else {
                user.setTemperatureAttackFactor(1f);
                user.setTemperatureDefenseFactor(DEFAULT_DEFENSE);
            }
        }
    },
    ZOMBIE(100, 100) {
        @Override
        public void applyWeatherCondition(FullUserInfo user, Weather weather, SunInfo sunInfo) {
            if (sunInfo.isNight()) {
                user.setSunAttackFactor(1.2f);
                user.setSunDefenseFactor(1f);
            }
            if (sunInfo.isDay()) {
                user.setSunAttackFactor(1f);
                user.setSunDefenseFactor(1f);
            }
            if (weather.getTemperature() < 0) {
                user.setTemperatureAttackFactor(0.85f);
                user.setTemperatureDefenseFactor(0.65f);
            } else {
                user.setTemperatureAttackFactor(DEFAULT_DEFENSE);
                user.setTemperatureDefenseFactor(DEFAULT_DEFENSE);
            }
        }
    };

    private static final float DEFAULT_DEFENSE = 0.8f;
    private int defaultVisibility;
    private int defaultHp;

    UserType(int defaultVisibility, int defaultHp) {
        this.defaultVisibility = defaultVisibility;
        this.defaultHp = defaultHp;
    }

    public static UserType define(int type) {
        UserType[] types = values();
        if (type >= types.length || type < 0) {
            throw new IllegalArgumentException("Invalid type");
        }
        return types[type];
    }

    public boolean isHuman() {
        return this == SOLDIER || this == SCIENTIST;
    }

    public boolean isZombie() {
        return this == ZOMBIE;
    }

    public int getDefaultVisibility() {
        return defaultVisibility;
    }

    public int getDefaultHp() {
        return defaultHp;
    }

    public abstract void applyWeatherCondition(FullUserInfo user, Weather weather, SunInfo sunInfo);
}
