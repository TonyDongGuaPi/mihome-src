package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

class hc extends XMPushService.i {
    final /* synthetic */ hb b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    hc(hb hbVar, int i) {
        super(i);
        this.b = hbVar;
    }

    public String a() {
        return "Handling bind stats";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m128a() {
        this.b.c();
    }
}
