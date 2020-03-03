package com.huawei.hms.support.api;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.a;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.transport.DatagramTransport;

class d implements DatagramTransport.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a.C0055a f5881a;
    final /* synthetic */ ResultCallback b;
    final /* synthetic */ a c;

    d(a aVar, a.C0055a aVar2, ResultCallback resultCallback) {
        this.c = aVar;
        this.f5881a = aVar2;
        this.b = resultCallback;
    }

    public void a(int i, IMessageEntity iMessageEntity) {
        this.c.a(i, iMessageEntity);
        this.f5881a.a(this.b, this.c.b);
    }
}
