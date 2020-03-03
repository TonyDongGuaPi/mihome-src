package com.liulishuo.filedownloader.notification;

import android.app.NotificationManager;
import com.coloros.mcssdk.PushManager;
import com.liulishuo.filedownloader.util.FileDownloadHelper;

public abstract class BaseNotificationItem {
    private String desc;
    private int id;
    private int lastStatus = 0;
    private NotificationManager manager;
    private int sofar;
    private int status = 0;
    private String title;
    private int total;

    public abstract void show(boolean z, int i, boolean z2);

    public BaseNotificationItem(int i, String str, String str2) {
        this.id = i;
        this.title = str;
        this.desc = str2;
    }

    public void show(boolean z) {
        show(isChanged(), getStatus(), z);
    }

    public void update(int i, int i2) {
        this.sofar = i;
        this.total = i2;
        show(true);
    }

    public void updateStatus(int i) {
        this.status = i;
    }

    public void cancel() {
        getManager().cancel(this.id);
    }

    /* access modifiers changed from: protected */
    public NotificationManager getManager() {
        if (this.manager == null) {
            this.manager = (NotificationManager) FileDownloadHelper.a().getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        }
        return this.manager;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getSofar() {
        return this.sofar;
    }

    public void setSofar(int i) {
        this.sofar = i;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public int getStatus() {
        this.lastStatus = this.status;
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getLastStatus() {
        return this.lastStatus;
    }

    public boolean isChanged() {
        return this.lastStatus != this.status;
    }
}
