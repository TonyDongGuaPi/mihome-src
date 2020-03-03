package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.gf;
import com.xiaomi.push.ik;
import com.xiaomi.push.service.XMPushService;

final class r extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ik f12936a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ XMPushService f354a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    r(int i, XMPushService xMPushService, ik ikVar) {
        super(i);
        this.f354a = xMPushService;
        this.f12936a = ikVar;
    }

    public String a() {
        return "send ack message for message.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m343a() {
        try {
            w.a(this.f354a, p.a((Context) this.f354a, this.f12936a));
        } catch (gf e) {
            b.a((Throwable) e);
            this.f354a.a(10, (Exception) e);
        }
    }
}
