package com.tiqiaa.remote.entity;

public enum AirWindHoz {
    HOZ_OFF(0),
    HOZ_ON(1);
    
    private final int value;

    private AirWindHoz(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirWindHoz getWindHoz(int i) {
        switch (i) {
            case 0:
                return HOZ_OFF;
            case 1:
                return HOZ_ON;
            default:
                return HOZ_OFF;
        }
    }
}
