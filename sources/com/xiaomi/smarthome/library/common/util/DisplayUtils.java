package com.xiaomi.smarthome.library.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.BulletSpan;
import android.text.style.LeadingMarginSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.library.common.ApiHelper;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DisplayUtils {

    /* renamed from: a  reason: collision with root package name */
    static final String f18668a = "DisplayUtils";
    public static final int b = 100;

    public interface CacheLockBmpCallback {
        void a(Bitmap bitmap);
    }

    public static Point a(Activity activity) {
        Point point = new Point();
        if (ApiHelper.h) {
            activity.getWindowManager().getDefaultDisplay().getSize(point);
        } else {
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            point.x = defaultDisplay.getWidth();
            point.y = defaultDisplay.getHeight();
        }
        return point;
    }

    public static Point a(Context context) {
        if (context == null) {
            return new Point();
        }
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
        double d = (double) f;
        double d2 = (double) context.getResources().getDisplayMetrics().scaledDensity;
        Double.isNaN(d);
        Double.isNaN(d2);
        return (int) ((d / d2) + 0.5d);
    }

    public static int c(Context context, float f) {
        double d = (double) f;
        double d2 = (double) context.getResources().getDisplayMetrics().scaledDensity;
        Double.isNaN(d);
        Double.isNaN(d2);
        return (int) ((d * d2) + 0.5d);
    }

    public static int b(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int a() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int d(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int a(float f) {
        return d(CommonApplication.getAppContext(), f);
    }

    public static int b(Activity activity, float f) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) ((f / displayMetrics.density) + 0.5f);
    }

    public static int e(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int b(float f) {
        return e(CommonApplication.getAppContext(), f);
    }

    public static void a(Context context, int i, int i2) {
        if (context != null && (context instanceof Activity)) {
            Activity activity = (Activity) context;
            String str = f18668a;
            Log.d(str, "OverridePending:Activity=" + activity);
            activity.overridePendingTransition(i, i2);
        }
    }

    public static Bitmap a(final Context context, final CacheLockBmpCallback cacheLockBmpCallback) {
        final String str = context.getCacheDir() + "/blur.png";
        File file = new File(str);
        if (file.exists()) {
            return BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        new Thread(new Runnable() {
            /* JADX WARNING: Removed duplicated region for block: B:30:0x0070 A[SYNTHETIC, Splitter:B:30:0x0070] */
            /* JADX WARNING: Removed duplicated region for block: B:40:0x0094 A[SYNTHETIC, Splitter:B:40:0x0094] */
            /* JADX WARNING: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    android.content.Context r0 = r3
                    android.graphics.drawable.BitmapDrawable r0 = com.xiaomi.smarthome.library.common.util.DisplayUtils.c(r0)
                    if (r0 != 0) goto L_0x0009
                    return
                L_0x0009:
                    android.graphics.Bitmap r0 = r0.getBitmap()
                    if (r0 != 0) goto L_0x0010
                    return
                L_0x0010:
                    android.content.Context r1 = r3
                    r2 = 1056964608(0x3f000000, float:0.5)
                    android.graphics.Bitmap r1 = com.xiaomi.smarthome.library.common.util.DisplayUtils.a((android.content.Context) r1, (android.graphics.Bitmap) r0, (float) r2)
                    r0.recycle()
                    if (r1 != 0) goto L_0x001e
                    return
                L_0x001e:
                    java.io.File r0 = new java.io.File
                    java.lang.String r2 = r0
                    r0.<init>(r2)
                    boolean r2 = r0.exists()
                    if (r2 == 0) goto L_0x002e
                    com.xiaomi.smarthome.library.file.FileUtil.b((java.io.File) r0)
                L_0x002e:
                    r0 = 0
                    java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0092, all -> 0x006d }
                    java.io.File r3 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0092, all -> 0x006d }
                    java.lang.String r4 = r0     // Catch:{ FileNotFoundException -> 0x0092, all -> 0x006d }
                    r3.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0092, all -> 0x006d }
                    r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x0092, all -> 0x006d }
                    android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ FileNotFoundException -> 0x006b, all -> 0x0067 }
                    r3 = 100
                    r1.compress(r0, r3, r2)     // Catch:{ FileNotFoundException -> 0x006b, all -> 0x0067 }
                    r1.recycle()     // Catch:{ FileNotFoundException -> 0x006b, all -> 0x0067 }
                    r2.close()     // Catch:{ IOException -> 0x00af }
                    com.xiaomi.smarthome.library.common.util.DisplayUtils$CacheLockBmpCallback r0 = r4     // Catch:{ IOException -> 0x00af }
                    if (r0 == 0) goto L_0x00af
                    java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x00af }
                    java.lang.String r1 = r0     // Catch:{ IOException -> 0x00af }
                    r0.<init>(r1)     // Catch:{ IOException -> 0x00af }
                    boolean r1 = r0.exists()     // Catch:{ IOException -> 0x00af }
                    if (r1 == 0) goto L_0x00af
                    com.xiaomi.smarthome.library.common.util.DisplayUtils$CacheLockBmpCallback r1 = r4     // Catch:{ IOException -> 0x00af }
                    java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x00af }
                L_0x005f:
                    android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeFile(r0)     // Catch:{ IOException -> 0x00af }
                    r1.a(r0)     // Catch:{ IOException -> 0x00af }
                    goto L_0x00af
                L_0x0067:
                    r0 = move-exception
                    r1 = r0
                    r0 = r2
                    goto L_0x006e
                L_0x006b:
                    r0 = r2
                    goto L_0x0092
                L_0x006d:
                    r1 = move-exception
                L_0x006e:
                    if (r0 == 0) goto L_0x0091
                    r0.close()     // Catch:{ IOException -> 0x0091 }
                    com.xiaomi.smarthome.library.common.util.DisplayUtils$CacheLockBmpCallback r0 = r4     // Catch:{ IOException -> 0x0091 }
                    if (r0 == 0) goto L_0x0091
                    java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0091 }
                    java.lang.String r2 = r0     // Catch:{ IOException -> 0x0091 }
                    r0.<init>(r2)     // Catch:{ IOException -> 0x0091 }
                    boolean r2 = r0.exists()     // Catch:{ IOException -> 0x0091 }
                    if (r2 == 0) goto L_0x0091
                    com.xiaomi.smarthome.library.common.util.DisplayUtils$CacheLockBmpCallback r2 = r4     // Catch:{ IOException -> 0x0091 }
                    java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x0091 }
                    android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeFile(r0)     // Catch:{ IOException -> 0x0091 }
                    r2.a(r0)     // Catch:{ IOException -> 0x0091 }
                L_0x0091:
                    throw r1
                L_0x0092:
                    if (r0 == 0) goto L_0x00af
                    r0.close()     // Catch:{ IOException -> 0x00af }
                    com.xiaomi.smarthome.library.common.util.DisplayUtils$CacheLockBmpCallback r0 = r4     // Catch:{ IOException -> 0x00af }
                    if (r0 == 0) goto L_0x00af
                    java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x00af }
                    java.lang.String r1 = r0     // Catch:{ IOException -> 0x00af }
                    r0.<init>(r1)     // Catch:{ IOException -> 0x00af }
                    boolean r1 = r0.exists()     // Catch:{ IOException -> 0x00af }
                    if (r1 == 0) goto L_0x00af
                    com.xiaomi.smarthome.library.common.util.DisplayUtils$CacheLockBmpCallback r1 = r4     // Catch:{ IOException -> 0x00af }
                    java.lang.String r0 = r0.getAbsolutePath()     // Catch:{ IOException -> 0x00af }
                    goto L_0x005f
                L_0x00af:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.util.DisplayUtils.AnonymousClass1.run():void");
            }
        }).start();
        return null;
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

    public static BitmapDrawable c(Context context) {
        try {
            Class<?> cls = Class.forName("miui.content.res.ThemeResources");
            return (BitmapDrawable) cls.getDeclaredMethod("getLockWallpaperCache", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception unused) {
            return null;
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

    public static SpannableString a(String str) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new LeadingMarginSpan.Standard(a(14.0f), 0), 0, str.length(), 0);
        return spannableString;
    }

    public static SpannableString b(String str) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new BulletSpan(a(6.0f), -16731273), 0, str.length(), 0);
        return spannableString;
    }

    public static Bitmap a(Context context, Bitmap bitmap, float f, int i) {
        if (f < 1.0f) {
            bitmap = Bitmap.createScaledBitmap(bitmap, Math.round(((float) bitmap.getWidth()) * f), Math.round(((float) bitmap.getHeight()) * f), false);
        }
        return a(bitmap, i);
    }

    private static Bitmap a(Bitmap bitmap, int i) {
        int[] iArr;
        int i2 = i;
        Bitmap copy = bitmap.copy(bitmap.getConfig(), true);
        if (i2 < 1) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i3 = width * height;
        int[] iArr2 = new int[i3];
        copy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i4 = width - 1;
        int i5 = height - 1;
        int i6 = i2 + i2 + 1;
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        int[] iArr5 = new int[i3];
        int[] iArr6 = new int[Math.max(width, height)];
        int i7 = (i6 + 1) >> 1;
        int i8 = i7 * i7;
        int i9 = i8 * 256;
        int[] iArr7 = new int[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            iArr7[i10] = i10 / i8;
        }
        int[][] iArr8 = (int[][]) Array.newInstance(int.class, new int[]{i6, 3});
        int i11 = i2 + 1;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i12 < height) {
            Bitmap bitmap2 = copy;
            int i15 = -i2;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            while (i15 <= i2) {
                int i25 = i5;
                int i26 = height;
                int i27 = iArr2[i13 + Math.min(i4, Math.max(i15, 0))];
                int[] iArr9 = iArr8[i15 + i2];
                iArr9[0] = (i27 & 16711680) >> 16;
                iArr9[1] = (i27 & 65280) >> 8;
                iArr9[2] = i27 & 255;
                int abs = i11 - Math.abs(i15);
                i16 += iArr9[0] * abs;
                i17 += iArr9[1] * abs;
                i18 += iArr9[2] * abs;
                if (i15 > 0) {
                    i19 += iArr9[0];
                    i20 += iArr9[1];
                    i21 += iArr9[2];
                } else {
                    i22 += iArr9[0];
                    i23 += iArr9[1];
                    i24 += iArr9[2];
                }
                i15++;
                height = i26;
                i5 = i25;
            }
            int i28 = i5;
            int i29 = height;
            int i30 = i2;
            int i31 = 0;
            while (i31 < width) {
                iArr3[i13] = iArr7[i16];
                iArr4[i13] = iArr7[i17];
                iArr5[i13] = iArr7[i18];
                int i32 = i16 - i22;
                int i33 = i17 - i23;
                int i34 = i18 - i24;
                int[] iArr10 = iArr8[((i30 - i2) + i6) % i6];
                int i35 = i22 - iArr10[0];
                int i36 = i23 - iArr10[1];
                int i37 = i24 - iArr10[2];
                if (i12 == 0) {
                    iArr = iArr7;
                    iArr6[i31] = Math.min(i31 + i2 + 1, i4);
                } else {
                    iArr = iArr7;
                }
                int i38 = iArr2[i14 + iArr6[i31]];
                iArr10[0] = (i38 & 16711680) >> 16;
                iArr10[1] = (i38 & 65280) >> 8;
                iArr10[2] = i38 & 255;
                int i39 = i19 + iArr10[0];
                int i40 = i20 + iArr10[1];
                int i41 = i21 + iArr10[2];
                i16 = i32 + i39;
                i17 = i33 + i40;
                i18 = i34 + i41;
                i30 = (i30 + 1) % i6;
                int[] iArr11 = iArr8[i30 % i6];
                i22 = i35 + iArr11[0];
                i23 = i36 + iArr11[1];
                i24 = i37 + iArr11[2];
                i19 = i39 - iArr11[0];
                i20 = i40 - iArr11[1];
                i21 = i41 - iArr11[2];
                i13++;
                i31++;
                iArr7 = iArr;
            }
            int[] iArr12 = iArr7;
            i14 += width;
            i12++;
            copy = bitmap2;
            height = i29;
            i5 = i28;
        }
        Bitmap bitmap3 = copy;
        int i42 = i5;
        int i43 = height;
        int[] iArr13 = iArr7;
        int i44 = 0;
        while (i44 < width) {
            int i45 = -i2;
            int i46 = i45 * width;
            int i47 = 0;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = 0;
            while (i45 <= i2) {
                int[] iArr14 = iArr6;
                int max = Math.max(0, i46) + i44;
                int[] iArr15 = iArr8[i45 + i2];
                iArr15[0] = iArr3[max];
                iArr15[1] = iArr4[max];
                iArr15[2] = iArr5[max];
                int abs2 = i11 - Math.abs(i45);
                i47 += iArr3[max] * abs2;
                i48 += iArr4[max] * abs2;
                i49 += iArr5[max] * abs2;
                if (i45 > 0) {
                    i50 += iArr15[0];
                    i51 += iArr15[1];
                    i52 += iArr15[2];
                } else {
                    i53 += iArr15[0];
                    i54 += iArr15[1];
                    i55 += iArr15[2];
                }
                int i56 = i42;
                if (i45 < i56) {
                    i46 += width;
                }
                i45++;
                i42 = i56;
                iArr6 = iArr14;
            }
            int[] iArr16 = iArr6;
            int i57 = i42;
            int i58 = i44;
            int i59 = i51;
            int i60 = i52;
            int i61 = 0;
            int i62 = i2;
            int i63 = i50;
            int i64 = i49;
            int i65 = i48;
            int i66 = i47;
            int i67 = i43;
            while (i61 < i67) {
                iArr2[i58] = (iArr2[i58] & -16777216) | (iArr13[i66] << 16) | (iArr13[i65] << 8) | iArr13[i64];
                int i68 = i66 - i53;
                int i69 = i65 - i54;
                int i70 = i64 - i55;
                int[] iArr17 = iArr8[((i62 - i2) + i6) % i6];
                int i71 = i53 - iArr17[0];
                int i72 = i54 - iArr17[1];
                int i73 = i55 - iArr17[2];
                if (i44 == 0) {
                    iArr16[i61] = Math.min(i61 + i11, i57) * width;
                }
                int i74 = iArr16[i61] + i44;
                iArr17[0] = iArr3[i74];
                iArr17[1] = iArr4[i74];
                iArr17[2] = iArr5[i74];
                int i75 = i63 + iArr17[0];
                int i76 = i59 + iArr17[1];
                int i77 = i60 + iArr17[2];
                i66 = i68 + i75;
                i65 = i69 + i76;
                i64 = i70 + i77;
                i62 = (i62 + 1) % i6;
                int[] iArr18 = iArr8[i62];
                i53 = i71 + iArr18[0];
                i54 = i72 + iArr18[1];
                i55 = i73 + iArr18[2];
                i63 = i75 - iArr18[0];
                i59 = i76 - iArr18[1];
                i60 = i77 - iArr18[2];
                i58 += width;
                i61++;
                i2 = i;
            }
            i44++;
            i42 = i57;
            i43 = i67;
            iArr6 = iArr16;
            i2 = i;
        }
        bitmap3.setPixels(iArr2, 0, width, 0, 0, width, i43);
        return bitmap3;
    }

    public static void a(View view, int i, int i2) {
        if (view != null && i >= 0 && view.getVisibility() != i2) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration((long) i);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            if (i2 == 4 || i2 == 8) {
                view.setVisibility(i2);
            } else {
                view.setVisibility(8);
            }
            view.startAnimation(alphaAnimation);
        }
    }

    public static void a(View view, int i) {
        if (view != null && i >= 0 && view.getVisibility() != 0) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration((long) i);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            view.setVisibility(0);
            view.startAnimation(alphaAnimation);
        }
    }

    private static boolean f(Context context) {
        try {
            Field declaredField = Activity.class.getDeclaredField("mActivityInfo");
            declaredField.setAccessible(true);
            ((ActivityInfo) declaredField.get(context)).screenOrientation = -1;
            declaredField.setAccessible(false);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean d(Context context) {
        boolean z;
        try {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes((int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get((Object) null));
            Method method = ActivityInfo.class.getMethod("isTranslucentOrFloating", new Class[]{TypedArray.class});
            method.setAccessible(true);
            z = ((Boolean) method.invoke((Object) null, new Object[]{obtainStyledAttributes})).booleanValue();
            try {
                method.setAccessible(false);
                obtainStyledAttributes.recycle();
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
            e.printStackTrace();
            return z;
        }
        return z;
    }

    public static void e(Context context) {
        if (Build.VERSION.SDK_INT == 26 && d(context)) {
            f(context);
        }
    }
}
