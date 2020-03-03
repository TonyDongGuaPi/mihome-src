package com.xiaomi.smarthome.library.common.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import com.coloros.mcssdk.PushManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.NotificationChannelCreator;

public class NotificationUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18691a = 1;
    public static final int b = 2;
    public static final String c = "pref_notification_setting";
    private static final int d = 5000;
    private static long e;

    public static void a(Intent intent, Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, boolean z, boolean z2, int i) {
        a(intent, context, charSequence, charSequence2, charSequence3, z, z2, i, R.drawable.notify_icon);
    }

    public static void a(Intent intent, Context context, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, boolean z, boolean z2, int i, int i2) {
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 134217728);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(SHApplication.getAppContext());
        builder.setSmallIcon(i2);
        builder.setContentTitle(charSequence2);
        builder.setContentText(charSequence3);
        builder.setContentIntent(activity);
        builder.setAutoCancel(true);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - e > 5000) {
            e = currentTimeMillis;
        }
        a(charSequence2, charSequence3, i, i2, activity);
    }

    public static void a(CharSequence charSequence, CharSequence charSequence2, int i, int i2, PendingIntent pendingIntent) {
        Context appContext = SHApplication.getAppContext();
        NotificationManager notificationManager = (NotificationManager) appContext.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (notificationManager != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.notify(i, new Notification.Builder(appContext, NotificationChannelCreator.b(notificationManager)).setTicker(charSequence).setContentTitle(charSequence).setContentText(charSequence2).setAutoCancel(true).setSmallIcon(i2).setContentIntent(pendingIntent).build());
                return;
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(appContext);
            builder.setTicker(charSequence);
            builder.setContentTitle(charSequence);
            builder.setContentText(charSequence2);
            builder.setAutoCancel(true);
            builder.setSmallIcon(i2);
            builder.setContentIntent(pendingIntent);
            notificationManager.notify(i, builder.build());
        }
    }

    public static void a(Context context, int i) {
        ((NotificationManager) context.getSystemService(PushManager.MESSAGE_TYPE_NOTI)).cancel(i);
    }
}
