package org.xutils.common.util;

import android.text.TextUtils;
import android.util.Log;
import org.xutils.x;

public class LogUtil {

    /* renamed from: a  reason: collision with root package name */
    public static String f4234a = "x_log";

    private LogUtil() {
    }

    private static String a() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[2];
        String className = stackTraceElement.getClassName();
        String format = String.format("%s.%s(L:%d)", new Object[]{className.substring(className.lastIndexOf(".") + 1), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber())});
        if (TextUtils.isEmpty(f4234a)) {
            return format;
        }
        return f4234a + ":" + format;
    }

    public static void a(String str) {
        if (x.a()) {
            Log.d(a(), str);
        }
    }

    public static void a(String str, Throwable th) {
        if (x.a()) {
            Log.d(a(), str, th);
        }
    }

    public static void b(String str) {
        if (x.a()) {
            Log.e(a(), str);
        }
    }

    public static void b(String str, Throwable th) {
        if (x.a()) {
            Log.e(a(), str, th);
        }
    }

    public static void c(String str) {
        if (x.a()) {
            Log.i(a(), str);
        }
    }

    public static void c(String str, Throwable th) {
        if (x.a()) {
            Log.i(a(), str, th);
        }
    }

    public static void d(String str) {
        if (x.a()) {
            Log.v(a(), str);
        }
    }

    public static void d(String str, Throwable th) {
        if (x.a()) {
            Log.v(a(), str, th);
        }
    }

    public static void e(String str) {
        if (x.a()) {
            Log.w(a(), str);
        }
    }

    public static void e(String str, Throwable th) {
        if (x.a()) {
            Log.w(a(), str, th);
        }
    }

    public static void a(Throwable th) {
        if (x.a()) {
            Log.w(a(), th);
        }
    }

    public static void f(String str) {
        if (x.a()) {
            Log.wtf(a(), str);
        }
    }

    public static void f(String str, Throwable th) {
        if (x.a()) {
            Log.wtf(a(), str, th);
        }
    }

    public static void b(Throwable th) {
        if (x.a()) {
            Log.wtf(a(), th);
        }
    }
}
