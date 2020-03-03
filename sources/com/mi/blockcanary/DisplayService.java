package com.mi.blockcanary;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import com.coloros.mcssdk.PushManager;
import com.mi.blockcanary.internal.BlockInfo;
import com.mi.blockcanary.ui.DisplayActivity;
import java.lang.reflect.InvocationTargetException;

final class DisplayService implements BlockInterceptor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6744a = "DisplayService";

    DisplayService() {
    }

    public void a(Context context, BlockInfo blockInfo) {
        Intent intent = new Intent(context, DisplayActivity.class);
        intent.putExtra("show_latest", blockInfo.y);
        intent.setFlags(335544320);
        a(context, context.getString(R.string.block_canary_class_has_blocked, new Object[]{blockInfo.y}), context.getString(R.string.block_canary_notification_message), PendingIntent.getActivity(context, 1, intent, 134217728));
    }

    @TargetApi(11)
    private void a(Context context, String str, String str2, PendingIntent pendingIntent) {
        Notification notification;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (Build.VERSION.SDK_INT < 11) {
            notification = new Notification();
            notification.icon = R.drawable.block_canary_notification;
            notification.when = System.currentTimeMillis();
            notification.flags = 16 | notification.flags;
            notification.defaults = 1;
            try {
                notification.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, str, str2, pendingIntent});
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e) {
                Log.w(f6744a, "Method not found", e);
            }
        } else {
            Notification.Builder defaults = new Notification.Builder(context).setSmallIcon(R.drawable.block_canary_notification).setWhen(System.currentTimeMillis()).setContentTitle(str).setContentText(str2).setAutoCancel(true).setContentIntent(pendingIntent).setDefaults(1);
            notification = Build.VERSION.SDK_INT < 16 ? defaults.getNotification() : defaults.build();
        }
        notificationManager.notify(-558907665, notification);
    }
}
