package com.xiaomi.jr.http;

import android.content.Context;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.common.net.HttpHeaders;
import com.google.gson.GsonBuilder;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.NetworkUtils;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.http.certificate.CertificatePinning;
import com.xiaomi.jr.http.netopt.NetworkDiagnosis;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.CacheControl;
import okhttp3.CertificatePinner;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.MiFiCache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class HttpManager {

    /* renamed from: a  reason: collision with root package name */
    private static final int f1425a = 90;
    private static final int b = 90;
    private static final int c = 90;
    private static final int d = 10485760;
    private static final String e = ".mifi_http_cache";
    private static final Interceptor h = $$Lambda$HttpManager$qMRyJfqAYPM3M2kWQUvdcisgj9I.INSTANCE;
    private Retrofit f;
    private OkHttpClient g;

    private HttpManager(Builder builder) {
        if (MifiLog.f1417a) {
            Stetho.initializeWithDefaults(builder.f1426a);
        }
        this.g = a(builder);
        this.f = a(this.g, builder.b);
    }

    public OkHttpClient a() {
        return this.g;
    }

    public <T> T a(Class<T> cls) {
        return this.f.create(cls);
    }

    private static final class RewriteCacheControlInterceptor implements Interceptor {

        /* renamed from: a  reason: collision with root package name */
        private Context f10814a;

        RewriteCacheControlInterceptor(Context context) {
            this.f10814a = context.getApplicationContext();
        }

        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            boolean b = NetworkUtils.b(this.f10814a);
            if (!b) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response proceed = chain.proceed(request);
            if (b) {
                return proceed.newBuilder().removeHeader(HttpHeaders.PRAGMA).header("Cache-Control", request.cacheControl().toString()).build();
            }
            Response.Builder removeHeader = proceed.newBuilder().removeHeader(HttpHeaders.PRAGMA);
            return removeHeader.header("Cache-Control", "public, only-if-cached, max-stale=" + 172800).build();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Response a(Interceptor.Chain chain) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        String httpUrl = chain.request().url().toString();
        NetworkDiagnosis a2 = NetworkDiagnosis.a();
        a2.a(currentTimeMillis, "interface slow: " + UrlUtils.b(httpUrl));
        Response proceed = chain.proceed(chain.request());
        NetworkDiagnosis.a().a(currentTimeMillis);
        return proceed;
    }

    private static OkHttpClient a(Builder builder) {
        File file = new File(builder.f1426a.getCacheDir(), e);
        OkHttpClient.Builder addNetworkInterceptor = new OkHttpClient.Builder().connectTimeout(90, TimeUnit.SECONDS).readTimeout(90, TimeUnit.SECONDS).writeTimeout(90, TimeUnit.SECONDS).authenticator(builder.f != null ? builder.f : new ServiceTokenAuthenticator(builder.f1426a)).cookieJar(builder.g != null ? builder.g : new XiaomiAccountCookieJar(builder.f1426a)).addNetworkInterceptor(new RewriteCacheControlInterceptor(builder.f1426a)).addNetworkInterceptor(new StethoInterceptor());
        MiFiCache.setGetCacheUrlCallback($$Lambda$UdnmcVrMGZQMDOyhxMXO8MVBnQ.INSTANCE);
        Internal.instance.setCache(addNetworkInterceptor, new MiFiCache(file, 10485760).internalCache);
        Iterator it = builder.c.iterator();
        while (it.hasNext()) {
            addNetworkInterceptor.addInterceptor((Interceptor) it.next());
        }
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(MifiLog.f1417a ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        addNetworkInterceptor.addInterceptor(httpLoggingInterceptor);
        Iterator it2 = builder.d.iterator();
        while (it2.hasNext()) {
            addNetworkInterceptor.addNetworkInterceptor((Interceptor) it2.next());
        }
        if (NetworkDiagnosis.a() != null && NetworkDiagnosis.a().a(builder.b)) {
            addNetworkInterceptor.addNetworkInterceptor(h);
        }
        a(addNetworkInterceptor, (Map<String, String[]>) builder.e);
        return addNetworkInterceptor.build();
    }

    private static Retrofit a(OkHttpClient okHttpClient, String str) {
        return new Retrofit.Builder().baseUrl(str).addConverterFactory(MultipartStringConverterFactory.a()).addConverterFactory(HttpGsonConverterFactory.a(new GsonBuilder().create())).client(okHttpClient).build();
    }

    static void a(OkHttpClient.Builder builder, Map<String, String[]> map) {
        if (!CertificatePinning.f1434a && !map.isEmpty()) {
            CertificatePinner.Builder builder2 = new CertificatePinner.Builder();
            for (Map.Entry next : map.entrySet()) {
                builder2.add((String) next.getKey(), (String[]) next.getValue());
            }
            builder.certificatePinner(builder2.build());
        }
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Context f1426a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public ArrayList<Interceptor> c = new ArrayList<>();
        /* access modifiers changed from: private */
        public ArrayList<Interceptor> d = new ArrayList<>();
        /* access modifiers changed from: private */
        public Map<String, String[]> e = new HashMap();
        /* access modifiers changed from: private */
        public Authenticator f;
        /* access modifiers changed from: private */
        public CookieJar g;

        public Builder(Context context) {
            this.f1426a = context;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder a(Interceptor interceptor) {
            this.c.add(interceptor);
            return this;
        }

        public Builder b(Interceptor interceptor) {
            this.d.add(interceptor);
            return this;
        }

        public Builder a(String str, String[] strArr) {
            this.e.put(str, strArr);
            return this;
        }

        public Builder a(Authenticator authenticator) {
            this.f = authenticator;
            return this;
        }

        public Builder a(CookieJar cookieJar) {
            this.g = cookieJar;
            return this;
        }

        public HttpManager a() {
            return new HttpManager(this);
        }
    }
}
