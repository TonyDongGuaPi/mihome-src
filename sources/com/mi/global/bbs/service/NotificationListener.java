package com.mi.global.bbs.service;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.notification.BaseNotificationItem;
import com.liulishuo.filedownloader.notification.FileDownloadNotificationHelper;
import com.liulishuo.filedownloader.notification.FileDownloadNotificationListener;
import java.util.List;

public class NotificationListener extends FileDownloadNotificationListener {
    /* access modifiers changed from: protected */
    public boolean interceptCancel(BaseDownloadTask baseDownloadTask, BaseNotificationItem baseNotificationItem) {
        return false;
    }

    public NotificationListener(FileDownloadNotificationHelper fileDownloadNotificationHelper, List<String> list) {
        super(fileDownloadNotificationHelper);
    }

    /* access modifiers changed from: protected */
    public BaseNotificationItem create(BaseDownloadTask baseDownloadTask) {
        return new NotificationItem(baseDownloadTask.k(), "Mi Community", "Mi Community is downloading");
    }

    public void addNotificationItem(BaseDownloadTask baseDownloadTask) {
        super.addNotificationItem(baseDownloadTask);
    }

    public void destroyNotification(BaseDownloadTask baseDownloadTask) {
        super.destroyNotification(baseDownloadTask);
    }

    /* access modifiers changed from: protected */
    public boolean disableNotification(BaseDownloadTask baseDownloadTask) {
        return super.disableNotification(baseDownloadTask);
    }

    /* access modifiers changed from: protected */
    public void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
        super.pending(baseDownloadTask, i, i2);
    }

    /* access modifiers changed from: protected */
    public void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
        super.progress(baseDownloadTask, i, i2);
    }

    /* access modifiers changed from: protected */
    public void completed(BaseDownloadTask baseDownloadTask) {
        super.completed(baseDownloadTask);
    }
}
