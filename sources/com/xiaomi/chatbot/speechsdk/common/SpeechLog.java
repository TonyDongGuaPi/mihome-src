package com.xiaomi.chatbot.speechsdk.common;

import android.util.Log;

public class SpeechLog {
    private static final String TAG = "xiaomi_speech_sdk";

    public static void d(String str, String str2) {
        Log.d("xiaomi_speech_sdk_" + str, str2);
    }

    public static void i(String str, String str2) {
        Log.i("xiaomi_speech_sdk_" + str, str2);
    }

    public static void e(String str, String str2) {
        Log.e("xiaomi_speech_sdk_" + str, str2);
    }

    public static void printNecessityLog(String str, Object obj) {
        Log.i("xiaomi_speech_sdk_" + str, " - " + obj);
    }

    public static void printException(String str, Exception exc) {
        Log.e("xiaomi_speech_sdk_" + str, "error", exc);
    }

    public static void printThrowable(String str, Throwable th) {
        Log.e("xiaomi_speech_sdk_" + str, Log.getStackTraceString(th));
    }
}
