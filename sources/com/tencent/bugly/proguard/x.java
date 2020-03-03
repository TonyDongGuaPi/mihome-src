package com.tencent.bugly.proguard;

import android.util.Log;
import java.util.Locale;

public final class x {

    /* renamed from: a  reason: collision with root package name */
    public static String f9062a = "CrashReport";
    public static boolean b = false;
    private static String c = "CrashReportInfo";

    private static boolean a(int i, String str, Object... objArr) {
        if (!b) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (!(objArr == null || objArr.length == 0)) {
            str = String.format(Locale.US, str, objArr);
        }
        if (i != 5) {
            switch (i) {
                case 0:
                    Log.i(f9062a, str);
                    return true;
                case 1:
                    Log.d(f9062a, str);
                    return true;
                case 2:
                    Log.w(f9062a, str);
                    return true;
                case 3:
                    Log.e(f9062a, str);
                    return true;
                default:
                    return false;
            }
        } else {
            Log.i(c, str);
            return true;
        }
    }

    public static boolean a(String str, Object... objArr) {
        return a(0, str, objArr);
    }

    public static boolean a(Class cls, String str, Object... objArr) {
        return a(0, String.format(Locale.US, "[%s] %s", new Object[]{cls.getSimpleName(), str}), objArr);
    }

    public static boolean b(String str, Object... objArr) {
        return a(5, str, objArr);
    }

    public static boolean c(String str, Object... objArr) {
        return a(1, str, objArr);
    }

    public static boolean b(Class cls, String str, Object... objArr) {
        return a(1, String.format(Locale.US, "[%s] %s", new Object[]{cls.getSimpleName(), str}), objArr);
    }

    public static boolean d(String str, Object... objArr) {
        return a(2, str, objArr);
    }

    public static boolean a(Throwable th) {
        if (!b) {
            return false;
        }
        return a(2, z.a(th), new Object[0]);
    }

    public static boolean e(String str, Object... objArr) {
        return a(3, str, objArr);
    }

    public static boolean b(Throwable th) {
        if (!b) {
            return false;
        }
        return a(3, z.a(th), new Object[0]);
    }
}
