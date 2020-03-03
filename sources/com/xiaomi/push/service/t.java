package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.gf;
import com.xiaomi.push.ik;
import com.xiaomi.push.service.XMPushService;

final class t extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ik f12940a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ XMPushService f361a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    t(int i, XMPushService xMPushService, ik ikVar) {
        super(i);
        this.f361a = xMPushService;
        this.f12940a = ikVar;
    }

    public String a() {
        return "send ack message for unrecognized new miui message.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m345a() {
        try {
            ik a2 = p.a((Context) this.f361a, this.f12940a);
            a2.a().a("miui_message_unrecognized", "1");
            w.a(this.f361a, a2);
        } catch (gf e) {
            b.a((Throwable) e);
            this.f361a.a(10, (Exception) e);
        }
    }
}
