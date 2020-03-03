package com.ximalaya.ting.android.opensdk.auth.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import com.xiaomi.accountsdk.service.DeviceInfoResult;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static String f1843a;

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f1843a)) {
            return f1843a;
        }
        if (TextUtils.isEmpty(f1843a)) {
            try {
                f1843a = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return f1843a;
    }

    @SuppressLint({"NewApi"})
    private static String b(Context context) throws Exception {
        String str = Build.VERSION.SDK_INT > 9 ? Build.SERIAL : null;
        if ((str != null && !str.equalsIgnoreCase("unknown")) || ((str = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID)) != null && !str.equalsIgnoreCase(DeviceInfoResult.BUNDLE_KEY_ANDROID_ID) && !str.equalsIgnoreCase("9774d56d682e549c"))) {
            return str;
        }
        throw new Exception("FATAL!!!! - This device doesn't have a UNIQUE Serial Number");
    }

    public static String a(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean b(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            return false;
        }
        return true;
    }
}
