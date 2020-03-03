package com.sina.weibo.sdk.statistic;

import android.content.Context;

class StatisticConfig {

    /* renamed from: a  reason: collision with root package name */
    public static final long f8834a = 30000;
    public static boolean b = true;
    public static long c = 30000;
    private static String d = null;
    private static String e = null;
    private static final long f = 90000;
    private static final long g = 28800000;
    private static boolean h = true;
    private static long i = 90000;
    private static long j = 30000;

    StatisticConfig() {
    }

    public static void a(String str) {
        d = str;
    }

    public static void b(String str) {
        e = str;
    }

    public static String a(Context context) {
        if (d == null) {
            d = LogBuilder.a(context);
        }
        return d;
    }

    public static String b(Context context) {
        if (e == null) {
            e = LogBuilder.b(context);
        }
        return e;
    }

    public static long a() {
        return i;
    }

    public static void a(long j2) throws Exception {
        if (j2 < 30000 || j2 > g) {
            throw new Exception("The interval must be between 30 seconds and 8 hours");
        }
        i = j2;
    }

    public static boolean b() {
        return h;
    }

    public static void a(boolean z) {
        h = z;
    }

    public static long c() {
        return j;
    }

    public static void b(long j2) {
        j = j2;
    }
}
