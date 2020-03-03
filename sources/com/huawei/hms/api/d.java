package com.huawei.hms.api;

import com.huawei.hms.api.HuaweiApiClientImpl;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.entity.core.ConnectResp;

class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ResolveResult f5853a;
    final /* synthetic */ HuaweiApiClientImpl.a b;

    d(HuaweiApiClientImpl.a aVar, ResolveResult resolveResult) {
        this.b = aVar;
        this.f5853a = resolveResult;
    }

    public void run() {
        HuaweiApiClientImpl.this.b((ResolveResult<ConnectResp>) this.f5853a);
    }
}
