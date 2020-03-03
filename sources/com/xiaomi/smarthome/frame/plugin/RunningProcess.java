package com.xiaomi.smarthome.frame.plugin;

import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.CoreManager;

public enum RunningProcess {
    MAIN("main"),
    PLUGIN0("plugin0"),
    PLUGIN1("plugin1"),
    PLUGIN2("plugin2"),
    PLUGIN3("plugin3"),
    FRAME1("frame1"),
    FRAME2("frame2");
    
    private long mTimeStamp;
    private String mValue;

    private RunningProcess(String str) {
        this.mValue = str;
        this.mTimeStamp = System.currentTimeMillis();
    }

    public static RunningProcess getByProcessName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.endsWith(CoreManager.j)) {
            return PLUGIN0;
        }
        if (str.endsWith(CoreManager.k)) {
            return PLUGIN1;
        }
        if (str.endsWith(CoreManager.l)) {
            return PLUGIN2;
        }
        if (str.endsWith(CoreManager.m)) {
            return PLUGIN3;
        }
        if (str.equalsIgnoreCase("com.xiaomi.smarthome")) {
            return MAIN;
        }
        if (str.equalsIgnoreCase(CoreManager.n)) {
            return FRAME1;
        }
        if (str.equalsIgnoreCase(CoreManager.o)) {
            return FRAME2;
        }
        return null;
    }

    static RunningProcess[] getFrameProcesses() {
        return new RunningProcess[]{FRAME1, FRAME2};
    }

    public void setTimeStamp(Long l) {
        this.mTimeStamp = l.longValue();
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public static RunningProcess getByProcessValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return MAIN;
        }
        if (str.equals(PLUGIN0.getValue())) {
            return PLUGIN0;
        }
        if (str.equals(PLUGIN1.getValue())) {
            return PLUGIN1;
        }
        if (str.equals(PLUGIN2.getValue())) {
            return PLUGIN2;
        }
        if (str.equals(PLUGIN3.getValue())) {
            return PLUGIN3;
        }
        if (str.equals(FRAME1.getValue())) {
            return FRAME1;
        }
        if (str.equals(FRAME2.getValue())) {
            return FRAME2;
        }
        if (str.equals(MAIN.getValue())) {
            return MAIN;
        }
        return MAIN;
    }

    public String getValue() {
        return this.mValue;
    }
}
