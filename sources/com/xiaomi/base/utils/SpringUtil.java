package com.xiaomi.base.utils;

public class SpringUtil {
    public static double a(double d, double d2, double d3, double d4, double d5) {
        return d4 + ((d5 - d4) * ((d - d2) / (d3 - d2)));
    }

    public static double a(double d, double d2, double d3) {
        return Math.min(Math.max(d, d2), d3);
    }
}
