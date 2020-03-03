package com.xiaomi.aiot.mibeacon.logging;

public class Loggers {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f9981a = new EmptyLogger();
    private static final Logger b = new VerboseAndroidLogger();
    private static final Logger c = new InfoAndroidLogger();
    private static final Logger d = new WarningAndroidLogger();

    public static Logger a() {
        return f9981a;
    }

    public static Logger b() {
        return b;
    }

    public static Logger c() {
        return c;
    }

    public static Logger d() {
        return d;
    }

    private Loggers() {
    }
}
