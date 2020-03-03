package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.gf;
import com.xiaomi.push.ik;
import com.xiaomi.push.service.XMPushService;

final class q extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ik f12935a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ XMPushService f353a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    q(int i, XMPushService xMPushService, ik ikVar) {
        super(i);
        this.f353a = xMPushService;
        this.f12935a = ikVar;
    }

    public String a() {
        return "send app absent message.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m342a() {
        try {
            w.a(this.f353a, w.a(this.f12935a.b(), this.f12935a.a()));
        } catch (gf e) {
            b.a((Throwable) e);
            this.f353a.a(10, (Exception) e);
        }
    }
}
