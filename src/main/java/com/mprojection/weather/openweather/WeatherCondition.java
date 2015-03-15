package com.mprojection.weather.openweather;

enum WeatherCondition {

    THUNDERSTORM(2),
    DRIZZLE(3),
    RAIN(5),
    SNOW(6),
    MIST(701),
    SMOKE(711),
    HAZE(721),
    SAND_AND_DUST_WHIRLS(731),
    FOG(741),
    SAND(751),
    DUST(761),
    VOLCANIC_ASH(762),
    SQUALLS(771),
    TORNADO(781),
    CLEAR_SKY(800),
    FEW_CLOUDS(801),
    SCATTERED_CLOUDS(802),
    BROKEN_CLOUDS(803),
    OVERCAST_CLOUDS(804),
    EXTREME_TORNADO(900),
    EXTREME_TROPICAL_STORM(901),
    EXTREME_HURRICANE(902),
    EXTREME_COLD(903),
    EXTREME_HOT(904),
    EXTREME_WINDY(905),
    EXTREME_HAIL(906),
    ADDITIONAL_CALM(951),
    ADDITIONAL_LIGHT_BREEZE(951),
    ADDITIONAL_GENTLE_BREEZE(953),
    ADDITIONAL_MODERATE_BREEZE(954),
    ADDITIONAL_FRESH_BREEZE(955),
    ADDITIONAL_STRONG_BREEZE(956),
    ADDITIONAL_HIGH_WIND_NEAR_GALE(957),
    ADDITIONAL_GALE(958),
    ADDITIONAL_SEVERE_GALE(959),
    ADDITIONAL_STORM(960),
    ADDITIONAL_VIOLENT_STORM(961),
    ADDITIONAL_HURRICANE(962);

    private Integer id;

    WeatherCondition(Integer id) {
        this.id = id;
    }

    public static WeatherCondition define(Integer code) {
        for (WeatherCondition condition : values()) {
            if (code.toString().startsWith(condition.id.toString())) {
                return condition;
            }
        }
        return null;
    }

    public static boolean isLowVisibility(int[] codes) {
        for (int code : codes) {
            WeatherCondition condition = define(code);
            if (condition == MIST || condition == SMOKE ||
                    condition == HAZE || condition == SAND_AND_DUST_WHIRLS ||
                    condition == FOG || condition == SAND ||
                    condition == DUST || condition == VOLCANIC_ASH) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRain(int[] codes) {
        for (int code : codes) {
            WeatherCondition condition = define(code);
            if (condition == DRIZZLE || condition == RAIN) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSnow(int[] codes) {
        for (int code : codes) {
            WeatherCondition condition = define(code);
            if (condition == SNOW) {
                return true;
            }
        }
        return false;
    }


    public static boolean isCloudy(int[] codes) {
        for (int code : codes) {
            WeatherCondition condition = define(code);
            if (condition == FEW_CLOUDS ||
                    condition == SCATTERED_CLOUDS ||
                    condition == BROKEN_CLOUDS ||
                    condition == OVERCAST_CLOUDS) {
                return true;
            }
        }
        return false;
    }

}
