package com.mibi.common.data;

import android.app.ActivityManager;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.alipay.android.phone.a.a.a;
import com.taobao.weex.common.Constants;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Locale;
import miuipub.os.SystemProperties;
import org.json.JSONException;
import org.json.JSONObject;

public class Client {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7499a = "Client";
    private static final String b = "content://com.miui.analytics.server.AnalyticsProvider";
    private static final String c = "getDeviceValidationToken";
    private static final String d = "device_token_json";
    private static final String e = "token";
    private static Context f;
    private static DisplayInfo g;
    private static TelephonyInfo h;
    private static AppInfo i;

    public static String D() {
        return "";
    }

    public static String E() {
        return "";
    }

    public static String q() {
        return "MIUI";
    }

    public static int y() {
        return 6;
    }

    public static void a(Context context) {
        f = context.getApplicationContext();
    }

    private static Context N() {
        return f;
    }

    public static boolean b(Context context) {
        if (d()) {
            return Utils.d(context);
        }
        return Utils.c(context);
    }

    public static int c(Context context) {
        return Utils.e(context);
    }

    public static String a() {
        return Locale.getDefault().getLanguage();
    }

    public static String b() {
        return Locale.getDefault().getCountry();
    }

    public static boolean c() {
        return l() >= 11;
    }

    public static boolean d() {
        return l() >= 16;
    }

    public static boolean e() {
        return !TextUtils.isEmpty(SystemProperties.a("ro.miui.ui.version.code", ""));
    }

    public static String f() {
        return Build.MODEL;
    }

    public static String g() {
        return Build.DEVICE;
    }

    public static String h() {
        return Build.PRODUCT;
    }

    public static String i() {
        return Build.MANUFACTURER;
    }

    public static String j() {
        return Build.BRAND;
    }

    public static String k() {
        return Build.TYPE;
    }

    public static int l() {
        return Build.VERSION.SDK_INT;
    }

    public static String m() {
        if (MiuiBuild.a()) {
            return "alpha";
        }
        if (MiuiBuild.b()) {
            return "development";
        }
        if (MiuiBuild.c()) {
            return Constants.Name.STABLE;
        }
        return !e() ? "android" : "";
    }

    public static String n() {
        return Build.VERSION.INCREMENTAL;
    }

    public static String o() {
        return Build.VERSION.RELEASE;
    }

    public static String p() {
        return e() ? "MIUI" : a.f813a;
    }

    public static String r() {
        return Build.HARDWARE;
    }

    public static String s() {
        return Build.DISPLAY;
    }

    public static String t() {
        return Build.TAGS;
    }

    public static String u() {
        return Build.HOST;
    }

    public static String v() {
        return Build.VERSION.CODENAME;
    }

    public static String w() {
        String a2 = SystemProperties.a("ro.miui.ui.version.name");
        return TextUtils.isEmpty(a2) ? "" : a2;
    }

    public static int x() {
        return SystemProperties.a("ro.miui.ui.version.code", 0);
    }

    public static class DisplayInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public int f7502a;
        /* access modifiers changed from: private */
        public int b;
        /* access modifiers changed from: private */
        public int c;
        /* access modifiers changed from: private */
        public float d;
        /* access modifiers changed from: private */
        public int e;

        public int a() {
            return this.f7502a;
        }

        public int b() {
            return this.b;
        }

        public String c() {
            return this.b + "*" + this.f7502a;
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

    public static DisplayInfo z() {
        if (g != null) {
            return g;
        }
        g = new DisplayInfo();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) N().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int unused = g.b = displayMetrics.heightPixels;
        int unused2 = g.f7502a = displayMetrics.widthPixels;
        int unused3 = g.c = displayMetrics.densityDpi;
        float unused4 = g.d = displayMetrics.density;
        int unused5 = g.e = N().getResources().getConfiguration().screenLayout & 15;
        return g;
    }

    public static class TelephonyInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f7503a = "";
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
        private String n = "";

