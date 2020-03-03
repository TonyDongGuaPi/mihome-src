package com.xiaomi.push.service;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;

class br extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12916a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    br(XMPushService xMPushService, Handler handler) {
        super(handler);
        this.f12916a = xMPushService;
    }

    public void onChange(boolean z) {
        super.onChange(z);
        boolean b = XMPushService.b(this.f12916a);
        b.a("SuperPowerMode:" + b);
        XMPushService.a(this.f12916a);
        if (b) {
            this.f12916a.a((XMPushService.i) new XMPushService.f(24, (Exception) null));
        } else {
            this.f12916a.a(true);
        }
    }
}
