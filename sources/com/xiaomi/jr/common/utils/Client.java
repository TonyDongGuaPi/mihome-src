package com.xiaomi.jr.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.miui.deviceid.IdentifierManager;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.UUID;

public class Client {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10364a = "Client";
    private static final String b = "02:00:00:00:00:00";
    private static final int c = 1;
    private static final int d = 0;
    private static String e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static String m;
    private static String n;
    private static String o;
    private static String p;
    private static String q;
    private static String r;
    private static String s;
    private static String t;
    private static String u;

    @SuppressLint({"MissingPermission"})
    public static String a() {
        if (Build.VERSION.SDK_INT < 26) {
            return Build.SERIAL;
        }
        try {
            return Build.getSerial();
        } catch (SecurityException unused) {
            MifiLog.e("Client", "Get IMEI failed... No permission.");
            return null;
        }
    }

    public static String a(Context context) {
        return Settings.System.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
    }

    public static String b(Context context) {
        if (TextUtils.isEmpty(n)) {
            n = e(context);
            if (TextUtils.isEmpty(n) && DeviceHelper.f10365a) {
                MifiLog.e("Client", "getImei failed, will get MAC.");
                n = l(context);
            }
            if (!TextUtils.isEmpty(n)) {
                o = HashUtils.a(n);
                p = HashUtils.c(n);
            } else {
                MifiLog.e("Client", "Get deviceId failed... Need retry.");
            }
        }
        return n;
    }

    public static String c(Context context) {
        if (TextUtils.isEmpty(o)) {
            b(context);
        }
        return o;
    }

    public static String d(Context context) {
        if (TextUtils.isEmpty(p)) {
            b(context);
        }
        return p;
    }

    public static String e(Context context) {
        if (TextUtils.isEmpty(e)) {
            try {
                String[] a2 = MiuiDeviceIdHelper.a(context);
                if (a2.length > 0) {
                    e = a2[0];
                }
                if (!TextUtils.isEmpty(e)) {
                    f = HashUtils.a(e);
                    g = HashUtils.c(e);
                } else {
                    MifiLog.e("Client", "Get IMEI failed... Need retry.");
                }
            } catch (SecurityException unused) {
                MifiLog.e("Client", "Get IMEI failed... No permission.");
                return null;
            }
        }
        return e;
    }

    public static String f(Context context) {
        if (TextUtils.isEmpty(f)) {
            e(context);
        }
        return f;
    }

    public static String g(Context context) {
        if (TextUtils.isEmpty(g)) {
            e(context);
        }
        return g;
    }

