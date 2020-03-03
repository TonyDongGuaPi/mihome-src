package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.am;

class ap extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ am.b.c f12887a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ap(am.b.c cVar, int i) {
        super(i);
        this.f12887a = cVar;
    }

    public String a() {
        return "check peer job";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m308a() {
        if (am.a().a(this.f12887a.f297a.g, this.f12887a.f297a.f293b).f284a == null) {
            am.b.a(am.b.this).a(this.f12887a.f297a.g, this.f12887a.f297a.f293b, 2, (String) null, (String) null);
        }
    }
}
