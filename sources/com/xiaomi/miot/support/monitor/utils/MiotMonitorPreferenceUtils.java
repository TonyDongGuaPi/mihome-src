package com.xiaomi.miot.support.monitor.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class MiotMonitorPreferenceUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11495a = "sp_monitor_sdk";
    public static final String b = "sp_key_dispose_time";
    public static final String c = "sp_key_last_clean_time";
    public static final String d = "sp_key_last_update_time";
    public static final String e = "sp_key_recent_pids";
    public static final String f = "sp_key_update_read_config_time";
    public static final String g = "sp_key_config_timestamp";
    public static final String h = "sp_key_last_file_info_time";
    public static final String i = "sp_key_last_memory_time";
    public static final String j = "sp_key_last_thread_cnt_time";
    public static final String k = "sp_key_monitor_config";

    public static void a(Context context, String str, Long l) {
        context.getSharedPreferences(f11495a, 0).edit().putLong(str, l.longValue()).commit();
    }

    public static long a(Context context, String str, long j2) {
        return context.getSharedPreferences(f11495a, 0).getLong(str, j2);
    }

    public static void a(Context context, String str, String str2) {
        context.getSharedPreferences(f11495a, 0).edit().putString(str, str2).commit();
    }

    public static String b(Context context, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f11495a, 0);
        if (TextUtils.isEmpty(str2)) {
            return sharedPreferences.getString(str, "");
        }
        return sharedPreferences.getString(str, "");
    }

    public static void a(Context context, String str, int i2) {
        context.getSharedPreferences(f11495a, 0).edit().putInt(str, i2).commit();
    }

    public static int b(Context context, String str, int i2) {
        return context.getSharedPreferences(f11495a, 0).getInt(str, i2);
    }

    public static boolean a(Context context, String str, boolean z) {
        return context.getSharedPreferences(f11495a, 0).getBoolean(str, z);
    }

    public static void b(Context context, String str, boolean z) {
        context.getSharedPreferences(f11495a, 0).edit().putBoolean(str, z).commit();
    }

    public static void a(Context context, String str, float f2) {
        context.getSharedPreferences(f11495a, 0).edit().putFloat(str, f2).commit();
    }

    public static float b(Context context, String str, float f2) {
        return context.getSharedPreferences(f11495a, 0).getFloat(str, f2);
    }
}
