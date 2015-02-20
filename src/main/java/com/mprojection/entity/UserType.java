package com.mprojection.entity;

public enum UserType {
    SOLDIER,
    SCIENTIST,
    ZOMBIE;

    public static UserType define(int type) {
        UserType[] roles = values();
        if (type >= roles.length || type < 0) {
            throw new IllegalArgumentException("Invalid type");
        }
        return roles[type];
    }

    public boolean isHuman() {
        return this == SOLDIER || this == SCIENTIST;
    }

    public boolean isZombie() {
        return this == ZOMBIE;
    }

}
