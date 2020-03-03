package com.huawei.hms.support.api.a;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.a;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.core.ConnectResp;

final class b extends a<ResolveResult<ConnectResp>, ConnectResp> {
    /* access modifiers changed from: protected */
    public boolean checkApiClient(ApiClient apiClient) {
        return apiClient != null;
    }

    b(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
        super(apiClient, str, iMessageEntity);
    }

    /* renamed from: a */
    public ResolveResult<ConnectResp> onComplete(ConnectResp connectResp) {
        ResolveResult<ConnectResp> resolveResult = new ResolveResult<>(connectResp);
        resolveResult.setStatus(Status.SUCCESS);
        return resolveResult;
    }
}
