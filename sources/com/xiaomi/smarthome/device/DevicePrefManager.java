package com.xiaomi.smarthome.device;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;

public class DevicePrefManager {

    /* renamed from: a  reason: collision with root package name */
    static final String f14833a = "device_last_use_time_";

    public static void b(String str) {
    }

    public static long a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        Context appContext = SHApplication.getAppContext();
        return PreferenceUtils.b(appContext, f14833a + str, 0);
    }
}
