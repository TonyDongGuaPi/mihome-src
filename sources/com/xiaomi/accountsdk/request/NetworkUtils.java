package com.xiaomi.accountsdk.request;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.xiaomi.accountsdk.utils.CloudCoder;

public class NetworkUtils {
    public static String getNetworkNameForMiUrlStat(Context context) {
        return deleteUrlUnsafeChar(getActiveConnPoint(context));
    }

    static String getActiveConnPoint(Context context) {
        String str = null;
        if (context == null) {
            return null;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                return "";
            }
            try {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return "";
                }
                if (activeNetworkInfo.getType() == 1) {
                    String str2 = "";
                    try {
                        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                        if (wifiManager.getConnectionInfo() != null) {
                            str = wifiManager.getConnectionInfo().getSSID();
                        }
                        str2 = str;
                    } catch (Exception unused) {
                    }
                    if (TextUtils.isEmpty(str2)) {
                        return "wifi";
                    }
                    return "wifi-" + CloudCoder.hashDeviceInfo(str2).substring(0, 3).toLowerCase();
                }
                return String.format("%s-%s-%s", new Object[]{activeNetworkInfo.getTypeName(), replacePlusToPChar(activeNetworkInfo.getSubtypeName()), activeNetworkInfo.getExtraInfo()}).toLowerCase();
            } catch (Exception unused2) {
                return "";
            }
        } catch (Exception unused3) {
            return "";
        }
    }

    static String replacePlusToPChar(String str) {
        if (str != null) {
            return str.replaceAll("\\+", "p");
        }
        return null;
    }

    static String deleteUrlUnsafeChar(String str) {
        if (str != null) {
            return str.replaceAll("[^a-zA-Z0-9-_.]", "");
        }
        return null;
    }
}
