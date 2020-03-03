package com.xiaomi.youpin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;

public class SharePrefsManager {

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<String, SharedPreferences> f23776a;

    private static String a(String str) {
        return str != null ? str : "";
    }

    public static SharedPreferences a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (f23776a == null) {
            f23776a = new HashMap<>();
        }
        if (f23776a.containsKey(str)) {
            return f23776a.get(str);
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        f23776a.put(str, sharedPreferences);
        return sharedPreferences;
    }

    public static void a(SharedPreferences sharedPreferences, String str, boolean z) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(str, z).commit();
        }
    }

    public static void a(Context context, String str, String str2, boolean z) {
        SharedPreferences a2 = a(context, str);
        if (a2 != null) {
            a2.edit().putBoolean(str2, z).commit();
        }
    }

    public static boolean b(SharedPreferences sharedPreferences, String str, boolean z) {
        return sharedPreferences != null ? sharedPreferences.getBoolean(str, z) : z;
    }

    public static boolean b(Context context, String str, String str2, boolean z) {
        SharedPreferences a2 = a(context, str);
        return a2 != null ? a2.getBoolean(str2, z) : z;
    }

    public static void a(SharedPreferences sharedPreferences, String str, String str2) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(str, a(str2)).commit();
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        SharedPreferences a2 = a(context, str);
        if (a2 != null) {
            a2.edit().putString(str2, str3).commit();
        }
    }

    public static String b(SharedPreferences sharedPreferences, String str, String str2) {
        return sharedPreferences != null ? sharedPreferences.getString(str, str2) : str2;
    }

    public static String b(Context context, String str, String str2, String str3) {
        SharedPreferences a2 = a(context, str);
        return a2 != null ? a2.getString(str2, str3) : str3;
    }

    public static int a(SharedPreferences sharedPreferences, String str, int i) {
        return sharedPreferences != null ? sharedPreferences.getInt(str, i) : i;
    }

    public static void b(SharedPreferences sharedPreferences, String str, int i) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(str, i).commit();
        }
    }

    public static long a(Context context, String str, String str2, long j) {
        SharedPreferences a2 = a(context, str);
        return a2 != null ? a2.getLong(str2, j) : j;
    }

    public static long a(SharedPreferences sharedPreferences, String str, long j) {
        return sharedPreferences != null ? sharedPreferences.getLong(str, j) : j;
    }

    public static void b(Context context, String str, String str2, long j) {
        SharedPreferences a2 = a(context, str);
        if (a2 != null) {
            a2.edit().putLong(str2, j).commit();
        }
    }

    public static void b(SharedPreferences sharedPreferences, String str, long j) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(str, j).commit();
        }
    }

    public static void a(SharedPreferences sharedPreferences, String str, float f) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putFloat(str, f).commit();
        }
    }

    public static void a(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(str).commit();
        }
    }

    public static void a(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.commit();
        }
    }
}