package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;

class ao extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ am.b.c f12886a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ao(am.b.c cVar, int i) {
        super(i);
        this.f12886a = cVar;
    }

    public String a() {
        return "clear peer job";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m307a() {
        if (this.f12886a.f12883a == this.f12886a.f297a.f284a) {
            b.b("clean peer, chid = " + this.f12886a.f297a.g);
            this.f12886a.f297a.f284a = null;
        }
    }
}
