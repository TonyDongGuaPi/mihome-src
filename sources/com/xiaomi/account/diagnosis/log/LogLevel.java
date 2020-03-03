package com.xiaomi.account.diagnosis.log;

import android.support.media.ExifInterface;
import miuipub.reflect.Field;

public enum LogLevel {
    VERBOSE("V"),
    DEBUG(Field.h),
    INFO(Field.e),
    WARN("W"),
    ERROR(ExifInterface.LONGITUDE_EAST);
    
    private final String mLevelStr;

    private LogLevel(String str) {
        this.mLevelStr = str;
    }

    public static LogLevel fromInt(int i) {
        switch (i) {
            case 2:
                return VERBOSE;
            case 3:
                return DEBUG;
            case 4:
                return INFO;
            case 5:
                return WARN;
            case 6:
                return ERROR;
            default:
                return VERBOSE;
        }
    }

    public String toString() {
        return this.mLevelStr;
    }
}
