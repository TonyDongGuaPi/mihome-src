package com.xiaomi.push.service;

import com.xiaomi.push.fu;
import com.xiaomi.push.service.XMPushService;

class bk extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12909a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bk(XMPushService xMPushService, int i) {
        super(i);
        this.f12909a = xMPushService;
    }

    public String a() {
        return "disconnect for service destroy.";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m321a() {
        if (XMPushService.a(this.f12909a) != null) {
            XMPushService.a(this.f12909a).b(15, (Exception) null);
            fu unused = this.f12909a.f233a = null;
        }
    }
}
