package com.unionpay.mobile.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.unionpay.mobile.android.global.a;
import com.unionpay.mobile.android.languages.c;
import java.io.File;
import java.net.NetworkInterface;
import java.util.Locale;
import java.util.TimeZone;

public final class f {
    public static String a() {
        return Locale.getDefault().toString().startsWith("zh") ? "zh_CN" : "en_US";
    }

    public static String a(Context context) {
        Activity activity = (Activity) context;
        try {
            String str = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4160).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return c.bD.f9574a;
    }

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

    public static String b() {
        return new File("/system/bin/su").exists() ? "1" : "0";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r1 = ((android.app.Activity) r1).getPackageName();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(android.content.Context r1) {
        /*
            if (r1 == 0) goto L_0x0012
            boolean r0 = r1 instanceof android.app.Activity
            if (r0 == 0) goto L_0x0012
            android.app.Activity r1 = (android.app.Activity) r1
            java.lang.String r1 = r1.getPackageName()
            if (r1 == 0) goto L_0x000f
            return r1
        L_0x000f:
            java.lang.String r1 = ""
            return r1
        L_0x0012:
            java.lang.String r1 = ""
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.utils.f.b(android.content.Context):java.lang.String");
    }

    public static String c() {
        String trim = Build.MODEL.trim();
        if (trim != null) {
            trim.replace(" ", "");
        }
        return trim;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String c(android.content.Context r3) {
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
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.utils.f.c(android.content.Context):java.lang.String");
    }

    public static String d() {
        return (a.I + "*" + a.t).trim();
    }

    public static String d(Context context) {
        String str;
        try {
            str = new File("/system/bin/su").exists() ? c(context) : ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            str = "";
        }
        if (str == null || str.length() == 0) {
            str = PreferenceUtils.a(context);
        }
        k.a("uppay", "user=" + str);
        return str;
    }

    public static String e() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object newInstance = cls.newInstance();
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(newInstance, new Object[]{"gsm.version.baseband", "no message"});
        } catch (Exception unused) {
            return "";
        }
    }

    public static String e(Context context) {
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            return subscriberId == null ? "" : subscriberId;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String f() {
        return TimeZone.getDefault().getDisplayName(false, 0);
    }

    public static String f(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return "disConnect";
        }
        if (activeNetworkInfo.getType() != 0) {
            return activeNetworkInfo.getType() == 1 ? "wifi" : "other";
        }
        if (activeNetworkInfo.getState() != NetworkInfo.State.CONNECTED) {
            return "mobile";
        }
        return "mobile:" + activeNetworkInfo.getExtraInfo();
    }

    public static Location g(Context context) {
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

    public static String h(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        } catch (Exception unused) {
            return "";
        }
    }
}
