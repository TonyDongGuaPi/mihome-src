package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.content.SharedPreferences;

class PageLog {
    private static String f = "session";
    private static long g = 1000;

    /* renamed from: a  reason: collision with root package name */
    protected LogType f8833a;
    protected String b;
    protected long c;
    private long d;
    private long e;

    public PageLog(Context context) {
        this.c = a(context, LogBuilder.i);
        this.d = a(context, LogBuilder.j);
        this.e = this.d - this.c;
    }

    public PageLog(String str) {
        this.b = str;
        this.c = System.currentTimeMillis();
    }

    public PageLog(Context context, long j) {
        this.c = j;
        this.d = g;
        a(context, (String) null, Long.valueOf(this.c), Long.valueOf(this.d));
    }

    public PageLog() {
    }

    public PageLog(String str, long j) {
        this.b = str;
        this.c = j;
    }

    public LogType h() {
        return this.f8833a;
    }

    public void a(LogType logType) {
        this.f8833a = logType;
    }

    public String i() {
        return this.b;
    }

    public long j() {
        return this.c;
    }

    public long k() {
        return this.d;
    }

    public void a(long j) {
        this.e = j;
    }

    public long l() {
        return this.e;
    }

    public void b(long j) {
        this.c = j;
    }

    public static boolean a(Context context, long j) {
        long a2 = a(context, LogBuilder.j);
        return a2 > g ? j - a2 > StatisticConfig.c : a2 != g;
    }

    private static long a(Context context, String str) {
        return context.getSharedPreferences(f, 0).getLong(str, 0);
    }

    public static void a(Context context, String str, Long l, Long l2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(f, 0).edit();
        if (l.longValue() != 0) {
            edit.putLong(LogBuilder.i, l.longValue());
        }
        edit.putLong(LogBuilder.j, l2.longValue());
        edit.commit();
    }
}
