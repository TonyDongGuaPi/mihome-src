package com.xiaomi.market.sdk;

import android.text.TextUtils;

public class Log {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f11110a = Utils.b;
    private static final int b = 3000;
    private static final String c = "UpdateCheck-";
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 4;

    public static void a(String str, String str2) {
        if (f11110a) {
            a(a(str), str2, 3);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f11110a) {
            a(a(str), str2, th, 3);
        }
    }

    public static void b(String str, String str2) {
        a(a(str), str2, 0);
    }

    public static void b(String str, String str2, Throwable th) {
        a(a(str), str2, th, 0);
    }

    public static void c(String str, String str2) {
        a(a(str), str2, 1);
    }

    public static void c(String str, String str2, Throwable th) {
        a(a(str), str2, th, 1);
    }

    public static void d(String str, String str2) {
        a(a(str), str2, 2);
    }

    public static void d(String str, String str2, Throwable th) {
        a(a(str), str2, th, 2);
    }

    public static void e(String str, String str2) {
        if (f11110a) {
            a(a(str), str2, 4);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (f11110a) {
            a(a(str), str2, th, 4);
        }
    }

    private static void a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str2) || str2.length() <= 3000) {
            b(str, str2, i);
            return;
        }
        int i2 = 0;
        while (i2 <= str2.length() / 3000) {
            int i3 = i2 * 3000;
            i2++;
            int min = Math.min(str2.length(), i2 * 3000);
            if (i3 < min) {
                b(str, str2.substring(i3, min), i);
            }
        }
    }

    private static void a(String str, String str2, Throwable th, int i) {
        if (TextUtils.isEmpty(str2) || str2.length() <= 3000) {
            b(str, str2, th, i);
            return;
        }
        int i2 = 0;
        while (i2 <= str2.length() / 3000) {
            int i3 = i2 * 3000;
            i2++;
            int min = Math.min(str2.length(), i2 * 3000);
            if (i3 < min) {
                b(str, str2.substring(i3, min), th, i);
            }
        }
    }

    private static void b(String str, String str2, int i) {
        if (str2 == null) {
            str2 = "";
        }
        switch (i) {
            case 0:
                android.util.Log.e(str, str2);
                return;
            case 1:
                android.util.Log.w(str, str2);
                return;
            case 2:
                android.util.Log.i(str, str2);
                return;
            case 3:
                android.util.Log.d(str, str2);
                return;
            case 4:
                android.util.Log.v(str, str2);
                return;
            default:
                return;
        }
    }

    private static void b(String str, String str2, Throwable th, int i) {
        if (str2 == null) {
            str2 = "";
        }
        switch (i) {
            case 0:
                android.util.Log.e(str, str2, th);
                return;
            case 1:
                android.util.Log.w(str, str2, th);
                return;
            case 2:
                android.util.Log.i(str, str2, th);
                return;
            case 3:
                android.util.Log.d(str, str2, th);
                return;
            case 4:
                android.util.Log.v(str, str2, th);
                return;
            default:
                return;
        }
    }

    public static String a(String str) {
        return c + str;
    }
}
