package com.alipay.android.phone.mobilecommon.rpc;

import android.content.Context;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.zoloz.android.phone.mrpc.core.Config;
import com.alipay.zoloz.android.phone.mrpc.core.HttpManager;
import com.alipay.zoloz.android.phone.mrpc.core.RpcParams;
import com.alipay.zoloz.android.phone.mrpc.core.Transport;

class c implements Config {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AlipayRpcService f821a;

    public boolean isGzip() {
        return true;
    }

    c(AlipayRpcService alipayRpcService) {
        this.f821a = alipayRpcService;
    }

    public String getUrl() {
        BioLog.w("AlipayRpcService", "getUrl() : mRemoteUrl=" + this.f821a.mRemoteUrl);
        return this.f821a.mRemoteUrl;
    }

    public Transport getTransport() {
        return HttpManager.getInstance(getApplicationContext());
    }

    public RpcParams getRpcParams() {
        return new d(this);
    }

    public Context getApplicationContext() {
        return this.f821a.mBioServiceManager.getBioApplicationContext();
    }
}
