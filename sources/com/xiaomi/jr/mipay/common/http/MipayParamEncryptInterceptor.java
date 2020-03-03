package com.xiaomi.jr.mipay.common.http;

import android.text.TextUtils;
import com.xiaomi.jr.account.XiaomiAccountInfo;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.mipay.common.MipayManager;
import com.xiaomi.jr.mipay.common.util.Coder;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MipayParamEncryptInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10946a = "MipayParamEncryptInterceptor";

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        HttpUtils.a("[origParam] " + HttpUtils.b(request));
        if (!HttpUtils.a(request)) {
            MifiLog.e(f10946a, "shouldn't reach here, in request " + request.toString());
        } else if (request.body() instanceof FormBody) {
            XiaomiAccountInfo a2 = XiaomiAccountManager.a().a(MipayManager.a(), request.url().toString(), "mipay_param_encrypt");
            if (a2 == null) {
                HttpUtils.a("can't encrypt params due to no account info");
                return chain.proceed(request);
            }
            String str = a2.d;
            Request build = request.newBuilder().tag(str).build();
            FormBody.Builder builder = new FormBody.Builder();
            FormBody formBody = (FormBody) build.body();
            for (int i = 0; i < formBody.size(); i++) {
                String a3 = Coder.a(formBody.value(i), str);
                if (!TextUtils.isEmpty(a3)) {
                    builder.add(formBody.name(i), a3);
                }
            }
            request = build.newBuilder().method(build.method(), builder.build()).build();
            HttpUtils.a("[encryptParam] " + HttpUtils.b(request) + ", security=" + str);
        }
        return chain.proceed(request);
    }
}
