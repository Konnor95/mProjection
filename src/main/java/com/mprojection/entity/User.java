package com.mprojection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User extends Entity {

    private String firstName;
    private String lastName;
    private String login;
    private String facebookToken;
    private String appleToken;
    private double lat;
    private double lng;
    private int hp;
    private short level;
    private short type;

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

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public String getAppleToken() {
        return appleToken;
    }

    public void setAppleToken(String appleToken) {
        this.appleToken = appleToken;
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

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level > 0 ? level : 1;
    }

    public UserType getType() {
        return UserType.define(type);
    }

    public void setType(UserType type) {
        this.type = (short) (type.ordinal());
    }

    @JsonIgnore
    public short getTypeOrdinal() {
        return type;
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
}
