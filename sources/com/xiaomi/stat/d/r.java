package com.xiaomi.stat.d;

import android.content.Context;
import android.os.SystemClock;
import com.xiaomi.stat.ak;
import com.xiaomi.stat.b.e;
import java.util.Calendar;

public class r {

    /* renamed from: a  reason: collision with root package name */
    public static final long f23065a = 86400000;
    private static final String b = "TimeUtil";
    private static final long c = 300000;
    private static long d;
    private static long e;
    private static long f;

    public static void a() {
        boolean z;
        Context a2 = ak.a();
        long f2 = p.f(a2);
        long g = p.g(a2);
        long h = p.h(a2);
        if (f2 == 0 || g == 0 || h == 0 || Math.abs((System.currentTimeMillis() - g) - (SystemClock.elapsedRealtime() - h)) > 300000) {
            z = true;
        } else {
            d = f2;
            f = g;
            e = h;
            z = false;
        }
        if (z) {
            e.a().execute(new s());
        }
        k.b(b, "syncTimeIfNeeded sync: " + z);
    }

    public static long b() {
        if (d == 0 || e == 0) {
            return System.currentTimeMillis();
        }
        return (d + SystemClock.elapsedRealtime()) - e;
    }

    public static void a(long j) {
        if (j > 0) {
            k.b("MI_STAT_TEST", "update server time:" + j);
            d = j;
            e = SystemClock.elapsedRealtime();
            f = System.currentTimeMillis();
            Context a2 = ak.a();
            p.a(a2, d);
            p.b(a2, f);
            p.c(a2, e);
        }
    }

    public static boolean a(long j, long j2) {
        return Math.abs(b() - j) >= j2;
    }

    public static boolean b(long j) {
        k.b("MI_STAT_TEST", "inToday,current ts :" + b());
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(b());
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        long j2 = timeInMillis + 86400000;
        k.b("MI_STAT_TEST", "[start]:" + timeInMillis + "\n[end]:" + j2 + "duration" + ((j2 - timeInMillis) - 86400000));
        StringBuilder sb = new StringBuilder();
        sb.append("is in today:");
        sb.append(timeInMillis <= j && j < j2);
        k.b("MI_STAT_TEST", sb.toString());
        if (timeInMillis > j || j >= j2) {
            return false;
        }
        return true;
    }
}
