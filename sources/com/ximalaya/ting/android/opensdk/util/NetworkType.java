package com.ximalaya.ting.android.opensdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class NetworkType {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2263a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;

    private static boolean f(Context context) {
        int i;
        try {
            i = ((TelephonyManager) context.getSystemService("phone")).getNetworkType();
        } catch (Exception unused) {
            i = 0;
        }
        switch (i) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return false;
            case 5:
                return true;
            case 6:
                return true;
            case 7:
                return false;
            case 8:
                return true;
            case 9:
                return true;
            case 10:
                return true;
            case 11:
                return false;
            case 12:
                return true;
            case 13:
                return true;
            case 14:
                return true;
            case 15:
                return true;
            default:
                return false;
        }
    }

    public static NetWorkType a(Context context) {
        if (context == null) {
            return NetWorkType.NETWORKTYPE_INVALID;
        }
        NetworkInfo networkInfo = null;
        try {
            networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
        }
        NetWorkType netWorkType = NetWorkType.NETWORKTYPE_INVALID;
        if (networkInfo == null || !networkInfo.isConnected()) {
            return NetWorkType.NETWORKTYPE_INVALID;
        }
        String typeName = networkInfo.getTypeName();
        if (typeName.equalsIgnoreCase("WIFI")) {
            return NetWorkType.NETWORKTYPE_WIFI;
        }
        if (!typeName.equalsIgnoreCase("MOBILE")) {
            return netWorkType;
        }
        return TextUtils.isEmpty(Proxy.getDefaultHost()) ? f(context) ? NetWorkType.NETWORKTYPE_3G : NetWorkType.NETWORKTYPE_2G : NetWorkType.NETWORKTYPE_WAP;
    }

    public enum NetWorkType {
        NETWORKTYPE_INVALID("no_network", 1),
        NETWORKTYPE_WAP("wap", 2),
        NETWORKTYPE_2G("2g", 3),
        NETWORKTYPE_3G("3g", 4),
        NETWORKTYPE_WIFI("wifi", 5);
        
        private int index;
        private String name;

        private NetWorkType(String str, int i) {
            this.name = str;
            this.index = i;
        }

        public static String getName(int i) {
            for (NetWorkType netWorkType : values()) {
                if (netWorkType.getIndex() == i) {
                    return netWorkType.name;
                }
            }
            return null;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            this.index = i;
        }
    }

    public static boolean b(Context context) {
        return a(context) != NetWorkType.NETWORKTYPE_INVALID;
    }

    public static boolean c(Context context) {
        return a(context) == NetWorkType.NETWORKTYPE_WIFI;
    }

    public static boolean d(Context context) {
        NetWorkType a2 = a(context);
        return a2 == NetWorkType.NETWORKTYPE_WAP || a2 == NetWorkType.NETWORKTYPE_2G || a2 == NetWorkType.NETWORKTYPE_3G;
    }

    public static int e(Context context) {
        String str;
        if (context == null) {
            return 3;
        }
        try {
            str = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
        } catch (Exception unused) {
            str = null;
        }
        if (str == null) {
            return 3;
        }
        if (str.equals("46000") || str.equals("46002") || str.equals("46007") || str.equals("46020")) {
            return 0;
        }
        if (str.equals("46001") || str.equals("46006") || str.equals("46009")) {
            return 1;
        }
        if (str.equals("46003") || str.equals("46005") || str.equals("46011")) {
            return 2;
        }
        return 3;
    }
}
