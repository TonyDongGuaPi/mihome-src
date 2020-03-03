package com.huawei.hms.api.internal;

import android.os.Bundle;
import com.huawei.hms.api.HuaweiApiClientImpl;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.RequestHeader;
import com.huawei.hms.core.aidl.a;
import com.huawei.hms.core.aidl.b;
import com.huawei.hms.core.aidl.d;
import com.huawei.hms.core.aidl.f;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.huawei.hms.support.api.transport.DatagramTransport;

public class IPCTransport implements DatagramTransport {

    /* renamed from: a  reason: collision with root package name */
    private final String f5855a;
    private final IMessageEntity b;
    private final Class<? extends IMessageEntity> c;

    public IPCTransport(String str, IMessageEntity iMessageEntity, Class<? extends IMessageEntity> cls) {
        this.f5855a = str;
        this.b = iMessageEntity;
        this.c = cls;
    }

    public final void a(ApiClient apiClient, DatagramTransport.a aVar) {
        int a2 = a(apiClient, (d) new f(this.c, aVar));
        if (a2 != 0) {
            aVar.a(a2, (IMessageEntity) null);
        }
    }

    public final void b(ApiClient apiClient, DatagramTransport.a aVar) {
        a(apiClient, aVar);
    }

    private int a(ApiClient apiClient, d dVar) {
        b bVar = new b(this.f5855a, g.a().b());
        f a2 = a.a(bVar.c());
        bVar.a(a2.a(this.b, new Bundle()));
        RequestHeader requestHeader = new RequestHeader();
        requestHeader.setAppID(apiClient.getAppID());
        requestHeader.setPackageName(apiClient.getPackageName());
        requestHeader.setSdkVersion(20502300);
        if (apiClient instanceof HuaweiApiClientImpl) {
            requestHeader.setSessionId(apiClient.getSessionId());
        }
        bVar.b = a2.a((IMessageEntity) requestHeader, new Bundle());
        try {
            ((HuaweiApiClientImpl) apiClient).getService().a(bVar, dVar);
            return 0;
        } catch (Exception unused) {
            return CommonCode.ErrorCode.INTERNAL_ERROR;
        }
    }
}
