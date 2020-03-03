package com.huawei.hms.api;

import com.huawei.hms.api.HuaweiApiClientImpl;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.entity.core.DisconnectResp;

class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ResolveResult f5854a;
    final /* synthetic */ HuaweiApiClientImpl.b b;

    e(HuaweiApiClientImpl.b bVar, ResolveResult resolveResult) {
        this.b = bVar;
        this.f5854a = resolveResult;
    }

    public void run() {
        HuaweiApiClientImpl.this.a((ResolveResult<DisconnectResp>) this.f5854a);
    }
}
