package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

class gc extends XMPushService.i {
    final /* synthetic */ long b;
    final /* synthetic */ gb c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    gc(gb gbVar, int i, long j) {
        super(i);
        this.c = gbVar;
        this.b = j;
    }

    public String a() {
        return "check the ping-pong." + this.b;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m126a() {
        Thread.yield();
        if (this.c.j() && !this.c.a(this.b)) {
            this.c.r.a(22, (Exception) null);
        }
    }
}
