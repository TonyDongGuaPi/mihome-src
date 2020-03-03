package com.xiaomi.phonenum.utils;

import android.util.Log;

public class LoggerManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f12578a = "phoneNum";
    private static volatile Logger b = new Logger() {
        public void c(String str, String str2) {
        }

        public void a(String str, String str2) {
            String b = LoggerManager.f12578a;
            Log.i(b, str + " " + str2);
        }

        public void b(String str, String str2) {
            String b = LoggerManager.f12578a;
            Log.e(b, str + " " + str2);
        }

        public void a(String str, String str2, Throwable th) {
            String b = LoggerManager.f12578a;
            Log.e(b, str + " " + str2, th);
        }
    };

    public static Logger a() {
        return b;
    }

    public static void a(Logger logger) {
        b = logger;
    }
}
