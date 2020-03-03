package com.xiaomi.smarthome.operation.js_sdk.intercept;

import android.content.Context;
import com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.CloudStorageGoodInterceptor;
import com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.ErrorInterceptor;
import com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.LoginInterceptor;
import com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.MibiInterceptor;
import com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.ThirdSchemeInterceptor;
import com.xiaomi.smarthome.operation.js_sdk.linker.UrlJumpHandler;

public class UrlInterceptorChainHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21071a = "UrlInterceptorChainHelp";
    private final UrlInterceptorChain b = new UrlInterceptorChain();

    public void a(Context context) {
        this.b.a(UrlJumpHandler.class.getSimpleName(), (IUrlInterceptor) new UrlJumpHandler());
        this.b.a(MibiInterceptor.class.getSimpleName(), (IUrlInterceptor) new MibiInterceptor(context));
        this.b.a(ThirdSchemeInterceptor.class.getSimpleName(), (IUrlInterceptor) new ThirdSchemeInterceptor(context));
        this.b.a(CloudStorageGoodInterceptor.class.getSimpleName(), (IUrlInterceptor) new CloudStorageGoodInterceptor());
        this.b.a(LoginInterceptor.class.getSimpleName(), (IUrlInterceptor) new LoginInterceptor(context));
        this.b.a(ErrorInterceptor.class.getSimpleName(), (IUrlInterceptor) new ErrorInterceptor());
    }

    public UrlInterceptorChain a() {
        return this.b;
    }
}
