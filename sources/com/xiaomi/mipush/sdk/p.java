package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.es;
import com.xiaomi.push.ht;
import com.xiaomi.push.service.ah;

final class p extends ah.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f11561a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    p(int i, String str, Context context) {
        super(i, str);
        this.f11561a = context;
    }

    /* access modifiers changed from: protected */
    public void a() {
        es.a(this.f11561a).a(ah.a(this.f11561a).a(ht.AwakeInfoUploadWaySwitch.a(), 0));
    }
}
