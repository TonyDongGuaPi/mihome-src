package com.xiaomi.smarthome.newui.widget.topnavi.indicator.buildins;

import android.content.Context;

public final class UIUtil {
    public static int a(Context context, double d) {
        double d2 = (double) context.getResources().getDisplayMetrics().density;
        Double.isNaN(d2);
        return (int) ((d * d2) + 0.5d);
    }

    public static int a(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
