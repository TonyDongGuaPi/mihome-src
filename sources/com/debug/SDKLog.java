package com.debug;

import android.util.Log;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

public class SDKLog {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5157a = "MiJiaCamera_";
    private static String b = "mijia.camera.v3";
    private static boolean c = false;

    public static int a(String str, String str2) {
        if (!c) {
            return 0;
        }
        return Log.v(f5157a + str, str2);
    }

    public static int a(String str, String str2, Throwable th) {
        if (!c) {
            return 0;
        }
        return Log.v(f5157a + str, str2, th);
    }

    public static int b(String str, String str2) {
        if (!c) {
            return 0;
        }
        return Log.d(f5157a + str, str2);
    }

    public static int b(String str, String str2, Throwable th) {
        if (!c) {
            return 0;
        }
        return Log.d(f5157a + str, str2, th);
    }

    public static int c(String str, String str2) {
        if (!c) {
            return 0;
        }
        return Log.i(f5157a + str, str2);
    }

    public static int c(String str, String str2, Throwable th) {
        if (!c) {
            return 0;
        }
        return Log.i(f5157a + str, str2, th);
    }

    public static int d(String str, String str2) {
        return Log.e(f5157a + str, str2);
    }

    public static int d(String str, String str2, Throwable th) {
        return Log.e(f5157a + str, str2, th);
    }

    public static int e(String str, String str2) {
        return e(str, str2, (Throwable) null);
    }

    public static int e(String str, String str2, Throwable th) {
        XmPluginHostApi instance = XmPluginHostApi.instance();
        String str3 = b;
        instance.logForModel(str3, str + ":" + str2);
        return Log.e(f5157a + str, str2, th);
    }

    public static void a(String str) {
        b = str;
    }
}
