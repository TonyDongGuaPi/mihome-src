package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.ab;
import com.xiaomi.push.fv;
import com.xiaomi.push.service.XMPushService;

class bu extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12919a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ String f331a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ byte[] f332a;
    final /* synthetic */ int b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bu(XMPushService xMPushService, int i, int i2, byte[] bArr, String str) {
        super(i);
        this.f12919a = xMPushService;
        this.b = i2;
        this.f332a = bArr;
        this.f331a = str;
    }

    public String a() {
        return "clear account cache.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m323a() {
        l.a((Context) this.f12919a);
        am.a().a("5");
        ab.a(this.b);
        XMPushService.a(this.f12919a).c(fv.a());
        this.f12919a.a(this.f332a, this.f331a);
    }
}
