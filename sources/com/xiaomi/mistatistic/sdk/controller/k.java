package com.xiaomi.mistatistic.sdk.controller;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;
import android.text.TextUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class k {

    /* renamed from: a  reason: collision with root package name */
    public static String f12037a;
    private static ExecutorService b = Executors.newSingleThreadExecutor();

    public static int a(Context context, String str, int i) {
        return context.getSharedPreferences(a(context), 0).getInt(str, i);
    }

    public static long a(Context context, String str, long j) {
        return context.getSharedPreferences(a(context), 0).getLong(str, j);
    }

    public static String a(Context context, String str, String str2) {
        return context.getSharedPreferences(a(context), 0).getString(str, str2);
    }

    public static boolean a(Context context, String str) {
        return context.getSharedPreferences(a(context), 0).getBoolean(str, false);
    }

    public static void b(Context context, String str, int i) {
        SharedPreferences.Editor edit = context.getSharedPreferences(a(context), 0).edit();
        edit.putInt(str, i);
        a(edit);
    }

    public static void b(Context context, String str, long j) {
        SharedPreferences.Editor edit = context.getSharedPreferences(a(context), 0).edit();
        edit.putLong(str, j);
        a(edit);
    }

    public static void b(Context context, String str, String str2) {
        SharedPreferences.Editor edit = context.getSharedPreferences(a(context), 0).edit();
        edit.putString(str, str2);
        a(edit);
    }

    public static void a(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(a(context), 0).edit();
        edit.putBoolean(str, z);
        a(edit);
    }

    public static boolean b(Context context, String str) {
        return context.getSharedPreferences(a(context), 0).contains(str);
    }

    private static void a(final SharedPreferences.Editor editor) {
        b.execute(new Runnable() {
            public void run() {
                editor.commit();
            }
        });
    }

    public static boolean c(Context context, String str) {
        return context.getSharedPreferences(a(context), 0).contains(str);
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f12037a)) {
            return f12037a;
        }
        String c = c(context);
        if (TextUtils.equals(c, context.getPackageName())) {
            f12037a = "mistat";
        } else {
            f12037a = "mistat" + q.c(c);
        }
        return f12037a;
    }

    private static String c(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager.getRunningAppProcesses() == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo next : activityManager.getRunningAppProcesses()) {
            if (next.pid == Process.myPid()) {
                return next.processName;
            }
        }
        return "";
    }

    public static void b(Context context) {
        context.getSharedPreferences(a(context), 0).edit().clear().commit();
    }
}
