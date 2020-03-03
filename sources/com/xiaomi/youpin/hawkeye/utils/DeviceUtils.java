package com.xiaomi.youpin.hawkeye.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.WindowManager;

public class DeviceUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23389a = "DeviceUtils";
    public static final String b = "0";
    public static final String c = "wifi";
    public static final String d = "2G";
    public static final String e = "3G";
    public static final String f = "4G";

    public static boolean a(Context context) {
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
            if (networkInfo != null) {
                return networkInfo.isConnected();
            }
            return false;
        } catch (Exception e2) {
            HLog.b(f23389a, e2.toString());
            return false;
        }
    }

    public static String b(Context context) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return "0";
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && (state = networkInfo.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            return "wifi";
        }
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo2 == null) {
            return "0";
        }
        NetworkInfo.State state2 = networkInfo2.getState();
        String subtypeName = networkInfo2.getSubtypeName();
        if (state2 == null) {
            return "0";
        }
        if (state2 != NetworkInfo.State.CONNECTED && state2 != NetworkInfo.State.CONNECTING) {
            return "0";
        }
        int subtype = activeNetworkInfo.getSubtype();
        if (subtype == 19) {
            return "4G";
        }
        switch (subtype) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA") && !subtypeName.equalsIgnoreCase("CDMA2000")) ? "0" : "3G";
        }
    }

    public static String a() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static String b() {
        return Build.MODEL;
    }

    public static int c(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int d(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public static String e(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return a(memoryInfo.availMem);
    }

    public static String f(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return a(memoryInfo.totalMem);
    }

    public static String a(long j) {
        if (j < 1024) {
            return String.valueOf(j) + " B";
        }
        long j2 = j / 1024;
        if (j2 < 1024) {
            return String.valueOf(j2) + " KB";
        }
        long j3 = j2 / 1024;
        if (j3 < 1024) {
            long j4 = j3 * 100;
            return String.valueOf(j4 / 100) + "." + String.valueOf(j4 % 100) + " MB";
        }
        long j5 = (j3 * 100) / 1024;
        return String.valueOf(j5 / 100) + "." + String.valueOf(j5 % 100) + " GB";
    }
}
