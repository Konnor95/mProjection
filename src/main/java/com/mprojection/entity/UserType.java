package com.mprojection.entity;

import com.mprojection.weather.SunInfo;
import com.mprojection.weather.Weather;

public enum UserType {

    SOLDIER(50, 100, 10, 5) {
        @Override
        public void applyWeatherCondition(FullUserInfo user, Weather weather, SunInfo sunInfo) {
            double attackFactor = 1;
            double defenseFactor = 1;
            int visibilityFactor = 1;
            int healthFactor = 1;
            defenseFactor *= sunInfo.isNight() ? 0.75 : 1;
            if (weather.getTemperature() < -20) {
                attackFactor *= 0.85;
                defenseFactor *= 0.65;
            } else {
                attackFactor *= 1;
                defenseFactor *= DEFAULT_DEFENSE;
            }
            visibilityFactor *= weather.isCloudy() || weather.isLowVisibility() ? 0.8 : 1;
            healthFactor *= weather.isRain() || weather.isSnow() ? 0.8 : 1;
            user.setAttackFactor(attackFactor);
            user.setDefenseFactor(defenseFactor);
            user.setVisibilityFactor(visibilityFactor);
            user.setHealthFactor(healthFactor);
        }
    },
    SCIENTIST(25, 100, 10, 5) {
        @Override
        public void applyWeatherCondition(FullUserInfo user, Weather weather, SunInfo sunInfo) {
            double attackFactor = 1;
            double defenseFactor = 1;
            int visibilityFactor = 1;
            int healthFactor = 1;
            defenseFactor *= sunInfo.isNight() ? 0.75 : 1;
            if (weather.getTemperature() < -20) {
                attackFactor *= 0.85;
                defenseFactor *= 0.65;
            } else {
                attackFactor *= 1;
                defenseFactor *= DEFAULT_DEFENSE;
            }
            visibilityFactor *= weather.isCloudy() || weather.isLowVisibility() ? 0.8 : 1;
            healthFactor *= weather.isRain() || weather.isSnow() ? 0.8 : 1;
            user.setAttackFactor(attackFactor);
            user.setDefenseFactor(defenseFactor);
            user.setVisibilityFactor(visibilityFactor);
            user.setHealthFactor(healthFactor);
        }
    },
    ZOMBIE(100, 100, 10, 5) {
        @Override
        public void applyWeatherCondition(FullUserInfo user, Weather weather, SunInfo sunInfo) {
            double attackFactor = 1;
            double defenseFactor = 1;
            int visibilityFactor = 1;
            int healthFactor = 1;
            defenseFactor *= sunInfo.isNight() ? 1.2 : 1;
            if (weather.getTemperature() < -10 || weather.getTemperature() > 35) {
                attackFactor *= 0.85;
                defenseFactor *= 0.65;
            } else {
                attackFactor *= 1;
                defenseFactor *= DEFAULT_DEFENSE;
            }
            visibilityFactor *= weather.isCloudy() || weather.isLowVisibility() ? 0.8 : 1;
            healthFactor *= weather.isRain() || weather.isSnow() ? 0.8 : 1;
            user.setAttackFactor(attackFactor);
            user.setDefenseFactor(defenseFactor);
            user.setVisibilityFactor(visibilityFactor);
            user.setHealthFactor(healthFactor);
        }
    };

    private static final float DEFAULT_DEFENSE = 0.8f;
    private int defaultVisibility;
    private int defaultHp;
    private int defaultAttack;
    private int defaultDefense;

    UserType(int defaultVisibility, int defaultHp, int defaultAttack, int defaultDefense) {
        this.defaultVisibility = defaultVisibility;
        this.defaultHp = defaultHp;
        this.defaultAttack = defaultAttack;
        this.defaultDefense = defaultDefense;
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

    public int getDefaultAttack() {
        return defaultAttack;
    }

    public int getDefaultDefense() {
        return defaultDefense;
    }

    public abstract void applyWeatherCondition(FullUserInfo user, Weather weather, SunInfo sunInfo);
}
