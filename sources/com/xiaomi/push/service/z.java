package com.xiaomi.push.service;

import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.service.am;

final class z implements am.b.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12945a;

    z(XMPushService xMPushService) {
        this.f12945a = xMPushService;
    }

    public void a(am.c cVar, am.c cVar2, int i) {
        if (cVar2 == am.c.binded) {
            o.a(this.f12945a);
            o.b(this.f12945a);
        } else if (cVar2 == am.c.unbind) {
            o.a(this.f12945a, ErrorCode.b, " the push is not connected.");
        }
    }
}
