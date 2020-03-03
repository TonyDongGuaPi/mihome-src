package com.xiaomi.youpin.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetTypeUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23260a = "0";
    public static final String b = "wifi";
    public static final String c = "2G";
    public static final String d = "3G";
    public static final String e = "4G";
    private static String f;

    public static String a(Context context) {
        return c(context) + "-" + b(context);
    }

    private static String b(Context context) {
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

    private static String c(Context context) {
        if (f != null) {
            return f;
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String subscriberId = telephonyManager != null ? telephonyManager.getSubscriberId() : null;
        if (subscriberId == null || subscriberId.length() <= 5) {
            return "00000";
        }
        String substring = subscriberId.substring(0, 5);
        f = substring;
        return substring;
    }
}
