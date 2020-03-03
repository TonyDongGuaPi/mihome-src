package com.xiaomi.channel.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.List;

public class VersionManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10074a = -10001;
    public static final int b = -10002;
    public static final int c = 100;
    private static final String d = "com.xiaomi.channel";

    public static int a(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages == null) {
            return -10001;
        }
        for (int i = 0; i < installedPackages.size(); i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            if (d.equals(packageInfo.packageName)) {
                return packageInfo.versionCode >= 1117 ? 100 : -10002;
            }
        }
        return -10001;
    }

    public static boolean b(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            int i = 0;
            while (i < installedPackages.size()) {
                PackageInfo packageInfo = installedPackages.get(i);
                if (!d.equals(packageInfo.packageName)) {
                    i++;
                } else if (packageInfo.versionCode >= 1117) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean a(Context context, int i) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            int i2 = 0;
            while (i2 < installedPackages.size()) {
                PackageInfo packageInfo = installedPackages.get(i2);
                if (!d.equals(packageInfo.packageName)) {
                    i2++;
                } else if (packageInfo.versionCode >= i) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static int c(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                PackageInfo packageInfo = installedPackages.get(i);
                if (d.equals(packageInfo.packageName)) {
                    return packageInfo.versionCode;
                }
            }
        }
        return 0;
    }

    public static boolean d(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(d, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            return true;
        }
        return false;
    }
}
