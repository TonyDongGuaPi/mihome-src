package com.mi.mistatistic.sdk.controller;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrefPersistUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7337a = "mistat";
    public static String b;
    private static ExecutorService c = Executors.newSingleThreadExecutor();

    public static int a(Context context, String str, int i) {
        if (context == null) {
            return i;
        }
        return context.getSharedPreferences(a(context), 0).getInt(str, i);
    }

    public static long a(Context context, String str, long j) {
        if (context == null) {
            return j;
        }
        return context.getSharedPreferences(a(context), 0).getLong(str, j);
    }

    public static String a(Context context, String str, String str2) {
        if (context == null) {
            return str2;
        }
        return context.getSharedPreferences(a(context), 0).getString(str, str2);
    }

    public static boolean a(Context context, String str) {
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences(a(context), 0).getBoolean(str, false);
    }

    public static void b(Context context, String str, int i) {
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences(a(context), 0).edit();
            edit.putInt(str, i);
            a(edit);
        }
    }

    public static void b(Context context, String str, long j) {
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences(a(context), 0).edit();
            edit.putLong(str, j);
            a(edit);
        }
    }

    public static void b(Context context, String str, String str2) {
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences(a(context), 0).edit();
            edit.putString(str, str2);
            a(edit);
        }
    }

    public static void a(Context context, String str, boolean z) {
        if (context != null) {
            SharedPreferences.Editor edit = context.getSharedPreferences(a(context), 0).edit();
            edit.putBoolean(str, z);
            a(edit);
        }
    }

    private static void a(final SharedPreferences.Editor editor) {
        c.execute(new Runnable() {
            public void run() {
                editor.commit();
            }
        });
    }

    public static boolean b(Context context, String str) {
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences(a(context), 0).contains(str);
    }

    public static boolean c(Context context, String str) {
        if (context == null) {
            return false;
        }
        return context.getSharedPreferences(a(context), 0).edit().remove(str).commit();
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        if (context == null) {
            return b;
        }
        String c2 = c(context);
        if (TextUtils.equals(c2, context.getPackageName())) {
            b = "mistat";
        } else {
            b = "mistat" + DeviceIDHolder.a(c2);
        }
        return b;
    }

    private static String c(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null || activityManager.getRunningAppProcesses() == null) {
                return "";
            }
            for (ActivityManager.RunningAppProcessInfo next : activityManager.getRunningAppProcesses()) {
                if (next.pid == Process.myPid()) {
                    return next.processName;
                }
            }
            return "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static void b(Context context) {
        if (context != null) {
            context.getSharedPreferences(a(context), 0).edit().clear().commit();
        }
    }
}
