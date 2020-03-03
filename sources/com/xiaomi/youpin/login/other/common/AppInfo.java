package com.xiaomi.youpin.login.other.common;

import android.annotation.SuppressLint;
import android.content.Context;
import com.xiaomi.smarthome.library.deviceId.DeviceIdCompat;

public class AppInfo {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23566a = "AppInfo";

    @SuppressLint({"HardwareIds"})
    public static String a(Context context) {
        return SHA1Util.a(DeviceIdCompat.a(context));
    }
}
