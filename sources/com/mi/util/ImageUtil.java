package com.mi.util;

import android.graphics.BitmapFactory;

public class ImageUtil {
    public static int b(BitmapFactory.Options options, int i, int i2) {
        int a2 = a(options, i, i2);
        if (a2 > 8) {
            return ((a2 + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < a2) {
            i3 <<= 1;
        }
        return i3;
    }

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int i3;
        int i4;
        double d = (double) options.outWidth;
        double d2 = (double) options.outHeight;
        if (i2 == -1) {
            i3 = 1;
        } else {
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = (double) i2;
            Double.isNaN(d3);
            i3 = (int) Math.ceil(Math.sqrt((d * d2) / d3));
        }
        if (i == -1) {
            i4 = 128;
        } else {
            double d4 = (double) i;
            Double.isNaN(d);
            Double.isNaN(d4);
            double floor = Math.floor(d / d4);
            Double.isNaN(d2);
            Double.isNaN(d4);
            i4 = (int) Math.min(floor, Math.floor(d2 / d4));
        }
        if (i4 < i3) {
            return i3;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        return i == -1 ? i3 : i4;
    }
}
