package com.xiaomi.jr.mipay.common.http;

import com.xiaomi.jr.mipay.common.util.Coder;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MipayResponseDecryptInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        if (!proceed.isSuccessful() || proceed.body() == null) {
            return proceed;
        }
        if (!(chain.request().tag() instanceof String)) {
            HttpUtils.a("can't decrypt response due to no security in request");
            return proceed;
        }
        String str = (String) chain.request().tag();
        String a2 = HttpUtils.a(proceed);
        HttpUtils.a("[origResponse] " + a2);
        String b = Coder.b(a2, str);
        if (b == null) {
            return proceed;
        }
        HttpUtils.a("[decryptResponse] " + b + ", security=" + str);
        return proceed.newBuilder().body(ResponseBody.create(MediaType.parse("application/json"), b)).build();
    }
}
