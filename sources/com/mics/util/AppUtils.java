package com.mics.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

public class AppUtils {
    public static synchronized String a(Context context) {
        String string;
        synchronized (AppUtils.class) {
            try {
                string = context.getResources().getString(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.labelRes);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return string;
    }

    public static synchronized String b(Context context) {
        String str;
        synchronized (AppUtils.class) {
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return str;
    }

    public static synchronized int c(Context context) {
        int i;
        synchronized (AppUtils.class) {
            try {
                i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return i;
    }

    public static synchronized String d(Context context) {
        String e;
        synchronized (AppUtils.class) {
            e = e(context);
        }
        return e;
    }

    private static String e(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mics-uuid", 0);
        String string = sharedPreferences.getString("deviceId", "");
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        sharedPreferences.edit().putString("deviceId", uuid).apply();
        return uuid;
    }
}
