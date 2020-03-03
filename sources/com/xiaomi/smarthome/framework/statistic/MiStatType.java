package com.xiaomi.smarthome.framework.statistic;

public enum MiStatType {
    CLICK("click");
    
    private String mValue;

    private MiStatType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}
