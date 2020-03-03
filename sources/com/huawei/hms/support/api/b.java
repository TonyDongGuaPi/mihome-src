package com.huawei.hms.support.api;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.transport.DatagramTransport;

class b implements DatagramTransport.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f5875a;

    b(a aVar) {
        this.f5875a = aVar;
    }

    public void a(int i, IMessageEntity iMessageEntity) {
        this.f5875a.a(i, iMessageEntity);
        this.f5875a.f5874a.countDown();
    }
}
