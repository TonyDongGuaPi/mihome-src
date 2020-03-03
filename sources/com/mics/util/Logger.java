package com.mics.util;

import android.util.Log;
import com.taobao.weex.el.parse.Operators;

public class Logger {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7787a = "Logger";
    private static boolean b = false;
    private static int c = 3;

    public static void a(int i) {
        c = i;
    }

    public static void a(boolean z) {
        b = z;
    }

    public static void a(String str, Object... objArr) {
        if (b || c <= 3) {
            Log.d(f7787a, a(e(str, objArr)));
        }
    }

    public static void b(String str, Object... objArr) {
        if (b || c <= 4) {
            Log.i(f7787a, a(e(str, objArr)));
        }
    }

    public static void c(String str, Object... objArr) {
        if (b || c <= 5) {
            Log.w(f7787a, a(e(str, objArr)));
        }
    }

    public static void d(String str, Object... objArr) {
        if (b || c <= 6) {
            Log.e(f7787a, a(e(str, objArr)));
        }
    }

    private static String e(String str, Object... objArr) {
        try {
            return String.format(str, objArr);
        } catch (Exception e) {
            d(e.getMessage(), new Object[0]);
            return null;
        }
    }

    private static String a(String str) {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        return Operators.BRACKET_START_STR + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ") " + str;
    }
}
