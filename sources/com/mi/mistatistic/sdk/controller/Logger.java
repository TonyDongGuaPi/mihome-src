package com.mi.mistatistic.sdk.controller;

import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

public class Logger {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7332a = "MI_STAT";
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static boolean g = false;

    public static void a() {
        g = true;
    }

    private static void a(int i, Throwable th, String str, String str2, Object... objArr) {
        String e2 = e(str);
        String a2 = a(str2, objArr);
        switch (i) {
            case 0:
                Log.e(e2, a2, th);
                return;
            case 1:
                Log.w(e2, a2, th);
                return;
            case 2:
                Log.i(e2, a2, th);
                return;
            case 3:
                Log.d(e2, a2, th);
                return;
            case 4:
                Log.v(e2, a2, th);
                return;
            default:
                return;
        }
    }

    private static void a(int i, Throwable th, String str, String str2) {
        String e2 = e(str);
        switch (i) {
            case 0:
                Log.e(e2, str2, th);
                return;
            case 1:
                Log.w(e2, str2, th);
                return;
            case 2:
                Log.i(e2, str2, th);
                return;
            case 3:
                Log.d(e2, str2, th);
                return;
            case 4:
                Log.v(e2, str2, th);
                return;
            default:
                return;
        }
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return f7332a;
        }
        return "MI_STAT_" + str;
    }

    private static String a(String str, Object... objArr) {
        try {
            return String.format(Locale.getDefault(), str, objArr);
        } catch (Exception e2) {
            a("log getMessage exception :", (Throwable) e2);
            return null;
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        if (g) {
            a(4, (Throwable) null, str, str2, objArr);
        }
    }

    public static void a(String str, String str2) {
        if (g) {
            a(4, (Throwable) null, str, str2);
        }
    }

    public static void a(String str) {
        if (g) {
            a(4, (Throwable) null, (String) null, str);
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        if (g) {
            a(0, (Throwable) null, str, str2, objArr);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (g) {
            a(0, th, str, str2);
        }
    }

    public static void a(String str, Throwable th) {
        if (g) {
            a(0, th, (String) null, str);
        }
    }

    public static void c(String str, String str2, Object... objArr) {
        if (g) {
            a(3, (Throwable) null, str, str2, objArr);
        }
    }

    public static void b(String str, String str2) {
        if (g) {
            a(3, (Throwable) null, str, str2);
        }
    }

    public static void b(String str) {
        if (g) {
            a(3, (Throwable) null, (String) null, str);
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (g) {
            a(2, (Throwable) null, str, str2, objArr);
        }
    }

    public static void c(String str, String str2) {
        if (g) {
            a(2, (Throwable) null, str, str2);
        }
    }

    public static void c(String str) {
        if (g) {
            a(2, (Throwable) null, (String) null, str);
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (g) {
            a(1, (Throwable) null, str, str2, objArr);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (g) {
            a(1, th, str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (g) {
            a(1, (Throwable) null, (String) null, str2);
        }
    }

    public static void d(String str) {
        if (g) {
            a(1, (Throwable) null, (String) null, str);
        }
    }
}
