package com.xiaomi.jr.mipay.common.http;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.jr.http.BasicParamsInterceptor;
import com.xiaomi.jr.http.HttpManager;
import com.xiaomi.jr.http.MifiNetQualityStatInterceptor;
import java.io.IOException;
import okhttp3.Authenticator;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.Response;

public class MipayHttpManager {

    /* renamed from: a  reason: collision with root package name */
    private static HttpManager f10944a;

    public static void a(final Context context) {
        f10944a = new HttpManager.Builder(context).a(HostManager.f10942a).a((Interceptor) new MipayRetryInterceptor(context)).a((Interceptor) new MifiNetQualityStatInterceptor("errcode")).a((Interceptor) new MipayUrlInterceptor()).a((Interceptor) new BasicParamsInterceptor(true) {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                if (!TextUtils.equals(chain.request().url().url().getPath(), "/api/device")) {
                    a(BasicParamsHelper.a(context));
                }
                return super.intercept(chain);
            }
        }).a((Interceptor) new MipayParamEncryptInterceptor()).a((Interceptor) new MipayParamSignInterceptor()).a((Interceptor) new MipayResponseDecryptInterceptor()).a(Authenticator.NONE).a((CookieJar) new MipayCookieJar(context)).a(MipayCertificatePinning.f1449a, MipayCertificatePinning.b).a();
    }

    public static HttpManager a() {
        return f10944a;
    }
}
