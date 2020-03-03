package com.tiqiaa.remote.entity;

public enum AirSuper {
    SUPER_OFF(0),
    SUPER_ON(1);
    
    private final int value;

    private AirSuper(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirSuper getSuperState(int i) {
        switch (i) {
            case 0:
                return SUPER_OFF;
            case 1:
                return SUPER_ON;
            default:
                return SUPER_OFF;
        }
    }
}
