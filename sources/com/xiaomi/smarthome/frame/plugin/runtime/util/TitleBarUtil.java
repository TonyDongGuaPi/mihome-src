package com.xiaomi.smarthome.frame.plugin.runtime.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.libra.Color;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.sdk.R;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;
import java.lang.reflect.Field;

@TargetApi(19)
public class TitleBarUtil {
    private static int TOP_PADDING = -1;
    public static boolean TRANSLUCENT_STATUS_ENABLED = false;

    public static void enableTranslucentStatus(Activity activity) {
        if (Build.VERSION.SDK_INT < 19) {
            TRANSLUCENT_STATUS_ENABLED = false;
        } else if (activity != null) {
            Window window = activity.getWindow();
            if (!SystemApi.c()) {
                enableTranslucentStatus(window);
                return;
            }
            try {
                Class<?> cls = window.getClass();
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(cls2);
                int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                int i3 = i2 | i;
                cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE}).invoke(window, new Object[]{Integer.valueOf(i3), Integer.valueOf(i3)});
                window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                View decorView = window.getDecorView();
                window.addFlags(Integer.MIN_VALUE);
                window.getDecorView().setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
                TRANSLUCENT_STATUS_ENABLED = true;
            } catch (Exception unused) {
                TRANSLUCENT_STATUS_ENABLED = false;
            }
            if (!TRANSLUCENT_STATUS_ENABLED) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags = 67108864 | attributes.flags;
                window.setAttributes(attributes);
                TRANSLUCENT_STATUS_ENABLED = true;
            }
        }
    }

    public static void enableWhiteTranslucentStatus(Window window) {
        if (Build.VERSION.SDK_INT < 19) {
            TRANSLUCENT_STATUS_ENABLED = false;
            return;
        }
        try {
            Class<?> cls = window.getClass();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(cls2);
            int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE}).invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i2 | i)});
            window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
            window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & -8193);
            TRANSLUCENT_STATUS_ENABLED = true;
        } catch (Exception unused) {
            TRANSLUCENT_STATUS_ENABLED = false;
        }
        if (!TRANSLUCENT_STATUS_ENABLED) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                declaredField2.setInt(attributes, (declaredField.getInt((Object) null) ^ -1) & declaredField2.getInt(attributes));
                window.setAttributes(attributes);
                window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                TRANSLUCENT_STATUS_ENABLED = true;
            } catch (Exception unused2) {
                TRANSLUCENT_STATUS_ENABLED = false;
            }
        }
        if (!TRANSLUCENT_STATUS_ENABLED) {
            try {
                if (Build.VERSION.SDK_INT >= 21) {
                    window.clearFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                    window.getDecorView().setSystemUiVisibility(9216);
                    window.addFlags(Integer.MIN_VALUE);
                    if (Build.BRAND.equalsIgnoreCase("oppo")) {
                        window.setStatusBarColor(Color.d);
                    } else {
                        window.setStatusBarColor(0);
                    }
                } else {
                    WindowManager.LayoutParams attributes2 = window.getAttributes();
                    attributes2.flags = 67108864 | attributes2.flags;
                    window.setAttributes(attributes2);
                }
                TRANSLUCENT_STATUS_ENABLED = true;
            } catch (Exception e) {
                e.printStackTrace();
                TRANSLUCENT_STATUS_ENABLED = false;
            }
        }
    }

    public static void enableWhiteTranslucentStatus(Activity activity) {
        if (activity != null) {
            if (Build.VERSION.SDK_INT < 19) {
                TRANSLUCENT_STATUS_ENABLED = false;
                return;
            }
            Window window = activity.getWindow();
            try {
                Class<?> cls = window.getClass();
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(cls2);
                int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE}).invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i2 | i)});
                window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                SystemApi.a();
                if (SystemApi.c()) {
                    window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility() & -8193);
                }
                TRANSLUCENT_STATUS_ENABLED = true;
            } catch (Exception unused) {
                TRANSLUCENT_STATUS_ENABLED = false;
            }
            if (!TRANSLUCENT_STATUS_ENABLED) {
                try {
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                    Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                    declaredField.setAccessible(true);
                    declaredField2.setAccessible(true);
                    declaredField2.setInt(attributes, (declaredField.getInt((Object) null) ^ -1) & declaredField2.getInt(attributes));
                    window.setAttributes(attributes);
                    window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                    TRANSLUCENT_STATUS_ENABLED = true;
                } catch (Exception unused2) {
                    TRANSLUCENT_STATUS_ENABLED = false;
                }
            }
            if (!TRANSLUCENT_STATUS_ENABLED) {
                if (Build.VERSION.SDK_INT >= 21) {
                    window.clearFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                    window.getDecorView().setSystemUiVisibility(9216);
                    window.addFlags(Integer.MIN_VALUE);
                    if (Build.BRAND.equalsIgnoreCase("oppo") || Build.BRAND.equalsIgnoreCase("HUAWEI")) {
                        window.setStatusBarColor(Color.d);
                    } else {
                        window.setStatusBarColor(0);
                    }
                } else {
                    WindowManager.LayoutParams attributes2 = window.getAttributes();
                    attributes2.flags = 67108864 | attributes2.flags;
                    window.setAttributes(attributes2);
                }
                TRANSLUCENT_STATUS_ENABLED = true;
            }
        }
    }

    public static void setTitleBar(Activity activity, View view) {
        View view2;
        int i;
        if (TRANSLUCENT_STATUS_ENABLED && activity != null) {
            if (view == null) {
                view2 = activity.findViewById(R.id.title_bar);
            } else {
                view2 = view.findViewById(R.id.title_bar);
            }
            if (view2 != null) {
                if (TOP_PADDING == -1) {
                    try {
                        Resources resources = activity.getResources();
                        i = resources.getDimensionPixelOffset(resources.getIdentifier(PreferenceConstantsInOpenSdk.B, "dimen", "android"));
                    } catch (Exception unused) {
                        i = activity.getResources().getDimensionPixelSize(R.dimen.title_bar_top_padding);
                    }
                    TOP_PADDING = i;
                }
                if (view2.getLayoutParams().height > 0) {
                    view2.getLayoutParams().height += TOP_PADDING;
                }
                view2.setPadding(0, TOP_PADDING, 0, 0);
                view2.setLayoutParams(view2.getLayoutParams());
            }
        }
    }

    public static void setTitleBarPadding(View view) {
        if (view != null) {
            setTitleBarPadding(getStatusHeight(view.getContext()), view);
        }
    }

    public static int getStatusHeight(Context context) {
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

    public static void setTitleBar(Activity activity) {
        setTitleBar(activity, (View) null);
    }

    public static void setTitleBarPadding(int i, View view) {
        if (TRANSLUCENT_STATUS_ENABLED && view != null) {
            if (view.getLayoutParams().height > 0) {
                view.getLayoutParams().height += i;
            }
            view.setPadding(0, i, 0, 0);
            view.setLayoutParams(view.getLayoutParams());
        }
    }

    public static void enableTranslucentStatus(Window window) {
        if (Build.VERSION.SDK_INT < 19) {
            TRANSLUCENT_STATUS_ENABLED = false;
            return;
        }
        if (SystemApi.c()) {
            try {
                Class<?> cls = window.getClass();
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(cls2);
                int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2) | i;
                cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE}).invoke(window, new Object[]{Integer.valueOf(i2), Integer.valueOf(i2)});
                window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
                window.addFlags(Integer.MIN_VALUE);
                window.getDecorView().setSystemUiVisibility(8192);
                TRANSLUCENT_STATUS_ENABLED = true;
            } catch (Exception unused) {
                TRANSLUCENT_STATUS_ENABLED = false;
            }
        }
        try {
            WindowManager.LayoutParams attributes = window.getAttributes();
            Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            declaredField.setAccessible(true);
            declaredField2.setAccessible(true);
            declaredField2.setInt(attributes, declaredField.getInt((Object) null) | declaredField2.getInt(attributes));
            window.setAttributes(attributes);
            window.setFlags(Constants.CALLIGRAPHY_TAG_PRICE, Constants.CALLIGRAPHY_TAG_PRICE);
            TRANSLUCENT_STATUS_ENABLED = true;
        } catch (Exception unused2) {
            TRANSLUCENT_STATUS_ENABLED = false;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            window.getDecorView().setSystemUiVisibility(9216);
            window.addFlags(Integer.MIN_VALUE);
            if (Build.BRAND.equalsIgnoreCase("oppo")) {
                window.setStatusBarColor(Color.d);
            } else if (Build.BRAND.equalsIgnoreCase("vivo")) {
                window.setStatusBarColor(33554431);
            } else {
                window.setStatusBarColor(0);
            }
        } else {
            WindowManager.LayoutParams attributes2 = window.getAttributes();
            attributes2.flags |= Constants.CALLIGRAPHY_TAG_PRICE;
            window.setAttributes(attributes2);
        }
        TRANSLUCENT_STATUS_ENABLED = true;
    }
}
