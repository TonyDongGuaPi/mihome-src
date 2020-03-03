package com.huawei.hms.support.api;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.transport.DatagramTransport;
import java.util.concurrent.atomic.AtomicBoolean;

class c implements DatagramTransport.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AtomicBoolean f5876a;
    final /* synthetic */ a b;

    c(a aVar, AtomicBoolean atomicBoolean) {
        this.b = aVar;
        this.f5876a = atomicBoolean;
    }

    public void a(int i, IMessageEntity iMessageEntity) {
        if (!this.f5876a.get()) {
            this.b.a(i, iMessageEntity);
        }
        this.b.f5874a.countDown();
    }
}
