package com.xiaomi.mishopsdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.taobao.weex.adapter.URIAdapter;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.smarthome.device.BleDevice;

public class NetworkUtil {
    public static boolean isNetWorkConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public static int getActiveNetworkType(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        if (networkInfo != null) {
            return networkInfo.getType();
        }
        return -1;
    }

    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public static String getWifiSSID(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID();
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static String getMacAddress() {
        WifiInfo connectionInfo = ((WifiManager) ShopApp.instance.getSystemService("wifi")).getConnectionInfo();
        String macAddress = connectionInfo != null ? connectionInfo.getMacAddress() : null;
        return macAddress == null ? "" : macAddress;
    }

    public static String getnetworkTypeStr() {
        NetworkInfo networkInfo = getNetworkInfo(ShopApp.instance);
        if (networkInfo == null) {
            return "unknown";
        }
        if (networkInfo.getType() == 1) {
            return "wifi";
        }
        if (networkInfo.getType() != 0) {
            return networkInfo.getType() == 7 ? BleDevice.f14751a : URIAdapter.OTHERS;
        }
        switch (networkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2g";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3g";
            case 13:
                return "4g";
            default:
                return "unknown";
        }
    }
}
