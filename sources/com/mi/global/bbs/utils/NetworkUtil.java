package com.mi.global.bbs.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.mi.global.bbs.BBSApplication;

public class NetworkUtil {
    public static final String WIFI = "WIFI";

    public static String getNetworkType() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) BBSApplication.getInstance().getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return "no_network";
        }
        return activeNetworkInfo.getTypeName();
    }

    public static boolean isWIFINetwork() {
        return getNetworkType().toUpperCase().equals("WIFI");
    }

    public static boolean isMobileNetwork() {
        String upperCase = getNetworkType().toUpperCase();
        if (!upperCase.equals("WIFI") && !upperCase.endsWith("no_network")) {
            return true;
        }
        return false;
    }
}
