package com.xiaomi.miot.support.monitor.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.miot.support.monitor.Env;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommonUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11493a = "CommonUtils";
    private static final String b = "360_DEFAULT_IMEI";
    private static String c = "360_DEFAULT_IMEI";

    public static String a() {
        StringWriter stringWriter = new StringWriter();
        new Throwable().printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (TextUtils.isEmpty(stringWriter2)) {
            return "";
        }
        String[] split = stringWriter2.split("\n\tat");
        StringBuilder sb = new StringBuilder();
        int min = Math.min(14, split.length);
        for (int i = 4; i < min; i++) {
            sb.append(split[i]);
            sb.append("\n\tat");
        }
        return sb.toString();
    }

    public static String a(Context context) {
        if (context == null) {
            return "no-permission";
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getSubscriberId();
            }
            return "no-permission";
        } catch (Exception unused) {
            return "no-permission";
        }
    }

    public static String b(Context context) {
        String c2 = c(context);
        String string = Settings.System.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
        String b2 = b();
        return a(c2 + string + b2);
    }

    public static String a(String str) {
        return str == null ? "" : a(str.getBytes());
    }

    public static String a(byte[] bArr) {
        return c(b(bArr));
    }

    public static byte[] b(byte[] bArr) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            messageDigest = null;
        }
        if (messageDigest == null) {
            return null;
        }
        messageDigest.update(bArr);
        return messageDigest.digest();
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            sb.append("0123456789abcdef".charAt((b2 >> 4) & 15));
            sb.append("0123456789abcdef".charAt(b2 & 15));
        }
        return sb.toString();
    }

    private static String b() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.serialno"});
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c(Context context) {
        if (!TextUtils.equals(c, b)) {
            return c;
        }
        if (context != null) {
            f(context);
        }
        return c;
    }

    private static void f(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            try {
                if (telephonyManager.getDeviceId() != null) {
                    c = telephonyManager.getDeviceId();
                }
            } catch (Exception unused) {
            }
        }
    }

    private static NetworkInfo g(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        } catch (Exception e) {
            LogX.d(Env.c, f11493a, e.toString());
            return null;
        }
    }

    public static boolean d(Context context) {
        NetworkInfo g = g(context);
        return g != null && g.isConnected();
    }

    public static String e(Context context) {
        PackageInfo packageInfo;
        if (context == null) {
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }
}
