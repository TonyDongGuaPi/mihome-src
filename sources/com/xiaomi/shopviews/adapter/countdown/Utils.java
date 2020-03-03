package com.xiaomi.shopviews.adapter.countdown;

import android.content.Context;

final class Utils {
    Utils() {
    }

    public static int a(Context context, float f) {
        if (f <= 0.0f) {
            return 0;
        }
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static float b(Context context, float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        return f * context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static String a(int i) {
        if (i >= 10) {
            return String.valueOf(i);
        }
        return "0" + i;
    }

    public static String b(int i) {
        if (i > 99) {
            return String.valueOf(i / 10);
        }
        if (i > 9) {
            return String.valueOf(i);
        }
        return "0" + i;
    }
}
