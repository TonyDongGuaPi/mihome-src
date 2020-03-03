package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.gf;
import com.xiaomi.push.ik;
import com.xiaomi.push.service.XMPushService;

final class u extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ik f12941a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ XMPushService f362a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ String f363a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    u(int i, XMPushService xMPushService, ik ikVar, String str) {
        super(i);
        this.f362a = xMPushService;
        this.f12941a = ikVar;
        this.f363a = str;
    }

    public String a() {
        return "send app absent ack message for message.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m346a() {
        try {
            ik a2 = p.a((Context) this.f362a, this.f12941a);
            a2.a().a("absent_target_package", this.f363a);
            w.a(this.f362a, a2);
        } catch (gf e) {
            b.a((Throwable) e);
            this.f362a.a(10, (Exception) e);
        }
    }
}
