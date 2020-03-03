package com.mi.global.bbs.service;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import com.liulishuo.filedownloader.notification.BaseNotificationItem;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.ui.MainActivity;

public class NotificationItem extends BaseNotificationItem {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(FileDownloadHelper.a());
    PendingIntent pendingIntent = PendingIntent.getActivities(BBSApplication.getInstance(), 0, new Intent[]{Intent.makeMainActivity(new ComponentName(BBSApplication.getInstance(), MainActivity.class))}, 134217728);

    public NotificationItem(int i, String str, String str2) {
        super(i, str, str2);
        this.builder.setDefaults(4).setOngoing(true).setPriority(-2).setContentTitle(getTitle()).setContentText(str2).setContentIntent(this.pendingIntent).setSmallIcon(R.mipmap.ic_launcher);
    }

    public void show(boolean z, int i, boolean z2) {
        String desc = getDesc();
        this.builder.setContentTitle(getTitle()).setContentText(desc);
        if (z) {
            this.builder.setTicker(desc);
        }
        this.builder.setProgress(getTotal(), getSofar(), !z2);
        getManager().notify(getId(), this.builder.build());
    }
}
