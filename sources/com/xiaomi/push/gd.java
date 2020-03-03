package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

class gd extends XMPushService.i {
    final /* synthetic */ int b;
    final /* synthetic */ Exception c;
    final /* synthetic */ gb d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    gd(gb gbVar, int i, int i2, Exception exc) {
        super(i);
        this.d = gbVar;
        this.b = i2;
        this.c = exc;
    }

    public String a() {
        return "shutdown the connection. " + this.b + ", " + this.c;
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m127a() {
        this.d.r.a(this.b, this.c);
    }
}
