package com.xiaomi.youpin.hawkeye.utils;

import com.xiaomi.youpin.hawkeye.HawkEye;

public class HLog {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1581a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 6;
    public static final int f = 7;
    private static ILoggingDelegate g = HawkEye.c().e();

    public static void a(ILoggingDelegate iLoggingDelegate) {
        g = iLoggingDelegate;
    }

    public static boolean a(int i) {
        return g.b(i);
    }

    public static void b(int i) {
        g.a(i);
    }

    public static int a() {
        return g.a();
    }

    public static void a(String str, String str2) {
        if (g != null && g.b(2)) {
            g.a(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (g != null && g.b(3)) {
            g.b(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (g != null && g.b(4)) {
            g.c(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (g != null && g.b(5)) {
            g.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (g != null && g.b(6)) {
            g.e(str, str2);
        }
    }
}
