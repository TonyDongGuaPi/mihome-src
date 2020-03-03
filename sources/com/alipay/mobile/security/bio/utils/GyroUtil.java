package com.alipay.mobile.security.bio.utils;

public class GyroUtil {
    public static double getDeviceAngle(float f, float f2) {
        double sqrt = Math.sqrt((double) ((f2 * f2) + (f * f)));
        double d = (double) f;
        Double.isNaN(d);
        double d2 = d / sqrt;
        double d3 = 1.0d;
        if (d2 <= 1.0d) {
            d3 = d2 < -1.0d ? -1.0d : d2;
        }
        double acos = Math.acos(d3);
        if (f2 < 0.0f) {
            acos = 6.283185307179586d - acos;
        }
        return (acos * 360.0d) / 6.283185307179586d;
    }
}
