package com.xiaomi.push.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.xiaomi.channel.commonutils.logger.b;

class bm implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12911a;

    bm(XMPushService xMPushService) {
        this.f12911a = xMPushService;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        b.b("onServiceConnected " + iBinder);
        Service a2 = XMJobService.a();
        if (a2 != null) {
            this.f12911a.startForeground(XMPushService.a(), XMPushService.a((Context) this.f12911a));
            a2.startForeground(XMPushService.a(), XMPushService.a((Context) this.f12911a));
            a2.stopForeground(true);
            this.f12911a.unbindService(this);
            return;
        }
        b.a("XMService connected but innerService is null " + iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }
}
