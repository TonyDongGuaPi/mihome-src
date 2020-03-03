package com.tiqiaa.remote.entity;

public enum AirComfort {
    COMFORT_OFF(0),
    COMFORT_ON(1);
    
    private final int value;

    private AirComfort(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirComfort getComfortState(int i) {
        switch (i) {
            case 0:
                return COMFORT_OFF;
            case 1:
                return COMFORT_ON;
            default:
                return COMFORT_OFF;
        }
    }
}
