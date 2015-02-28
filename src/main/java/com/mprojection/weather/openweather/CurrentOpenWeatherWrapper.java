package com.mprojection.weather.openweather;

import com.mprojection.weather.Weather;
import com.mprojection.weather.WeatherWrapper;
import com.mprojection.util.measureunit.MeasureUnit;

public final class CurrentOpenWeatherWrapper implements WeatherWrapper {

    private Long id;
    private Long dt;
    private String name;
    private String base;
    private Integer cod;
    private Coordinates coord;
    private SystemInfo sys;
    private MainInfo main;
    private WindInfo wind;
    private CloudsInfo clouds;
    private WeatherInfo[] weather;
    private RainInfo rain;
    private SnowInfo snow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public SystemInfo getSys() {
        return sys;
    }

    public void setSys(SystemInfo sys) {
        this.sys = sys;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public WindInfo getWind() {
        return wind;
    }

    public void setWind(WindInfo wind) {
        this.wind = wind;
    }

    public CloudsInfo getClouds() {
        return clouds;
    }

    public void setClouds(CloudsInfo clouds) {
        this.clouds = clouds;
    }

    public WeatherInfo[] getWeather() {
        return weather;
    }

    public void setWeather(WeatherInfo[] weather) {
        this.weather = weather;
    }

    public RainInfo getRain() {
        return rain;
    }

    public void setRain(RainInfo rain) {
        this.rain = rain;
    }

    public SnowInfo getSnow() {
        return snow;
    }

    public void setSnow(SnowInfo snow) {
        this.snow = snow;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    @Override
    public Weather toWeather(MeasureUnit measureUnit) {
        Weather w = new Weather();
        w.setTemperature((int) main.getTemp());
        return w;
    }
}


