package com.xiaomi.jr.common.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.List;

public class AppUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10361a = "AppUtils";
    private static String b;
    private static int c;
    private static Boolean d;

    public static String a(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toChars());
        } catch (PackageManager.NameNotFoundException e) {
            MifiLog.e(f10361a, "get package info failed. " + e.toString());
            return null;
        } catch (Exception e2) {
            MifiLog.e(f10361a, "get package info failed. " + e2.toString());
            return null;
        }
    }

    public static String b(Context context) {
        String a2 = a(context);
        if (a2 != null) {
            return HashUtils.a(a2);
        }
        return null;
    }

    public static boolean c(Context context) {
        if (d == null) {
            d = Boolean.valueOf(TextUtils.equals(b(context), "88daa889de21a80bca64464243c9ede6"));
        }
        return d.booleanValue();
    }

    public static String d(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
            return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "");
        } catch (Exception e) {
            MifiLog.b(f10361a, "Exception in getApplicationName: " + e.toString());
            return "";
        }
    }

    public static boolean a(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean e(Context context) {
        Context applicationContext = context.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) applicationContext.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.processName.equals(packageName) && next.importance <= 100) {
                return true;
            }
        }
        return false;
    }

    public static int f(Context context) {
        if (c == 0) {
            h(context);
        }
        return c;
    }

    public static String g(Context context) {
        if (TextUtils.isEmpty(b)) {
            h(context);
        }
        return b;
    }

    private static void h(Context context) {
        b = "";
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            b = packageInfo.versionName;
            c = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            MifiLog.b(f10361a, "NameNotFoundException in getVersion: " + e.toString());
        } catch (Exception e2) {
            MifiLog.b(f10361a, "Exception in getVersion: " + e2.toString());
        }
    }
}
