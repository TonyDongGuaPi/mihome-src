package com.tiqiaa.remote.entity;

public enum AirPowerSaving {
    POWER_SAVING_OFF(0),
    POWER_SAVING_ON(1);
    
    private final int value;

    private AirPowerSaving(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirPowerSaving getPowerSavingState(int i) {
        switch (i) {
            case 0:
                return POWER_SAVING_OFF;
            case 1:
                return POWER_SAVING_ON;
            default:
                return POWER_SAVING_OFF;
        }
    }
}
