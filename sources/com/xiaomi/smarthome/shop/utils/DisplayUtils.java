package com.xiaomi.smarthome.shop.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Window;
import com.xiaomi.mishopsdk.util.Constants;

public class DisplayUtils {

    /* renamed from: a  reason: collision with root package name */
    static final String f22188a = "DisplayUtils";

    public static Point a(Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }

    public static Point a(Context context) {
        Point point = new Point();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        point.x = displayMetrics.widthPixels;
        point.y = displayMetrics.heightPixels;
        return point;
    }

    public static int a(Activity activity, float f) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) ((f * displayMetrics.density) + 0.5f);
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(Activity activity, float f) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) ((f / displayMetrics.density) + 0.5f);
    }

    public static int c(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void a(Activity activity, int i, int i2) {
        if (activity != null) {
            activity.overridePendingTransition(i, i2);
        }
    }

    public static int a(Paint paint, String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i += (int) Math.ceil((double) fArr[i2]);
        }
        return i;
    }

    public static Bitmap a(Context context, Bitmap bitmap, float f) {
        try {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * f), (int) (((float) bitmap.getHeight()) * f), false);
            Class<?> cls = Class.forName("miui.util.ScreenshotUtils");
            Bitmap bitmap2 = (Bitmap) cls.getMethod("getBlurBackground", new Class[]{Bitmap.class, Bitmap.class}).invoke(cls, new Object[]{createScaledBitmap, null});
            createScaledBitmap.recycle();
            return bitmap2;
        } catch (Exception unused) {
            return null;
        }
    }

    public static BitmapDrawable b(Context context) {
        try {
            Class<?> cls = Class.forName("miui.content.res.ThemeResources");
            return (BitmapDrawable) cls.getDeclaredMethod("getLockWallpaperCache", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception unused) {
            return null;
        }
    }

    public static void a(Window window) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Class<?> cls = window.getClass();
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(cls2);
                int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE}).invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i2 | i)});
                window.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            } catch (Exception unused) {
            }
        }
    }
}
