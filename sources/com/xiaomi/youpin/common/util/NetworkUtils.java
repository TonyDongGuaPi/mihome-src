package com.xiaomi.youpin.common.util;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.xiaomi.youpin.common.util.ShellUtils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public final class NetworkUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23261a = 16;
    private static final int b = 17;
    private static final int c = 18;

    public enum NetworkType {
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void a() {
        Utils.a().startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(C.ENCODING_PCM_MU_LAW));
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean b() {
        NetworkInfo m = m();
        return m != null && m.isConnected();
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean c() {
        return a((String) null);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean a(String str) {
        if (str == null || str.length() <= 0) {
            str = "223.5.5.5";
        }
        boolean z = true;
        ShellUtils.CommandResult a2 = ShellUtils.a(String.format("ping -c 1 %s", new Object[]{str}), false);
        if (a2.f23270a != 0) {
            z = false;
        }
        if (a2.c != null) {
            Log.d("NetworkUtils", "isAvailableByPing() called" + a2.c);
        }
        if (a2.b != null) {
            Log.d("NetworkUtils", "isAvailableByPing() called" + a2.b);
        }
        return z;
    }

    public static boolean d() {
        Method declaredMethod;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
            if (!(telephonyManager == null || (declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0])) == null)) {
                return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequiresPermission("android.permission.MODIFY_PHONE_STATE")
    public static void a(boolean z) {
        Method declaredMethod;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
            if (telephonyManager != null && (declaredMethod = telephonyManager.getClass().getDeclaredMethod("setDataEnabled", new Class[]{Boolean.TYPE})) != null) {
                declaredMethod.invoke(telephonyManager, new Object[]{Boolean.valueOf(z)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean e() {
        NetworkInfo m = m();
        return m != null && m.isAvailable() && m.getType() == 0;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean f() {
        NetworkInfo m = m();
        return m != null && m.isAvailable() && m.getSubtype() == 13;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static boolean g() {
        WifiManager wifiManager = (WifiManager) Utils.a().getSystemService("wifi");
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    public static void b(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.a().getSystemService("wifi");
        if (wifiManager != null) {
            if (z) {
                if (!wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(true);
                }
            } else if (wifiManager.isWifiEnabled()) {
                wifiManager.setWifiEnabled(false);
            }
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean h() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.a().getSystemService("connectivity");
        if (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null || connectivityManager.getActiveNetworkInfo().getType() != 1) {
            return false;
        }
        return true;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static boolean i() {
        return g() && c();
    }

    public static String j() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.a().getSystemService("phone");
        return telephonyManager != null ? telephonyManager.getNetworkOperatorName() : "";
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkType k() {
        NetworkType networkType = NetworkType.NETWORK_NO;
        NetworkInfo m = m();
        if (m == null || !m.isAvailable()) {
            return networkType;
        }
        if (m.getType() == 1) {
            return NetworkType.NETWORK_WIFI;
        }
        if (m.getType() != 0) {
            return NetworkType.NETWORK_UNKNOWN;
        }
        switch (m.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkType.NETWORK_2G;
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
                return NetworkType.NETWORK_3G;
            case 13:
            case 18:
                return NetworkType.NETWORK_4G;
            default:
                String subtypeName = m.getSubtypeName();
                if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                    return NetworkType.NETWORK_3G;
                }
                return NetworkType.NETWORK_UNKNOWN;
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static NetworkInfo m() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.a().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String c(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement2 = inetAddresses.nextElement();
                        if (!nextElement2.isLoopbackAddress()) {
                            String hostAddress = nextElement2.getHostAddress();
                            boolean z2 = hostAddress.indexOf(58) < 0;
                            if (z) {
                                if (z2) {
                                    return hostAddress;
                                }
                            } else if (!z2) {
                                int indexOf = hostAddress.indexOf(37);
                                if (indexOf < 0) {
                                    return hostAddress.toUpperCase();
                                }
                                return hostAddress.substring(0, indexOf).toUpperCase();
                            }
                        }
                    }
                    continue;
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String b(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean l() {
        try {
            NetworkInfo m = m();
            if (m == null || !m.isAvailable()) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
