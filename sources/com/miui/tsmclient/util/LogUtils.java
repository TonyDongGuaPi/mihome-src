package com.miui.tsmclient.util;

import android.util.Log;

public class LogUtils {
    public static final String TAG = "TSMClient";
    public static final String TAG_STAGING = "TSMClientStaging";

    public static void i(String str) {
        Log.i(TAG, str);
    }

    public static void i(String str, String str2) {
        Log.i("TSMClient." + str, str2);
    }

    public static void e(String str) {
        Log.e(TAG, str);
    }

    public static void e(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    public static void w(String str) {
        Log.w(TAG, str);
    }

    public static void w(String str, String str2) {
        Log.w("TSMClient." + str, str2);
    }

    public static void d(String str) {
        Log.d(TAG, str);
    }

    public static void d(String str, String str2) {
        Log.d("TSMClient." + str, str2);
    }

    public static void v(String str) {
        Log.v(TAG, str);
    }

    public static void t(String str) {
        if (EnvironmentConfig.isStaging()) {
            Log.v(TAG_STAGING, str);
        }
    }

    private LogUtils() {
    }
}
