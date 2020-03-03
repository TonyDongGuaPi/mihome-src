package com.alipay.deviceid.module.x;

import android.app.KeyguardManager;
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
import com.facebook.share.internal.MessengerShareContentUtility;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public final class k {

    /* renamed from: a  reason: collision with root package name */
    private static k f929a = new k();
    private static long b = 0;

    public static String h() {
        return "";
    }

    private k() {
    }

    public static k a() {
        return f929a;
    }

    public static String a(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getDeviceId();
                }
            } catch (Exception unused) {
            }
        }
        return str == null ? "" : str;
    }

    public static String b(Context context) {
        String str = "";
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getSubscriberId();
                }
            } catch (Exception unused) {
            }
        }
        return str == null ? "" : str;
    }

    public static String c(Context context) {
        int i;
        try {
            i = Settings.System.getInt(context.getContentResolver(), "airplane_mode_on", 0);
        } catch (Exception unused) {
            i = 0;
        }
        return i == 1 ? "1" : "0";
    }

    public static String b() {
        HashSet<String> hashSet = new HashSet<>();
        Class<Build> cls = Build.class;
        String[] strArr = {"CPU_ABI", "CPU_ABI2", "SUPPORTED_ABIS", "SUPPORTED_32_BIT_ABIS", "SUPPORTED_64_BIT_ABIS"};
        for (int i = 0; i < 5; i++) {
            try {
                String str = (String) cls.getField(strArr[i]).get((Object) null);
                if (str != null && str.length() > 0 && !hashSet.contains(str)) {
                    hashSet.add(str);
                }
            } catch (Exception unused) {
            }
        }
        JSONArray jSONArray = new JSONArray();
        for (String str2 : hashSet) {
            if (str2 != null) {
                try {
                    if (str2.length() > 0) {
                        jSONArray.put(str2);
                    }
                } catch (Exception unused2) {
                }
            }
        }
        try {
            return jSONArray.toString();
        } catch (Exception unused3) {
            return "";
        }
    }

    public static String c() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Exception unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    public static String d() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(e.a().getPath());
                j = ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
            }
        } catch (Exception unused) {
        }
        return String.valueOf(j);
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
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    public static String e(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getSimOperatorName();
                }
            } catch (Exception unused) {
            }
        }
        return str == null ? "" : str;
    }

    public static String f(Context context) {
        String str = null;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    str = telephonyManager.getNetworkOperatorName();
                }
            } catch (Exception unused) {
            }
        }
        return (str == null || "null".equals(str)) ? "" : str;
    }

    public static String g(Context context) {
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
                    str = e.d(sb.toString());
                }
            } catch (Exception unused) {
            }
        }
        return str == null ? "" : str;
    }

    public static String h(Context context) {
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
            } catch (Exception unused) {
            }
        }
        return jSONArray.toString();
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return Integer.toString(displayMetrics.widthPixels) + "*" + Integer.toString(displayMetrics.heightPixels);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String j(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.widthPixels);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String k(Context context) {
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            StringBuilder sb = new StringBuilder();
            sb.append(displayMetrics.heightPixels);
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String l(Context context) {
        try {
            String macAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                try {
                    if (macAddress.length() != 0 && !"02:00:00:00:00:00".equals(macAddress)) {
                        return macAddress;
                    }
                } catch (Exception unused) {
                    return macAddress;
                }
            }
            return y();
        } catch (Exception unused2) {
            return "";
        }
    }

    private static String y() {
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
        } catch (Exception unused) {
            return "02:00:00:00:00:00";
        }
    }

    public static String m(Context context) {
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
                } catch (Exception unused) {
                    return simSerialNumber;
                }
            }
            str = "";
        } catch (Exception unused2) {
        }
        return str;
    }

    public static String n(Context context) {
        String str;
        try {
            str = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        } catch (Exception unused) {
            str = "";
        }
        return str == null ? "" : str;
    }

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
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x006e, all -> 0x005b }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x006e, all -> 0x005b }
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch:{ Exception -> 0x006e, all -> 0x005b }
            r2.<init>(r3)     // Catch:{ Exception -> 0x006e, all -> 0x005b }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch:{ Exception -> 0x0070, all -> 0x0054 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0070, all -> 0x0054 }
            r1 = 1
            r5 = 1
        L_0x001b:
            r6 = 100
            if (r5 >= r6) goto L_0x004a
            java.lang.String r6 = r4.readLine()     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            if (r6 == 0) goto L_0x004a
            java.lang.String r7 = "Serial"
            int r7 = r6.indexOf(r7)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            if (r7 < 0) goto L_0x0042
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            java.lang.String r1 = r6.substring(r5, r1)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
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
            r4.close()     // Catch:{ Exception -> 0x004d }
        L_0x004d:
            r3.close()     // Catch:{ Exception -> 0x0050 }
        L_0x0050:
            r2.close()     // Catch:{ Exception -> 0x007d }
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
            r1.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            if (r3 == 0) goto L_0x0068
            r3.close()     // Catch:{ Exception -> 0x0068 }
        L_0x0068:
            if (r2 == 0) goto L_0x006d
            r2.close()     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            throw r0
        L_0x006e:
            r2 = r1
            r3 = r2
        L_0x0070:
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ Exception -> 0x0075 }
        L_0x0075:
            if (r3 == 0) goto L_0x007a
            r3.close()     // Catch:{ Exception -> 0x007a }
        L_0x007a:
            if (r2 == 0) goto L_0x007d
            goto L_0x0050
        L_0x007d:
            if (r0 != 0) goto L_0x0081
            java.lang.String r0 = ""
        L_0x0081:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.e():java.lang.String");
    }

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
    public static java.lang.String f() {
        /*
            java.lang.String r0 = "-1"
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x006e, all -> 0x005b }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x006e, all -> 0x005b }
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch:{ Exception -> 0x006e, all -> 0x005b }
            r2.<init>(r3)     // Catch:{ Exception -> 0x006e, all -> 0x005b }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            r3.<init>(r2)     // Catch:{ Exception -> 0x0059, all -> 0x0056 }
            java.io.LineNumberReader r4 = new java.io.LineNumberReader     // Catch:{ Exception -> 0x0070, all -> 0x0054 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0070, all -> 0x0054 }
            r1 = 1
            r5 = 1
        L_0x001b:
            r6 = 100
            if (r5 >= r6) goto L_0x004a
            java.lang.String r6 = r4.readLine()     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            if (r6 == 0) goto L_0x004a
            java.lang.String r7 = "Hardware"
            int r7 = r6.indexOf(r7)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            if (r7 < 0) goto L_0x0042
            java.lang.String r5 = ":"
            int r5 = r6.indexOf(r5)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            int r5 = r5 + r1
            int r1 = r6.length()     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            java.lang.String r1 = r6.substring(r5, r1)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            java.lang.String r1 = r1.trim()     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
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
            r4.close()     // Catch:{ Exception -> 0x004d }
        L_0x004d:
            r3.close()     // Catch:{ Exception -> 0x0050 }
        L_0x0050:
            r2.close()     // Catch:{ Exception -> 0x007d }
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
            r1.close()     // Catch:{ Exception -> 0x0063 }
        L_0x0063:
            if (r3 == 0) goto L_0x0068
            r3.close()     // Catch:{ Exception -> 0x0068 }
        L_0x0068:
            if (r2 == 0) goto L_0x006d
            r2.close()     // Catch:{ Exception -> 0x006d }
        L_0x006d:
            throw r0
        L_0x006e:
            r2 = r1
            r3 = r2
        L_0x0070:
            if (r1 == 0) goto L_0x0075
            r1.close()     // Catch:{ Exception -> 0x0075 }
        L_0x0075:
            if (r3 == 0) goto L_0x007a
            r3.close()     // Catch:{ Exception -> 0x007a }
        L_0x007a:
            if (r2 == 0) goto L_0x007d
            goto L_0x0050
        L_0x007d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.f():java.lang.String");
    }

    class a implements FileFilter {
        a() {
        }

        public final boolean accept(File file) {
            return Pattern.matches("cpu[0-9]+", file.getName());
        }
    }

    public final String g() {
        try {
            return String.valueOf(new File("/sys/devices/system/cpu/").listFiles(new a()).length);
        } catch (Exception unused) {
            return "1";
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|19|20|51) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:10|11|12|13|14|15) */
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
    public static java.lang.String i() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Exception -> 0x0046, all -> 0x0037 }
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch:{ Exception -> 0x0046, all -> 0x0037 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0047, all -> 0x0032 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0047, all -> 0x0032 }
            java.lang.String r0 = r2.readLine()     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            java.lang.String r3 = ":\\s+"
            r4 = 2
            java.lang.String[] r0 = r0.split(r3, r4)     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            if (r0 == 0) goto L_0x0027
            int r3 = r0.length     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            r4 = 1
            if (r3 <= r4) goto L_0x0027
            r0 = r0[r4]     // Catch:{ Exception -> 0x0030, all -> 0x002e }
            r1.close()     // Catch:{ Exception -> 0x0023 }
        L_0x0023:
            r2.close()     // Catch:{ Exception -> 0x0026 }
        L_0x0026:
            return r0
        L_0x0027:
            r1.close()     // Catch:{ Exception -> 0x002a }
        L_0x002a:
            r2.close()     // Catch:{ Exception -> 0x0051 }
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
            r1.close()     // Catch:{ Exception -> 0x0040 }
        L_0x0040:
            if (r2 == 0) goto L_0x0045
            r2.close()     // Catch:{ Exception -> 0x0045 }
        L_0x0045:
            throw r0
        L_0x0046:
            r1 = r0
        L_0x0047:
            if (r1 == 0) goto L_0x004c
            r1.close()     // Catch:{ Exception -> 0x004c }
        L_0x004c:
            if (r0 == 0) goto L_0x0051
            r0.close()     // Catch:{ Exception -> 0x0051 }
        L_0x0051:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.i():java.lang.String");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:5|6|(2:8|9)|10|11|12|13) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0029 */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003c A[SYNTHETIC, Splitter:B:22:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0041 A[SYNTHETIC, Splitter:B:26:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0048 A[SYNTHETIC, Splitter:B:34:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x004d A[SYNTHETIC, Splitter:B:38:0x004d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String j() {
        /*
            java.lang.String r0 = "/proc/meminfo"
            r1 = 0
            r2 = 0
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Exception -> 0x0045, all -> 0x0036 }
            r4.<init>(r0)     // Catch:{ Exception -> 0x0045, all -> 0x0036 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0046, all -> 0x0031 }
            r5 = 8192(0x2000, float:1.14794E-41)
            r0.<init>(r4, r5)     // Catch:{ Exception -> 0x0046, all -> 0x0031 }
            java.lang.String r1 = r0.readLine()     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            if (r1 == 0) goto L_0x0026
            java.lang.String r5 = "\\s+"
            java.lang.String[] r1 = r1.split(r5)     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            r5 = 1
            r1 = r1[r5]     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ Exception -> 0x002f, all -> 0x002d }
            long r1 = (long) r1
            r2 = r1
        L_0x0026:
            r4.close()     // Catch:{ Exception -> 0x0029 }
        L_0x0029:
            r0.close()     // Catch:{ Exception -> 0x0050 }
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
            r4.close()     // Catch:{ Exception -> 0x003f }
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()     // Catch:{ Exception -> 0x0044 }
        L_0x0044:
            throw r1
        L_0x0045:
            r4 = r1
        L_0x0046:
            if (r4 == 0) goto L_0x004b
            r4.close()     // Catch:{ Exception -> 0x004b }
        L_0x004b:
            if (r1 == 0) goto L_0x0050
            r1.close()     // Catch:{ Exception -> 0x0050 }
        L_0x0050:
            java.lang.String r0 = java.lang.String.valueOf(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.j():java.lang.String");
    }

    public static String k() {
        long j;
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            j = ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Exception unused) {
            j = 0;
        }
        return String.valueOf(j);
    }

    public static String l() {
        long j = 0;
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                j = ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
            }
        } catch (Exception unused) {
        }
        return String.valueOf(j);
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
                } catch (Exception unused) {
                }
            }
            str = bluetoothAdapter.getAddress();
        } catch (Exception unused2) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0012, code lost:
        if ("02:00:00:00:00:00".equals(r0) == false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String o(android.content.Context r2) {
        /*
            java.lang.String r0 = z()
            if (r0 == 0) goto L_0x0014
            int r1 = r0.length()     // Catch:{ Exception -> 0x0024 }
            if (r1 == 0) goto L_0x0014
            java.lang.String r1 = "02:00:00:00:00:00"
            boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x0024 }
            if (r1 == 0) goto L_0x001f
        L_0x0014:
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ Exception -> 0x0024 }
            java.lang.String r1 = "bluetooth_address"
            java.lang.String r2 = android.provider.Settings.Secure.getString(r2, r1)     // Catch:{ Exception -> 0x0024 }
            r0 = r2
        L_0x001f:
            if (r0 != 0) goto L_0x0024
            java.lang.String r2 = ""
            goto L_0x0025
        L_0x0024:
            r2 = r0
        L_0x0025:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.o(android.content.Context):java.lang.String");
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

    public static String p(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            return telephonyManager != null ? String.valueOf(telephonyManager.getNetworkType()) : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String m() {
        String str = "";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str = (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls.newInstance(), new Object[]{"gsm.version.baseband", "no message"});
        } catch (Exception unused) {
        }
        return str == null ? "" : str;
    }

    public static String q(Context context) {
        String str = "";
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager.isWifiEnabled()) {
                str = wifiManager.getConnectionInfo().getBSSID();
            }
        } catch (Throwable unused) {
        }
        return str == null ? "" : str;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0043 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0047 */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x004d A[Catch:{ Exception -> 0x0066 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0068 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String n() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = ""
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x006b }
            java.lang.String r3 = "/proc/version"
            r2.<init>(r3)     // Catch:{ Exception -> 0x006b }
            r3 = 0
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0043, all -> 0x003a }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0043, all -> 0x003a }
            r5.<init>(r2)     // Catch:{ Exception -> 0x0043, all -> 0x003a }
            r6 = 8192(0x2000, float:1.14794E-41)
            r4.<init>(r5, r6)     // Catch:{ Exception -> 0x0043, all -> 0x003a }
        L_0x0018:
            java.lang.String r3 = r4.readLine()     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            if (r3 == 0) goto L_0x002f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r5.<init>()     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r5.append(r1)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r5.append(r3)     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            java.lang.String r3 = r5.toString()     // Catch:{ Exception -> 0x0038, all -> 0x0036 }
            r1 = r3
            goto L_0x0018
        L_0x002f:
            r4.close()     // Catch:{ Exception -> 0x0047 }
        L_0x0032:
            r2.close()     // Catch:{ Exception -> 0x0047 }
            goto L_0x0047
        L_0x0036:
            r0 = move-exception
            goto L_0x003c
        L_0x0038:
            r3 = r4
            goto L_0x0043
        L_0x003a:
            r0 = move-exception
            r4 = r3
        L_0x003c:
            r4.close()     // Catch:{ Exception -> 0x0042 }
            r2.close()     // Catch:{ Exception -> 0x0042 }
        L_0x0042:
            throw r0
        L_0x0043:
            r3.close()     // Catch:{ Exception -> 0x0047 }
            goto L_0x0032
        L_0x0047:
            boolean r2 = com.alipay.deviceid.module.x.e.b(r1)     // Catch:{ Exception -> 0x0066 }
            if (r2 == 0) goto L_0x0066
            java.lang.String r2 = "version "
            int r2 = r1.indexOf(r2)     // Catch:{ Exception -> 0x0066 }
            int r2 = r2 + 8
            java.lang.String r1 = r1.substring(r2)     // Catch:{ Exception -> 0x0066 }
            java.lang.String r2 = " "
            int r2 = r1.indexOf(r2)     // Catch:{ Exception -> 0x0066 }
            r3 = 0
            java.lang.String r1 = r1.substring(r3, r2)     // Catch:{ Exception -> 0x0066 }
            r0 = r1
        L_0x0066:
            if (r0 != 0) goto L_0x006a
            java.lang.String r0 = ""
        L_0x006a:
            return r0
        L_0x006b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.n():java.lang.String");
    }

    public static String o() {
        String str = "";
        try {
            str = Build.SERIAL;
        } catch (Exception unused) {
        }
        return str == null ? "" : str;
    }

    public static String p() {
        String str = "";
        try {
            str = Locale.getDefault().toString();
        } catch (Exception unused) {
        }
        return str == null ? "" : str;
    }

    public static String q() {
        String str = "";
        try {
            str = TimeZone.getDefault().getDisplayName(false, 0);
        } catch (Exception unused) {
        }
        return str == null ? "" : str;
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
        } catch (Exception unused) {
            return "";
        }
    }

    public static long r() {
        try {
            if (b == 0) {
                b = System.currentTimeMillis() - SystemClock.elapsedRealtime();
            }
        } catch (Exception unused) {
        }
        return b;
    }

    public static String s(Context context) {
        if (!((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure()) {
            return "0:0";
        }
        String[] strArr = {"/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key"};
        long j = 0;
        for (int i = 0; i < 5; i++) {
            long j2 = -1;
            try {
                j2 = new File(strArr[i]).lastModified();
            } catch (Exception unused) {
            }
            j = Math.max(j2, j);
        }
        return "1:" + j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002b A[Catch:{ Exception -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e A[Catch:{ Exception -> 0x0040 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String t(android.content.Context r3) {
        /*
            android.content.IntentFilter r0 = new android.content.IntentFilter     // Catch:{ Exception -> 0x0040 }
            java.lang.String r1 = "android.intent.action.BATTERY_CHANGED"
            r0.<init>(r1)     // Catch:{ Exception -> 0x0040 }
            r1 = 0
            android.content.Intent r3 = r3.registerReceiver(r1, r0)     // Catch:{ Exception -> 0x0040 }
            java.lang.String r0 = "level"
            r1 = -1
            int r0 = r3.getIntExtra(r0, r1)     // Catch:{ Exception -> 0x0040 }
            java.lang.String r2 = "status"
            int r3 = r3.getIntExtra(r2, r1)     // Catch:{ Exception -> 0x0040 }
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
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0040 }
            r1.<init>()     // Catch:{ Exception -> 0x0040 }
            if (r3 == 0) goto L_0x002e
            java.lang.String r3 = "1"
            goto L_0x0030
        L_0x002e:
            java.lang.String r3 = "0"
        L_0x0030:
            r1.append(r3)     // Catch:{ Exception -> 0x0040 }
            java.lang.String r3 = ":"
            r1.append(r3)     // Catch:{ Exception -> 0x0040 }
            r1.append(r0)     // Catch:{ Exception -> 0x0040 }
            java.lang.String r3 = r1.toString()     // Catch:{ Exception -> 0x0040 }
            return r3
        L_0x0040:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.t(android.content.Context):java.lang.String");
    }

    public static String s() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(SystemClock.elapsedRealtime());
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String t() {
        try {
            StringBuilder sb = new StringBuilder();
            String[] strArr = {"/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd"};
            sb.append("00" + ":");
            for (int i = 0; i < 7; i++) {
                if (new File(strArr[i]).exists()) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String u() {
        String[] strArr = {"dalvik.system.Taint"};
        StringBuilder sb = new StringBuilder();
        sb.append("00");
        sb.append(":");
        for (int i = 0; i <= 0; i++) {
            try {
                Class.forName(strArr[0]);
                sb.append("1");
            } catch (Exception unused) {
                sb.append("0");
            }
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0085 A[SYNTHETIC, Splitter:B:23:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008e A[SYNTHETIC, Splitter:B:30:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x003d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String v() {
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
            java.io.LineNumberReader r6 = new java.io.LineNumberReader     // Catch:{ Exception -> 0x0089, all -> 0x007f }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0089, all -> 0x007f }
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0089, all -> 0x007f }
            r8.<init>(r3)     // Catch:{ Exception -> 0x0089, all -> 0x007f }
            r7.<init>(r8)     // Catch:{ Exception -> 0x0089, all -> 0x007f }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0089, all -> 0x007f }
        L_0x005b:
            java.lang.String r4 = r6.readLine()     // Catch:{ Exception -> 0x007d, all -> 0x007a }
            if (r4 == 0) goto L_0x0073
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ Exception -> 0x007d, all -> 0x007a }
            java.lang.Object r7 = r2.get(r3)     // Catch:{ Exception -> 0x007d, all -> 0x007a }
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ Exception -> 0x007d, all -> 0x007a }
            boolean r4 = r4.contains(r7)     // Catch:{ Exception -> 0x007d, all -> 0x007a }
            if (r4 == 0) goto L_0x005b
            r5 = 49
        L_0x0073:
            r1.append(r5)
            r6.close()     // Catch:{ Exception -> 0x003d }
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
            r4.close()     // Catch:{ Exception -> 0x0088 }
        L_0x0088:
            throw r0
        L_0x0089:
            r1.append(r5)
            if (r4 == 0) goto L_0x003d
            r4.close()     // Catch:{ Exception -> 0x003d }
            goto L_0x003d
        L_0x0092:
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.k.v():java.lang.String");
    }

    public static String w() {
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
            } catch (Exception unused) {
            } catch (Throwable th) {
                sb.append('0');
                throw th;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String x() {
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
            String b2 = e.b(str, "");
            if (b2 != null && b2.contains(str2)) {
                c = '1';
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String r(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            String str = null;
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    str = "WIFI";
                } else if (activeNetworkInfo.getType() == 0) {
                    int subtype = activeNetworkInfo.getSubtype();
                    if (!(subtype == 4 || subtype == 1 || subtype == 2 || subtype == 7)) {
                        if (subtype != 11) {
                            if (!(subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 9 || subtype == 10 || subtype == 12 || subtype == 14)) {
                                if (subtype != 15) {
                                    str = subtype == 13 ? "4G" : "UNKNOW";
                                }
                            }
                            str = "3G";
                        }
                    }
                    str = "2G";
                }
            }
            String A = A();
            if (!e.b(str) || !e.b(A)) {
                return "";
            }
            return str + ":" + A();
        } catch (Exception unused) {
            return "";
        }
    }
}
