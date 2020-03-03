package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.gf;
import com.xiaomi.push.ik;
import com.xiaomi.push.service.XMPushService;

final class v extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ik f12942a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ XMPushService f364a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ String f365a;
    final /* synthetic */ String b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    v(int i, XMPushService xMPushService, ik ikVar, String str, String str2) {
        super(i);
        this.f364a = xMPushService;
        this.f12942a = ikVar;
        this.f365a = str;
        this.b = str2;
    }

    public String a() {
        return "send wrong message ack for message.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m347a() {
        try {
            ik a2 = p.a((Context) this.f364a, this.f12942a);
            a2.f166a.a("error", this.f365a);
            a2.f166a.a("reason", this.b);
            w.a(this.f364a, a2);
        } catch (gf e) {
            b.a((Throwable) e);
            this.f364a.a(10, (Exception) e);
        }
    }
}
