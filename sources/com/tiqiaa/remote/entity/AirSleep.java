package com.tiqiaa.remote.entity;

public enum AirSleep {
    SLEEP_OFF(0),
    SLEEP_ON(1);
    
    private final int value;

    private AirSleep(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirSleep getSleepState(int i) {
        switch (i) {
            case 0:
                return SLEEP_OFF;
            case 1:
                return SLEEP_ON;
            default:
                return SLEEP_OFF;
        }
    }
}
