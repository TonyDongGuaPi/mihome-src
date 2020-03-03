package com.mics.util;

import android.content.SharedPreferences;
import com.mics.core.MiCS;

public class Preferences {

    /* renamed from: a  reason: collision with root package name */
    private static SharedPreferences f7788a = null;
    private static final String b = "com.mics.util.Preferences";

    private Preferences() {
    }

    private static SharedPreferences b() {
        if (f7788a == null) {
            f7788a = MiCS.a().h().getSharedPreferences(b, 0);
        }
        return f7788a;
    }

    public static String a(String str) {
        return b().getString(str, (String) null);
    }

    public static int b(String str) {
        return b().getInt(str, 0);
    }

    public static long c(String str) {
        return b().getLong(str, 0);
    }

    public static boolean d(String str) {
        return b().getBoolean(str, false);
    }

    public static boolean a(String str, boolean z) {
        return b().getBoolean(str, z);
    }

    public static void a() {
        SharedPreferences.Editor edit = b().edit();
        edit.clear();
        edit.apply();
    }

    public static void a(String str, String str2) {
        SharedPreferences.Editor edit = b().edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static void a(String str, long j) {
        SharedPreferences.Editor edit = b().edit();
        edit.putLong(str, j);
        edit.apply();
    }

    public static void a(String str, int i) {
        SharedPreferences.Editor edit = b().edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static void b(String str, boolean z) {
        SharedPreferences.Editor edit = b().edit();
        edit.putBoolean(str, z);
        edit.apply();
    }
}
