package com.xiaomi.smarthome.framework.push.listener;

import android.util.Log;
import com.xiaomi.smarthome.framework.push.PushListener;

public class DeviceBindPushListener extends PushListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17671a = "DeviceBindPushListener";

    public boolean a(String str, String str2) {
        c(str, str2);
        return true;
    }

    public boolean b(String str, String str2) {
        c(str, str2);
        return true;
    }

    private void c(String str, String str2) {
        Log.i(f17671a, "processMessage messageId:" + str + " " + str2);
    }
}
