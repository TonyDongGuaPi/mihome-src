package com.huawei.hms.api;

import android.os.Bundle;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.ResponseHeader;
import com.huawei.hms.core.aidl.a;
import com.huawei.hms.core.aidl.b;
import com.huawei.hms.core.aidl.d;
import com.huawei.hms.core.aidl.f;
import com.huawei.hms.support.api.client.BundleResult;
import com.huawei.hms.support.api.client.ResultCallback;

class c extends d.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ResultCallback f5852a;
    final /* synthetic */ HuaweiApiClientImpl b;

    c(HuaweiApiClientImpl huaweiApiClientImpl, ResultCallback resultCallback) {
        this.b = huaweiApiClientImpl;
        this.f5852a = resultCallback;
    }

    public void a(b bVar) {
        if (bVar != null) {
            f a2 = a.a(bVar.c());
            ResponseHeader responseHeader = new ResponseHeader();
            a2.a(bVar.b, (IMessageEntity) responseHeader);
            this.f5852a.onResult(new BundleResult(responseHeader.getStatusCode(), bVar.a()));
            return;
        }
        this.f5852a.onResult(new BundleResult(-1, (Bundle) null));
    }
}
