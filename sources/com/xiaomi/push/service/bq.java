package com.xiaomi.push.service;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;

class bq extends ContentObserver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12915a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bq(XMPushService xMPushService, Handler handler) {
        super(handler);
        this.f12915a = xMPushService;
    }

    public void onChange(boolean z) {
        super.onChange(z);
        boolean a2 = XMPushService.a(this.f12915a);
        b.a("ExtremePowerMode:" + a2);
        if (a2) {
            this.f12915a.a((XMPushService.i) new XMPushService.f(23, (Exception) null));
        } else {
            this.f12915a.a(true);
        }
    }
}
