package com.alipay.android.phone.mobilecommon.rpc;

import com.alipay.zoloz.android.phone.mrpc.core.Config;
import com.alipay.zoloz.android.phone.mrpc.core.RpcFactory;
import java.lang.reflect.Proxy;

public class a extends RpcFactory {
    public a(Config config) {
        super(config);
    }

    public <T> T getRpcProxy(Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new b(this, this.mConfig, cls, this.mRpcInvoker));
    }
}
