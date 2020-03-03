package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.gf;
import com.xiaomi.push.service.XMPushService;

class bj extends XMPushService.i {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ XMPushService f12908a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ String f328a;

    /* renamed from: a  reason: collision with other field name */
    final /* synthetic */ byte[] f329a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bj(XMPushService xMPushService, int i, String str, byte[] bArr) {
        super(i);
        this.f12908a = xMPushService;
        this.f328a = str;
        this.f329a = bArr;
    }

    public String a() {
        return "send mi push message";
    }

    /* renamed from: a  reason: collision with other method in class */
    public void m320a() {
        try {
            w.a(this.f12908a, this.f328a, this.f329a);
        } catch (gf e) {
            b.a((Throwable) e);
            this.f12908a.a(10, (Exception) e);
        }
    }
}
