package com.xiaomi.jr.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Pair;
import com.xiaomi.jr.account.XiaomiAccountInfo;
import com.xiaomi.jr.account.XiaomiAccountManager;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class PostHintInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private Context f1430a;

    public PostHintInterceptor(Context context) {
        this.f1430a = context;
    }

    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        Pair<String, String> a2 = a(this.f1430a, url);
        if (a2 == null) {
            return chain.proceed(request);
        }
        HttpUrl.Builder newBuilder = url.newBuilder();
        newBuilder.addQueryParameter((String) a2.first, (String) a2.second);
        return chain.proceed(request.newBuilder().url(newBuilder.build()).build());
    }

    private static Pair<String, String> a(Context context, HttpUrl httpUrl) {
        XiaomiAccountInfo a2;
        if (!XiaomiAccountManager.a().d() || (a2 = XiaomiAccountManager.a().a(context, httpUrl.toString(), "get_post_hint")) == null) {
            return null;
        }
        return new Pair<>(a2.f10279a + "_ph", a2.e);
    }
}
