package com.payu.magicretry.Helpers;

import android.util.Log;

public class L {
    public static final int DEBUG = 4;
    private static final String DEFAULT_LOG_TAG = "### PAYU ####";
    private static final String DEFAULT_TIMESTAMP_TAG = "PAYU-TIMESTAMP";
    public static final int ERROR = 6;
    public static final int INFO = 3;
    public static final int NONE = 7;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static int sLogLevel = 2;

    public static synchronized void t(String str) {
        synchronized (L.class) {
            if (sLogLevel <= 2) {
                Log.v(DEFAULT_TIMESTAMP_TAG, str);
            }
        }
    }

    public static synchronized void v(String str, String str2) {
        synchronized (L.class) {
            if (sLogLevel <= 2) {
                Log.v(str, str2);
            }
        }
    }

    public static synchronized void v(String str) {
        synchronized (L.class) {
            if (sLogLevel <= 2) {
                Log.v(DEFAULT_LOG_TAG, str);
            }
        }
    }

    public static synchronized void v(int i) {
        synchronized (L.class) {
            if (sLogLevel <= 2) {
                v(DEFAULT_LOG_TAG, i + "");
            }
        }
    }

    public static synchronized void v(String str, int i) {
        synchronized (L.class) {
            if (sLogLevel <= 2) {
                v(str, i + "");
            }
        }
    }

    public static synchronized void d(String str, String str2) {
        synchronized (L.class) {
            if (sLogLevel <= 4) {
                Log.d(str, str2);
            }
        }
    }

    public static synchronized void w(String str, String str2) {
        synchronized (L.class) {
            if (sLogLevel <= 5) {
                Log.w(str, str2);
            }
        }
    }

    public static synchronized void i(String str, String str2) {
        synchronized (L.class) {
            if (sLogLevel <= 3) {
                Log.i(str, str2);
            }
        }
    }

    public static synchronized void e(String str, String str2) {
        synchronized (L.class) {
            if (sLogLevel <= 6) {
                Log.e(str, str2);
            }
        }
    }
}
