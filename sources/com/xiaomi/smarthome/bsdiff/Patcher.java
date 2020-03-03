package com.xiaomi.smarthome.bsdiff;

public class Patcher {
    public native int patch(String str, String str2, String str3);

    static {
        System.loadLibrary("miot_patch");
    }

    private Patcher() {
    }

    public static int applyPatch(String str, String str2, String str3) {
        return new Patcher().patch(str, str2, str3);
    }
}
