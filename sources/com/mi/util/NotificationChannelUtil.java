package com.mi.util;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.text.TextUtils;
import com.coloros.mcssdk.PushManager;
import com.mi.MiApplicationContext;

public class NotificationChannelUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1350a = "message";

    public static void a() {
        if (Build.VERSION.SDK_INT >= 26) {
            String a2 = ResourceUtil.a("message");
            if (TextUtils.isEmpty(a2)) {
                a2 = "message";
            }
            a("message", a2, 3);
        }
    }

    @TargetApi(26)
    public static void a(String str, String str2, int i) {
        ((NotificationManager) MiApplicationContext.f1260a.getSystemService(PushManager.MESSAGE_TYPE_NOTI)).createNotificationChannel(new NotificationChannel(str, str2, i));
    }
}
