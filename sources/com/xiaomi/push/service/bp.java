package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;

class bp implements am.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12914a;

    bp(XMPushService xMPushService) {
        this.f12914a = xMPushService;
    }

    public void a() {
        XMPushService.a(this.f12914a);
        if (am.a().a() <= 0) {
            this.f12914a.a((XMPushService.i) new XMPushService.f(12, (Exception) null));
        }
    }
}
