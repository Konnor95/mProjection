package com.mprojection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicUserInfo extends Entity {

    private String firstName;
    private String lastName;
    private boolean gender;
    private String login;
    private String appleToken;
    private String lang;
    private double lat;
    private double lng;
    private int hp;
    private short type;
    private int visibility;
    private int attack;
    private int defense;
    private double attackFactor;
    private double defenseFactor;
    private double visibilityFactor;
    private double healthFactor;
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
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

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
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

    public double getAttackFactor() {
        return attackFactor;
    }

    public void setAttackFactor(double attackFactor) {
        this.attackFactor = attackFactor;
    }

    public double getDefenseFactor() {
        return defenseFactor;
    }

    public void setDefenseFactor(double defenseFactor) {
        this.defenseFactor = defenseFactor;
    }

    public double getVisibilityFactor() {
        return visibilityFactor;
    }

    public void setVisibilityFactor(double visibilityFactor) {
        this.visibilityFactor = visibilityFactor;
    }

    public double getHealthFactor() {
        return healthFactor;
    }

    public void setHealthFactor(double healthFactor) {
        this.healthFactor = healthFactor;
    }
}
