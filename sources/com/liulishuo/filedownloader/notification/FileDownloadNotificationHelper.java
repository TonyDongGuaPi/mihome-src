package com.liulishuo.filedownloader.notification;

import android.util.SparseArray;
import com.liulishuo.filedownloader.notification.BaseNotificationItem;

public class FileDownloadNotificationHelper<T extends BaseNotificationItem> {

    /* renamed from: a  reason: collision with root package name */
    private final SparseArray<T> f6445a = new SparseArray<>();

    public T a(int i) {
        return (BaseNotificationItem) this.f6445a.get(i);
    }

    public boolean b(int i) {
        return a(i) != null;
    }

    public T c(int i) {
        T a2 = a(i);
        if (a2 == null) {
            return null;
        }
        this.f6445a.remove(i);
        return a2;
    }

    public void a(T t) {
        this.f6445a.remove(t.getId());
        this.f6445a.put(t.getId(), t);
    }

    public void a(int i, int i2, int i3) {
        BaseNotificationItem a2 = a(i);
        if (a2 != null) {
            a2.updateStatus(3);
            a2.update(i2, i3);
        }
    }

    public void a(int i, int i2) {
        BaseNotificationItem a2 = a(i);
        if (a2 != null) {
            a2.updateStatus(i2);
            a2.show(false);
        }
    }

    public void d(int i) {
        BaseNotificationItem c = c(i);
        if (c != null) {
            c.cancel();
        }
    }

    public void a() {
        SparseArray<T> clone = this.f6445a.clone();
        this.f6445a.clear();
        for (int i = 0; i < clone.size(); i++) {
            ((BaseNotificationItem) clone.get(clone.keyAt(i))).cancel();
        }
    }
}
