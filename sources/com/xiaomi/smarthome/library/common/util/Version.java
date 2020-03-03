package com.xiaomi.smarthome.library.common.util;

import android.os.Build;

public class Version {
    public static boolean a() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
