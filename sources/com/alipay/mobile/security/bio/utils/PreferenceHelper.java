package com.alipay.mobile.security.bio.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceHelper {
    public static final String KEY_FACEDETECT_SERVER_CONFIGS = "facedetect_server_configs";
    public static final String KEY_FACEDETECT_SERVER_CONFIGS_VERSION = "facedetect_server_configs_version";
    public static final String KEY_FACEDETECT_SOUND_ENABLE = "facedetect_sound_enable";

    /* renamed from: a  reason: collision with root package name */
    private static SharedPreferences.Editor f1026a;
    private static SharedPreferences b;

    protected static SharedPreferences.Editor a(Context context) {
        if (f1026a == null) {
            f1026a = PreferenceManager.getDefaultSharedPreferences(context).edit();
        }
        return f1026a;
    }

    protected static SharedPreferences b(Context context) {
        if (b == null) {
            b = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return b;
    }

    public static String getValue(Context context, String str) {
        return b(context).getString(SignHelper.SHA1(str), "");
    }

    public static void setValue(Context context, String str, String str2) {
        a(context).putString(SignHelper.SHA1(str), str2).commit();
    }

    public static String getServerConfig(Context context) {
        return b(context).getString(SignHelper.SHA1(KEY_FACEDETECT_SERVER_CONFIGS), "");
    }

    public static void setServerConfig(Context context, String str) {
        a(context).putString(SignHelper.SHA1(KEY_FACEDETECT_SERVER_CONFIGS), str).commit();
    }

    public static boolean getSoundEnable(Context context) {
        return b(context).getBoolean(SignHelper.SHA1(KEY_FACEDETECT_SOUND_ENABLE), true);
    }

    public static void setSoundEnable(Context context, boolean z) {
        a(context).putBoolean(SignHelper.SHA1(KEY_FACEDETECT_SOUND_ENABLE), z);
    }

    public static String getServerConfigVersion(Context context) {
        return b(context).getString(SignHelper.SHA1(KEY_FACEDETECT_SERVER_CONFIGS_VERSION), "");
    }

    public static void setServerConfigVersion(Context context, String str) {
        a(context).putString(SignHelper.SHA1(KEY_FACEDETECT_SERVER_CONFIGS_VERSION), str).commit();
    }
}
