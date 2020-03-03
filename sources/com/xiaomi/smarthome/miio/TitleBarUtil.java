package com.xiaomi.smarthome.miio;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.org.smarthome_activity.R;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@TargetApi(19)
public class TitleBarUtil {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f11582a = false;
    private static int b = -1;

    public static void a(Window window) {
        if (window != null) {
            f11582a = a(window, true);
        }
    }

    public static void b(Window window) {
        if (window != null) {
            f11582a = a(window, false);
        }
    }

    public static void a(View view) {
        if (view != null) {
            a(a(view.getContext()), view);
        }
    }

    public static void a(int i, View view) {
        if (f11582a && view != null) {
            if (view.getLayoutParams().height >= 0) {
                view.getLayoutParams().height += i;
            }
            view.setPadding(0, i, 0, 0);
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    private static boolean a(Window window, boolean z) {
        if (Build.BRAND.equalsIgnoreCase("htc") && Build.MODEL.contains("M8w")) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT < 19 || !SystemApi.c() || !c(window, z)) {
                if (Build.VERSION.SDK_INT >= 19 && b(window, z)) {
                    window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                    window.addFlags(2048);
                    return true;
                } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
                    window.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                    window.addFlags(2048);
                    return true;
                } else if (Build.VERSION.SDK_INT < 21) {
                    return false;
                } else {
                    window.clearFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                    window.addFlags(2048);
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
                }
            } else if (z) {
                window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                window.addFlags(Integer.MIN_VALUE);
                window.getDecorView().setSystemUiVisibility(8192);
                return true;
            } else {
                window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                window.addFlags(2048);
                window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & -8193);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean b(Window window, boolean z) {
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

    private static boolean c(Window window, boolean z) {
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
        int i;
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            i = context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField(PreferenceConstantsInOpenSdk.B).get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
            i = -1;
        }
        return i == -1 ? (int) TypedValue.applyDimension(1, 20.0f, context.getResources().getDisplayMetrics()) : i;
    }

    public static void a(Activity activity, View view) {
        View view2;
        if (f11582a && activity != null) {
            if (view == null) {
                view2 = activity.findViewById(R.id.title_bar);
            } else {
                view2 = view.findViewById(R.id.title_bar);
            }
            if (view2 != null) {
                if (b == -1) {
                    int dimensionPixelSize = activity.getResources().getDimensionPixelSize(R.dimen.title_bar_top_padding);
                    try {
                        Resources resources = CommonApplication.getApplication().getResources();
                        dimensionPixelSize = resources.getDimensionPixelOffset(resources.getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android"));
                    } catch (Exception unused) {
                    }
                    b = dimensionPixelSize;
                }
                if (view2.getLayoutParams().height > 0) {
                    view2.getLayoutParams().height += b;
                }
                view2.setPadding(0, b, 0, 0);
                view2.setLayoutParams(view2.getLayoutParams());
            }
        }
    }

    public static int a() {
        if (b == -1) {
            int dimensionPixelSize = CommonApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.title_bar_top_padding);
            try {
                Resources resources = CommonApplication.getApplication().getResources();
                dimensionPixelSize = resources.getDimensionPixelOffset(resources.getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android"));
            } catch (Exception unused) {
            }
            b = dimensionPixelSize;
        }
        return b;
    }

    public static int b() {
        if (f11582a) {
            return a();
        }
        return 0;
    }

    public static void a(Activity activity) {
        if (activity != null) {
            f11582a = a(activity.getWindow(), !DarkModeCompat.a((Context) activity));
        }
    }

    public static void b(Activity activity) {
        if (activity != null) {
            f11582a = a(activity.getWindow(), false);
        }
    }

    public static void c(Activity activity) {
        a(activity, (View) null);
    }
}
