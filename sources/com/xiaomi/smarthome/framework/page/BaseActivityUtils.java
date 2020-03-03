package com.xiaomi.smarthome.framework.page;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.xiaomi.smarthome.application.CommonApplication;

public class BaseActivityUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16690a = "miot_activit_finish_tag";
    public static Runnable b = new Runnable() {
        public void run() {
            Intent intent = new Intent();
            intent.setAction("miot_activit_finish_tag");
            LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).sendBroadcast(intent);
        }
    };
}
