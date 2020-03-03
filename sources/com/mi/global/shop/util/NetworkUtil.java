package com.mi.global.shop.util;

import android.net.ConnectivityManager;
import com.mi.global.shop.ShopApp;

public class NetworkUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7101a = "WIFI";
    public static final String b = "NO_NETWORK";

    public static String a() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ShopApp.g().getSystemService("connectivity");
        return (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null) ? "NO_NETWORK" : connectivityManager.getActiveNetworkInfo().getTypeName();
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