        public String a() {
            return this.f7503a;
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

    private static void O() {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) N().getSystemService("phone");
        if (telephonyManager.getSimState() == 5) {
            String unused = h.f7503a = telephonyManager.getSimOperator();
            String unused2 = h.b = telephonyManager.getSimOperatorName();
            String unused3 = h.c = telephonyManager.getSimCountryIso();
        }
        String networkOperator = telephonyManager.getNetworkOperator();
        if (!TextUtils.isEmpty(networkOperator)) {
            String unused4 = h.d = networkOperator;
            String unused5 = h.e = telephonyManager.getNetworkOperatorName();
            String unused6 = h.f = telephonyManager.getNetworkCountryIso();
        }
        String str2 = null;
        if (PermissionUtils.a(N(), "android.permission.READ_PHONE_STATE")) {
            str2 = telephonyManager.getSimSerialNumber();
            str = telephonyManager.getSubscriberId();
        } else {
            Log.i("Client", "Could not get iccid and subscriberId as has no READ_PHONE_STATE permission");
            str = null;
        }
        if (!TextUtils.isEmpty(str2)) {
            String unused7 = h.h = str2;
        }
        if (!TextUtils.isEmpty(str)) {
            String unused8 = h.l = str;
        }
    }

    public static TelephonyInfo A() {
        String str;
        if (h != null) {
            O();
            return h;
        }
        TelephonyManager telephonyManager = (TelephonyManager) N().getSystemService("phone");
        h = new TelephonyInfo();
        int unused = h.g = telephonyManager.getPhoneType();
        String str2 = null;
        if (PermissionUtils.a(N(), "android.permission.READ_PHONE_STATE")) {
            str2 = telephonyManager.getDeviceId();
            str = telephonyManager.getDeviceSoftwareVersion();
        } else {
            Log.i("Client", "Could not get deviceId and deviceSoftwareVersion as has no READ_PHONE_STATE permission");
            str = null;
        }
        if (!TextUtils.isEmpty(str2)) {
            String unused2 = h.i = str2;
            String unused3 = h.j = Coder.b(str2);
            String unused4 = h.k = Coder.c(str2);
        }
        if (!TextUtils.isEmpty(str)) {
            String unused5 = h.m = str;
        }
        O();
        return h;
    }

    public static class WifiNetworkInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f7504a = "";
        /* access modifiers changed from: private */
        public String b = "";
        /* access modifiers changed from: private */
        public String c = "";
        /* access modifiers changed from: private */
        public String d = "";

