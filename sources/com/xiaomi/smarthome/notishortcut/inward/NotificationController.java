package com.xiaomi.smarthome.notishortcut.inward;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.coloros.mcssdk.PushManager;
import com.xiaomi.smarthome.notishortcut.R;
import com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager;
import java.util.List;

public class NotificationController {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1565a = 990;
    private static final int b = 999;

    public static boolean a(Context context) {
        return false;
    }

    public static void a(Service service, List<NotiSettingManager.NotiItem> list) {
        Notification notification;
        if (list == null || list.size() == 0) {
            a(service, 999, service.getResources().getString(R.string.app_name), service.getResources().getString(R.string.app_name), new Intent("com.xiaomi.smarthome.notification.auth_expired"));
            a(service);
            LogUtil.a("NotificationController", "createShortcutNotification cancelShortcutNotifications");
            return;
        }
        RemoteViews a2 = RemoteViewHelper.a((Context) service, list);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) service.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
            NotificationChannel notificationChannel = new NotificationChannel("shortcut", "shortcut", 2);
            if (Build.VERSION.SDK_INT >= 28) {
                if (notificationManager.getNotificationChannelGroup("other") == null) {
                    notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("other", "other"));
                }
                if (TextUtils.isEmpty(notificationChannel.getGroup())) {
                    notificationChannel.setGroup("other");
                }
            }
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
            Notification.Builder builder = new Notification.Builder(service, "shortcut");
            builder.setSmallIcon(R.drawable.notify_icon).setCustomContentView(a2).setOngoing(true);
            notification = builder.build();
        } else {
            NotificationCompat.Builder builder2 = new NotificationCompat.Builder(service);
            builder2.setSmallIcon(R.drawable.notify_icon).setCustomContentView(a2).setCustomBigContentView(a2).setOngoing(true).setPriority(2);
            notification = builder2.build();
        }
        notification.flags |= 16;
        NotificationManager notificationManager2 = (NotificationManager) service.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (a((Context) service)) {
            notificationManager2.notify(999, notification);
        } else if (Build.VERSION.SDK_INT < 24) {
            notificationManager2.notify(999, notification);
        } else {
            service.startForeground(999, notification);
        }
    }

    public static void a(Service service) {
        NotificationManager notificationManager = (NotificationManager) service.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (a((Context) service)) {
            notificationManager.cancel(999);
        } else if (Build.VERSION.SDK_INT < 24) {
            notificationManager.cancel(999);
        } else {
            service.stopForeground(true);
        }
    }

    public static void a(Service service, int i, String str, String str2, Intent intent) {
        Notification.Builder builder;
        PendingIntent broadcast = PendingIntent.getBroadcast(service, (int) System.currentTimeMillis(), intent, 1073741824);
        NotificationManager notificationManager = (NotificationManager) service.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("shortcut", "shortcut", 2);
            if (Build.VERSION.SDK_INT >= 28) {
                if (notificationManager.getNotificationChannelGroup("other") == null) {
                    notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("other", "other"));
                }
                if (TextUtils.isEmpty(notificationChannel.getGroup())) {
                    notificationChannel.setGroup("other");
                }
            }
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
            builder = new Notification.Builder(service, "shortcut");
        } else {
            builder = new Notification.Builder(service);
        }
        builder.setSmallIcon(R.drawable.notify_icon).setContentTitle(str).setContentText(str2).setContentIntent(broadcast);
        Notification build = builder.build();
        build.flags |= 16;
        if (a((Context) service)) {
            notificationManager.notify(i, build);
        } else if (Build.VERSION.SDK_INT < 24) {
            notificationManager.notify(i, build);
        } else {
            service.startForeground(i, build);
        }
    }
}
