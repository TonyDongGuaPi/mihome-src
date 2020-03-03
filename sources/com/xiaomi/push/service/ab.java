package com.xiaomi.push.service;

import android.app.NotificationManager;
import com.xiaomi.push.ai;

final class ab extends ai.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f12868a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ NotificationManager f269a;

    ab(int i, NotificationManager notificationManager) {
        this.f12868a = i;
        this.f269a = notificationManager;
    }

    public int a() {
        return this.f12868a;
    }

    public void run() {
        this.f269a.cancel(this.f12868a);
    }
}
