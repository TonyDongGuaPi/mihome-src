package com.xiaomi.smarthome.newui.utils;

import android.util.Log;

public class NumberUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20744a = "NumberUtils";

    public static double a(Object obj, double d) {
        try {
            if (obj instanceof Number) {
                return ((Number) obj).doubleValue();
            }
            return Double.parseDouble(String.valueOf(obj));
        } catch (Exception unused) {
            Log.e(f20744a, "parseDouble " + obj + " catch exception, return defalut value " + d);
            return d;
        }
    }

    public static long a(Object obj, long j) {
        try {
            if (obj instanceof Number) {
                return ((Number) obj).longValue();
            }
            return Long.parseLong(String.valueOf(obj));
        } catch (Exception unused) {
            Log.e(f20744a, "parseLong " + obj + " catch exception, return defalut value " + j);
            return j;
        }
    }

    public static int a(Object obj, int i) {
        return a(obj, 10, i);
    }

    public static int a(Object obj, int i, int i2) {
        try {
            if (obj instanceof Number) {
                return ((Number) obj).intValue();
            }
            return Integer.parseInt(String.valueOf(obj), i);
        } catch (Exception unused) {
            Log.e(f20744a, "parseDouble " + obj + " catch exception, return defalut value " + i2);
            return i2;
        }
    }
}
