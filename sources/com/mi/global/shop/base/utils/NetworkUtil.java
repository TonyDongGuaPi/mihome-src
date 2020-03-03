package com.mi.global.shop.base.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.mi.global.shop.base.ApplicationAgent;

public class NetworkUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5680a = "WIFI";
    public static final String b = "NO_NETWORK";

    public static String a() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) ApplicationAgent.f5586a.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return "NO_NETWORK";
        }
        return activeNetworkInfo.getTypeName();
    }

    public static boolean b() {
        return a().toUpperCase().equals("WIFI");
    }

    public static boolean c() {
        String upperCase = a().toUpperCase();
        if (!upperCase.equals("WIFI") && !upperCase.endsWith("NO_NETWORK")) {
            return true;
        }
        return false;
    }

    public static boolean d() {
        return a().toUpperCase().equals("NO_NETWORK");
    }
}
