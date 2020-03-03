package com.xiaomi.jr.common.utils;

import android.content.Context;

public class DensityUtils {
    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
