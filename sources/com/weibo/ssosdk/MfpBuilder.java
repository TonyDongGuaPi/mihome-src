package com.weibo.ssosdk;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.miuipub.internal.hybrid.SignUtils;
import com.sina.weibo.BuildConfig;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.market.sdk.Constants;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import java.io.UnsupportedEncodingException;
import java.net.NetworkInterface;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

public class MfpBuilder {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9854a = "weibo_aid_value";
    private static final String b = "MfpBuilder";

    public static void a(Context context) {
    }

    private static void a(String str) {
    }

    private static void b(String str) {
    }

    private static final class BatteryInfo {

        /* renamed from: a  reason: collision with root package name */
        private Intent f9855a;

        private BatteryInfo(Context context) {
            this.f9855a = context.registerReceiver((BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        }

        private int a() {
            return this.f9855a.getIntExtra("status", 0);
        }

        private int b() {
            return this.f9855a.getIntExtra("health", 1);
        }

        private boolean c() {
            return this.f9855a.getBooleanExtra("present", false);
        }

        /* access modifiers changed from: private */
        public int d() {
            return this.f9855a.getIntExtra("level", 0);
        }

        /* access modifiers changed from: private */
        public int e() {
            return this.f9855a.getIntExtra("scale", 0);
        }

        private int f() {
            return this.f9855a.getIntExtra("plugged", 0);
        }

        /* access modifiers changed from: private */
        public int g() {
            return this.f9855a.getIntExtra("voltage", 0);
        }

        /* access modifiers changed from: private */
        public int h() {
            return this.f9855a.getIntExtra(AreaPropInfo.n, 0);
        }

        private String i() {
            return this.f9855a.getStringExtra("technology");
        }
    }

    public static String b(Context context) {
        try {
            return new String(e(context).getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    private static String d(Context context) {
        StringBuilder sb = new StringBuilder();
        String packageName = context.getPackageName();
        String str = "ssosdk";
        if (!TextUtils.isEmpty(packageName) && packageName.contains(BuildConfig.b)) {
            str = "weibo";
        }
        sb.append(Build.MANUFACTURER);
        sb.append("-");
        sb.append(Build.MODEL);
        sb.append("__");
        sb.append(str);
        sb.append("__");
        try {
            sb.append("1.0".replaceAll("\\s+", JSMethod.NOT_SET));
        } catch (Exception unused) {
            sb.append("unknown");
        }
        sb.append("__");
        sb.append("android");
        sb.append("__android");
        sb.append(Build.VERSION.RELEASE);
        return sb.toString();
    }

    private static String e(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            String a2 = a();
            if (!TextUtils.isEmpty(a2)) {
                jSONObject.put("os", a2);
            }
            String f = f(context);
            if (!TextUtils.isEmpty(f)) {
                jSONObject.put("imei", f);
            }
            String g = g(context);
            if (!TextUtils.isEmpty(g)) {
                jSONObject.put("meid", g);
            }
            String h = h(context);
            if (!TextUtils.isEmpty(h)) {
                jSONObject.put("imsi", h);
            }
            String i = i(context);
            if (!TextUtils.isEmpty(i)) {
                jSONObject.put("mac", i);
            }
            String j = j(context);
            if (!TextUtils.isEmpty(j)) {
                jSONObject.put("iccid", j);
            }
            String c = c();
            if (!TextUtils.isEmpty(c)) {
                jSONObject.put("serial", c);
            }
            String l = l(context);
            if (!TextUtils.isEmpty(l)) {
                jSONObject.put("androidid", l);
            }
            String e = e();
            if (!TextUtils.isEmpty(e)) {
                jSONObject.put("cpu", e);
            }
            String f2 = f();
            if (!TextUtils.isEmpty(f2)) {
                jSONObject.put("model", f2);
            }
            String g2 = g();
            if (!TextUtils.isEmpty(g2)) {
                jSONObject.put("sdcard", g2);
            }
            String m = m(context);
            if (!TextUtils.isEmpty(m)) {
                jSONObject.put(Constants.x, m);
            }
            String n = n(context);
            if (!TextUtils.isEmpty(n)) {
                jSONObject.put(DeviceTagInterface.e, n);
            }
            String c2 = c(context);
            if (!TextUtils.isEmpty(c2)) {
                jSONObject.put("bssid", c2);
            }
            String h2 = h();
            if (!TextUtils.isEmpty(h2)) {
                jSONObject.put("deviceName", h2);
            }
            String o = o(context);
            if (!TextUtils.isEmpty(o)) {
                jSONObject.put("connecttype", o);
            }
            String str = "";
            try {
                str = d(context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("ua", str);
            }
            double k = k(context);
            jSONObject.put("batterymaxcapacity", String.valueOf(k));
            jSONObject.put("batterycurrentcapacity", String.valueOf(k));
            BatteryInfo batteryInfo = new BatteryInfo(context);
            jSONObject.put("batterycurrentvoltage", batteryInfo.g());
            jSONObject.put("batterycurrenttemperature", batteryInfo.h());
            double c3 = (double) batteryInfo.d();
            Double.isNaN(c3);
            double d = k * c3;
            double d2 = (double) batteryInfo.e();
            Double.isNaN(d2);
            jSONObject.put("batterycurrentcapacity", d / d2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005b A[SYNTHETIC, Splitter:B:18:0x005b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r4, java.lang.String r5) throws java.lang.Exception {
        /*
            java.lang.String r0 = "RSA/ECB/PKCS1Padding"
            javax.crypto.Cipher r0 = javax.crypto.Cipher.getInstance(r0)
            java.security.PublicKey r5 = c((java.lang.String) r5)
            r1 = 1
            r0.init(r1, r5)
            java.lang.String r5 = "UTF-8"
            byte[] r4 = r4.getBytes(r5)
            r5 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0057 }
            r1.<init>()     // Catch:{ all -> 0x0057 }
            r5 = 0
        L_0x001b:
            r2 = 117(0x75, float:1.64E-43)
            int r2 = a(r4, r5, r2)     // Catch:{ all -> 0x0055 }
            r3 = -1
            if (r2 == r3) goto L_0x002d
            byte[] r3 = r0.doFinal(r4, r5, r2)     // Catch:{ all -> 0x0055 }
            r1.write(r3)     // Catch:{ all -> 0x0055 }
            int r5 = r5 + r2
            goto L_0x001b
        L_0x002d:
            r1.flush()     // Catch:{ all -> 0x0055 }
            byte[] r4 = r1.toByteArray()     // Catch:{ all -> 0x0055 }
            r5 = 2
            byte[] r4 = android.util.Base64.encode(r4, r5)     // Catch:{ all -> 0x0055 }
            java.lang.String r5 = new java.lang.String     // Catch:{ all -> 0x0055 }
            java.lang.String r0 = "UTF-8"
            r5.<init>(r4, r0)     // Catch:{ all -> 0x0055 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0055 }
            r4.<init>()     // Catch:{ all -> 0x0055 }
            java.lang.String r0 = "01"
            r4.append(r0)     // Catch:{ all -> 0x0055 }
            r4.append(r5)     // Catch:{ all -> 0x0055 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0055 }
            r1.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0054:
            return r4
        L_0x0055:
            r4 = move-exception
            goto L_0x0059
        L_0x0057:
            r4 = move-exception
            r1 = r5
        L_0x0059:
            if (r1 == 0) goto L_0x005e
            r1.close()     // Catch:{ IOException -> 0x005e }
        L_0x005e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.weibo.ssosdk.MfpBuilder.a(java.lang.String, java.lang.String):java.lang.String");
    }

    private static int a(byte[] bArr, int i, int i2) {
        if (i >= bArr.length) {
            return -1;
        }
        return Math.min(bArr.length - i, i2);
    }

    private static PublicKey c(String str) throws Exception {
        return KeyFactory.getInstance(SignUtils.f8267a).generatePublic(new X509EncodedKeySpec(Base64.decode(str.getBytes(), 2)));
    }

    private static String a() {
        try {
            return "Android " + Build.VERSION.RELEASE;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String f(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String g(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String h(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02X:", new Object[]{Byte.valueOf(bArr[i])}));
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private static String b() {
        try {
            for (T t : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (t.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = t.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r2 = r2.getConnectionInfo();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String i(android.content.Context r2) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 23
            if (r0 < r1) goto L_0x000b
            java.lang.String r2 = b()
            return r2
        L_0x000b:
            java.lang.String r0 = "wifi"
            java.lang.Object r2 = r2.getSystemService(r0)     // Catch:{ Exception -> 0x0026 }
            android.net.wifi.WifiManager r2 = (android.net.wifi.WifiManager) r2     // Catch:{ Exception -> 0x0026 }
            if (r2 != 0) goto L_0x0018
            java.lang.String r2 = ""
            return r2
        L_0x0018:
            android.net.wifi.WifiInfo r2 = r2.getConnectionInfo()     // Catch:{ Exception -> 0x0026 }
            if (r2 == 0) goto L_0x0023
            java.lang.String r2 = r2.getMacAddress()     // Catch:{ Exception -> 0x0026 }
            goto L_0x0025
        L_0x0023:
            java.lang.String r2 = ""
        L_0x0025:
            return r2
        L_0x0026:
            java.lang.String r2 = ""
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.weibo.ssosdk.MfpBuilder.i(android.content.Context):java.lang.String");
    }

    private static String j(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimSerialNumber();
        } catch (Exception unused) {
            return "";
        }
    }

    private static String c() {
        if (Build.VERSION.SDK_INT >= 26) {
            return d();
        }
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{"ro.serialno", "unknown"});
        } catch (Exception unused) {
            return "";
        }
    }

    private static double k(Context context) {
        Object obj;
        try {
            obj = Class.forName("com.android.internal.os.PowerProfile").getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
        } catch (Exception unused) {
            obj = null;
        }
        try {
            return ((Double) Class.forName("com.android.internal.os.PowerProfile").getMethod("getAveragePower", new Class[]{String.class}).invoke(obj, new Object[]{"battery.capacity"})).doubleValue();
        } catch (Exception unused2) {
            return 0.0d;
        }
    }

    @TargetApi(26)
    private static String d() {
        try {
            return Build.getSerial();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String l(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String e() {
        try {
            return Build.CPU_ABI;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String f() {
        try {
            return Build.MODEL;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String g() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.toString(((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize()));
        } catch (Exception unused) {
            return "";
        }
    }

    private static String m(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return String.valueOf(displayMetrics.widthPixels) + "*" + String.valueOf(displayMetrics.heightPixels);
        } catch (Exception unused) {
            return "";
        }
    }

    private static String n(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getSSID() : "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c(Context context) {
        try {
            WifiInfo connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            return connectionInfo != null ? connectionInfo.getBSSID() : "";
        } catch (SecurityException unused) {
            return "";
        }
    }

    private static String h() {
        try {
            return Build.BRAND;
        } catch (Exception unused) {
            return "";
        }
    }

    private static String o(Context context) {
        String str;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "none";
            }
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        str = "2G";
                        break;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        str = "3G";
                        break;
                    case 13:
                        str = "4G";
                        break;
                    default:
                        str = "none";
                        break;
                }
            } else if (activeNetworkInfo.getType() != 1) {
                return "none";
            } else {
                str = "wifi";
            }
            return str;
        } catch (Exception unused) {
            return "none";
        }
    }
}
