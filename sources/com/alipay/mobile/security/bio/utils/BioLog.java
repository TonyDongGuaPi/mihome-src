package com.alipay.mobile.security.bio.utils;

import android.util.Log;
import com.alipay.mobile.security.bio.config.Constant;

public final class BioLog {

    /* renamed from: a  reason: collision with root package name */
    private static Logger f1021a = new a();

    private static final class a extends Logger {
        private a() {
        }

        public int verbose(String str, String str2) {
            return Log.v(str, str2);
        }

        public int debug(String str, String str2) {
            return Log.d(str, str2);
        }

        public int info(String str, String str2) {
            return Log.i(str, str2);
        }

        public int warn(String str, String str2) {
            return Log.w(str, str2);
        }

        public int error(String str, String str2) {
            return Log.e(str, str2);
        }

        /* access modifiers changed from: protected */
        public String a(Throwable th) {
            return Log.getStackTraceString(th);
        }
    }

    private BioLog() {
    }

    public static void setLogger(Logger logger) {
        f1021a = logger;
    }

    public static void v(String str) {
        f1021a.v(Constant.DEBUG_LOG_TAG, str);
    }

    public static void v(String str, String str2) {
        f1021a.v(str, str2);
    }

    public static void d(String str) {
        f1021a.d(Constant.DEBUG_LOG_TAG, str);
    }

    public static void d(String str, String str2) {
        f1021a.d(str, str2);
    }

    public static void i(String str) {
        f1021a.i(Constant.DEBUG_LOG_TAG, str);
    }

    public static void i(String str, String str2) {
        f1021a.i(str, str2);
    }

    public static void w(String str) {
        f1021a.w(Constant.DEBUG_LOG_TAG, str);
    }

    public static void w(String str, String str2) {
        f1021a.w(str, str2);
    }

    public static void w(Throwable th) {
        f1021a.w(Constant.DEBUG_LOG_TAG, th);
    }

    public static void w(String str, Throwable th) {
        f1021a.w(Constant.DEBUG_LOG_TAG, str, th);
    }

    public static void w(String str, String str2, Throwable th) {
        f1021a.w(str, str2, th);
    }

    public static void e(String str) {
        f1021a.e(Constant.DEBUG_LOG_TAG, str);
    }

    public static void e(String str, String str2) {
        f1021a.e(str, str2);
    }

    public static void e(Throwable th) {
        f1021a.e(Constant.DEBUG_LOG_TAG, th);
    }

    public static void e(String str, Throwable th) {
        f1021a.e(Constant.DEBUG_LOG_TAG, str, th);
    }

    public static void e(String str, String str2, Throwable th) {
        f1021a.e(str, str2, th);
    }
}
