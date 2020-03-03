package com.xiaomi.smarthome.core.server.internal.plugin.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.xiaomi.smarthome.core.server.CoreService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class PreferenceUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14700a = "country";
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
        return a(CoreService.getAppContext(), str, str2);
    }

    public static String a(Context context, String str, String str2) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
    }

    public static void b(String str, String str2) {
        b(CoreService.getAppContext(), str, str2);
    }

    public static void a(SharedPreferences sharedPreferences, String str, String str2) {
        SharedPreferences.Editor edit;
        if (sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
            edit.putString(str, str2).apply();
        }
    }

    public static void b(Context context, String str, String str2) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, str2).apply();
    }

    public static void b(SharedPreferences sharedPreferences, String str, String str2) {
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(str, "");
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(str, string + str2).apply();
        }
    }

    public static boolean a(String str, boolean z) {
        return a(CoreService.getAppContext(), str, z);
    }

    public static boolean a(Context context, String str, boolean z) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(str, z);
    }

    public static boolean a(SharedPreferences sharedPreferences, String str, boolean z) {
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.getBoolean(str, z);
    }

    public static boolean a(String str) {
        return a(CoreService.getAppContext(), str);
    }

    public static boolean a(Context context, String str) {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(str);
    }

    public static void b(String str, boolean z) {
        b(CoreService.getAppContext(), str, z);
    }

    public static void b(Context context, String str, boolean z) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(str, z).apply();
    }

    public static void b(SharedPreferences sharedPreferences, String str, boolean z) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(str, z).apply();
        }
    }

    public static void a(Context context, String str, int i) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(str, i).apply();
    }

    public static void a(SharedPreferences sharedPreferences, String str, int i) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(str, i).apply();
        }
    }

    public static void b(Context context, String str) {
        a(PreferenceManager.getDefaultSharedPreferences(context), str);
    }

    public static void a(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(str, sharedPreferences.getInt(str, 0) + 1).apply();
        }
    }

    public static void b(SharedPreferences sharedPreferences, String str, int i) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(str, sharedPreferences.getInt(str, 0) + i).apply();
        }
    }

    public static int b(Context context, String str, int i) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(str, i);
    }

    public static int c(SharedPreferences sharedPreferences, String str, int i) {
        if (sharedPreferences == null) {
            return 0;
        }
        return sharedPreferences.getInt(str, i);
    }

    public static void a(Context context, String str, float f) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putFloat(str, f).apply();
    }

    public static float b(Context context, String str, float f) {
        return PreferenceManager.getDefaultSharedPreferences(context).getFloat(str, f);
    }

    public static void a(Context context, String str, long j) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(str, j).apply();
    }

    public static void a(SharedPreferences sharedPreferences, String str, long j) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(str, j).apply();
        }
    }

    public static long b(SharedPreferences sharedPreferences, String str, long j) {
        if (sharedPreferences == null) {
            return 0;
        }
        return sharedPreferences.getLong(str, j);
    }

    public static long b(Context context, String str, long j) {
        return PreferenceManager.getDefaultSharedPreferences(context).getLong(str, j);
    }

    public static void c(SharedPreferences sharedPreferences, String str, long j) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(str, sharedPreferences.getLong(str, 0) + j).apply();
        }
    }

    public static void c(Context context, String str) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).apply();
    }

    public static void b(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(str).apply();
        }
    }

    public static void a(SharedPreferences sharedPreferences) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.clear();
            edit.apply();
        }
    }

    public static void a(Context context) {
        c(PreferenceManager.getDefaultSharedPreferences(context), "default preference:");
    }

    public static void d(Context context, String str) {
        c(context.getSharedPreferences(str, 0), str);
    }

    private static void c(SharedPreferences sharedPreferences, String str) {
        if (sharedPreferences != null) {
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
}
