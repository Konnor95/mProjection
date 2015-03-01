package com.mprojection.entity;

public enum UserType {

    SOLDIER(50, 100),
    SCIENTIST(25, 100),
    ZOMBIE(100, 100);

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
}
