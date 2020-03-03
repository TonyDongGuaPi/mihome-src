package com.xiaomi.mishopsdk.util;

import com.xiaomi.mishopsdk.ShopApp;

public class DensityUtil {
    public static int dip2px(float f) {
        return (int) ((f * ShopApp.instance.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(float f) {
        return (int) ((f / ShopApp.instance.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
