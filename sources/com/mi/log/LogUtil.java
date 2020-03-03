package com.mi.log;

import android.text.TextUtils;
import com.mi.util.Device;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

public class LogUtil {
    public static void a(boolean z) {
        String str = Device.q;
        if (TextUtils.isEmpty(str)) {
            str = "MI_LOG";
        }
        Logger.a(str).a(z ? LogLevel.FULL : LogLevel.NONE);
    }

    public static void a(String str, String str2) {
        Logger.b(str).b(str2, new Object[0]);
    }

    public static void a(String str) {
        Logger.b(str, new Object[0]);
    }

    public static void b(String str, String str2) {
        Logger.b(str).a((Object) str2);
    }

    public static void b(String str) {
        Logger.a((Object) str);
    }

    public static void c(String str, String str2) {
        Logger.b(str).c(str2, new Object[0]);
    }

    public static void c(String str) {
        Logger.e(str, new Object[0]);
    }

    public static void d(String str, String str2) {
        Logger.b(str).d(str2, new Object[0]);
    }

    public static void d(String str) {
        Logger.c(str, new Object[0]);
    }

    public static void e(String str, String str2) {
        Logger.b(str).e(str2, new Object[0]);
    }

    public static void e(String str) {
        Logger.d(str, new Object[0]);
    }

    public static void f(String str, String str2) {
        Logger.b(str).b(str2);
    }

    public static void f(String str) {
        Logger.c(str);
    }

    public static void g(String str, String str2) {
        Logger.b(str).c(str2);
    }

    public static void g(String str) {
        Logger.d(str);
    }
}
