package com.alipay.deviceid.module.x;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import java.io.File;

public final class l {

    /* renamed from: a  reason: collision with root package name */
    private static l f931a = new l();

    public static String b() {
        return "android";
    }

    private l() {
    }

    public static l a() {
        return f931a;
    }

    public static boolean c() {
        String[] strArr = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 5) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    return true;
                }
                i++;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055 A[Catch:{ Exception -> 0x0066 }, RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r7) {
        /*
            r0 = 0
            java.lang.String r1 = android.os.Build.HARDWARE     // Catch:{ Exception -> 0x0066 }
            java.lang.String r2 = "goldfish"
            boolean r1 = r1.contains(r2)     // Catch:{ Exception -> 0x0066 }
            r2 = 1
            if (r1 != 0) goto L_0x0065
            java.lang.String r1 = android.os.Build.PRODUCT     // Catch:{ Exception -> 0x0066 }
            java.lang.String r3 = "sdk"
            boolean r1 = r1.contains(r3)     // Catch:{ Exception -> 0x0066 }
            if (r1 != 0) goto L_0x0065
            java.lang.String r1 = android.os.Build.FINGERPRINT     // Catch:{ Exception -> 0x0066 }
            java.lang.String r3 = "generic"
            boolean r1 = r1.contains(r3)     // Catch:{ Exception -> 0x0066 }
            if (r1 == 0) goto L_0x0021
            goto L_0x0065
        L_0x0021:
            java.lang.String r1 = "phone"
            java.lang.Object r1 = r7.getSystemService(r1)     // Catch:{ Exception -> 0x0066 }
            android.telephony.TelephonyManager r1 = (android.telephony.TelephonyManager) r1     // Catch:{ Exception -> 0x0066 }
            if (r1 == 0) goto L_0x0056
            java.lang.String r1 = r1.getDeviceId()     // Catch:{ Exception -> 0x0066 }
            if (r1 == 0) goto L_0x0052
            int r3 = r1.length()     // Catch:{ Exception -> 0x0066 }
            if (r3 != 0) goto L_0x0038
            goto L_0x0052
        L_0x0038:
            r4 = 0
        L_0x0039:
            if (r4 >= r3) goto L_0x0052
            char r5 = r1.charAt(r4)     // Catch:{ Exception -> 0x0066 }
            boolean r5 = java.lang.Character.isWhitespace(r5)     // Catch:{ Exception -> 0x0066 }
            if (r5 != 0) goto L_0x004f
            char r5 = r1.charAt(r4)     // Catch:{ Exception -> 0x0066 }
            r6 = 48
            if (r5 == r6) goto L_0x004f
            r1 = 0
            goto L_0x0053
        L_0x004f:
            int r4 = r4 + 1
            goto L_0x0039
        L_0x0052:
            r1 = 1
        L_0x0053:
            if (r1 == 0) goto L_0x0056
            return r2
        L_0x0056:
            android.content.ContentResolver r7 = r7.getContentResolver()     // Catch:{ Exception -> 0x0066 }
            java.lang.String r1 = "android_id"
            java.lang.String r7 = android.provider.Settings.Secure.getString(r7, r1)     // Catch:{ Exception -> 0x0066 }
            boolean r7 = com.alipay.deviceid.module.x.e.a((java.lang.String) r7)     // Catch:{ Exception -> 0x0066 }
            return r7
        L_0x0065:
            return r2
        L_0x0066:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.l.a(android.content.Context):boolean");
    }

    public static String d() {
        return Build.BOARD;
    }

    public static String e() {
        return Build.BRAND;
    }

    public static String f() {
        return Build.DEVICE;
    }

    public static String g() {
        return Build.DISPLAY;
    }

    public static String h() {
        return Build.VERSION.INCREMENTAL;
    }

    public static String i() {
        return Build.MANUFACTURER;
    }

    public static String j() {
        return Build.MODEL;
    }

    public static String k() {
        return Build.PRODUCT;
    }

    public static String l() {
        return Build.VERSION.RELEASE;
    }

    public static String m() {
        return Build.VERSION.SDK;
    }

    public static String n() {
        return Build.TAGS;
    }

    public static String b(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (!connectivityManager.getActiveNetworkInfo().isConnected()) {
                return "";
            }
            String typeName = connectivityManager.getActiveNetworkInfo().getTypeName();
            if (typeName == null ? false : typeName.equalsIgnoreCase("WIFI")) {
                return "WIFI";
            }
            return connectivityManager.getActiveNetworkInfo().getExtraInfo();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke((Object) null, new Object[]{str, str2});
        } catch (Exception unused) {
            return str2;
        }
    }
}
