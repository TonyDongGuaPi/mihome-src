package com.taobao.weex.utils;

import com.payu.custombrowser.util.CBConstant;

public enum LogLevel {
    WTF("wtf", 0, 7),
    ERROR("error", 1, 6),
    WARN(CBConstant.WARN, 2, 5),
    INFO("info", 3, 4),
    DEBUG("debug", 4, 3),
    VERBOSE("verbose", 5, 2),
    ALL("debug", 6, 3),
    OFF("off", 7, 3);
    
    String name;
    int priority;
    int value;

    static {
        boolean[] $jacocoInit;
        $jacocoInit[8] = true;
    }

    private LogLevel(String str, int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.name = str;
        this.value = i;
        this.priority = i2;
        $jacocoInit[2] = true;
    }

    public String getName() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.name;
        $jacocoInit[3] = true;
        return str;
    }

    public int getValue() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.value;
        $jacocoInit[4] = true;
        return i;
    }

    public int getPriority() {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.priority;
        $jacocoInit[5] = true;
        return i;
    }

    public int compare(LogLevel logLevel) {
        boolean[] $jacocoInit = $jacocoInit();
        int i = this.value - logLevel.value;
        $jacocoInit[6] = true;
        return i;
    }
}
