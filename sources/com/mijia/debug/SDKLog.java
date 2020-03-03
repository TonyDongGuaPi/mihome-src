package com.mijia.debug;

import android.util.Log;
import com.mijia.app.AppConfig;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

public class SDKLog {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7947a = "MiJiaCamera_";
    private static String b = "mijia.camera.v3";

    public static int a(String str, String str2) {
        if (!AppConfig.a()) {
            return 0;
        }
        return Log.v(f7947a + str, str2);
    }

    public static int a(String str, String str2, Throwable th) {
        if (!AppConfig.a()) {
            return 0;
        }
        return Log.v(f7947a + str, str2, th);
    }

    public static int b(String str, String str2) {
        if (!AppConfig.a()) {
            return 0;
        }
        return Log.d(f7947a + str, str2);
    }

    public static int b(String str, String str2, Throwable th) {
        if (!AppConfig.a()) {
            return 0;
        }
        return Log.d(f7947a + str, str2, th);
    }

    public static int c(String str, String str2) {
        if (!AppConfig.a()) {
            return 0;
        }
        return Log.i(f7947a + str, str2);
    }

    public static int c(String str, String str2, Throwable th) {
        if (!AppConfig.a()) {
            return 0;
        }
        return Log.i(f7947a + str, str2, th);
    }

    public static int d(String str, String str2) {
        if (!AppConfig.a()) {
            return 0;
        }
        return Log.e(f7947a + str, str2);
    }

    public static int d(String str, String str2, Throwable th) {
        if (!AppConfig.a()) {
            return 0;
        }
        return Log.e(f7947a + str, str2, th);
    }

    public static int e(String str, String str2) {
        return e(str, str2, (Throwable) null);
    }

    public static int e(String str, String str2, Throwable th) {
        XmPluginHostApi instance = XmPluginHostApi.instance();
        String str3 = b;
        instance.logForModel(str3, str + ":" + str2);
        return Log.e(f7947a + str, str2, th);
    }

    public static void a(String str) {
        b = str;
    }
}
