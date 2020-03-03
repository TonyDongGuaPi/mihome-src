package com.xiaomi.phonenum.obtain;

public enum PhoneLevel {
    LINE_NUMBER(10, "line1Number"),
    CACHE(30, "cache"),
    DATA(50, "data"),
    SMS_VERIFY(90, "");
    
    public String param;
    public final int value;

    private PhoneLevel(int i, String str) {
        this.value = i;
        this.param = str;
    }
}
