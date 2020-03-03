package com.xiaomi.mobilestats.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.telephony.TelephonyManager;
import com.xiaomi.mobilestats.controller.LogController;

public class NetType {
    public static final int NETTYPE_2G = 2;
    public static final int NETTYPE_3G_UP = 3;
    public static final int NETTYPE_INVALID = 0;
    public static final int NETTYPE_WAP = 1;
    public static final int NETTYPE_WIFI = 4;

    public static int getNetType(Context context) {
        if (context == null) {
            return -1;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 0;
        }
        String typeName = activeNetworkInfo.getTypeName();
        if (typeName.equalsIgnoreCase("wifi")) {
            return 4;
        }
        if (!typeName.equalsIgnoreCase("mobile")) {
            return -1;
        }
        if (!StrUtil.isEmpty(Proxy.getDefaultHost())) {
            return 1;
        }
        return !j(context) ? 2 : 3;
    }

    public static boolean isNet2G_DOWN(Context context) {
        boolean z = true;
        if (context == null) {
            return true;
        }
        int netType = getNetType(context);
        if (!(netType == 2 || netType == 1)) {
            z = false;
        }
        LogController.is2GDownNet = z;
        return z;
    }

    private static boolean j(Context context) {
        if (context == null) {
            return false;
        }
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case 0:
            case 1:
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
            case 6:
                return true;
            case 7:
                return false;
            case 8:
            case 9:
            case 10:
                return true;
            case 11:
                return false;
            case 12:
            case 13:
            case 14:
            case 15:
                return true;
            default:
                return false;
        }
    }
}
