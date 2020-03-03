package com.xiaomi.mipush.sdk;

import com.xiaomi.push.ht;
import com.xiaomi.push.service.ah;

class f extends ah.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f11551a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    f(e eVar, int i, String str) {
        super(i, str);
        this.f11551a = eVar;
    }

    /* access modifiers changed from: protected */
    public void a() {
        boolean a2 = ah.a(this.f11551a.b).a(ht.AggregatePushSwitch.a(), true);
        if (this.f11551a.d != a2) {
            boolean unused = this.f11551a.d = a2;
            h.d(this.f11551a.b);
        }
    }
}
