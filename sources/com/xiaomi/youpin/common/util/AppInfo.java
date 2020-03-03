package com.xiaomi.youpin.common.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.alipay.android.phone.a.a.a;
import com.taobao.weex.common.Constants;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.youpin.common.util.crypto.SHA1Util;
import com.xiaomi.youpin.log.LogUtils;
import java.util.Locale;
import java.util.regex.Pattern;

public class AppInfo {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1576a = "AppInfo";
    private static String b;
    private static String c;
    private static int d;
    private static String e;
    private static String f;
    private static Application g;
    private static Boolean h;

    public static String d() {
        return a.f813a;
    }

    public static void a(Application application) {
        g = application;
    }

    public static Application a() {
        return g;
    }

    public static String b() {
        if (b == null) {
            try {
                b = g.getPackageName();
            } catch (Exception unused) {
                LogUtils.w(f1576a, "Get Package Name failed. return com.xiaomi.youpin");
                b = "com.xiaomi.youpin";
            }
        }
        return b;
    }

    public static String c() {
        try {
            int i = g.getPackageManager().getPackageInfo(g.getPackageName(), 0).applicationInfo.labelRes;
            if (i != 0) {
                return g.getResources().getString(i);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String e() {
        if (!TextUtils.isEmpty(Build.VERSION.RELEASE)) {
            return Build.VERSION.RELEASE;
        }
        LogUtils.w(f1576a, "Unknown OS Version.");
        return "unknown";
    }

    public static String f() {
        if (c != null) {
            return c;
        }
        try {
            c = g.getPackageManager().getPackageInfo(g.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            LogUtils.w(f1576a, "Get Version Name failed. return 1.0.0");
            c = "1.0.0";
        }
        return c;
    }

    public static int g() {
        if (d == 0) {
            try {
                d = g.getPackageManager().getPackageInfo(g.getPackageName(), 0).versionCode;
            } catch (Exception unused) {
                LogUtils.w(f1576a, "Get Version Code failed. return 1.0.0");
            }
        }
        return d;
    }

    public static String h() {
        return Build.MODEL;
    }

    public static String i() {
        return Build.VERSION.INCREMENTAL;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:6|7) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r1 = r3.getBytes();
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.lang.String r3) {
        /*
            java.lang.String r0 = "MD5"
            java.security.MessageDigest r0 = java.security.MessageDigest.getInstance(r0)     // Catch:{ NoSuchAlgorithmException -> 0x002a }
            java.lang.String r1 = "UTF-8"
            byte[] r1 = r3.getBytes(r1)     // Catch:{ UnsupportedEncodingException -> 0x000d }
            goto L_0x0011
        L_0x000d:
            byte[] r1 = r3.getBytes()     // Catch:{ NoSuchAlgorithmException -> 0x002a }
        L_0x0011:
            r0.update(r1)     // Catch:{ NoSuchAlgorithmException -> 0x002a }
            java.math.BigInteger r3 = new java.math.BigInteger     // Catch:{ NoSuchAlgorithmException -> 0x002a }
            byte[] r0 = r0.digest()     // Catch:{ NoSuchAlgorithmException -> 0x002a }
            r1 = 1
            r3.<init>(r1, r0)     // Catch:{ NoSuchAlgorithmException -> 0x002a }
            java.lang.String r0 = "%1$032X"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ NoSuchAlgorithmException -> 0x002a }
            r2 = 0
            r1[r2] = r3     // Catch:{ NoSuchAlgorithmException -> 0x002a }
            java.lang.String r3 = java.lang.String.format(r0, r1)     // Catch:{ NoSuchAlgorithmException -> 0x002a }
            return r3
        L_0x002a:
            r3 = move-exception
            r3.printStackTrace()
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.common.util.AppInfo.a(java.lang.String):java.lang.String");
    }

    @SuppressLint({"HardwareIds"})
    public static String j() {
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) g.getSystemService("phone");
            if (telephonyManager == null) {
                return "";
            }
            e = telephonyManager.getDeviceId();
            if (b(e)) {
                e = "";
            }
            if (TextUtils.isEmpty(e)) {
                try {
                    e = Settings.Secure.getString(g.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
                } catch (Exception unused) {
                }
                if (b(e)) {
                    e = "";
                }
            }
            try {
                if (!TextUtils.isEmpty(e)) {
                    e = a(e);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (TextUtils.isEmpty(e)) {
                e = k();
            }
            return e;
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (Pattern.compile("[^0]").matcher(str).find() && !TextUtils.equals(str, "unknown") && !TextUtils.equals(str, Constants.Name.UNDEFINED)) {
            return false;
        }
        return true;
    }

    @SuppressLint({"HardwareIds"})
    public static String k() {
        String str;
        String str2;
        if (!TextUtils.isEmpty(f)) {
            return f;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) g.getSystemService("phone");
            if (telephonyManager == null) {
                return "";
            }
            str = telephonyManager.getDeviceId();
            if (b(str)) {
                str = "";
            }
            try {
                str2 = Settings.Secure.getString(g.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
                if (str2 == null) {
                    str2 = "";
                }
            } catch (Exception unused) {
                LogUtils.w(f1576a, "Get Android Id failed. return null");
                str2 = "";
            }
            if (b(str2)) {
                str2 = "";
            }
            String str3 = "";
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    str3 = Build.getSerial();
                } catch (Exception unused2) {
                }
            } else {
                str3 = Build.SERIAL;
            }
            if (b(str3)) {
                str3 = "";
            }
            try {
                if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
                    f = SHA1Util.a(str + str2 + str3);
                }
            } catch (Exception unused3) {
            }
            if (TextUtils.isEmpty(f)) {
                try {
                    f = SHA1Util.a(new HashedDeviceIdUtil(a()).getHashedDeviceIdNoThrow());
                } catch (Exception unused4) {
                }
            }
            return f;
        } catch (Exception unused5) {
            LogUtils.w(f1576a, "Get IMEI failed. return null");
            str = "";
        }
    }

    public static String l() {
        WindowManager windowManager = (WindowManager) g.getSystemService("window");
        if (windowManager == null) {
            return "";
        }
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return String.format(Locale.CHINA, "%dx%d", new Object[]{Integer.valueOf(point.x), Integer.valueOf(point.y)});
    }

    public static boolean m() {
        try {
            return Class.forName("miui.os.Build") != null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean n() {
        if (g == null) {
            return false;
        }
        if (h != null) {
            return h.booleanValue();
        }
        try {
            h = Boolean.valueOf((g.getApplicationInfo().flags & 2) != 0);
            return h.booleanValue();
        } catch (Exception unused) {
            return false;
        }
    }
}
