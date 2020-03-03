package com.liulishuo.filedownloader.notification;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadList;
import com.liulishuo.filedownloader.FileDownloadListener;
import junit.framework.Assert;

public abstract class FileDownloadNotificationListener extends FileDownloadListener {
    private final FileDownloadNotificationHelper helper;

    /* access modifiers changed from: protected */
    public void blockComplete(BaseDownloadTask baseDownloadTask) {
    }

    /* access modifiers changed from: protected */
    public abstract BaseNotificationItem create(BaseDownloadTask baseDownloadTask);

    /* access modifiers changed from: protected */
    public boolean disableNotification(BaseDownloadTask baseDownloadTask) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean interceptCancel(BaseDownloadTask baseDownloadTask, BaseNotificationItem baseNotificationItem) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void warn(BaseDownloadTask baseDownloadTask) {
    }

    public FileDownloadNotificationListener(FileDownloadNotificationHelper fileDownloadNotificationHelper) {
        Assert.assertNotNull("FileDownloadNotificationHelper must not null", fileDownloadNotificationHelper);
        this.helper = fileDownloadNotificationHelper;
    }

    public FileDownloadNotificationHelper getHelper() {
        return this.helper;
    }

    public void addNotificationItem(int i) {
        BaseDownloadTask.IRunningTask b;
        if (i != 0 && (b = FileDownloadList.a().b(i)) != null) {
            addNotificationItem(b.P());
        }
    }

    public void addNotificationItem(BaseDownloadTask baseDownloadTask) {
        BaseNotificationItem create;
        if (!disableNotification(baseDownloadTask) && (create = create(baseDownloadTask)) != null) {
            this.helper.a(create);
        }
    }

    public void destroyNotification(BaseDownloadTask baseDownloadTask) {
        if (!disableNotification(baseDownloadTask)) {
            this.helper.a(baseDownloadTask.k(), baseDownloadTask.B());
            BaseNotificationItem c = this.helper.c(baseDownloadTask.k());
            if (!interceptCancel(baseDownloadTask, c) && c != null) {
                c.cancel();
            }
        }
    }

    public void showIndeterminate(BaseDownloadTask baseDownloadTask) {
        if (!disableNotification(baseDownloadTask)) {
            this.helper.a(baseDownloadTask.k(), baseDownloadTask.B());
        }
    }

    public void showProgress(BaseDownloadTask baseDownloadTask, int i, int i2) {
        if (!disableNotification(baseDownloadTask)) {
            this.helper.a(baseDownloadTask.k(), baseDownloadTask.v(), baseDownloadTask.y());
        }
    }

    /* access modifiers changed from: protected */
    public void pending(BaseDownloadTask baseDownloadTask, int i, int i2) {
        addNotificationItem(baseDownloadTask);
        showIndeterminate(baseDownloadTask);
    }

    /* access modifiers changed from: protected */
    public void started(BaseDownloadTask baseDownloadTask) {
        super.started(baseDownloadTask);
        showIndeterminate(baseDownloadTask);
    }

    /* access modifiers changed from: protected */
    public void progress(BaseDownloadTask baseDownloadTask, int i, int i2) {
        showProgress(baseDownloadTask, i, i2);
    }

    /* access modifiers changed from: protected */
    public void retry(BaseDownloadTask baseDownloadTask, Throwable th, int i, int i2) {
        super.retry(baseDownloadTask, th, i, i2);
        showIndeterminate(baseDownloadTask);
    }

    /* access modifiers changed from: protected */
    public void completed(BaseDownloadTask baseDownloadTask) {
        destroyNotification(baseDownloadTask);
    }

    /* access modifiers changed from: protected */
    public void paused(BaseDownloadTask baseDownloadTask, int i, int i2) {
        destroyNotification(baseDownloadTask);
    }

    /* access modifiers changed from: protected */
    public void error(BaseDownloadTask baseDownloadTask, Throwable th) {
        destroyNotification(baseDownloadTask);
    }
}
