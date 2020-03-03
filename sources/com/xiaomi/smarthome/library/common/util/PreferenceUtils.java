package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.xiaomi.smarthome.application.SHApplication;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public abstract class PreferenceUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18697a = "country";
    private static final ArrayList<PrefObserver> b = new ArrayList<>();

    public interface PrefObserver {
        void a(String str, Object obj);
    }

    public static void a(PrefObserver prefObserver) {
        b.add(prefObserver);
    }

    public static void b(PrefObserver prefObserver) {
        Iterator<PrefObserver> it = b.iterator();
        while (it.hasNext()) {
            PrefObserver next = it.next();
            if (next == prefObserver) {
                b.remove(next);
                return;
            }
        }
    }

    public static void a(String str, Object obj) {
        Iterator<PrefObserver> it = b.iterator();
        while (it.hasNext()) {
            it.next().a(str, obj);
        }
    }

    public static String a(String str, String str2) {
        return a(SHApplication.getAppContext(), str, str2);
    }

    public static String a(Context context, String str, String str2) {
        if (context == null) {
            return "";
        }
        return PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
    }

    public static void b(String str, String str2) {
        b(SHApplication.getAppContext(), str, str2);
    }

    public static void a(SharedPreferences sharedPreferences, String str, String str2) {
        sharedPreferences.edit().putString(str, str2).commit();
    }

    public static void b(SharedPreferences sharedPreferences, String str, String str2) {
        sharedPreferences.edit().putString(str, str2).apply();
    }

    public static void b(Context context, String str, String str2) {
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, str2).commit();
        }
    }

    public static void c(Context context, String str, String str2) {
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, str2).apply();
        }
    }

    public static void c(SharedPreferences sharedPreferences, String str, String str2) {
        String string = sharedPreferences.getString(str, "");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(str, string + str2).commit();
    }

    public static boolean a(String str, boolean z) {
        return a(SHApplication.getAppContext(), str, z);
    }

    public static boolean a(Context context, String str, boolean z) {
        if (context == null) {
            return false;
        }
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, z);
    }

    public static boolean a(SharedPreferences sharedPreferences, String str, boolean z) {
        return sharedPreferences.getBoolean(str, z);
    }

    public static boolean a(String str) {
        return a(SHApplication.getAppContext(), str);
    }

    public static boolean a(Context context, String str) {
        if (context == null) {
            return false;
        }
        return PreferenceManager.getDefaultSharedPreferences(context).contains(str);
    }

    public static void b(String str, boolean z) {
        b(SHApplication.getAppContext(), str, z);
    }

    public static void b(Context context, String str, boolean z) {
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(str, z).commit();
        }
    }

    public static void c(Context context, String str, boolean z) {
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(str, z).apply();
        }
    }

    public static void b(SharedPreferences sharedPreferences, String str, boolean z) {
        sharedPreferences.edit().putBoolean(str, z).commit();
    }

    public static void a(Context context, String str, int i) {
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(str, i).commit();
        }
    }

    public static void b(Context context, String str, int i) {
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(str, i).apply();
        }
    }

    public static void a(SharedPreferences sharedPreferences, String str, int i) {
        sharedPreferences.edit().putInt(str, i).commit();
    }

    public static void b(Context context, String str) {
        if (context != null) {
            a(PreferenceManager.getDefaultSharedPreferences(context), str);
        }
    }

    public static void a(SharedPreferences sharedPreferences, String str) {
        sharedPreferences.edit().putInt(str, sharedPreferences.getInt(str, 0) + 1).commit();
    }

    public static void b(SharedPreferences sharedPreferences, String str, int i) {
        sharedPreferences.edit().putInt(str, sharedPreferences.getInt(str, 0) + i).commit();
    }

    public static int c(Context context, String str, int i) {
        if (context == null) {
            return 0;
        }
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(str, i);
    }

    public static int c(SharedPreferences sharedPreferences, String str, int i) {
        return sharedPreferences.getInt(str, i);
    }

    public static void a(Context context, String str, float f) {
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(str, f).commit();
        }
    }

    public static float b(Context context, String str, float f) {
        if (context == null) {
            return 0.0f;
        }
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(str, f);
    }

    public static void a(Context context, String str, long j) {
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(str, j).commit();
        }
    }

    public static void a(SharedPreferences sharedPreferences, String str, long j) {
        sharedPreferences.edit().putLong(str, j).commit();
    }

    public static long b(SharedPreferences sharedPreferences, String str, long j) {
        return sharedPreferences.getLong(str, j);
    }

    public static long b(Context context, String str, long j) {
        if (context == null) {
            return 0;
        }
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(str, j);
    }

    public static void c(SharedPreferences sharedPreferences, String str, long j) {
        sharedPreferences.edit().putLong(str, sharedPreferences.getLong(str, 0) + j).commit();
    }

    public static void c(Context context, String str) {
        if (context != null) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).commit();
        }
    }

    public static void b(SharedPreferences sharedPreferences, String str) {
        sharedPreferences.edit().remove(str).commit();
    }

    public static void a(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.commit();
    }

    public static void a(Context context) {
        if (context != null) {
            c(PreferenceManager.getDefaultSharedPreferences(context), "default preference:");
        }
    }

    public static void d(Context context, String str) {
        if (context != null) {
            c(context.getSharedPreferences(str, 0), str);
        }
    }

    private static void c(SharedPreferences sharedPreferences, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("\n");
        Map<String, ?> all = sharedPreferences.getAll();
        for (String next : all.keySet()) {
            sb.append(next);
            sb.append(":");
            sb.append(all.get(next));
            sb.append("\n");
        }
    }
}
