package com.xiaomi.youpin.hawkeye.utils;

import com.xiaomi.youpin.hawkeye.HawkEye;

public class UIUtils {
    public static int a(float f) {
        return (int) ((f * HawkEye.d().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(float f) {
        return (int) ((f / HawkEye.d().getResources().getDisplayMetrics().density) + 0.5f);
    }
}
