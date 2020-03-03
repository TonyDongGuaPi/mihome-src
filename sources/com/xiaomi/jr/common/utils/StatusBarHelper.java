package com.xiaomi.jr.common.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StatusBarHelper {

    /* renamed from: a  reason: collision with root package name */
    private static Integer f10374a = null;
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;

    public static void a(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (window != null) {
            a(window, 1024, true);
            if (Build.VERSION.SDK_INT >= 23) {
                c(window, z);
            } else if (Build.VERSION.SDK_INT < 19) {
            } else {
                if (f10374a == null) {
                    f10374a = 0;
                    if (a(window, z)) {
                        f10374a = 1;
                    } else if (b(window, z)) {
                        f10374a = 2;
                    }
                } else if (f10374a.intValue() == 1) {
                    a(window, z);
                } else if (f10374a.intValue() == 2) {
                    b(window, z);
                }
            }
        }
    }

    private static boolean a(Window window, boolean z) {
        Class<?> cls = window.getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(z ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean b(Window window, boolean z) {
        try {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            int i = declaredField.getInt((Object) null);
            int i2 = declaredField2.getInt(attributes);
            declaredField2.setInt(attributes, z ? i2 | i : (i ^ -1) & i2);
            window.setAttributes(attributes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @TargetApi(23)
    private static void c(Window window, boolean z) {
        a(window, 8192, z);
    }

    private static void a(Window window, int i, boolean z) {
        int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
        window.getDecorView().setSystemUiVisibility(z ? i | systemUiVisibility : (i ^ -1) & systemUiVisibility);
    }
}
