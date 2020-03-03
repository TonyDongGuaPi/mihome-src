package com.xiaomi.smarthome.core.server.internal.plugin;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;

public class NotificationChannelCreator {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14586a = "SmartHome";
    public static final String b = "SmartHome";
    public static final String c = "SmartHome";
    public static final String d = "other";
    public static final String e = "other";
    private static final String f = "SmartHome";
    private static final String g = "SmartHome";
    private static final String h = "SmartHome";

    public static String a(NotificationManager notificationManager) {
        return a(notificationManager, (String) null, "SmartHome", "SmartHome");
    }

    public static String b(NotificationManager notificationManager) {
        return a(notificationManager, (String) null, "SmartHome", "SmartHome");
    }

    public static String c(NotificationManager notificationManager) {
        return a(notificationManager, (String) null, "SmartHome", "SmartHome");
    }

    public static String a(NotificationManager notificationManager, String str, String str2) {
        b(notificationManager, str, str2, str2);
        return str2;
    }

    public static String a(NotificationManager notificationManager, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            str = "other";
        }
        b(notificationManager, str, str2, str3);
        return str2;
    }

    private static NotificationManager b(NotificationManager notificationManager, String str, String str2, String str3) {
        if (notificationManager == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 26 && notificationManager.getNotificationChannel(str2) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(str2, str3, 4);
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    NotificationChannelGroup notificationChannelGroup = notificationManager.getNotificationChannelGroup(str);
                    if (notificationChannelGroup == null) {
                        notificationChannelGroup = new NotificationChannelGroup(str, "other");
                        notificationManager.createNotificationChannelGroup(notificationChannelGroup);
                    }
                    notificationChannel.setGroup(notificationChannelGroup.getId());
                }
            } catch (Exception e2) {
                LogUtil.b("NotificationChannelCreator", e2.toString());
            }
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return notificationManager;
    }
}
