package com.tiqiaa.remote.entity;

public enum AirPower {
    POWER_OFF(0),
    POWER_ON(1),
    WORK(2);
    
    private final int value;

    private AirPower(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirPower getPowerState(int i) {
        switch (i) {
            case 0:
                return POWER_OFF;
            case 1:
                return POWER_ON;
            case 2:
                return WORK;
            default:
                return POWER_OFF;
        }
    }
}
