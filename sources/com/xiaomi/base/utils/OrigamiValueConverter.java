package com.xiaomi.base.utils;

public class OrigamiValueConverter {
    public static double a(double d) {
        if (d != 0.0d) {
            return 25.0d + ((d - 8.0d) * 3.0d);
        }
        return 0.0d;
    }

    public static double b(double d) {
        if (d != 0.0d) {
            return 8.0d + ((d - 25.0d) / 3.0d);
        }
        return 0.0d;
    }

    public static double c(double d) {
        if (d != 0.0d) {
            return 30.0d + ((d - 194.0d) / 3.62d);
        }
        return 0.0d;
    }

    public static double d(double d) {
        if (d != 0.0d) {
            return 194.0d + ((d - 30.0d) * 3.62d);
        }
        return 0.0d;
    }
}
