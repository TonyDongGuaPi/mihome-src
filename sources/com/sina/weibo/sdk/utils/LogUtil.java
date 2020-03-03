package com.sina.weibo.sdk.utils;

import android.util.Log;
import com.taobao.weex.el.parse.Operators;

public class LogUtil {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f8845a = false;

    public static void a() {
        f8845a = true;
    }

    public static void b() {
        f8845a = false;
    }

    public static void a(String str, String str2) {
        if (f8845a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.d(str, (stackTraceElement.getFileName() + Operators.BRACKET_START_STR + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()) + ": " + str2);
        }
    }

    public static void b(String str, String str2) {
        if (f8845a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.i(str, (stackTraceElement.getFileName() + Operators.BRACKET_START_STR + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()) + ": " + str2);
        }
    }

    public static void c(String str, String str2) {
        if (f8845a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.e(str, (stackTraceElement.getFileName() + Operators.BRACKET_START_STR + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()) + ": " + str2);
        }
    }

    public static void d(String str, String str2) {
        if (f8845a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.w(str, (stackTraceElement.getFileName() + Operators.BRACKET_START_STR + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()) + ": " + str2);
        }
    }

    public static void e(String str, String str2) {
        if (f8845a) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            Log.v(str, (stackTraceElement.getFileName() + Operators.BRACKET_START_STR + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName()) + ": " + str2);
        }
    }

    public static String c() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        return stackTraceElement.getFileName() + Operators.BRACKET_START_STR + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
    }
}
