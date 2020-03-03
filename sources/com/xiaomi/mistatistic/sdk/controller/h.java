package com.xiaomi.mistatistic.sdk.controller;

import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

public class h {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f12032a = false;

    public static void a() {
        f12032a = true;
    }

    private static void a(int i, Throwable th, String str, String str2, Object... objArr) {
        String e = e(str);
        String a2 = a(str2, objArr);
        switch (i) {
            case 0:
                Log.e(e, a2, th);
                return;
            case 1:
                Log.w(e, a2, th);
                return;
            case 2:
                Log.i(e, a2, th);
                return;
            case 3:
                Log.d(e, a2, th);
                return;
            case 4:
                Log.v(e, a2, th);
                return;
            default:
                return;
        }
    }

    private static void a(int i, Throwable th, String str, String str2) {
        String e = e(str);
        switch (i) {
            case 0:
                Log.e(e, str2, th);
                return;
            case 1:
                Log.w(e, str2, th);
                return;
            case 2:
                Log.i(e, str2, th);
                return;
            case 3:
                Log.d(e, str2, th);
                return;
            case 4:
                Log.v(e, str2, th);
                return;
            default:
                return;
        }
    }

    private static String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "MI_STAT";
        }
        return "MI_STAT_" + str;
    }

    private static String a(String str, Object... objArr) {
        try {
            return String.format(Locale.getDefault(), str, objArr);
        } catch (Exception e) {
            a("log getMessage exception :", (Throwable) e);
            return null;
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        if (f12032a) {
            a(4, (Throwable) null, str, str2, objArr);
        }
    }

    public static void a(String str, String str2) {
        if (f12032a) {
            a(4, (Throwable) null, str, str2);
        }
    }

    public static void a(String str) {
        if (f12032a) {
            a(4, (Throwable) null, (String) null, str);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f12032a) {
            a(0, th, str, str2);
        }
    }

    public static void a(String str, Throwable th) {
        if (f12032a) {
            a(0, th, (String) null, str);
        }
    }

    public static void b(String str, String str2, Object... objArr) {
        if (f12032a) {
            a(3, (Throwable) null, str, str2, objArr);
        }
    }

    public static void b(String str, String str2) {
        if (f12032a) {
            a(3, (Throwable) null, str, str2);
        }
    }

    public static void b(String str) {
        if (f12032a) {
            a(3, (Throwable) null, (String) null, str);
        }
    }

    public static void c(String str, String str2) {
        if (f12032a) {
            a(2, (Throwable) null, str, str2);
        }
    }

    public static void c(String str) {
        if (f12032a) {
            a(2, (Throwable) null, (String) null, str);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (f12032a) {
            a(1, th, str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (f12032a) {
            a(1, (Throwable) null, (String) null, str2);
        }
    }

    public static void d(String str) {
        if (f12032a) {
            a(1, (Throwable) null, (String) null, str);
        }
    }
}
