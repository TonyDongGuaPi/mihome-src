package com.xiaomi.youpin.login.other.common;

import android.content.Context;

public final class ConvertUtils {
    private ConvertUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
