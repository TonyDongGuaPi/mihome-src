package com.xiaomi.jr.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

public class PreferenceUtils {
    public static boolean a(Context context, String str, String str2) {
        return context.getSharedPreferences(str, 0).contains(str2);
    }

    public static void b(Context context, String str, String str2) {
        context.getSharedPreferences(str, 0).edit().remove(str2).apply();
    }

    public static void a(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.clear();
        edit.apply();
    }

    public static void a(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.apply();
    }

    public static void a(Context context, String str, String str2, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putBoolean(str2, z);
        edit.apply();
    }

    public static void a(Context context, String str, String str2, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putInt(str2, i);
        edit.apply();
    }

    public static void a(Context context, String str, String str2, long j) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putLong(str2, j);
        edit.apply();
    }

    public static void c(Context context, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        Map<String, ?> all = sharedPreferences.getAll();
        if (all != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            for (String next : all.keySet()) {
                if (next.startsWith(str2)) {
                    edit.remove(next);
                }
            }
            edit.apply();
        }
    }

    public static String d(Context context, String str, String str2) {
        return context.getSharedPreferences(str, 0).getString(str2, (String) null);
    }

    public static boolean e(Context context, String str, String str2) {
        return b(context, str, str2, false);
    }

    public static boolean b(Context context, String str, String str2, boolean z) {
        return context.getSharedPreferences(str, 0).getBoolean(str2, z);
    }

    public static int f(Context context, String str, String str2) {
        return b(context, str, str2, 0);
    }

    public static int b(Context context, String str, String str2, int i) {
        return context.getSharedPreferences(str, 0).getInt(str2, i);
    }

    public static long b(Context context, String str, String str2, long j) {
        return context.getSharedPreferences(str, 0).getLong(str2, j);
    }
}
