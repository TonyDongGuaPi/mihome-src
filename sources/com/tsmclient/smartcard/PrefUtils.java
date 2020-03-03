package com.tsmclient.smartcard;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefUtils {
    public static final String PREF_KEY_CIN = "key_cin";
    public static final String PREF_KEY_CPLC = "key_cplc";
    public static final String PREF_KEY_SEID = "key_seid";
    private static final String PREF_NAME = "pref_com_tsmclient_smartcard";

    private PrefUtils() {
    }

    public static void putBoolean(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME, 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static boolean getBoolean(Context context, String str, boolean z) {
        return context.getSharedPreferences(PREF_NAME, 0).getBoolean(str, z);
    }

    public static void putString(Context context, String str, String str2) {
        putString(context, PREF_NAME, str, str2);
    }

    public static void putString(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.apply();
    }

    public static String getString(Context context, String str, String str2) {
        return context.getSharedPreferences(PREF_NAME, 0).getString(str, str2);
    }

    public static void putInt(Context context, String str, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME, 0).edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static int getInt(Context context, String str, int i) {
        return context.getSharedPreferences(PREF_NAME, 0).getInt(str, i);
    }

    public static void putLong(Context context, String str, long j) {
        SharedPreferences.Editor edit = context.getSharedPreferences(PREF_NAME, 0).edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public static long getLong(Context context, String str, long j) {
        return context.getSharedPreferences(PREF_NAME, 0).getLong(str, j);
    }

    public static boolean contains(Context context, String str) {
        return context.getSharedPreferences(PREF_NAME, 0).contains(str);
    }

    public static boolean remove(Context context, String str) {
        return context.getSharedPreferences(PREF_NAME, 0).edit().remove(str).commit();
    }

    public static void clear(Context context) {
        context.getSharedPreferences(PREF_NAME, 0).edit().clear().commit();
    }
}
