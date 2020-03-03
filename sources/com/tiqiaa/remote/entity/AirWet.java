package com.tiqiaa.remote.entity;

public enum AirWet {
    WET_OFF(0),
    WET_ON(1);
    
    private final int value;

    private AirWet(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirWet getWetState(int i) {
        switch (i) {
            case 0:
                return WET_OFF;
            case 1:
                return WET_ON;
            default:
                return WET_OFF;
        }
    }
}
