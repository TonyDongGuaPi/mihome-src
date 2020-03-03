package com.alipay.zoloz.android.phone.mrpc.core;

import android.content.Context;

public class DefaultRpcClient extends RpcClient {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f1181a;

    public DefaultRpcClient(Context context) {
        this.f1181a = context;
    }

    public <T> T getRpcProxy(Class<T> cls, RpcParams rpcParams) {
        return new RpcFactory(a(rpcParams)).getRpcProxy(cls);
    }

    private Config a(final RpcParams rpcParams) {
        return new Config() {
            public String getUrl() {
                return rpcParams.getGwUrl();
            }

            public Transport getTransport() {
                return HttpManager.getInstance(getApplicationContext());
            }

            public RpcParams getRpcParams() {
                return rpcParams;
            }

            public Context getApplicationContext() {
                return DefaultRpcClient.this.f1181a.getApplicationContext();
            }

            public boolean isGzip() {
                return rpcParams.isGzip();
            }
        };
    }
}
