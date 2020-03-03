package com.xiaomi.base.utils;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import java.util.Calendar;

@SuppressLint({"WrongConstant"})
public class ServerTimerUtils {

    /* renamed from: a  reason: collision with root package name */
    private static long f10037a;

    public static int a(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j2);
        return instance.get(6) - instance2.get(6);
    }

    public static long a(long j) {
        return j + (SystemClock.uptimeMillis() - f10037a);
    }

    public static long b(long j, long j2) {
        return j - a(j2);
    }

    public static long b(long j) {
        Calendar instance = Calendar.getInstance();
        instance.set(10, 23);
        instance.set(12, 59);
        instance.set(13, 59);
        instance.set(14, 999);
        return (instance.getTimeInMillis() + 1) - j;
    }

    public static boolean c(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j2);
        if (instance2.get(1) == instance.get(1) && instance2.get(6) - instance.get(6) == 0) {
            return true;
        }
        return false;
    }

    public static void a() {
        f10037a = SystemClock.uptimeMillis();
    }
}
