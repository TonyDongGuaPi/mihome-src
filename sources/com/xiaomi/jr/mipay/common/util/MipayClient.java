package com.xiaomi.jr.mipay.common.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.RemoteException;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.jr.common.os.SystemProperties;
import com.xiaomi.jr.common.utils.HashUtils;
import com.xiaomi.jr.common.utils.MifiLog;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class MipayClient {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10953a = "MipayClient";
    private static DisplayInfo b = null;
    private static TelephonyInfo c = null;
    private static final String d = "content://com.miui.analytics.server.AnalyticsProvider";
    private static final String e = "getDeviceValidationToken";
    private static final String f = "device_token_json";
    private static final String g = "token";

    public static boolean a(Context context) {
        if (d()) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager.getActiveNetworkInfo() == null || !connectivityManager.isActiveNetworkMetered()) {
                return false;
            }
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 0) {
            return false;
        }
        return true;
    }

    public static String a() {
        return Locale.getDefault().getLanguage();
    }

    public static String b() {
        return Locale.getDefault().getCountry();
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 11;
    }

    public static boolean d() {
        return Build.VERSION.SDK_INT >= 16;
    }

    public static String e() {
        String a2 = SystemProperties.a("ro.miui.ui.version.name");
        return TextUtils.isEmpty(a2) ? "" : a2;
    }

    public static int f() {
        return SystemProperties.a("ro.miui.ui.version.code", 0);
    }

    public static String g() {
        String str;
        try {
            str = BluetoothAdapter.getDefaultAdapter().getAddress();
        } catch (SecurityException e2) {
            e2.printStackTrace();
            str = null;
        }
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static class DisplayInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f10955a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public float d;
        /* access modifiers changed from: private */
        public int e;

        public int a() {
            return this.f10955a;
        }

        public int b() {
            return this.b;
        }

        public String c() {
            return this.b + "*" + this.f10955a;
        }

        public int d() {
            return this.c;
        }

        public float e() {
            return this.d;
        }

        public int f() {
            return this.e;
        }
    }

    public static DisplayInfo b(Context context) {
        if (b != null) {
            return b;
        }
        b = new DisplayInfo();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int unused = b.b = displayMetrics.heightPixels;
        int unused2 = b.f10955a = displayMetrics.widthPixels;
        int unused3 = b.c = displayMetrics.densityDpi;
        float unused4 = b.d = displayMetrics.density;
        int unused5 = b.e = context.getResources().getConfiguration().screenLayout & 15;
        return b;
    }

    public static class TelephonyInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f10956a = "";
        /* access modifiers changed from: private */
        public String b = "";
        /* access modifiers changed from: private */
        public String c = "";
        /* access modifiers changed from: private */
        public String d = "";
        /* access modifiers changed from: private */
        public String e = "";
        /* access modifiers changed from: private */
        public String f = "";
        /* access modifiers changed from: private */
        public int g;
        /* access modifiers changed from: private */
        public String h = "";
        /* access modifiers changed from: private */
        public String i = "";
        /* access modifiers changed from: private */
        public String j = "";
        /* access modifiers changed from: private */
        public String k = "";
        /* access modifiers changed from: private */
        public String l = "";
        /* access modifiers changed from: private */
        public String m = "";
        /* access modifiers changed from: private */
        public String n = "";

        public String a() {
            return this.f10956a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }

        public String e() {
            return this.e;
        }

        public String f() {
            return this.f;
        }

        public int g() {
            return this.g;
        }

        public String h() {
            return this.h;
        }

        public String i() {
            return this.i;
        }

        public String j() {
            return this.j;
        }

        public String k() {
            return this.k;
        }

        public String l() {
            return this.l;
        }

        public String m() {
            return this.m;
        }

        public String n() {
            return this.n;
        }
    }

    @SuppressLint({"MissingPermission"})
    private static void j(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager.getSimState() == 5) {
            String unused = c.f10956a = telephonyManager.getSimOperator();
            String unused2 = c.b = telephonyManager.getSimOperatorName();
            String unused3 = c.c = telephonyManager.getSimCountryIso();
        }
        String networkOperator = telephonyManager.getNetworkOperator();
        if (!TextUtils.isEmpty(networkOperator)) {
            String unused4 = c.d = networkOperator;
            String unused5 = c.e = telephonyManager.getNetworkOperatorName();
            String unused6 = c.f = telephonyManager.getNetworkCountryIso();
        }
        String simSerialNumber = telephonyManager.getSimSerialNumber();
        if (!TextUtils.isEmpty(simSerialNumber)) {
            String unused7 = c.h = simSerialNumber;
        }
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation != null) {
            String unused8 = c.n = cellLocation.toString();
        }
        String subscriberId = telephonyManager.getSubscriberId();
        if (!TextUtils.isEmpty(subscriberId)) {
            String unused9 = c.l = subscriberId;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static TelephonyInfo c(Context context) {
        try {
            if (c != null) {
                j(context);
                return c;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            c = new TelephonyInfo();
            int unused = c.g = telephonyManager.getPhoneType();
            String deviceId = telephonyManager.getDeviceId();
            if (!TextUtils.isEmpty(deviceId)) {
                String unused2 = c.i = deviceId;
                String unused3 = c.j = HashUtils.a(deviceId);
                String unused4 = c.k = Coder.a(deviceId);
            }
            String deviceSoftwareVersion = telephonyManager.getDeviceSoftwareVersion();
            if (!TextUtils.isEmpty(deviceSoftwareVersion)) {
                String unused5 = c.m = deviceSoftwareVersion;
            }
            j(context);
            return c;
        } catch (SecurityException e2) {
            e2.printStackTrace();
        }
    }

    public static class WifiNetworkInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f10957a = "";
        /* access modifiers changed from: private */
        public String b = "";
        /* access modifiers changed from: private */
        public String c = "";
        /* access modifiers changed from: private */
        public String d = "";

        public String a() {
            return this.f10957a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }
    }

    private static String a(WifiManager wifiManager) {
        int i;
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        if (dhcpInfo == null || (i = dhcpInfo.gateway) == 0) {
            return "";
        }
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            i = Integer.reverseBytes(i);
        }
        try {
            return InetAddress.getByAddress(BigInteger.valueOf((long) i).toByteArray()).getHostAddress();
        } catch (UnknownHostException e2) {
            MifiLog.a(f10953a, "unknown host", (Throwable) e2);
            return "";
        }
    }

    public static WifiNetworkInfo d(Context context) {
        WifiNetworkInfo wifiNetworkInfo = new WifiNetworkInfo();
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo != null) {
            String unused = wifiNetworkInfo.f10957a = connectionInfo.getMacAddress();
            String unused2 = wifiNetworkInfo.b = connectionInfo.getSSID();
            String unused3 = wifiNetworkInfo.c = connectionInfo.getBSSID();
        }
        String unused4 = wifiNetworkInfo.d = a(wifiManager);
        return wifiNetworkInfo;
    }

    public static String e(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
    }

    public static String f(Context context) {
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo();
        if (deviceConfigurationInfo != null) {
            return deviceConfigurationInfo.toString();
        }
        return "";
    }

    public static String g(Context context) {
        ArrayList arrayList = new ArrayList();
        for (Sensor type : ((SensorManager) context.getSystemService("sensor")).getSensorList(-1)) {
            arrayList.add(Integer.valueOf(type.getType()));
        }
        return TextUtils.join(",", arrayList);
    }

    public static long h() {
        return SystemClock.elapsedRealtime();
    }

    public static long i() {
        return SystemClock.uptimeMillis();
    }

    public static long j() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static class CPUInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f10954a = "";
        /* access modifiers changed from: private */
        public String b = "";
        /* access modifiers changed from: private */
        public String c = "";
        /* access modifiers changed from: private */
        public String d = "";

        public String a() {
            return this.f10954a;
        }

        public String b() {
            return this.b;
        }

        public String c() {
            return this.c;
        }

        public String d() {
            return this.d;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0087 A[SYNTHETIC, Splitter:B:36:0x0087] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0097 A[SYNTHETIC, Splitter:B:43:0x0097] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.jr.mipay.common.util.MipayClient.CPUInfo k() {
        /*
            com.xiaomi.jr.mipay.common.util.MipayClient$CPUInfo r0 = new com.xiaomi.jr.mipay.common.util.MipayClient$CPUInfo
            r0.<init>()
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x007a, all -> 0x0077 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ IOException -> 0x007a, all -> 0x0077 }
            java.lang.String r4 = "/proc/cpuinfo"
            r3.<init>(r4)     // Catch:{ IOException -> 0x007a, all -> 0x0077 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x007a, all -> 0x0077 }
        L_0x0012:
            java.lang.String r1 = r2.readLine()     // Catch:{ IOException -> 0x0075 }
            if (r1 == 0) goto L_0x0071
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch:{ IOException -> 0x0075 }
            if (r3 == 0) goto L_0x001f
            goto L_0x0012
        L_0x001f:
            java.lang.String r3 = ":"
            java.lang.String[] r1 = r1.split(r3)     // Catch:{ IOException -> 0x0075 }
            int r3 = r1.length     // Catch:{ IOException -> 0x0075 }
            r4 = 2
            if (r3 != r4) goto L_0x0012
            r3 = 0
            r3 = r1[r3]     // Catch:{ IOException -> 0x0075 }
            java.lang.String r3 = r3.trim()     // Catch:{ IOException -> 0x0075 }
            r4 = 1
            r1 = r1[r4]     // Catch:{ IOException -> 0x0075 }
            java.lang.String r1 = r1.trim()     // Catch:{ IOException -> 0x0075 }
            java.lang.String r4 = "Processor"
            boolean r4 = android.text.TextUtils.equals(r3, r4)     // Catch:{ IOException -> 0x0075 }
            if (r4 == 0) goto L_0x0043
            java.lang.String unused = r0.f10954a = r1     // Catch:{ IOException -> 0x0075 }
            goto L_0x0012
        L_0x0043:
            java.lang.String r4 = r0.b     // Catch:{ IOException -> 0x0075 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IOException -> 0x0075 }
            if (r4 == 0) goto L_0x0059
            java.lang.String r4 = "BogoMIPS"
            boolean r4 = android.text.TextUtils.equals(r3, r4)     // Catch:{ IOException -> 0x0075 }
            if (r4 == 0) goto L_0x0059
            java.lang.String unused = r0.b = r1     // Catch:{ IOException -> 0x0075 }
            goto L_0x0012
        L_0x0059:
            java.lang.String r4 = "Hardware"
            boolean r4 = android.text.TextUtils.equals(r3, r4)     // Catch:{ IOException -> 0x0075 }
            if (r4 == 0) goto L_0x0065
            java.lang.String unused = r0.c = r1     // Catch:{ IOException -> 0x0075 }
            goto L_0x0012
        L_0x0065:
            java.lang.String r4 = "Serial"
            boolean r3 = android.text.TextUtils.equals(r3, r4)     // Catch:{ IOException -> 0x0075 }
            if (r3 == 0) goto L_0x0012
            java.lang.String unused = r0.d = r1     // Catch:{ IOException -> 0x0075 }
            goto L_0x0012
        L_0x0071:
            r2.close()     // Catch:{ IOException -> 0x008b }
            goto L_0x0093
        L_0x0075:
            r1 = move-exception
            goto L_0x007e
        L_0x0077:
            r0 = move-exception
            r2 = r1
            goto L_0x0095
        L_0x007a:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x007e:
            java.lang.String r3 = "MipayClient"
            java.lang.String r4 = "Error when fetch cpu info"
            com.xiaomi.jr.common.utils.MifiLog.e(r3, r4, r1)     // Catch:{ all -> 0x0094 }
            if (r2 == 0) goto L_0x0093
            r2.close()     // Catch:{ IOException -> 0x008b }
            goto L_0x0093
        L_0x008b:
            r1 = move-exception
            java.lang.String r2 = "MipayClient"
            java.lang.String r3 = "failed to close reader"
            com.xiaomi.jr.common.utils.MifiLog.e(r2, r3, r1)
        L_0x0093:
            return r0
        L_0x0094:
            r0 = move-exception
        L_0x0095:
            if (r2 == 0) goto L_0x00a3
            r2.close()     // Catch:{ IOException -> 0x009b }
            goto L_0x00a3
        L_0x009b:
            r1 = move-exception
            java.lang.String r2 = "MipayClient"
            java.lang.String r3 = "failed to close reader"
            com.xiaomi.jr.common.utils.MifiLog.e(r2, r3, r1)
        L_0x00a3:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.mipay.common.util.MipayClient.k():com.xiaomi.jr.mipay.common.util.MipayClient$CPUInfo");
    }

    public static String h(Context context) {
        long j;
        long j2;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            j = statFs.getBlockSizeLong();
            j2 = statFs.getAvailableBlocksLong();
        } else {
            j = (long) statFs.getBlockSize();
            j2 = (long) statFs.getAvailableBlocks();
        }
        return Formatter.formatFileSize(context, j2 * j);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String i(android.content.Context r5) {
        /*
            java.lang.String r0 = ""
            android.content.ContentResolver r5 = r5.getContentResolver()
            r1 = 0
            java.lang.String r2 = "content://com.miui.analytics.server.AnalyticsProvider"
            android.net.Uri r2 = android.net.Uri.parse(r2)     // Catch:{ Exception -> 0x0026 }
            android.content.ContentProviderClient r5 = r5.acquireContentProviderClient(r2)     // Catch:{ Exception -> 0x0026 }
            java.lang.String r1 = a((android.content.ContentProviderClient) r5)     // Catch:{ Exception -> 0x001f, all -> 0x001c }
            if (r5 == 0) goto L_0x001a
            r5.release()
        L_0x001a:
            r0 = r1
            goto L_0x0033
        L_0x001c:
            r0 = move-exception
            r1 = r5
            goto L_0x0034
        L_0x001f:
            r1 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
            goto L_0x0027
        L_0x0024:
            r0 = move-exception
            goto L_0x0034
        L_0x0026:
            r5 = move-exception
        L_0x0027:
            java.lang.String r2 = "MipayClient"
            java.lang.String r3 = "Error when get device token"
            com.xiaomi.jr.common.utils.MifiLog.e(r2, r3, r5)     // Catch:{ all -> 0x0024 }
            if (r1 == 0) goto L_0x0033
            r1.release()
        L_0x0033:
            return r0
        L_0x0034:
            if (r1 == 0) goto L_0x0039
            r1.release()
        L_0x0039:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.mipay.common.util.MipayClient.i(android.content.Context):java.lang.String");
    }

    private static String a(ContentProviderClient contentProviderClient) throws RemoteException, JSONException {
        if (contentProviderClient == null) {
            return "";
        }
        Bundle bundle = null;
        if (Build.VERSION.SDK_INT >= 17) {
            bundle = contentProviderClient.call(e, "", new Bundle());
        }
        if (bundle == null) {
            return "";
        }
        String string = bundle.getString(f);
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        return new JSONObject(string).getString("token");
    }
}
