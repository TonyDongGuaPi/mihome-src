package com.alipay.android.phone.mobilecommon.rpc;

import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.bio.workspace.Env;
import com.alipay.sdk.packet.e;
import com.alipay.zoloz.android.phone.mrpc.core.RpcParams;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

class d extends RpcParams {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c f822a;

    d(c cVar) {
        this.f822a = cVar;
    }

    public String getGwUrl() {
        BioLog.w("AlipayRpcService", "getGwUrl() : mRemoteUrl=" + this.f822a.f821a.mRemoteUrl);
        return this.f822a.f821a.mRemoteUrl;
    }

    public List<Header> getHeaders() {
        ArrayList arrayList = new ArrayList();
        if (BioServiceManager.getEnv().name.equals(Env.PRE_ANT_CLOUD.name)) {
            arrayList.add(new BasicHeader("WorkspaceId", "staging"));
            arrayList.add(new BasicHeader(e.f, "C321516081430"));
        } else {
            arrayList.add(new BasicHeader("WorkspaceId", "prod"));
            arrayList.add(new BasicHeader(e.f, "C321516081430"));
        }
        BioLog.w("AlipayRpcService", "getHeaders() : headers=" + arrayList);
        return arrayList;
    }
}
