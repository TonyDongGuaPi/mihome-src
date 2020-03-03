package com.xiaomi.smarthome.app.startup;

import android.os.Build;
import android.view.Window;
import com.xiaomi.mishopsdk.util.Constants;

public class StartupUtils {
    public static void a(Window window) {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Class<?> cls = window.getClass();
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT").getInt(cls2);
                cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                cls.getMethod("setExtraFlags", new Class[]{Integer.TYPE, Integer.TYPE}).invoke(window, new Object[]{Integer.valueOf(i), Integer.valueOf(i)});
                window.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            } catch (Exception unused) {
            }
        }
    }
}
