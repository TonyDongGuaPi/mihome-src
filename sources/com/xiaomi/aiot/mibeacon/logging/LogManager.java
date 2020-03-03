package com.xiaomi.aiot.mibeacon.logging;

public class LogManager {

    /* renamed from: a  reason: collision with root package name */
    private static Logger f9980a = Loggers.c();
    private static boolean b = false;

    public static boolean a() {
        return b;
    }

    public static void a(Logger logger) {
        if (logger != null) {
            f9980a = logger;
            return;
        }
        throw new NullPointerException("Logger may not be null.");
    }

    public static Logger b() {
        return f9980a;
    }

    public static void a(boolean z) {
        b = z;
    }

    public static void a(String str, String str2, Object... objArr) {
        f9980a.a(str, str2, objArr);
    }

    public static void a(Throwable th, String str, String str2, Object... objArr) {
        f9980a.a(th, str, str2, objArr);
    }

    public static void b(String str, String str2, Object... objArr) {
        f9980a.b(str, str2, objArr);
    }

    public static void b(Throwable th, String str, String str2, Object... objArr) {
        f9980a.b(th, str, str2, objArr);
    }

    public static void c(String str, String str2, Object... objArr) {
        f9980a.c(str, str2, objArr);
    }

    public static void c(Throwable th, String str, String str2, Object... objArr) {
        f9980a.c(th, str, str2, objArr);
    }

    public static void d(String str, String str2, Object... objArr) {
        f9980a.d(str, str2, objArr);
    }

    public static void d(Throwable th, String str, String str2, Object... objArr) {
        f9980a.d(th, str, str2, objArr);
    }

    public static void e(String str, String str2, Object... objArr) {
        f9980a.e(str, str2, objArr);
    }

    public static void e(Throwable th, String str, String str2, Object... objArr) {
        f9980a.e(th, str, str2, objArr);
    }

    private LogManager() {
    }
}
