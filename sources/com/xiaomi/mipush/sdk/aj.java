package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.MiTinyDataClient;
import com.xiaomi.push.hs;

class aj implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ hs f11529a;
    final /* synthetic */ MiTinyDataClient.a.C0084a b;

    aj(MiTinyDataClient.a.C0084a aVar, hs hsVar) {
        this.b = aVar;
        this.f11529a = hsVar;
    }

    public void run() {
        this.b.f11518a.add(this.f11529a);
        this.b.a();
    }
}
