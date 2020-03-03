package com.tiqiaa.remote.entity;

public enum AirTime {
    TIME_OFF(0),
    TIME_ON(1);
    
    private final int value;

    private AirTime(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirTime getTimeState(int i) {
        switch (i) {
            case 0:
                return TIME_OFF;
            case 1:
                return TIME_ON;
            default:
                return TIME_OFF;
        }
    }
}
