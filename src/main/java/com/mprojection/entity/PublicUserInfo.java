package com.mprojection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicUserInfo extends Entity {

    private String firstName;
    private String lastName;
    private String login;
    private String appleToken;
    private String lang;
    private double lat;
    private double lng;
    private int hp;
    private short type;
    private int visibility;
    private float abilityAttackFactor;
    private float abilityDefenseFactor;
    private float temperatureAttackFactor;
    private float temperatureDefenseFactor;
    private float sunAttackFactor;
    private float sunDefenseFactor;
    private Double distance;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAppleToken() {
        return appleToken;
    }

    public void setAppleToken(String appleToken) {
        this.appleToken = appleToken;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setType(short type) {
        this.type = type;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public short getRole() {
        return type;
    }

    public void setRole(short role) {
        type = role;
    }

    @JsonIgnore
    public UserType getType() {
        return UserType.define(type);
    }

    @JsonIgnore
    public void setType(UserType type) {
        this.type = (short) (type.ordinal());
    }

    @JsonIgnore
    public short getTypeOrdinal() {
        return type;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    @JsonIgnore
    public float getAbilityAttackFactor() {
        return abilityAttackFactor;
    }

    @JsonIgnore
    public void setAbilityAttackFactor(float abilityAttackFactor) {
        this.abilityAttackFactor = abilityAttackFactor;
    }

    @JsonIgnore
    public float getAbilityDefenseFactor() {
        return abilityDefenseFactor;
    }

    @JsonIgnore
    public void setAbilityDefenseFactor(float abilityDefenseFactor) {
        this.abilityDefenseFactor = abilityDefenseFactor;
    }

    @JsonIgnore
    public float getTemperatureAttackFactor() {
        return temperatureAttackFactor;
    }

    @JsonIgnore
    public void setTemperatureAttackFactor(float temperatureAttackFactor) {
        this.temperatureAttackFactor = temperatureAttackFactor;
    }

    @JsonIgnore
    public float getTemperatureDefenseFactor() {
        return temperatureDefenseFactor;
    }

    @JsonIgnore
    public void setTemperatureDefenseFactor(float temperatureDefenseFactor) {
        this.temperatureDefenseFactor = temperatureDefenseFactor;
    }

    @JsonIgnore
    public float getSunAttackFactor() {
        return sunAttackFactor;
    }

    @JsonIgnore
    public void setSunAttackFactor(float sunAttackFactor) {
        this.sunAttackFactor = sunAttackFactor;
    }

    @JsonIgnore
    public float getSunDefenseFactor() {
        return sunDefenseFactor;
    }

    @JsonIgnore
    public void setSunDefenseFactor(float sunDefenseFactor) {
        this.sunDefenseFactor = sunDefenseFactor;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @JsonProperty
    public boolean isHuman() {
        return getType().isHuman();
    }

    @JsonIgnore
    @Deprecated
    public void setHuman(boolean isHuman) {
    }

    @JsonProperty
    public boolean isZombie() {
        return getType().isZombie();
    }

    @JsonIgnore
    @Deprecated
    public void setZombie(boolean isZombie) {

    }

    @JsonIgnore
    public float getAttackFactor() {
        return abilityAttackFactor * temperatureAttackFactor * sunAttackFactor;
    }

    @JsonIgnore
    public float getDefenseFactor() {
        return abilityDefenseFactor * temperatureDefenseFactor * sunDefenseFactor;
    }

}
