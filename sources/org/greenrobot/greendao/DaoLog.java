package org.greenrobot.greendao;

import android.util.Log;

public class DaoLog {

    /* renamed from: a  reason: collision with root package name */
    public static final int f3512a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 6;
    public static final int f = 7;
    private static final String g = "greenDAO";

    public static boolean a(int i) {
        return Log.isLoggable(g, i);
    }

    public static String a(Throwable th) {
        return Log.getStackTraceString(th);
    }

    public static int a(int i, String str) {
        return Log.println(i, g, str);
    }

    public static int a(String str) {
        return Log.v(g, str);
    }

    public static int a(String str, Throwable th) {
        return Log.v(g, str, th);
    }

    public static int b(String str) {
        return Log.d(g, str);
    }

    public static int b(String str, Throwable th) {
        return Log.d(g, str, th);
    }

    public static int c(String str) {
        return Log.i(g, str);
    }

    public static int c(String str, Throwable th) {
        return Log.i(g, str, th);
    }

    public static int d(String str) {
        return Log.w(g, str);
    }

    public static int d(String str, Throwable th) {
        return Log.w(g, str, th);
    }

    public static int b(Throwable th) {
        return Log.w(g, th);
    }

    public static int e(String str) {
        return Log.w(g, str);
    }

    public static int e(String str, Throwable th) {
        return Log.e(g, str, th);
    }
}
