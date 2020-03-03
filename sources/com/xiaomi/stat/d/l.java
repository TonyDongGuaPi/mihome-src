package com.xiaomi.stat.d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.stat.ak;
import java.util.Iterator;

public class l {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23059a = "NetWorkStateUtil";
    private static final int b = 16;
    private static final int c = 17;
    private static final int d = 18;
    private static final int e = 19;
    private static final String f = "2G";
    private static final String g = "3G";
    private static final String h = "4G";
    private static final String i = "WIFI";
    private static final String j = "ETHERNET";
    private static final String k = "UNKNOWN";
    private static final String l = "NOT_CONNECTED";

    public static int a(Context context) {
        if (context == null) {
            return 0;
        }
        String b2 = b(context);
        if (TextUtils.isEmpty(b2) || b2.equals(l)) {
            return 0;
        }
        if (b2.equals("2G")) {
            return 1;
        }
        if (b2.equals("3G")) {
            return 2;
        }
        if (b2.equals("4G")) {
            return 4;
        }
        if (b2.startsWith("WIFI")) {
            return 8;
        }
        if (b2.equals(j)) {
            return 16;
        }
        return 0;
    }

    public static boolean a() {
        Context a2 = ak.a();
        if (a2 == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) a2.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception unused) {
            k.b("isNetworkConnected exception");
            return false;
        }
    }

    public static String b(Context context) {
        try {
            if (e.w(context)) {
                return a.a(context);
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return l;
            }
            if (!activeNetworkInfo.isConnected()) {
                return l;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() != 0) {
                return k;
            }
            switch (activeNetworkInfo.getSubtype()) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                case 16:
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
                case 17:
                    return "3G";
                case 13:
                case 18:
                case 19:
                    return "4G";
                default:
                    return k;
            }
        } catch (Exception e2) {
            k.d(f23059a, "getNetworkState error", e2);
            return k;
        }
    }

    private static class a {
        private static boolean a(int i) {
            return i > 4900 && i < 5900;
        }

        private static boolean b(int i) {
            return i > 2400 && i < 2500;
        }

        private a() {
        }

        public static String a(Context context) {
            NetworkInfo activeNetworkInfo;
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) {
                return l.l;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(9);
            if (networkInfo == null || !networkInfo.isConnected()) {
                return (networkInfo2 == null || !networkInfo2.isConnected()) ? l.k : l.j;
            }
            return "WIFI" + b(context);
        }

        private static String b(Context context) {
            String str;
            try {
                if (Build.VERSION.SDK_INT >= 22) {
                    int frequency = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getFrequency();
                    if (a(frequency)) {
                        str = "5G";
                    } else if (!b(frequency)) {
                        return "";
                    } else {
                        str = "2G";
                    }
                } else {
                    char c = 65535;
                    WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                    String ssid = wifiManager.getConnectionInfo().getSSID();
                    String substring = ssid.substring(1, ssid.length() - 1);
                    if (ssid != null && ssid.length() > 2) {
                        Iterator<ScanResult> it = wifiManager.getScanResults().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ScanResult next = it.next();
                            if (next.SSID.equals(substring)) {
                                if (a(next.frequency)) {
                                    c = 2;
                                } else if (b(next.frequency)) {
                                    c = 1;
                                }
                            }
                        }
                    }
                    if (c == 2) {
                        str = "5G";
                    } else if (c != 1) {
                        return "";
                    } else {
                        str = "2G";
                    }
                }
                return str;
            } catch (Exception e) {
                k.d(l.f23059a, "getWifiFreeBand error", e);
                return "";
            }
        }
    }
}
