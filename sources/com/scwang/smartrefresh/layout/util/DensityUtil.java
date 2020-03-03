package com.scwang.smartrefresh.layout.util;

import android.content.res.Resources;

public class DensityUtil {

    /* renamed from: a  reason: collision with root package name */
    public float f8801a = Resources.getSystem().getDisplayMetrics().density;

    public static int a(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static float a(int i) {
        return ((float) i) / Resources.getSystem().getDisplayMetrics().density;
    }

    public int b(float f) {
        return (int) ((f * this.f8801a) + 0.5f);
    }

    public float b(int i) {
        return ((float) i) / this.f8801a;
    }
}
