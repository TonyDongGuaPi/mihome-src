package com.tiqiaa.remote.entity;

public enum AirLight {
    LIGHT_OFF(0),
    LIGHT_ON(1);
    
    private final int value;

    private AirLight(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirLight getLightState(int i) {
        switch (i) {
            case 0:
                return LIGHT_OFF;
            case 1:
                return LIGHT_ON;
            default:
                return LIGHT_OFF;
        }
    }
}
