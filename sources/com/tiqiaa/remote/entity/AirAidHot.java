package com.tiqiaa.remote.entity;

public enum AirAidHot {
    AIDHOT_OFF(0),
    AIDHOT_ON(1);
    
    private final int value;

    private AirAidHot(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirAidHot getAidHottate(int i) {
        switch (i) {
            case 0:
                return AIDHOT_OFF;
            case 1:
                return AIDHOT_ON;
            default:
                return AIDHOT_OFF;
        }
    }
}
