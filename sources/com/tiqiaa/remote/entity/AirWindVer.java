package com.tiqiaa.remote.entity;

public enum AirWindVer {
    VER_OFF(0),
    VER_ON(1);
    
    private final int value;

    private AirWindVer(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirWindVer getWindVer(int i) {
        switch (i) {
            case 0:
                return VER_OFF;
            case 1:
                return VER_ON;
            default:
                return VER_OFF;
        }
    }
}
