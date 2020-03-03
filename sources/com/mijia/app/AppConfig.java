package com.mijia.app;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;

public class AppConfig {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7856a = 1;
    public static int b = 0;
    public static int c = 0;
    public static float d = 0.0f;
    public static int e = 0;
    public static final boolean f = true;
    public static final boolean g = true;
    private static String h = "";
    private static boolean i = false;
    private static boolean j = false;
    private static boolean k = false;

    public static void a(Context context) {
        b(context);
        i = (context.getApplicationInfo().flags & 2) != 0;
    }

    public static boolean a() {
        return i;
    }

    public static synchronized void a(boolean z) {
        synchronized (AppConfig.class) {
            j = z;
        }
    }

    public static synchronized boolean b() {
        boolean z;
        synchronized (AppConfig.class) {
            z = j;
        }
        return z;
    }

    public static synchronized void b(boolean z) {
        synchronized (AppConfig.class) {
            k = z;
        }
    }

    public static synchronized boolean c() {
        boolean z;
        synchronized (AppConfig.class) {
            z = k;
        }
        return z;
    }

    private static void b(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (context.getResources().getConfiguration().orientation == 2) {
            b = displayMetrics.heightPixels;
            c = displayMetrics.widthPixels;
        } else {
            b = displayMetrics.widthPixels;
            c = displayMetrics.heightPixels;
        }
        d = displayMetrics.density;
        e = displayMetrics.densityDpi;
    }

    public static synchronized String d() {
        String str;
        synchronized (AppConfig.class) {
            if (TextUtils.isEmpty(h)) {
                try {
                    h = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", new Class[]{String.class}).invoke((Object) null, new Object[]{"ro.board.platform"});
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            str = h;
        }
        return str;
    }
}
