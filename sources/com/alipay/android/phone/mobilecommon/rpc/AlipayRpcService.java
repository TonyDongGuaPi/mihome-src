package com.alipay.android.phone.mobilecommon.rpc;

import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.local.rpc.BioRPCService;
import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.zoloz.android.phone.mrpc.core.RpcFactory;
import com.taobao.weex.el.parse.Operators;

public class AlipayRpcService extends BioRPCService {
    public static final String APP_KEY_DEBUG = "98F6BCD082047";
    public static final String APP_KEY_ONLINE = "rpc-sdk-online";
    protected String mRemoteUrl = BioServiceManager.getEnv().gwUrl;
    protected RpcFactory mRpcFactory = new a(new c(this));

    public <T> T getRpcProxy(Class<T> cls) {
        return this.mRpcFactory.getRpcProxy(cls);
    }

    public void setRemoteUrl(String str) {
        BioLog.w("AlipayRpcService", "setRemoteUrl(" + str + Operators.BRACKET_END_STR);
        this.mRemoteUrl = str;
        BioLog.w("AlipayRpcService", "setRemoteUrl() : mRemoteUrl=" + this.mRemoteUrl);
    }
}
