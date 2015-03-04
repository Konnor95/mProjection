package com.mprojection.entity;

import com.mprojection.entity.ability.UserAbility;

import java.util.List;

public class FullUserInfo extends PublicUserInfo {

    private String facebookToken;
    private int xp;
    private boolean isOnline;
    private boolean isDead;
    private List<UserAbility> abilities;

    public String getFacebookToken() {
        return facebookToken;
    }

    public void setFacebookToken(String facebookToken) {
        this.facebookToken = facebookToken;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public List<UserAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<UserAbility> abilities) {
        this.abilities = abilities;
    }
}
