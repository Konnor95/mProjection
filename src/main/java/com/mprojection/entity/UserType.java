package com.mprojection.entity;

public enum UserType {

    SOLDIER(50),
    SCIENTIST(25),
    ZOMBIE(100);

    private int defaultVisibility;

    UserType(int defaultVisibility) {
        this.defaultVisibility = defaultVisibility;
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
}
