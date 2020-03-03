package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.gf;
import com.xiaomi.push.ik;
import com.xiaomi.push.service.XMPushService;

final class s extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ik f12939a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ XMPushService f360a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    s(int i, XMPushService xMPushService, ik ikVar) {
        super(i);
        this.f360a = xMPushService;
        this.f12939a = ikVar;
    }

    public String a() {
        return "send ack message for obsleted message.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m344a() {
        try {
            ik a2 = p.a((Context) this.f360a, this.f12939a);
            a2.a().a("message_obsleted", "1");
            w.a(this.f360a, a2);
        } catch (gf e) {
            b.a((Throwable) e);
            this.f360a.a(10, (Exception) e);
        }
    }
}
