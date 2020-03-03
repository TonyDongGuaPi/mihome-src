package com.xiaomi.smarthome.library.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiUtil {

    /* renamed from: a  reason: collision with root package name */
    private static WifiManager.MulticastLock f18643a;

    public static String a(Context context) {
        WifiInfo wifiInfo;
        if (context == null) {
            return "";
        }
        try {
            wifiInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        } catch (Exception unused) {
            wifiInfo = null;
        }
        if (wifiInfo != null) {
            return wifiInfo.getBSSID();
        }
        return null;
    }

    public static String b(Context context) {
        WifiInfo wifiInfo;
        if (context == null) {
            return "";
        }
        try {
            wifiInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        } catch (Exception unused) {
            wifiInfo = null;
        }
        if (wifiInfo != null) {
            return wifiInfo.getBSSID();
        }
        return null;
    }

    public static String c(Context context) {
        WifiInfo wifiInfo;
        String ssid;
        if (context == null) {
            return "";
        }
        try {
            wifiInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        } catch (Exception unused) {
            wifiInfo = null;
        }
        if (wifiInfo == null || (ssid = wifiInfo.getSSID()) == null) {
            return null;
        }
        return (ssid.length() < 3 || !ssid.startsWith("\"") || !ssid.endsWith("\"")) ? ssid : ssid.substring(1, ssid.length() - 1);
    }

    public static String d(Context context) {
        WifiInfo wifiInfo;
        if (context == null) {
            return "";
        }
        try {
            wifiInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        } catch (Exception unused) {
            wifiInfo = null;
        }
        if (wifiInfo != null) {
            return wifiInfo.getMacAddress();
        }
        return null;
    }

    public static String e(Context context) {
        if (context == null) {
            return "";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (!wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(true);
            }
            return a(wifiManager.getConnectionInfo().getIpAddress());
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static void f(Context context) {
        if (context != null) {
            g(context);
            try {
                f18643a = ((WifiManager) context.getSystemService("wifi")).createMulticastLock("multicast.test");
                f18643a.acquire();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void g(Context context) {
        try {
            if (f18643a != null && f18643a.isHeld()) {
                f18643a.release();
            }
            f18643a = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean h(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }
}
