package com.alibaba.android.arouter.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.alibaba.android.arouter.launcher.ARouter;

public class PackageUtils {

    /* renamed from: a  reason: collision with root package name */
    private static String f735a;
    private static int b;

    public static boolean a(Context context) {
        PackageInfo c = c(context);
        if (c == null) {
            return true;
        }
        String str = c.versionName;
        int i = c.versionCode;
        SharedPreferences sharedPreferences = context.getSharedPreferences(Consts.j, 0);
        if (str.equals(sharedPreferences.getString(Consts.l, (String) null)) && i == sharedPreferences.getInt(Consts.m, -1)) {
            return false;
        }
        f735a = str;
        b = i;
        return true;
    }

    public static void b(Context context) {
        if (!TextUtils.isEmpty(f735a) && b != 0) {
            context.getSharedPreferences(Consts.j, 0).edit().putString(Consts.l, f735a).putInt(Consts.m, b).apply();
        }
    }

    private static PackageInfo c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384);
        } catch (Exception unused) {
            ARouter.c.error("ARouter::", "Get package info error.");
            return null;
        }
    }
}
