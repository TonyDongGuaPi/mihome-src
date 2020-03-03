package com.tiqiaa.remote.entity;

public enum AirFlashAir {
    FLASH_OFF(0),
    FLASH_ON(1);
    
    private final int value;

    private AirFlashAir(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirFlashAir getFlashAirState(int i) {
        switch (i) {
            case 0:
                return FLASH_OFF;
            case 1:
                return FLASH_ON;
            default:
                return FLASH_OFF;
        }
    }
}
