package com.libra;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

public class Log {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6237a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 6;
    public static final int f = 7;
    public static final int g = 0;
    public static final int h = 1;
    public static final int i = 2;
    public static final int j = 3;
    public static final int k = 4;

    private Log() {
    }

    public static int a(String str, String str2) {
        return a(0, 2, str, str2);
    }

    public static int a(String str, String str2, Throwable th) {
        return a(0, 2, str, str2 + 10 + a(th));
    }

    public static int b(String str, String str2) {
        return a(0, 3, str, str2);
    }

    public static int b(String str, String str2, Throwable th) {
        return a(0, 3, str, str2 + 10 + a(th));
    }

    public static int c(String str, String str2) {
        return a(0, 4, str, str2);
    }

    public static int c(String str, String str2, Throwable th) {
        return a(0, 4, str, str2 + 10 + a(th));
    }

    public static int d(String str, String str2) {
        return a(0, 5, str, str2);
    }

    public static int d(String str, String str2, Throwable th) {
        return a(0, 5, str, str2 + 10 + a(th));
    }

    public static int a(String str, Throwable th) {
        return a(0, 5, str, a(th));
    }

    public static int e(String str, String str2) {
        return a(0, 6, str, str2);
    }

    public static int e(String str, String str2, Throwable th) {
        return a(0, 6, str, str2 + 10 + a(th));
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static int a(int i2, String str, String str2) {
        return a(0, i2, str, str2);
    }

    public static int a(int i2, int i3, String str, String str2) {
        System.out.println(str2);
        return 0;
    }
}