        public String a() {
            return this.f7504a;
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
        int i2;
        DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
        if (dhcpInfo == null || (i2 = dhcpInfo.gateway) == 0) {
            return "";
        }
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            i2 = Integer.reverseBytes(i2);
        }
        try {
            return InetAddress.getByAddress(BigInteger.valueOf((long) i2).toByteArray()).getHostAddress();
        } catch (UnknownHostException e2) {
            Log.e("Client", "exception: " + e2.toString() + " message: " + e2.getMessage());
            return "";
        }
    }

    public static WifiNetworkInfo B() {
        WifiNetworkInfo wifiNetworkInfo = new WifiNetworkInfo();
        WifiManager wifiManager = (WifiManager) N().getSystemService("wifi");
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo != null) {
            String unused = wifiNetworkInfo.f7504a = connectionInfo.getMacAddress();
            String unused2 = wifiNetworkInfo.b = connectionInfo.getSSID();
            String unused3 = wifiNetworkInfo.c = connectionInfo.getBSSID();
        }
        String unused4 = wifiNetworkInfo.d = a(wifiManager);
        return wifiNetworkInfo;
    }

    public static String C() {
        return Settings.Secure.getString(N().getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
    }

    public static class AppInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f7500a = "";
        /* access modifiers changed from: private */
        public String b = "";
        /* access modifiers changed from: private */
        public int c = -1;
        /* access modifiers changed from: private */
        public String d = "";
        /* access modifiers changed from: private */
        public String e = "";
        /* access modifiers changed from: private */
        public String f = "";

        public String a() {
            return this.f7500a;
        }

        public int b() {
            return this.c;
        }

        public String c() {
            return this.b;
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
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mibi.common.data.Client.AppInfo F() {
        /*
            com.mibi.common.data.Client$AppInfo r0 = i
            if (r0 == 0) goto L_0x0007
            com.mibi.common.data.Client$AppInfo r0 = i
            return r0
        L_0x0007:
            com.mibi.common.data.Client$AppInfo r0 = new com.mibi.common.data.Client$AppInfo
            r0.<init>()
            i = r0
            com.mibi.common.data.Client$AppInfo r0 = i
            android.content.Context r1 = N()
            java.lang.String r1 = r1.getPackageName()
            java.lang.String unused = r0.e = r1
            android.content.Context r0 = N()
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            r1 = 0
            com.mibi.common.data.Client$AppInfo r2 = i     // Catch:{ NameNotFoundException -> 0x003f }
            java.lang.String r2 = r2.e     // Catch:{ NameNotFoundException -> 0x003f }
            r3 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r2 = r0.getApplicationInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x003f }
            com.mibi.common.data.Client$AppInfo r3 = i     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r3 = r3.e     // Catch:{ NameNotFoundException -> 0x003d }
            r4 = 64
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x003d }
            goto L_0x0068
        L_0x003d:
            r0 = move-exception
            goto L_0x0041
        L_0x003f:
            r0 = move-exception
            r2 = r1
        L_0x0041:
            java.lang.String r3 = "Client"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "exception: "
            r4.append(r5)
            java.lang.String r5 = r0.toString()
            r4.append(r5)
            java.lang.String r5 = " message: "
            r4.append(r5)
            java.lang.String r0 = r0.getMessage()
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            android.util.Log.e(r3, r0)
            r0 = r1
        L_0x0068:
            if (r2 == 0) goto L_0x0097
            android.os.Bundle r1 = r2.metaData
            if (r1 == 0) goto L_0x0097
            com.mibi.common.data.Client$AppInfo r1 = i
            android.os.Bundle r2 = r2.metaData
            java.lang.String r3 = "payment_version"
            java.lang.String r2 = r2.getString(r3)
            java.lang.String unused = r1.f7500a = r2
            java.lang.String r1 = "Client"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "old_version:"
            r2.append(r3)
            com.mibi.common.data.Client$AppInfo r3 = i
            java.lang.String r3 = r3.f7500a
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r1, r2)
        L_0x0097:
            if (r0 == 0) goto L_0x00d2
            com.mibi.common.data.Client$AppInfo r1 = i
            android.content.Context r2 = N()
            android.content.res.Resources r2 = r2.getResources()
            android.content.pm.ApplicationInfo r3 = r0.applicationInfo
            int r3 = r3.labelRes
            java.lang.String r2 = r2.getString(r3)
            java.lang.String unused = r1.d = r2
            com.mibi.common.data.Client$AppInfo r1 = i
            android.content.pm.Signature[] r2 = r0.signatures
            r3 = 0
            r2 = r2[r3]
            char[] r2 = r2.toChars()
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r2 = com.mibi.common.data.Coder.b((java.lang.String) r2)
            java.lang.String unused = r1.f = r2
            com.mibi.common.data.Client$AppInfo r1 = i
            java.lang.String r2 = r0.versionName
            java.lang.String unused = r1.b = r2
            com.mibi.common.data.Client$AppInfo r1 = i
            int r0 = r0.versionCode
            int unused = r1.c = r0
        L_0x00d2:
            com.mibi.common.data.Client$AppInfo r0 = i
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.Client.F():com.mibi.common.data.Client$AppInfo");
    }

    public static String G() {
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) N().getSystemService("activity")).getDeviceConfigurationInfo();
        if (deviceConfigurationInfo != null) {
            return deviceConfigurationInfo.toString();
        }
        return "";
    }

    public static String H() {
        ArrayList arrayList = new ArrayList();
        for (Sensor type : ((SensorManager) N().getSystemService("sensor")).getSensorList(-1)) {
            arrayList.add(Integer.valueOf(type.getType()));
        }
        return TextUtils.join(",", arrayList);
    }

    public static long I() {
        return SystemClock.elapsedRealtime();
    }

    public static long J() {
        return SystemClock.uptimeMillis();
    }

    public static long K() {
        return System.currentTimeMillis() - SystemClock.elapsedRealtime();
    }

    public static class CPUInfo {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f7501a = "";
        /* access modifiers changed from: private */
        public String b = "";
        /* access modifiers changed from: private */
        public String c = "";
        /* access modifiers changed from: private */
        public String d = "";

        public String a() {
            return this.f7501a;
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

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0085, code lost:
        if (r2 != null) goto L_0x0071;
     */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008c A[SYNTHETIC, Splitter:B:40:0x008c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mibi.common.data.Client.CPUInfo L() {
        /*
            com.mibi.common.data.Client$CPUInfo r0 = new com.mibi.common.data.Client$CPUInfo
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
            java.lang.String unused = r0.f7501a = r1     // Catch:{ IOException -> 0x0075 }
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
            r2.close()     // Catch:{ IOException -> 0x0088 }
            goto L_0x0088
        L_0x0075:
            r1 = move-exception
            goto L_0x007e
        L_0x0077:
            r0 = move-exception
            r2 = r1
            goto L_0x008a
        L_0x007a:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L_0x007e:
            java.lang.String r3 = "Client"
            java.lang.String r4 = "Error when fetch cpu info"
            android.util.Log.e(r3, r4, r1)     // Catch:{ all -> 0x0089 }
            if (r2 == 0) goto L_0x0088
            goto L_0x0071
        L_0x0088:
            return r0
        L_0x0089:
            r0 = move-exception
        L_0x008a:
            if (r2 == 0) goto L_0x008f
            r2.close()     // Catch:{ IOException -> 0x008f }
        L_0x008f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.Client.L():com.mibi.common.data.Client$CPUInfo");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String M() {
        /*
            java.lang.String r0 = ""
            android.content.Context r1 = N()
            android.content.ContentResolver r1 = r1.getContentResolver()
            r2 = 0
            java.lang.String r3 = "content://com.miui.analytics.server.AnalyticsProvider"
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ Exception -> 0x002a }
            android.content.ContentProviderClient r1 = r1.acquireContentProviderClient(r3)     // Catch:{ Exception -> 0x002a }
            java.lang.String r2 = a((android.content.ContentProviderClient) r1)     // Catch:{ Exception -> 0x0023, all -> 0x0020 }
            if (r1 == 0) goto L_0x001e
            r1.release()
        L_0x001e:
            r0 = r2
            goto L_0x0037
        L_0x0020:
            r0 = move-exception
            r2 = r1
            goto L_0x0038
        L_0x0023:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x002b
        L_0x0028:
            r0 = move-exception
            goto L_0x0038
        L_0x002a:
            r1 = move-exception
        L_0x002b:
            java.lang.String r3 = "Client"
            java.lang.String r4 = "Error when get device token"
            android.util.Log.e(r3, r4, r1)     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x0037
            r2.release()
        L_0x0037:
            return r0
        L_0x0038:
            if (r2 == 0) goto L_0x003d
            r2.release()
        L_0x003d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mibi.common.data.Client.M():java.lang.String");
    }

    private static String a(ContentProviderClient contentProviderClient) throws RemoteException, JSONException {
        Bundle call;
        String string;
        if (contentProviderClient == null || (call = contentProviderClient.call(c, "", new Bundle())) == null || (string = call.getString(d)) == null) {
            return "";
        }
        return new JSONObject(string).getString("token");
    }
}
