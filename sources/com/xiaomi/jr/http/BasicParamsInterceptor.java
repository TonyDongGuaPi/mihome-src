package com.xiaomi.jr.http;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.xiaomi.jr.stats.StatUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BasicParamsInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, String> f1424a;
    private boolean b;

    public BasicParamsInterceptor() {
        this(false);
    }

    public BasicParamsInterceptor(boolean z) {
        this.b = z;
    }

    public void a(@Nullable Map<String, String> map) {
        this.f1424a = map;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (this.f1424a != null && !this.f1424a.isEmpty()) {
            Request.Builder newBuilder = request.newBuilder();
            request = !a(request) ? a(request.url().newBuilder(), newBuilder, this.f1424a) : a(request.body(), newBuilder, this.f1424a);
        }
        return chain.proceed(request);
    }

    private boolean a(Request request) {
        RequestBody body;
        MediaType contentType;
        if (this.b && TextUtils.equals(request.method(), "POST") && (body = request.body()) != null && (contentType = body.contentType()) != null && TextUtils.equals(contentType.subtype(), "x-www-form-urlencoded")) {
            return true;
        }
        return false;
    }

    static Request a(RequestBody requestBody, Request.Builder builder, Map<String, String> map) {
        FormBody.Builder builder2 = new FormBody.Builder();
        if (requestBody instanceof FormBody) {
            FormBody formBody = (FormBody) requestBody;
            for (int i = 0; i < formBody.size(); i++) {
                if (!TextUtils.isEmpty(formBody.encodedValue(i))) {
                    builder2.addEncoded(formBody.encodedName(i), formBody.encodedValue(i));
                }
            }
        }
        if (map != null) {
            for (String next : map.keySet()) {
                String str = map.get(next);
                if (!TextUtils.isEmpty(str)) {
                    builder2.add(next, str);
                }
            }
        }
        builder.post(builder2.build());
        return builder.build();
    }

    static Request a(HttpUrl.Builder builder, Request.Builder builder2, Map<String, String> map) {
        if (map != null) {
            for (String next : map.keySet()) {
                String str = map.get(next);
                if (!TextUtils.isEmpty(str)) {
                    try {
                        builder.addQueryParameter(next, str);
                    } catch (Exception unused) {
                        HashMap hashMap = new HashMap();
                        hashMap.put(next, str);
                        StatUtils.a(LogCategory.CATEGORY_EXCEPTION, "EOFException", (Map<String, String>) hashMap);
                    }
                }
            }
        }
        builder2.url(builder.build());
        return builder2.build();
    }
}