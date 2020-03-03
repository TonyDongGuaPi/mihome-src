package com.mics.widget.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.mics.util.Logger;
import com.xiaomi.mishopsdk.util.Constants;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StatusBarUtils {
    public static void a(View view) {
        if (view != null) {
            a(a(view.getContext()), view);
        }
    }

    public static void a(int i, View view) {
        if (view != null) {
            if (view.getLayoutParams().height >= 0) {
                view.getLayoutParams().height += i;
            }
            view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), view.getPaddingBottom());
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public static boolean a(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (Build.BRAND.equalsIgnoreCase("htc") && Build.MODEL.contains("M8w")) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            if (Build.VERSION.SDK_INT < 23 || !z) {
                window.getDecorView().setSystemUiVisibility(1280);
            } else {
                window.getDecorView().setSystemUiVisibility(9472);
            }
            window.addFlags(Integer.MIN_VALUE);
            if (!z || (!Build.BRAND.equalsIgnoreCase("oppo") && !Build.BRAND.equalsIgnoreCase("vivo"))) {
                window.setStatusBarColor(0);
                return true;
            }
            window.setStatusBarColor(33554431);
            return true;
        } else if (Build.VERSION.SDK_INT < 19 || !b(window, z)) {
            if (Build.VERSION.SDK_INT >= 19 && a(window, z)) {
                window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                return true;
            } else if (Build.VERSION.SDK_INT < 19 || Build.VERSION.SDK_INT >= 21) {
                return false;
            } else {
                window.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                return true;
            }
        } else if (z) {
            window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
            window.addFlags(Integer.MIN_VALUE);
            window.getDecorView().setSystemUiVisibility(8192);
            return true;
        } else {
            window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & -8193);
            return true;
        }
    }

    private static boolean a(Window window, boolean z) {
        if (window == null) {
            return false;
        }
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
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean b(Window window, boolean z) {
        if (window == null) {
            return false;
        }
        Class<?> cls = window.getClass();
        try {
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(cls2);
            int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE});
            if (z) {
                int i3 = i2 | i;
                method.invoke(window, new Object[]{Integer.valueOf(i3), Integer.valueOf(i3)});
            } else {
                method.invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i2 | i)});
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static int a(Context context) {
        int identifier = context.getApplicationContext().getResources().getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android");
        if (identifier <= 0) {
            return -1;
        }
        int dimensionPixelSize = context.getApplicationContext().getResources().getDimensionPixelSize(identifier);
        Logger.a("statusHeight = %s", Integer.valueOf(dimensionPixelSize));
        return dimensionPixelSize;
    }
}
