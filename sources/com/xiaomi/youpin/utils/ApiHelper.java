package com.xiaomi.youpin.utils;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;

public class ApiHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f23769a = (Build.VERSION.SDK_INT >= 11);
    public static final boolean b = (Build.VERSION.SDK_INT > 10);
    public static final boolean c = (Build.VERSION.SDK_INT >= 23);
    public static final boolean d = (Build.VERSION.SDK_INT >= 11);
    public static final boolean e = (Build.VERSION.SDK_INT >= 13);
    public static final boolean f = (Build.VERSION.SDK_INT >= 17);
    public static final boolean g = (Build.VERSION.SDK_INT >= 16 && a(View.class, "SYSTEM_UI_FLAG_LAYOUT_STABLE"));
    public static final boolean h = (Build.VERSION.SDK_INT >= 14 ? a(MediaStore.MediaColumns.class, "WIDTH") : false);
    public static final boolean i = (Build.VERSION.SDK_INT >= 15 ? a("android.graphics.SurfaceTexture", "setDefaultBufferSize", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE}) : false);
    public static final boolean j = (Build.VERSION.SDK_INT >= 14 ? a("android.graphics.SurfaceTexture", "release", (Class<?>[]) new Class[0]) : false);
    public static final boolean k = (Build.VERSION.SDK_INT >= 11 ? a((Class<?>) View.class, "setSystemUiVisibility", (Class<?>[]) new Class[]{Integer.TYPE}) : false);
    public static final boolean l = (Build.VERSION.SDK_INT >= 9 ? a((Class<?>) SharedPreferences.Editor.class, "apply", (Class<?>[]) null) : false);
    public static final boolean m = (Build.VERSION.SDK_INT >= 14 ? a((Class<?>) DevicePolicyManager.class, "getCameraDisabled", (Class<?>[]) new Class[]{ComponentName.class}) : false);
    public static final boolean n = (Runtime.getRuntime().availableProcessors() > 1);
    public static final boolean o;

    public interface VERSION_CODES {

        /* renamed from: a  reason: collision with root package name */
        public static final int f23770a = 8;
        public static final int b = 9;
        public static final int c = 10;
        public static final int d = 11;
        public static final int e = 12;
        public static final int f = 13;
        public static final int g = 14;
        public static final int h = 15;
        public static final int i = 16;
        public static final int j = 17;
        public static final int k = 18;
        public static final int l = 23;
        public static final int m = 27;
    }

    static {
        boolean z = true;
        if (Build.VERSION.SDK_INT < 13) {
            z = false;
        }
        o = z;
    }

    public static int a(Class<?> cls, String str, Class<?> cls2, int i2) {
        try {
            return cls.getDeclaredField(str).getInt(cls2);
        } catch (Exception unused) {
            return i2;
        }
    }

    private static boolean a(Class<?> cls, String str) {
        try {
            cls.getDeclaredField(str);
            return true;
        } catch (NoSuchFieldException unused) {
            return false;
        }
    }

    private static boolean a(String str, String str2, Class<?>... clsArr) {
        try {
            Class.forName(str).getDeclaredMethod(str2, clsArr);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static boolean a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            cls.getDeclaredMethod(str, clsArr);
            return true;
        } catch (NoSuchMethodException unused) {
            return false;
        }
    }
}
