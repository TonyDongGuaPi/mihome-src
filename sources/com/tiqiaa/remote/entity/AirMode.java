package com.tiqiaa.remote.entity;

public enum AirMode {
    AUTO(0),
    WIND(1),
    DRY(2),
    HOT(3),
    COOL(4);
    
    private final int value;

    private AirMode(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirMode getMode(int i) {
        switch (i) {
            case 0:
                return AUTO;
            case 1:
                return WIND;
            case 2:
                return DRY;
            case 3:
                return HOT;
            case 4:
                return COOL;
            default:
                return AUTO;
        }
    }
}
