package com.xiaomi.miot.support.monitor.utils;

import java.math.BigDecimal;

public class DoubleUtils {
    public static double a(double d, double d2) {
        try {
            return new BigDecimal(Double.toString(d)).multiply(new BigDecimal(Double.toString(d2))).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }

    public static double a(double d, double d2, int i) {
        try {
            return new BigDecimal(Double.toString(d)).divide(new BigDecimal(Double.toString(d2)), i, 1).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0d;
        }
    }
}
