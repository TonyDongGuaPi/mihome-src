package com.mi.multi_image_selector.utils;

import android.content.Context;

public class AppUtil {
    public static synchronized String a(Context context) {
        String string;
        synchronized (AppUtil.class) {
            try {
                string = context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return string;
    }
}
