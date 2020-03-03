package com.mi.mistatistic.sdk.controller;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class DeviceIDHolder {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7328a = "imei";
    private static final String b = "device_id";
    private static final String c = "random_device_id";
    private static String d;
    private static String e;

    @SuppressLint({"NewApi"})
    public static String a(Context context) {
        String str;
        if (TextUtils.isEmpty(d)) {
            d = PrefPersistUtils.a(context, "device_id", "");
        }
        if (TextUtils.isEmpty(d)) {
            String c2 = c(context);
            String str2 = null;
            try {
                str = Settings.Secure.getString(context.getContentResolver(), DeviceInfoResult.BUNDLE_KEY_ANDROID_ID);
            } catch (Exception e2) {
                e2.printStackTrace();
                str = null;
            }
            if (Build.VERSION.SDK_INT > 8) {
                str2 = Build.SERIAL;
            }
            if (Build.VERSION.SDK_INT >= 28 && context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0) {
                str2 = Build.getSerial();
            }
            d = a(c2 + str + str2);
            PrefPersistUtils.b(context, "device_id", d);
        }
        return d;
    }

    @TargetApi(9)
    public static String b(Context context) {
        try {
            if (TextUtils.isEmpty(e)) {
                e = PrefPersistUtils.a(context, "imei", "");
                if (TextUtils.isEmpty(e)) {
                    if (context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0) {
                        Logger.b("get READ_PHONE_STATE permission");
                        e = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                        PrefPersistUtils.b(context, "imei", e);
                    } else {
                        Logger.d("cannot get READ_PHONE_STATE permission");
                    }
                }
            }
        } catch (Throwable th) {
            Logger.a("getImei exception:", th);
        }
        if (TextUtils.isEmpty(e)) {
            Logger.c("Imei is empty");
            e = "";
        }
        return e;
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(b(str));
            return String.format("%1$032X", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static String c(Context context) {
        String a2 = PrefPersistUtils.a(context, c, "");
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String uuid = UUID.randomUUID().toString();
        PrefPersistUtils.b(context, c, uuid);
        return uuid;
    }

    private static byte[] b(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }
}
