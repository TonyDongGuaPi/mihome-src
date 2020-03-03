package com.mipay.common.data;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.Locale;

public class b {

    /* renamed from: a  reason: collision with root package name */
    public static int f8125a;
    public static String b;
    public static String c;
    public static int d;
    public static String e;
    public static String f;

    public static String a() {
        return Locale.getDefault().getLanguage();
    }

    public static void a(Context context) {
        e(context);
        f(context);
        d(context);
    }

    public static String b() {
        return Locale.getDefault().getCountry();
    }

    public static boolean b(Context context) {
        return d() ? i.d(context) : i.c(context);
    }

    public static int c(Context context) {
        return i.e(context);
    }

    public static boolean c() {
        return f8125a >= 11;
    }

    private static void d(Context context) {
        f8125a = Build.VERSION.SDK_INT;
    }

    public static boolean d() {
        return f8125a >= 16;
    }

    private static void e(Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        b = TextUtils.isEmpty(deviceId) ? "" : c.b(deviceId);
    }

    private static void f(Context context) {
        PackageInfo packageInfo;
        String b2;
        e = context.getPackageName();
        try {
            packageInfo = context.getPackageManager().getPackageInfo(e, 64);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            c = "";
            d = 0;
            b2 = "";
        } else {
            c = packageInfo.versionName;
            d = packageInfo.versionCode;
            b2 = c.b(String.valueOf(packageInfo.signatures[0].toChars()));
        }
        f = b2;
    }
}
