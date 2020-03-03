package com.xiaomi.youpin.common.util;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;

public final class ScreenUtils {
    private ScreenUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int a() {
        WindowManager windowManager = (WindowManager) Utils.a().getSystemService("window");
        if (windowManager == null) {
            return Utils.a().getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    public static int b() {
        WindowManager windowManager = (WindowManager) Utils.a().getSystemService("window");
        if (windowManager == null) {
            return Utils.a().getResources().getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealSize(point);
        } else {
            windowManager.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    public static float c() {
        return Utils.a().getResources().getDisplayMetrics().density;
    }

    public static int d() {
        return Utils.a().getResources().getDisplayMetrics().densityDpi;
    }

    public static void a(@NonNull Activity activity) {
        activity.getWindow().addFlags(1536);
    }

    public static void b(@NonNull Activity activity) {
        activity.setRequestedOrientation(0);
    }

    public static void c(@NonNull Activity activity) {
        activity.setRequestedOrientation(1);
    }

    public static boolean e() {
        return Utils.a().getResources().getConfiguration().orientation == 2;
    }

    public static boolean f() {
        return Utils.a().getResources().getConfiguration().orientation == 1;
    }

    public static int d(@NonNull Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 0:
                return 0;
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    public static Bitmap e(@NonNull Activity activity) {
        return a(activity, false);
    }

    public static Bitmap a(@NonNull Activity activity, boolean z) {
        Bitmap bitmap;
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (z) {
            Resources resources = activity.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(resources.getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android"));
            bitmap = Bitmap.createBitmap(drawingCache, 0, dimensionPixelSize, displayMetrics.widthPixels, displayMetrics.heightPixels - dimensionPixelSize);
        } else {
            bitmap = Bitmap.createBitmap(drawingCache, 0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
        decorView.destroyDrawingCache();
        return bitmap;
    }

    public static boolean g() {
        KeyguardManager keyguardManager = (KeyguardManager) Utils.a().getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    @RequiresPermission("android.permission.WRITE_SETTINGS")
    public static void a(int i) {
        Settings.System.putInt(Utils.a().getContentResolver(), "screen_off_timeout", i);
    }

    public static int h() {
        try {
            return Settings.System.getInt(Utils.a().getContentResolver(), "screen_off_timeout");
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return -123;
        }
    }

    public static boolean i() {
        return (Utils.a().getResources().getConfiguration().screenLayout & 15) >= 3;
    }
}
