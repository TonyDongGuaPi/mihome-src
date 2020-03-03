package com.xiaomi.base.utils;

public class ImageAdapUtil {
    public static ImageInfo a(int i, int i2, int i3, int i4) {
        double d = (double) i;
        double d2 = (double) i4;
        Double.isNaN(d);
        Double.isNaN(d2);
        double d3 = d / d2;
        double d4 = (double) i2;
        Double.isNaN(d4);
        double d5 = (double) i3;
        Double.isNaN(d5);
        return new ImageInfo((int) (d4 * d3), (int) (d3 * d5));
    }

    public static class ImageInfo {

        /* renamed from: a  reason: collision with root package name */
        public int f10029a;
        public int b;

        public ImageInfo(int i, int i2) {
            this.b = i;
            this.f10029a = i2;
        }
    }
}
