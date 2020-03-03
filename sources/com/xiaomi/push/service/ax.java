package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.fn;
import com.xiaomi.push.gf;
import com.xiaomi.push.service.XMPushService;

class ax extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    private fn f12894a;

    /* renamed from: a  reason: collision with other field name */
    private XMPushService f305a = null;

    public ax(XMPushService xMPushService, fn fnVar) {
        super(4);
        this.f305a = xMPushService;
        this.f12894a = fnVar;
    }

    public String a() {
        return "send a message.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m313a() {
        try {
            if (this.f12894a != null) {
                this.f305a.a(this.f12894a);
            }
        } catch (gf e) {
            b.a((Throwable) e);
            this.f305a.a(10, (Exception) e);
        }
    }
}
