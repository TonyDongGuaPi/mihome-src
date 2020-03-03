package com.xiaomi.smarthome.library.common.statcode;

import com.hannto.printservice.hanntoprintservice.HanntoPrinter;

public class ApErrorCode {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18646a = 1101;
    public static final int b = 1401;
    public static final int c = 1201;
    public static final int d = 1202;
    public static final int e = 1203;
    public static final int f = 7;
    public static final int g = 3;
    public static final int h = 35;
    public static final int i = 3100;
    public static final int j = 3101;
    public static final int k = 3102;
    public static final int l = 3103;
    private static final int m = 0;
    private static final int n = 1;
    private static final int o = 3;

    public static int a(int i2) {
        if (i2 == 0) {
            return 1100;
        }
        if (i2 == 1) {
            return 1400;
        }
        return i2 == 3 ? 1200 : 1900;
    }

    public static boolean a(int i2, int i3) {
        return (i3 == 0 && i2 > 7) || (i3 == 1 && i2 > 3) || (i3 == 3 && i2 > 35);
    }

    public static boolean a(String str) {
        return "chuangmi.camera.ipc009".equalsIgnoreCase(str) || HanntoPrinter.c.equalsIgnoreCase(str) || "xiaomi.repeater.v3".equalsIgnoreCase(str);
    }
}
