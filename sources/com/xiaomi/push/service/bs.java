package com.xiaomi.push.service;

import com.xiaomi.push.az;
import com.xiaomi.push.service.XMPushService;

class bs extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12917a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bs(XMPushService xMPushService, int i) {
        super(i);
        this.f12917a = xMPushService;
    }

    public String a() {
        return "prepare the mi push account.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m322a() {
        w.a(this.f12917a);
        if (az.c(this.f12917a)) {
            this.f12917a.a(true);
        }
    }
}
