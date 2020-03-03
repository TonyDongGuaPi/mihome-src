package com.tiqiaa.remote.entity;

public enum AirWindAmount {
    LEVEL_1(0),
    LEVEL_2(1),
    LEVEL_3(2),
    LEVEL_4(3),
    AUTO(4);
    
    private final int value;

    private AirWindAmount(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirWindAmount getWindAmount(int i) {
        switch (i) {
            case 0:
                return LEVEL_1;
            case 1:
                return LEVEL_2;
            case 2:
                return LEVEL_3;
            case 3:
                return LEVEL_4;
            case 4:
                return AUTO;
            default:
                return LEVEL_1;
        }
    }
}
