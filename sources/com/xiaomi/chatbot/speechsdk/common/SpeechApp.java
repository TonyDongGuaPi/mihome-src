package com.xiaomi.chatbot.speechsdk.common;

import android.content.Context;

public class SpeechApp {
    private static Context context = null;
    private static String filePath = null;
    private static boolean inited = false;
    private static String packageName = null;
    private static String serverUrl = "http://ics-speech.ai.xiaomi.com";

    public static boolean isInited() {
        return inited;
    }

    public static void setInited(boolean z) {
        inited = z;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context2) {
        context = context2;
    }

    public static String getPackageName() {
        if (packageName == null) {
            packageName = context.getPackageName();
        }
        return packageName;
    }

    public static String getFilePath() {
        if (filePath == null) {
            filePath = context.getFilesDir().getAbsolutePath();
        }
        return filePath;
    }

    public static String getServerUrl() {
        return serverUrl;
    }

    public static void setServerUrl(String str) {
        serverUrl = str;
    }
}
