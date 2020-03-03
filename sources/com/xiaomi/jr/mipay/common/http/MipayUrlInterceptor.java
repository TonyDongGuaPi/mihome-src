package com.xiaomi.jr.mipay.common.http;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MipayUrlInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        String httpUrl = request.url().toString();
        if (httpUrl.startsWith(HostManager.f10942a)) {
            httpUrl = httpUrl.replace(HostManager.f10942a, HostManager.b());
        }
        return chain.proceed(newBuilder.url(httpUrl).build());
    }
}
