package com.tiqiaa.remote.entity;

public enum AirWindDirection {
    AUTO(0),
    UP(1),
    MIDDLE(2),
    DOWN(3);
    
    private final int value;

    private AirWindDirection(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirWindDirection getWindDirection(int i) {
        switch (i) {
            case 0:
                return AUTO;
            case 1:
                return UP;
            case 2:
                return MIDDLE;
            case 3:
                return DOWN;
            default:
                return AUTO;
        }
    }
}
