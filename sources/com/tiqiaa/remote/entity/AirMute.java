package com.tiqiaa.remote.entity;

public enum AirMute {
    MUTE_OFF(0),
    MUTE_ON(1);
    
    private final int value;

    private AirMute(int i) {
        this.value = i;
    }

    public int value() {
        return this.value;
    }

    public static AirMute getMuteState(int i) {
        switch (i) {
            case 0:
                return MUTE_OFF;
            case 1:
                return MUTE_ON;
            default:
                return MUTE_OFF;
        }
    }
}
