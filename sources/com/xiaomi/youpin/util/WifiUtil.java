package com.xiaomi.youpin.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiUtil {

    /* renamed from: a  reason: collision with root package name */
    private static WifiManager.MulticastLock f23768a;

    public static String a(Context context) {
        WifiInfo wifiInfo;
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

    public static void e(Context context) {
        f(context);
        f23768a = ((WifiManager) context.getSystemService("wifi")).createMulticastLock("multicast.test");
        f23768a.acquire();
    }

    public static void f(Context context) {
        if (f23768a != null && f23768a.isHeld()) {
            f23768a.release();
        }
        f23768a = null;
    }
}
