package com.xiaomi.smarthome.lite.scene;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.frame.HostSetting;

public class SceneLogUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f19412a = "SceneLogUtil";

    public static void a(String str) {
        if (!TextUtils.isEmpty(str) && HostSetting.g) {
            Log.d(f19412a, str);
        }
    }
}
