package com.paytm.pgsdk;

public class Log {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f8533a = false;

    public static void a(String str, String str2) {
        android.util.Log.i(str, str2);
    }

    public static void b(String str, String str2) {
        if (f8533a) {
            android.util.Log.e(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f8533a) {
            android.util.Log.e(str, str2, th);
        }
    }

    public static void c(String str, String str2) {
        if (f8533a) {
            android.util.Log.d(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (f8533a) {
            android.util.Log.d(str, str2, th);
        }
    }

    public static void d(String str, String str2) {
        if (f8533a) {
            android.util.Log.v(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (f8533a) {
            android.util.Log.w(str, str2);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (f8533a) {
            android.util.Log.w(str, str2, th);
        }
    }

    public static void a(boolean z) {
        f8533a = z;
    }
}
