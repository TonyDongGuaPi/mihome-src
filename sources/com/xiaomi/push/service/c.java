package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.fn;
import com.xiaomi.push.gf;
import com.xiaomi.push.service.XMPushService;

class c extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    private XMPushService f12920a = null;

    /* renamed from: a  reason: collision with other field name */
    private fn[] f333a;

    public c(XMPushService xMPushService, fn[] fnVarArr) {
        super(4);
        this.f12920a = xMPushService;
        this.f333a = fnVarArr;
    }

    public String a() {
        return "batch send message.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m324a() {
        try {
            if (this.f333a != null) {
                this.f12920a.a(this.f333a);
            }
        } catch (gf e) {
            b.a((Throwable) e);
            this.f12920a.a(10, (Exception) e);
        }
    }
}
