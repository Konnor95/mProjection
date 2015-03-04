package com.mprojection.entity;

public class AttackInfo {

    private PublicUserInfo victim;
    private PublicUserInfo attacker;

    public AttackInfo(PublicUserInfo victim, PublicUserInfo attacker) {
        this.victim = victim;
        this.attacker = attacker;
    }

    public PublicUserInfo getVictim() {
        return victim;
    }

    public void setVictim(PublicUserInfo victim) {
        this.victim = victim;
    }

    public PublicUserInfo getAttacker() {
        return attacker;
    }

    public void setAttacker(PublicUserInfo attacker) {
        this.attacker = attacker;
    }

}
