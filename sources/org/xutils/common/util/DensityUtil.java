package org.xutils.common.util;

import org.xutils.x;

public final class DensityUtil {

    /* renamed from: a  reason: collision with root package name */
    private static float f4231a = -1.0f;
    private static int b = -1;
    private static int c = -1;

    private DensityUtil() {
    }

    public static float a() {
        if (f4231a <= 0.0f) {
            f4231a = x.b().getResources().getDisplayMetrics().density;
        }
        return f4231a;
    }

    public static int a(float f) {
        return (int) ((f * a()) + 0.5f);
    }

    public static int b(float f) {
        return (int) ((f / a()) + 0.5f);
    }

    public static int b() {
        if (b <= 0) {
            b = x.b().getResources().getDisplayMetrics().widthPixels;
        }
        return b;
    }

    public static int c() {
        if (c <= 0) {
            c = x.b().getResources().getDisplayMetrics().heightPixels;
        }
        return c;
    }
}
