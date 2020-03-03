package com.tiqiaa.remote.entity;

public enum AirTempDisplay {
    DISPLAY_OUTDOOR_TEMP(0),
    DISPLAY_INDOOR_TEMP(1),
    DISPLAY_TARGET_TEMP(2),
    DISPLAY_NONE(3);
    
    private final int value;

    private AirTempDisplay(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirTempDisplay getTempDisplay(int i) {
        switch (i) {
            case 0:
                return DISPLAY_OUTDOOR_TEMP;
            case 1:
                return DISPLAY_INDOOR_TEMP;
            case 2:
                return DISPLAY_TARGET_TEMP;
            default:
                return DISPLAY_NONE;
        }
    }
}