    public static String h(Context context) {
        if (TextUtils.isEmpty(h)) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (Build.VERSION.SDK_INT >= 26) {
                    h = telephonyManager.getImei();
                } else {
                    h = telephonyManager.getDeviceId();
                }
                if (!TextUtils.isEmpty(h)) {
                    i = HashUtils.a(h);
                    j = HashUtils.c(h);
                } else {
                    MifiLog.e("Client", "Get default IMEI failed... Need retry.");
                }
            } catch (SecurityException unused) {
                MifiLog.e("Client", "Get default IMEI failed... No permission.");
                return null;
            }
        }
        return h;
    }

    public static String i(Context context) {
        if (TextUtils.isEmpty(i)) {
            h(context);
        }
        return i;
    }

    public static String j(Context context) {
        if (TextUtils.isEmpty(j)) {
            h(context);
        }
        return j;
    }

    public static String k(Context context) {
        if (TextUtils.isEmpty(k) && IdentifierManager.a()) {
            k = IdentifierManager.b(context);
        }
        return k;
    }

    public static String l(Context context) {
        if (TextUtils.isEmpty(l)) {
            l = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (l == null || l.equals(b)) {
                l = f();
            }
            if (!TextUtils.isEmpty(l)) {
                m = HashUtils.a(l);
            } else {
                MifiLog.e("Client", "Get MAC failed... Need retry.");
            }
        }
        return l;
    }

    public static String m(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo == null) {
            return "";
        }
        String ssid = connectionInfo.getSSID();
        return (TextUtils.isEmpty(ssid) || !ssid.startsWith("\"") || !ssid.endsWith("\"") || ssid.lastIndexOf("\"") <= 0) ? ssid : ssid.substring(ssid.indexOf("\"") + 1, ssid.lastIndexOf("\""));
    }

    public static String n(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return connectionInfo != null ? connectionInfo.getBSSID() : "";
    }

    private static String f() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            NetworkInterface byName = NetworkInterface.getByName("eth1");
            if (byName == null) {
                byName = NetworkInterface.getByName("wlan0");
            }
            if (byName != null) {
                byte[] hardwareAddress = byName.getHardwareAddress();
                int length = hardwareAddress.length;
                for (int i2 = 0; i2 < length; i2++) {
                    stringBuffer.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i2])}));
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                }
                return stringBuffer.toString();
            }
            MifiLog.e("Client", "Get networkInterface failed");
            return null;
        } catch (SocketException e2) {
            MifiLog.e("Client", "SocketException in getMacAddressByNetworkInterface: " + e2.toString());
            return null;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static String o(Context context) {
        if (TextUtils.isEmpty(q)) {
            try {
                q = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            } catch (SecurityException unused) {
                MifiLog.e("Client", "Get IMSI failed... No permission.");
            }
        }
        return q;
    }

    public static String p(Context context) {
        String o2 = o(context);
        if (TextUtils.isEmpty(o2)) {
            return null;
        }
        return HashUtils.a(o2);
    }

    @SuppressLint({"MissingPermission"})
    public static String q(Context context) {
        if (TextUtils.isEmpty(r)) {
            try {
                r = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            } catch (SecurityException unused) {
                MifiLog.e("Client", "Get ICCID failed... No permission.");
            }
        }
        return r;
    }

    @SuppressLint({"MissingPermission"})
    private static String t(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        } catch (SecurityException unused) {
            MifiLog.e("Client", "Get phone number failed... No permission.");
            return null;
        }
    }

    public static String r(Context context) {
        String t2 = t(context);
        if (TextUtils.isEmpty(t2)) {
            return null;
        }
        return HashUtils.a(t2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x005d A[SYNTHETIC, Splitter:B:27:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006d A[SYNTHETIC, Splitter:B:33:0x006d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b() {
        /*
            java.lang.String r0 = s
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x007a
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0050, all -> 0x004b }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ IOException -> 0x0050, all -> 0x004b }
            java.lang.String r3 = "/proc/cpuinfo"
            r2.<init>(r3)     // Catch:{ IOException -> 0x0050, all -> 0x004b }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0050, all -> 0x004b }
        L_0x0015:
            java.lang.String r0 = r1.readLine()     // Catch:{ IOException -> 0x0049 }
            if (r0 == 0) goto L_0x0045
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IOException -> 0x0049 }
            if (r2 == 0) goto L_0x0022
            goto L_0x0015
        L_0x0022:
            java.lang.String r2 = ":"
            java.lang.String[] r0 = r0.split(r2)     // Catch:{ IOException -> 0x0049 }
            int r2 = r0.length     // Catch:{ IOException -> 0x0049 }
            r3 = 2
            if (r2 != r3) goto L_0x0015
            r2 = 0
            r2 = r0[r2]     // Catch:{ IOException -> 0x0049 }
            java.lang.String r2 = r2.trim()     // Catch:{ IOException -> 0x0049 }
            r3 = 1
            r0 = r0[r3]     // Catch:{ IOException -> 0x0049 }
            java.lang.String r0 = r0.trim()     // Catch:{ IOException -> 0x0049 }
            java.lang.String r3 = "Serial"
            boolean r2 = android.text.TextUtils.equals(r2, r3)     // Catch:{ IOException -> 0x0049 }
            if (r2 == 0) goto L_0x0015
            s = r0     // Catch:{ IOException -> 0x0049 }
            goto L_0x0015
        L_0x0045:
            r1.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x007a
        L_0x0049:
            r0 = move-exception
            goto L_0x0054
        L_0x004b:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x006b
        L_0x0050:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0054:
            java.lang.String r2 = "Client"
            java.lang.String r3 = "Error when fetch cpu info"
            com.xiaomi.jr.common.utils.MifiLog.e(r2, r3, r0)     // Catch:{ all -> 0x006a }
            if (r1 == 0) goto L_0x007a
            r1.close()     // Catch:{ IOException -> 0x0061 }
            goto L_0x007a
        L_0x0061:
            r0 = move-exception
            java.lang.String r1 = "Client"
            java.lang.String r2 = "failed to close reader"
            com.xiaomi.jr.common.utils.MifiLog.e(r1, r2, r0)
            goto L_0x007a
        L_0x006a:
            r0 = move-exception
        L_0x006b:
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0079
        L_0x0071:
            r1 = move-exception
            java.lang.String r2 = "Client"
            java.lang.String r3 = "failed to close reader"
            com.xiaomi.jr.common.utils.MifiLog.e(r2, r3, r1)
        L_0x0079:
            throw r0
        L_0x007a:
            java.lang.String r0 = s
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.common.utils.Client.b():java.lang.String");
    }

    public static long c() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static int s(Context context) {
        try {
            if (((TelephonyManager) context.getSystemService("phone")).getCallState() == 2) {
                return 1;
            }
            return 0;
        } catch (SecurityException unused) {
            MifiLog.e("Client", "Get phoneState failed... No permission.");
            return 0;
        }
    }

    public static String d() {
        return u;
    }

    public static void a(String str) {
        u = str;
    }

    public static String e() {
        if (TextUtils.isEmpty(t)) {
            t = UUID.randomUUID().toString();
        }
        return t;
    }
}
