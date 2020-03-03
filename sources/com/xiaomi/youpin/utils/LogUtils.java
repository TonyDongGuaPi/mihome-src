package com.xiaomi.youpin.utils;

import android.util.Log;

public class LogUtils {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f23774a = false;

    public static int a(String str, String str2) {
        if (f23774a) {
            return Log.v(str, str2);
        }
        return 0;
    }

    public static int a(String str, String str2, Throwable th) {
        if (f23774a) {
            return Log.v(str, str2, th);
        }
        return 0;
    }

    public static int b(String str, String str2) {
        if (f23774a) {
            return Log.d(str, str2);
        }
        return 0;
    }

    public static int b(String str, String str2, Throwable th) {
        if (f23774a) {
            return Log.d(str, str2, th);
        }
        return 0;
    }

    public static int c(String str, String str2) {
        if (f23774a) {
            return Log.i(str, str2);
        }
        return 0;
    }

    public static int c(String str, String str2, Throwable th) {
        if (f23774a) {
            return Log.i(str, str2, th);
        }
        return 0;
    }

    public static int d(String str, String str2) {
        if (f23774a) {
            return Log.w(str, str2);
        }
        return 0;
    }

    public static int d(String str, String str2, Throwable th) {
        if (f23774a) {
            return Log.w(str, str2, th);
        }
        return 0;
    }

    public static int a(String str, Throwable th) {
        if (f23774a) {
            return Log.w(str, th);
        }
        return 0;
    }

    public static int e(String str, String str2) {
        return Log.e(str, str2);
    }

    public static int e(String str, String str2, Throwable th) {
        return Log.e(str, str2, th);
    }
}
