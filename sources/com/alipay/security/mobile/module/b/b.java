package com.alipay.security.mobile.module.b;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.alipay.security.mobile.module.a.a;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static b f1164a = new b();

    private b() {
    }

    private static String A() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                            return nextElement.getHostAddress().toString();
                        }
                    }
                }
            }
            return "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static b a() {
        return f1164a;
    }

    private static String a(BluetoothAdapter bluetoothAdapter) {
        try {
            Field declaredField = BluetoothAdapter.class.getDeclaredField("mService");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(bluetoothAdapter);
            if (obj == null) {
                return null;
            }
            Method declaredMethod = obj.getClass().getDeclaredMethod("getAddress", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(obj, new Object[0]);
            if (invoke != null && (invoke instanceof String)) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable unused) {
        }
    }

    public static String a(Context context) {
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    private static boolean a(Context context, String str) {
        return !(context.getPackageManager().checkPermission(str, context.getPackageName()) == 0);
    }

    public static String b() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    public static String b(Context context) {
        String str = "";
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getSubscriberId();
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    public static String c() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(a.a().getPath());
                j = ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    public static String c(Context context) {
        int i;
        try {
            i = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Throwable unused) {
            i = 0;
        }
        return i == 1 ? "1" : "0";
    }

    public static String d() {
        return "";
    }

    public static String d(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            AudioManager audioManager = (AudioManager) context.getSystemService("audio");
            int i = audioManager.getRingerMode() == 0 ? 1 : 0;
            int streamVolume = audioManager.getStreamVolume(0);
            int streamVolume2 = audioManager.getStreamVolume(1);
            int streamVolume3 = audioManager.getStreamVolume(2);
            int streamVolume4 = audioManager.getStreamVolume(3);
            int streamVolume5 = audioManager.getStreamVolume(4);
            jSONObject.put("ringermode", String.valueOf(i));
            jSONObject.put("call", String.valueOf(streamVolume));
            jSONObject.put("system", String.valueOf(streamVolume2));
            jSONObject.put("ring", String.valueOf(streamVolume3));
            jSONObject.put("music", String.valueOf(streamVolume4));
            jSONObject.put("alarm", String.valueOf(streamVolume5));
        } catch (Throwable unused) {
        }
        return jSONObject.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|(2:8|(3:10|11|(2:13|(3:63|15|16)(1:17))(0))(0))|26|27|(1:62)(1:64)|(1:(0))) */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:24|25) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x007a, code lost:
        if (r2 != null) goto L_0x0050;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:24:0x004d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0050 */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0060 A[SYNTHETIC, Splitter:B:36:0x0060] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0065 A[SYNTHETIC, Splitter:B:40:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x006a A[SYNTHETIC, Splitter:B:44:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0072 A[SYNTHETIC, Splitter:B:52:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0077 A[SYNTHETIC, Splitter:B:56:0x0077] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String e() {
        /*
            java.lang.String r0 = "0000000000000000"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x006e, all -> 0x005b }
            java.io.File r3 = new java.io.File     // Catch:{ Throwable -> 0x006e, all -> 0x005b }
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x006e, all -> 0x005b }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x006e, all -> 0x005b }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0059, all -> 0x0056 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x0059, all -> 0x0056 }
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch:{ Throwable -> 0x0070, all -> 0x0054 }
            r4.<init>(r3)     // Catch:{ Throwable -> 0x0070, all -> 0x0054 }
            r1 = 1
            r5 = 1
        L_0x001b:
            r6 = 100
            if (r5 >= r6) goto L_0x004a
            java.lang.String r6 = r4.readLine()     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            if (r6 == 0) goto L_0x004a
            java.lang.String r7 = "Serial"
            int r7 = r6.indexOf(r7)     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            if (r7 < 0) goto L_0x0042
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            java.lang.String r1 = r6.substring(r5, r1)     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x0048, all -> 0x0045 }
            r0 = r1
            goto L_0x004a
        L_0x0042:
            int r5 = r5 + 1
            goto L_0x001b
        L_0x0045:
            r0 = move-exception
            r1 = r4
            goto L_0x005e
        L_0x0048:
            r1 = r4
            goto L_0x0070
        L_0x004a:
            r4.close()     // Catch:{ Throwable -> 0x004d }
        L_0x004d:
            r3.close()     // Catch:{ Throwable -> 0x0050 }
        L_0x0050:
            r2.close()     // Catch:{ Throwable -> 0x007d }
            goto L_0x007d
        L_0x0054:
            r0 = move-exception
            goto L_0x005e
        L_0x0056:
            r0 = move-exception
            r3 = r1
            goto L_0x005e
        L_0x0059:
            r3 = r1
            goto L_0x0070
        L_0x005b:
            r0 = move-exception
            r2 = r1
            r3 = r2
        L_0x005e:
            if (r1 == 0) goto L_0x0063
            r1.close()     // Catch:{ Throwable -> 0x0063 }
        L_0x0063:
            if (r3 == 0) goto L_0x0068
            r3.close()     // Catch:{ Throwable -> 0x0068 }
        L_0x0068:
            if (r2 == 0) goto L_0x006d
            r2.close()     // Catch:{ Throwable -> 0x006d }
        L_0x006d:
            throw r0
        L_0x006e:
            r2 = r1
            r3 = r2
        L_0x0070:
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ Throwable -> 0x0075 }
        L_0x0075:
            if (r3 == 0) goto L_0x007a
            r3.close()     // Catch:{ Throwable -> 0x007a }
        L_0x007a:
            if (r2 == 0) goto L_0x007d
            goto L_0x0050
        L_0x007d:
            if (r0 != 0) goto L_0x0081
            java.lang.String r0 = ""
        L_0x0081:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.e():java.lang.String");
    }

    public static String e(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getNetworkOperatorName();
                }
            } catch (Throwable unused) {
            }
        }
        return (str == null || "null".equals(str)) ? "" : str;
    }

    public static String f(Context context) {
        List<Sensor> sensorList;
        String str = null;
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (!(sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null || sensorList.size() <= 0)) {
                    StringBuilder sb = new StringBuilder();
                    for (Sensor next : sensorList) {
                        sb.append(next.getName());
                        sb.append(next.getVersion());
                        sb.append(next.getVendor());
                    }
                    str = a.e(sb.toString());
                }
            } catch (Throwable unused) {
            }
        }
        return str == null ? "" : str;
    }

    public static String g() {
        String x = x();
        return !a.a(x) ? x : y();
    }

    public static String g(Context context) {
        List<Sensor> sensorList;
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            try {
                SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
                if (!(sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null || sensorList.size() <= 0)) {
                    for (Sensor next : sensorList) {
                        if (next != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("name", next.getName());
                            jSONObject.put("version", next.getVersion());
                            jSONObject.put("vendor", next.getVendor());
                            jSONArray.put(jSONObject);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return jSONArray.toString();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|19|20|51) */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        return "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0023 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x002a */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x003d A[SYNTHETIC, Splitter:B:29:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0042 A[SYNTHETIC, Splitter:B:33:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0049 A[SYNTHETIC, Splitter:B:41:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x004e A[SYNTHETIC, Splitter:B:45:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String h() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Throwable -> 0x0046, all -> 0x0037 }
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x0046, all -> 0x0037 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0047, all -> 0x0032 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0047, all -> 0x0032 }
            java.lang.String r0 = r2.readLine()     // Catch:{ Throwable -> 0x0030, all -> 0x002e }
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r0 = r0.split(r3, r4)     // Catch:{ Throwable -> 0x0030, all -> 0x002e }
            if (r0 == 0) goto L_0x0027
            int r3 = r0.length     // Catch:{ Throwable -> 0x0030, all -> 0x002e }
            r4 = 1
            if (r3 <= r4) goto L_0x0027
            r0 = r0[r4]     // Catch:{ Throwable -> 0x0030, all -> 0x002e }
            r1.close()     // Catch:{ Throwable -> 0x0023 }
        L_0x0023:
            r2.close()     // Catch:{ Throwable -> 0x0026 }
        L_0x0026:
            return r0
        L_0x0027:
            r1.close()     // Catch:{ Throwable -> 0x002a }
        L_0x002a:
            r2.close()     // Catch:{ Throwable -> 0x0051 }
            goto L_0x0051
        L_0x002e:
            r0 = move-exception
            goto L_0x003b
        L_0x0030:
            r0 = r2
            goto L_0x0047
        L_0x0032:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
            goto L_0x003b
        L_0x0037:
            r1 = move-exception
            r2 = r0
            r0 = r1
            r1 = r2
        L_0x003b:
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch:{ Throwable -> 0x0040 }
        L_0x0040:
            if (r2 == 0) goto L_0x0045
            r2.close()     // Catch:{ Throwable -> 0x0045 }
        L_0x0045:
            throw r0
        L_0x0046:
            r1 = r0
        L_0x0047:
            if (r1 == 0) goto L_0x004c
            r1.close()     // Catch:{ Throwable -> 0x004c }
        L_0x004c:
            if (r0 == 0) goto L_0x0051
            r0.close()     // Catch:{ Throwable -> 0x0051 }
        L_0x0051:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.h():java.lang.String");
    }

    public static String h(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:5|6|(2:8|9)|10|11|12|13) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0029 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003c A[SYNTHETIC, Splitter:B:22:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0041 A[SYNTHETIC, Splitter:B:26:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0048 A[SYNTHETIC, Splitter:B:34:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x004d A[SYNTHETIC, Splitter:B:38:0x004d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String i() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            r2 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x0045, all -> 0x0036 }
            r4.<init>(r0)     // Catch:{ Throwable -> 0x0045, all -> 0x0036 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0046, all -> 0x0031 }
            r5 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r4, r5)     // Catch:{ Throwable -> 0x0046, all -> 0x0031 }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x002f, all -> 0x002d }
            if (r1 == 0) goto L_0x0026
            java.lang.String r5 = "\\s+"
            java.lang.String[] r1 = r1.split(r5)     // Catch:{ Throwable -> 0x002f, all -> 0x002d }
            r5 = 1
            r1 = r1[r5]     // Catch:{ Throwable -> 0x002f, all -> 0x002d }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Throwable -> 0x002f, all -> 0x002d }
            long r1 = (long) r1
            r2 = r1
        L_0x0026:
            r4.close()     // Catch:{ Throwable -> 0x0029 }
        L_0x0029:
            r0.close()     // Catch:{ Throwable -> 0x0050 }
            goto L_0x0050
        L_0x002d:
            r1 = move-exception
            goto L_0x003a
        L_0x002f:
            r1 = r0
            goto L_0x0046
        L_0x0031:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L_0x003a
        L_0x0036:
            r0 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x003a:
            if (r4 == 0) goto L_0x003f
            r4.close()     // Catch:{ Throwable -> 0x003f }
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()     // Catch:{ Throwable -> 0x0044 }
        L_0x0044:
            throw r1
        L_0x0045:
            r4 = r1
        L_0x0046:
            if (r4 == 0) goto L_0x004b
            r4.close()     // Catch:{ Throwable -> 0x004b }
        L_0x004b:
            if (r1 == 0) goto L_0x0050
            r1.close()     // Catch:{ Throwable -> 0x0050 }
        L_0x0050:
            java.lang.String r0 = java.lang.String.valueOf(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.i():java.lang.String");
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String j() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    public static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String k() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
            }
        } catch (Throwable unused) {
        }
        return String.valueOf(j);
    }

    public static String k(Context context) {
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                try {
                    if (macAddress.length() != 0 && !"02:00:00:00:00:00".equals(macAddress)) {
                        return macAddress;
                    }
                } catch (Throwable unused) {
                    return macAddress;
                }
            }
            return w();
        } catch (Throwable unused2) {
            return "";
        }
    }

    public static String l() {
        String str = "";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls.newInstance(), new Object[]{"gsm.version.baseband", "no message"});
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    public static String l(Context context) {
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return "";
        }
        String str = "";
        try {
            String simSerialNumber = ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
            if (simSerialNumber != null) {
                if (simSerialNumber == null) {
                    return simSerialNumber;
                }
                try {
                    if (simSerialNumber.length() != 0) {
                        return simSerialNumber;
                    }
                } catch (Throwable unused) {
                    return simSerialNumber;
                }
            }
            str = "";
        } catch (Throwable unused2) {
        }
        return str;
    }

    public static String m() {
        String str = "";
        try {
            str = Build.SERIAL;
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    public static String m(Context context) {
        String str;
        try {
            str = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        } catch (Throwable unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

    public static String n() {
        String str = "";
        try {
            str = Locale.getDefault().toString();
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if ("02:00:00:00:00:00".equals(r0) == false) goto L_0x002a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String n(android.content.Context r2) {
        /*
            java.lang.String r0 = "android.permission.BLUETOOTH"
            boolean r0 = a(r2, r0)
            if (r0 == 0) goto L_0x000b
            java.lang.String r2 = ""
            return r2
        L_0x000b:
            java.lang.String r0 = z()
            if (r0 == 0) goto L_0x001f
            int r1 = r0.length()     // Catch:{ Throwable -> 0x002f }
            if (r1 == 0) goto L_0x001f
            java.lang.String r1 = "02:00:00:00:00:00"
            boolean r1 = r1.equals(r0)     // Catch:{ Throwable -> 0x002f }
            if (r1 == 0) goto L_0x002a
        L_0x001f:
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ Throwable -> 0x002f }
            java.lang.String r1 = "bluetooth_address"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r1)     // Catch:{ Throwable -> 0x002f }
            r0 = r2
        L_0x002a:
            if (r0 != 0) goto L_0x002f
            java.lang.String r2 = ""
            goto L_0x0030
        L_0x002f:
            r2 = r0
        L_0x0030:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.n(android.content.Context):java.lang.String");
    }

    public static String o() {
        String str = "";
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    public static String o(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? String.valueOf(telephonyManager.getNetworkType()) : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String p() {
        try {
            long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            StringBuilder sb = new StringBuilder();
            sb.append(currentTimeMillis - (currentTimeMillis % 1000));
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String p(Context context) {
        String str = "";
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return "";
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                str = wifiManager.getConnectionInfo().getBSSID();
            }
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    public static String q() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String q(Context context) {
        try {
            String t = t(context);
            String A = A();
            if (!a.b(t) || !a.b(A)) {
                return "";
            }
            return t + ":" + A();
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String r() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append("00" + ":");
            for (int i = 0; i < 7; i++) {
                sb.append(new File(strArr[i]).exists() ? "1" : "0");
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:7|8|9|10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x003f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String r(android.content.Context r9) {
        /*
            java.lang.String r0 = "keyguard"
            java.lang.Object r9 = r9.getSystemService(r0)     // Catch:{ Throwable -> 0x0055 }
            android.app.KeyguardManager r9 = (android.app.KeyguardManager) r9     // Catch:{ Throwable -> 0x0055 }
            boolean r9 = r9.isKeyguardSecure()     // Catch:{ Throwable -> 0x0055 }
            r0 = 0
            if (r9 != 0) goto L_0x0013
            java.lang.String r9 = "0:0"
            return r9
        L_0x0013:
            r9 = 5
            java.lang.String[] r2 = new java.lang.String[r9]     // Catch:{ Throwable -> 0x0055 }
            java.lang.String r3 = "/data/system/password.key"
            r4 = 0
            r2[r4] = r3     // Catch:{ Throwable -> 0x0055 }
            java.lang.String r3 = "/data/system/gesture.key"
            r5 = 1
            r2[r5] = r3     // Catch:{ Throwable -> 0x0055 }
            r3 = 2
            java.lang.String r5 = "/data/system/gatekeeper.password.key"
            r2[r3] = r5     // Catch:{ Throwable -> 0x0055 }
            r3 = 3
            java.lang.String r5 = "/data/system/gatekeeper.gesture.key"
            r2[r3] = r5     // Catch:{ Throwable -> 0x0055 }
            r3 = 4
            java.lang.String r5 = "/data/system/gatekeeper.pattern.key"
            r2[r3] = r5     // Catch:{ Throwable -> 0x0055 }
        L_0x002f:
            if (r4 >= r9) goto L_0x0046
            r3 = r2[r4]     // Catch:{ Throwable -> 0x0055 }
            r5 = -1
            java.io.File r7 = new java.io.File     // Catch:{ Throwable -> 0x003f }
            r7.<init>(r3)     // Catch:{ Throwable -> 0x003f }
            long r7 = r7.lastModified()     // Catch:{ Throwable -> 0x003f }
            r5 = r7
        L_0x003f:
            long r0 = java.lang.Math.max(r5, r0)     // Catch:{ Throwable -> 0x0055 }
            int r4 = r4 + 1
            goto L_0x002f
        L_0x0046:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0055 }
            java.lang.String r2 = "1:"
            r9.<init>(r2)     // Catch:{ Throwable -> 0x0055 }
            r9.append(r0)     // Catch:{ Throwable -> 0x0055 }
            java.lang.String r9 = r9.toString()     // Catch:{ Throwable -> 0x0055 }
            return r9
        L_0x0055:
            java.lang.String r9 = ""
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.r(android.content.Context):java.lang.String");
    }

    public static String s() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder();
        sb.append("00");
        sb.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Throwable unused) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002b A[Catch:{ Throwable -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e A[Catch:{ Throwable -> 0x0040 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String s(android.content.Context r3) {
        /*
            android.content.IntentFilter r0 = new android.content.IntentFilter     // Catch:{ Throwable -> 0x0040 }
            java.lang.String r1 = "android.intent.action.BATTERY_CHANGED"
            r0.<init>(r1)     // Catch:{ Throwable -> 0x0040 }
            r1 = 0
            android.content.Intent r3 = r3.registerReceiver(r1, r0)     // Catch:{ Throwable -> 0x0040 }
            java.lang.String r0 = "level"
            r1 = -1
            int r0 = r3.getIntExtra(r0, r1)     // Catch:{ Throwable -> 0x0040 }
            java.lang.String r2 = "status"
            int r3 = r3.getIntExtra(r2, r1)     // Catch:{ Throwable -> 0x0040 }
            r1 = 2
            if (r3 == r1) goto L_0x0023
            r1 = 5
            if (r3 != r1) goto L_0x0021
            goto L_0x0023
        L_0x0021:
            r3 = 0
            goto L_0x0024
        L_0x0023:
            r3 = 1
        L_0x0024:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0040 }
            r1.<init>()     // Catch:{ Throwable -> 0x0040 }
            if (r3 == 0) goto L_0x002e
            java.lang.String r3 = "1"
            goto L_0x0030
        L_0x002e:
            java.lang.String r3 = "0"
        L_0x0030:
            r1.append(r3)     // Catch:{ Throwable -> 0x0040 }
            java.lang.String r3 = ":"
            r1.append(r3)     // Catch:{ Throwable -> 0x0040 }
            r1.append(r0)     // Catch:{ Throwable -> 0x0040 }
            java.lang.String r3 = r1.toString()     // Catch:{ Throwable -> 0x0040 }
            return r3
        L_0x0040:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.s(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0085 A[SYNTHETIC, Splitter:B:23:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008e A[SYNTHETIC, Splitter:B:30:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x003d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String t() {
        /*
            java.lang.String r0 = "00"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            java.lang.String r3 = "/system/build.prop"
            java.lang.String r4 = "ro.product.name=sdk"
            r2.put(r3, r4)
            java.lang.String r3 = "/proc/tty/drivers"
            java.lang.String r4 = "goldfish"
            r2.put(r3, r4)
            java.lang.String r3 = "/proc/cpuinfo"
            java.lang.String r4 = "goldfish"
            r2.put(r3, r4)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = ":"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.append(r0)
            java.util.Set r0 = r2.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x003d:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0092
            java.lang.Object r3 = r0.next()
            java.lang.String r3 = (java.lang.String) r3
            r4 = 0
            r5 = 48
            java.io.LineNumberReader r6 = new java.io.LineNumberReader     // Catch:{ Throwable -> 0x0089, all -> 0x007f }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0089, all -> 0x007f }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0089, all -> 0x007f }
            r8.<init>(r3)     // Catch:{ Throwable -> 0x0089, all -> 0x007f }
            r7.<init>(r8)     // Catch:{ Throwable -> 0x0089, all -> 0x007f }
            r6.<init>(r7)     // Catch:{ Throwable -> 0x0089, all -> 0x007f }
        L_0x005b:
            java.lang.String r4 = r6.readLine()     // Catch:{ Throwable -> 0x007d, all -> 0x007a }
            if (r4 == 0) goto L_0x0073
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ Throwable -> 0x007d, all -> 0x007a }
            java.lang.Object r7 = r2.get(r3)     // Catch:{ Throwable -> 0x007d, all -> 0x007a }
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ Throwable -> 0x007d, all -> 0x007a }
            boolean r4 = r4.contains(r7)     // Catch:{ Throwable -> 0x007d, all -> 0x007a }
            if (r4 == 0) goto L_0x005b
            r5 = 49
        L_0x0073:
            r1.append(r5)
            r6.close()     // Catch:{ Throwable -> 0x003d }
            goto L_0x003d
        L_0x007a:
            r0 = move-exception
            r4 = r6
            goto L_0x0080
        L_0x007d:
            r4 = r6
            goto L_0x0089
        L_0x007f:
            r0 = move-exception
        L_0x0080:
            r1.append(r5)
            if (r4 == 0) goto L_0x0088
            r4.close()     // Catch:{ Throwable -> 0x0088 }
        L_0x0088:
            throw r0
        L_0x0089:
            r1.append(r5)
            if (r4 == 0) goto L_0x003d
            r4.close()     // Catch:{ Throwable -> 0x003d }
            goto L_0x003d
        L_0x0092:
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.t():java.lang.String");
    }

    private static String t(Context context) {
        if (a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "";
        }
        String str = null;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return "WIFI";
                }
                if (activeNetworkInfo.getType() == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    if (!(subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7)) {
                        if (subtype != 11) {
                            if (!(subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14)) {
                                if (subtype != 15) {
                                    if (subtype == 13) {
                                        return "4G";
                                    }
                                    str = "UNKNOW";
                                }
                            }
                            return "3G";
                        }
                    }
                    return "2G";
                }
            }
        } catch (Throwable unused) {
        }
        return str;
    }

    public static String u() {
        StringBuilder sb = new StringBuilder();
        sb.append("00" + ":");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("BRAND", MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE);
        linkedHashMap.put("BOARD", "unknown");
        linkedHashMap.put("DEVICE", MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE);
        linkedHashMap.put("HARDWARE", "goldfish");
        linkedHashMap.put("PRODUCT", "sdk");
        linkedHashMap.put("MODEL", "sdk");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            try {
                String str2 = null;
                String str3 = (String) Build.class.getField(str).get((Object) null);
                String str4 = (String) linkedHashMap.get(str);
                if (str3 != null) {
                    str2 = str3.toLowerCase();
                }
                if (str2 != null && str2.contains(str4)) {
                    c = '1';
                }
            } catch (Throwable th) {
                sb.append('0');
                throw th;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String v() {
        StringBuilder sb = new StringBuilder();
        sb.append("00" + ":");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("ro.hardware", "goldfish");
        linkedHashMap.put("ro.kernel.qemu", "1");
        linkedHashMap.put("ro.product.device", MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE);
        linkedHashMap.put("ro.product.model", "sdk");
        linkedHashMap.put("ro.product.brand", MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE);
        linkedHashMap.put("ro.product.name", "sdk");
        linkedHashMap.put("ro.build.fingerprint", "test-keys");
        linkedHashMap.put("ro.product.manufacturer", "unknow");
        for (String str : linkedHashMap.keySet()) {
            char c = '0';
            String str2 = (String) linkedHashMap.get(str);
            String b = a.b(str, "");
            if (b != null && b.contains(str2)) {
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String w() {
        try {
            ArrayList<T> list = Collections.list(NetworkInterface.getNetworkInterfaces());
            if (list == null) {
                return "02:00:00:00:00:00";
            }
            for (T t : list) {
                if (t != null && t.getName() != null && t.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = t.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "02:00:00:00:00:00";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", new Object[]{Integer.valueOf(hardwareAddress[i] & 255)}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "02:00:00:00:00:00";
        } catch (Throwable unused) {
            return "02:00:00:00:00:00";
        }
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0020 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0027 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0038 A[SYNTHETIC, Splitter:B:27:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x003d A[SYNTHETIC, Splitter:B:31:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0044 A[SYNTHETIC, Splitter:B:39:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String x() {
        /*
            java.lang.String r0 = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"
            r1 = 0
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Throwable -> 0x0041, all -> 0x0034 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0041, all -> 0x0034 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0042, all -> 0x0032 }
            r3 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r2, r3)     // Catch:{ Throwable -> 0x0042, all -> 0x0032 }
            java.lang.String r1 = r0.readLine()     // Catch:{ Throwable -> 0x0030, all -> 0x002b }
            boolean r3 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r1)     // Catch:{ Throwable -> 0x0030, all -> 0x002b }
            if (r3 != 0) goto L_0x0024
            java.lang.String r1 = r1.trim()     // Catch:{ Throwable -> 0x0030, all -> 0x002b }
            r0.close()     // Catch:{ Throwable -> 0x0020 }
        L_0x0020:
            r2.close()     // Catch:{ Throwable -> 0x0023 }
        L_0x0023:
            return r1
        L_0x0024:
            r0.close()     // Catch:{ Throwable -> 0x0027 }
        L_0x0027:
            r2.close()     // Catch:{ Throwable -> 0x004a }
            goto L_0x004a
        L_0x002b:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x0036
        L_0x0030:
            r1 = r0
            goto L_0x0042
        L_0x0032:
            r0 = move-exception
            goto L_0x0036
        L_0x0034:
            r0 = move-exception
            r2 = r1
        L_0x0036:
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ Throwable -> 0x003b }
        L_0x003b:
            if (r2 == 0) goto L_0x0040
            r2.close()     // Catch:{ Throwable -> 0x0040 }
        L_0x0040:
            throw r0
        L_0x0041:
            r2 = r1
        L_0x0042:
            if (r1 == 0) goto L_0x0047
            r1.close()     // Catch:{ Throwable -> 0x0047 }
        L_0x0047:
            if (r2 == 0) goto L_0x004a
            goto L_0x0027
        L_0x004a:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.x():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x003e */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x004e A[SYNTHETIC, Splitter:B:30:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0053 A[SYNTHETIC, Splitter:B:34:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x005a A[SYNTHETIC, Splitter:B:42:0x005a] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x005f A[SYNTHETIC, Splitter:B:46:0x005f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String y() {
        /*
            java.lang.String r0 = "/proc/cpuinfo"
            java.lang.String r1 = ""
            r2 = 0
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x0057, all -> 0x0049 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0057, all -> 0x0049 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0058, all -> 0x0046 }
            r4 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r3, r4)     // Catch:{ Throwable -> 0x0058, all -> 0x0046 }
        L_0x0011:
            java.lang.String r2 = r0.readLine()     // Catch:{ Throwable -> 0x0044, all -> 0x0042 }
            if (r2 == 0) goto L_0x003b
            boolean r4 = com.alipay.security.mobile.module.a.a.a((java.lang.String) r2)     // Catch:{ Throwable -> 0x0044, all -> 0x0042 }
            if (r4 != 0) goto L_0x0011
            java.lang.String r4 = ":"
            java.lang.String[] r2 = r2.split(r4)     // Catch:{ Throwable -> 0x0044, all -> 0x0042 }
            if (r2 == 0) goto L_0x0011
            int r4 = r2.length     // Catch:{ Throwable -> 0x0044, all -> 0x0042 }
            r5 = 1
            if (r4 <= r5) goto L_0x0011
            r4 = 0
            r4 = r2[r4]     // Catch:{ Throwable -> 0x0044, all -> 0x0042 }
            java.lang.String r6 = "BogoMIPS"
            boolean r4 = r4.contains(r6)     // Catch:{ Throwable -> 0x0044, all -> 0x0042 }
            if (r4 == 0) goto L_0x0011
            r2 = r2[r5]     // Catch:{ Throwable -> 0x0044, all -> 0x0042 }
            java.lang.String r2 = r2.trim()     // Catch:{ Throwable -> 0x0044, all -> 0x0042 }
            r1 = r2
        L_0x003b:
            r3.close()     // Catch:{ Throwable -> 0x003e }
        L_0x003e:
            r0.close()     // Catch:{ Throwable -> 0x0062 }
            goto L_0x0062
        L_0x0042:
            r1 = move-exception
            goto L_0x004c
        L_0x0044:
            r2 = r0
            goto L_0x0058
        L_0x0046:
            r1 = move-exception
            r0 = r2
            goto L_0x004c
        L_0x0049:
            r1 = move-exception
            r0 = r2
            r3 = r0
        L_0x004c:
            if (r3 == 0) goto L_0x0051
            r3.close()     // Catch:{ Throwable -> 0x0051 }
        L_0x0051:
            if (r0 == 0) goto L_0x0056
            r0.close()     // Catch:{ Throwable -> 0x0056 }
        L_0x0056:
            throw r1
        L_0x0057:
            r3 = r2
        L_0x0058:
            if (r3 == 0) goto L_0x005d
            r3.close()     // Catch:{ Throwable -> 0x005d }
        L_0x005d:
            if (r2 == 0) goto L_0x0062
            r2.close()     // Catch:{ Throwable -> 0x0062 }
        L_0x0062:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.security.mobile.module.b.b.y():java.lang.String");
    }

    private static String z() {
        BluetoothAdapter bluetoothAdapter;
        String str = "";
        try {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter != null) {
                try {
                    if (!bluetoothAdapter.isEnabled()) {
                        return "";
                    }
                } catch (Throwable unused) {
                }
            }
            str = bluetoothAdapter.getAddress();
        } catch (Throwable unused2) {
            bluetoothAdapter = null;
        }
        if (str == null || str.endsWith("00:00:00:00:00")) {
            try {
                str = a(bluetoothAdapter);
            } catch (Throwable unused3) {
            }
        }
        return str == null ? "" : str;
    }

    public final String f() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new c(this)).length);
        } catch (Throwable unused) {
            return "1";
        }
    }
}
