package com.unionpay.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.alipay.mobile.common.logging.api.LogCategory;
import java.net.NetworkInterface;

public final class e {
    public static String a() {
        try {
            return Build.getRadioVersion();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String a(android.content.Context r3) {
        /*
            java.lang.String r0 = ""
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            if (r1 >= r2) goto L_0x002f
            java.lang.String r1 = "wifi"
            java.lang.Object r3 = r3.getSystemService(r1)     // Catch:{ Exception -> 0x002f }
            android.net.wifi.WifiManager r3 = (android.net.wifi.WifiManager) r3     // Catch:{ Exception -> 0x002f }
            android.net.wifi.WifiInfo r3 = r3.getConnectionInfo()     // Catch:{ Exception -> 0x002f }
            if (r3 == 0) goto L_0x0020
            java.lang.String r1 = r3.getMacAddress()     // Catch:{ Exception -> 0x002f }
            if (r1 == 0) goto L_0x0020
            java.lang.String r0 = r3.getMacAddress()     // Catch:{ Exception -> 0x002f }
        L_0x0020:
            if (r0 == 0) goto L_0x0028
            int r3 = r0.length()     // Catch:{ Exception -> 0x002f }
            if (r3 != 0) goto L_0x0035
        L_0x0028:
            java.lang.String r3 = "wlan0"
            java.lang.String r0 = a((java.lang.String) r3)     // Catch:{ Exception -> 0x002f }
            goto L_0x0035
        L_0x002f:
            java.lang.String r3 = "wlan0"
            java.lang.String r0 = a((java.lang.String) r3)
        L_0x0035:
            java.lang.String r3 = ""
            if (r0 == 0) goto L_0x0045
            java.lang.String r1 = ""
            if (r0 == r1) goto L_0x0045
            java.lang.String r3 = ":"
            java.lang.String r1 = ""
            java.lang.String r3 = r0.replaceAll(r3, r1)
        L_0x0045:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.utils.e.a(android.content.Context):java.lang.String");
    }

    @SuppressLint({"NewApi"})
    private static String a(String str) {
        try {
            byte[] hardwareAddress = NetworkInterface.getByName(str).getHardwareAddress();
            if (hardwareAddress == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int length = hardwareAddress.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c(Context context) {
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            return subscriberId == null ? "" : subscriberId;
        } catch (Exception unused) {
            return "";
        }
    }

    public static Location d(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        try {
            if (locationManager.isProviderEnabled("gps")) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation == null) {
                    try {
                        if (locationManager.isProviderEnabled(LogCategory.CATEGORY_NETWORK)) {
                            return locationManager.getLastKnownLocation(LogCategory.CATEGORY_NETWORK);
                        }
                    } catch (Exception unused) {
                    }
                }
                return lastKnownLocation;
            }
            if (locationManager.isProviderEnabled(LogCategory.CATEGORY_NETWORK)) {
                return locationManager.getLastKnownLocation(LogCategory.CATEGORY_NETWORK);
            }
            return null;
        } catch (Exception unused2) {
        }
    }

    public static String e(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        } catch (Exception unused) {
            return "";
        }
    }
}
