package com.libra.sinvoice;

import android.util.Log;

public class LogHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6246a = "SinVoice";

    public static final int a(String str, String str2, String str3) {
        return Log.d(String.format("%s %s %s", new Object[]{"SinVoice", str, str2}), str3);
    }

    public static final int a(String str, String str2) {
        return a(str, "", a(str2));
    }

    public static final int b(String str, String str2, String str3) {
        return Log.i(String.format("%s %s %s", new Object[]{"SinVoice", str, str2}), str3);
    }

    public static final int b(String str, String str2) {
        return b(str, "", a(str2));
    }

    public static final int c(String str, String str2, String str3) {
        return Log.e(String.format("%s %s %s", new Object[]{"SinVoice", str, str2}), str3);
    }

    public static final int c(String str, String str2) {
        return c(str, "", a(str2));
    }

    public static final int d(String str, String str2, String str3) {
        return Log.v(String.format("%s %s %s", new Object[]{"SinVoice", str, str2}), str3);
    }

    public static final int d(String str, String str2) {
        return d(str, "", a(str2));
    }

    private static final String a(String str) {
        StackTraceElement stackTraceElement = new Exception().getStackTrace()[2];
        return String.format("File:%s, Function:%s, Line:%d, ThreadId:%d, %s", new Object[]{stackTraceElement.getFileName(), stackTraceElement.getMethodName(), Integer.valueOf(stackTraceElement.getLineNumber()), Long.valueOf(Thread.currentThread().getId()), str});
    }
}
