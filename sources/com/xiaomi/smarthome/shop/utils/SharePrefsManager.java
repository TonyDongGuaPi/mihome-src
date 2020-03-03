package com.xiaomi.smarthome.shop.utils;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import java.util.HashMap;

public class SharePrefsManager {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, SharedPreferences> f22202a;

    public static SharedPreferences a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (f22202a == null) {
            f22202a = new HashMap<>();
        }
        if (f22202a.containsKey(str)) {
            return f22202a.get(str);
        }
        SharedPreferences sharedPreferences = SHApplication.getApplication().getSharedPreferences(str, 0);
        f22202a.put(str, sharedPreferences);
        return sharedPreferences;
    }

    public static void a(SharedPreferences sharedPreferences, String str, boolean z) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(str, z).commit();
        }
    }

    public static void a(String str, String str2, boolean z) {
        SharedPreferences a2 = a(str);
        if (a2 != null) {
            a2.edit().putBoolean(str2, z).commit();
        }
    }

    public static boolean b(SharedPreferences sharedPreferences, String str, boolean z) {
        return sharedPreferences != null ? sharedPreferences.getBoolean(str, z) : z;
    }

    public static boolean b(String str, String str2, boolean z) {
        SharedPreferences a2 = a(str);
        return a2 != null ? a2.getBoolean(str2, z) : z;
    }

    public static void a(SharedPreferences sharedPreferences, String str, String str2) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(str, XMStringUtils.i(str2)).commit();
        }
    }

    public static void a(String str, String str2, String str3) {
        SharedPreferences a2 = a(str);
        if (a2 != null) {
            a2.edit().putString(str2, str3).commit();
        }
    }

    public static String b(SharedPreferences sharedPreferences, String str, String str2) {
        return sharedPreferences != null ? sharedPreferences.getString(str, str2) : str2;
    }

    public static String b(String str, String str2, String str3) {
        SharedPreferences a2 = a(str);
        return a2 != null ? a2.getString(str2, str3) : str3;
    }

    public static long a(String str, String str2, long j) {
        SharedPreferences a2 = a(str);
        return a2 != null ? a2.getLong(str2, j) : j;
    }

    public static void b(String str, String str2, long j) {
        SharedPreferences a2 = a(str);
        if (a2 != null) {
            a2.edit().putLong(str2, j).commit();
        }
    }
}
