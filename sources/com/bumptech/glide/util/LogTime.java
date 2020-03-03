package com.bumptech.glide.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.SystemClock;

public final class LogTime {

    /* renamed from: a  reason: collision with root package name */
    private static final double f5101a;

    static {
        double d = 1.0d;
        if (Build.VERSION.SDK_INT >= 17) {
            d = 1.0d / Math.pow(10.0d, 6.0d);
        }
        f5101a = d;
    }

    private LogTime() {
    }

    @TargetApi(17)
    public static long a() {
        if (Build.VERSION.SDK_INT >= 17) {
            return SystemClock.elapsedRealtimeNanos();
        }
        return SystemClock.uptimeMillis();
    }

    public static double a(long j) {
        double a2 = (double) (a() - j);
        double d = f5101a;
        Double.isNaN(a2);
        return a2 * d;
    }
}
