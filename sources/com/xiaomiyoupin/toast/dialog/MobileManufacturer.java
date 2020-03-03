package com.xiaomiyoupin.toast.dialog;

import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.youpin.UserMode;

public class MobileManufacturer {
    public static boolean isMIUI() {
        return Build.BRAND.equalsIgnoreCase(UserMode.f23179a);
    }

    public static int getMIUIVersion() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str = (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.miui.ui.version.name"});
            if (!TextUtils.isEmpty(str)) {
                return Integer.valueOf(str.substring(1)).intValue();
            }
            return 6;
        } catch (Exception e) {
            e.printStackTrace();
            return 6;
        }
    }
}
