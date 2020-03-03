package com.xiaomi.youpin.log;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpNetInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long currentTimeMillis = System.currentTimeMillis();
        Response proceed = chain.proceed(request);
        NetMonitor.onOkHttp(request, proceed, System.currentTimeMillis() - currentTimeMillis);
        return proceed;
    }
}
