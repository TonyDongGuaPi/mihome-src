package com.huawei.hms.support.api.push;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.a;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.push.TokenResp;
import com.huawei.hms.support.api.push.a.a.a.c;

public class GetTokenPendingResultImpl extends a<TokenResult, TokenResp> {

    /* renamed from: a  reason: collision with root package name */
    private ApiClient f5883a;

    public GetTokenPendingResultImpl(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
        super(apiClient, str, iMessageEntity);
        this.f5883a = apiClient;
    }

    public TokenResult onComplete(TokenResp tokenResp) {
        TokenResult tokenResult = new TokenResult();
        if (com.huawei.hms.support.log.a.b()) {
            com.huawei.hms.support.log.a.b("GetTokenPendingResultImpl", "get token complete, the return code:" + tokenResp.getRetCode());
        }
        tokenResult.setStatus(new Status(tokenResp.getRetCode()));
        tokenResult.setTokenRes(tokenResp);
        return tokenResult;
    }

    /* access modifiers changed from: protected */
    public TokenResult onError(int i) {
        TokenResult tokenResult = new TokenResult();
        tokenResult.setStatus(new Status(i));
        new c(this.f5883a.getContext(), "push_client_self_info").a("hasRequestToken", false);
        return tokenResult;
    }
}
