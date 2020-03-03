package com.alipay.android.phone.mobilecommon.rpc;

import com.alipay.zoloz.android.phone.mrpc.core.Config;
import com.alipay.zoloz.android.phone.mrpc.core.RpcInvocationHandler;
import com.alipay.zoloz.android.phone.mrpc.core.RpcInvoker;
import java.lang.reflect.Method;

class b extends RpcInvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f820a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    b(a aVar, Config config, Class cls, RpcInvoker rpcInvoker) {
        super(config, cls, rpcInvoker);
        this.f820a = aVar;
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return super.invoke(obj, method, objArr);
        } catch (com.alipay.zoloz.mobile.a.a.b e) {
            throw new e(e);
        }
    }
}
