package com.ximalaya.ting.android.opensdk.httputil.util.freeflow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.constants.ConstantsOpenSdk;
import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.httputil.Config;
import com.ximalaya.ting.android.opensdk.util.BaseUtil;
import com.ximalaya.ting.android.player.IGetHttpUrlConnectByUrl;
import com.ximalaya.ting.android.player.model.HttpConfig;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Authenticator;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.huc.OkHttpURLConnection;

public class FreeFlowServiceUtil {

    /* renamed from: a  reason: collision with root package name */
    private static Interceptor f1999a;

    public interface ISetHttpUrlConnectAttribute {
        void a(@NonNull HttpURLConnection httpURLConnection);
    }

    public static OkHttpClient.Builder a(@NonNull Context context, @Nullable final Config config, @NonNull OkHttpClient.Builder builder, boolean z) {
        builder.retryOnConnectionFailure(true);
        if (config != null) {
            builder.connectTimeout((long) config.j, TimeUnit.MILLISECONDS);
            builder.readTimeout((long) config.j, TimeUnit.MILLISECONDS);
            builder.writeTimeout((long) config.l, TimeUnit.MILLISECONDS);
        }
        if (config == null || !config.d || TextUtils.isEmpty(config.f) || config.g <= 0) {
            builder.proxy((Proxy) null);
            if (f1999a != null) {
                builder.interceptors().remove(f1999a);
            }
            if (a(builder, TimeUnit.MINUTES.toNanos(5))) {
                builder.connectionPool(new ConnectionPool());
            }
            builder.authenticator(Authenticator.NONE);
            builder.proxyAuthenticator(Authenticator.NONE);
        } else {
            if (a(builder, TimeUnit.SECONDS.toNanos(10))) {
                builder.connectionPool(new ConnectionPool(10, 10, TimeUnit.SECONDS));
            }
            final int i = config.g;
            if (z) {
                i = config.h > 0 ? config.h : config.g;
            }
            final Proxy[] proxyArr = new Proxy[1];
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        proxyArr[0] = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(config.f, i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "Config.java Create Proxy");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            builder.proxy(proxyArr[0]);
            if (f1999a == null) {
                f1999a = new Interceptor() {
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request.Builder newBuilder = chain.request().newBuilder();
                        Map<String, String> map = config.n;
                        if (map != null) {
                            for (Map.Entry next : map.entrySet()) {
                                newBuilder.header((String) next.getKey(), (String) next.getValue());
                            }
                        }
                        return chain.proceed(newBuilder.build());
                    }
                };
            }
            if (!builder.interceptors().contains(f1999a)) {
                builder.addInterceptor(f1999a);
            }
        }
        return builder;
    }

    private static boolean a(@NonNull OkHttpClient.Builder builder, long j) {
        try {
            Field declaredField = OkHttpClient.Builder.class.getDeclaredField("connectionPool");
            declaredField.setAccessible(true);
            ConnectionPool connectionPool = (ConnectionPool) declaredField.get(builder);
            if (connectionPool == null) {
                return true;
            }
            Field declaredField2 = ConnectionPool.class.getDeclaredField("keepAliveDurationNs");
            declaredField2.setAccessible(true);
            if (((Long) declaredField2.get(connectionPool)).longValue() == j) {
                return false;
            }
            return true;
        } catch (Exception e) {
            if (!ConstantsOpenSdk.b) {
                e.printStackTrace();
                return true;
            }
            throw new RuntimeException("Config -> OKHTTP 底层框架发生改变需要做相应的处理!!!");
        }
    }

    public static HttpURLConnection a(@Nullable Config config, String str, String str2, ISetHttpUrlConnectAttribute iSetHttpUrlConnectAttribute) throws IOException {
        Proxy proxy;
        HttpURLConnection httpURLConnection;
        if (str == null) {
            return null;
        }
        try {
            URL url = new URL(str);
            try {
                proxy = a(config, "https".equalsIgnoreCase(url.toURI().getScheme()));
            } catch (Exception e) {
                e.printStackTrace();
                proxy = null;
            }
            boolean z = config != null ? config.d : false;
            if (proxy == null || proxy == Proxy.NO_PROXY || !z) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = new OkHttpURLConnection(url, BaseCall.a().a(url));
                Map<String, String> map = config.n;
                if (map != null) {
                    for (Map.Entry next : map.entrySet()) {
                        httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
                    }
                }
                httpURLConnection.setInstanceFollowRedirects(false);
            }
            if (config != null) {
                httpURLConnection.setConnectTimeout(config.j);
                httpURLConnection.setReadTimeout(config.k);
            }
            httpURLConnection.setRequestMethod(str2);
            if (iSetHttpUrlConnectAttribute != null) {
                iSetHttpUrlConnectAttribute.a(httpURLConnection);
            }
            return httpURLConnection;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static Proxy a(@Nullable Config config, boolean z) {
        if (config == null || !config.d) {
            return null;
        }
        int i = config.g;
        if (z && config.h > 0) {
            i = config.h;
        }
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(config.f, i));
    }

    @Nullable
    public static HttpConfig a(@Nullable final Config config) {
        if (config == null) {
            return null;
        }
        HttpConfig httpConfig = new HttpConfig();
        httpConfig.d = config.d;
        httpConfig.e = config.e;
        httpConfig.f = config.f;
        httpConfig.g = config.g;
        httpConfig.h = config.h;
        httpConfig.i = config.i;
        httpConfig.j = config.j;
        httpConfig.k = config.k;
        httpConfig.l = config.l;
        httpConfig.m = config.m;
        httpConfig.n = config.n;
        if (BaseUtil.d()) {
            httpConfig.o = new IGetHttpUrlConnectByUrl() {
                public HttpURLConnection a(String str, String str2, final HttpConfig httpConfig) {
                    try {
                        return FreeFlowServiceUtil.a(config, str, str2, (ISetHttpUrlConnectAttribute) new ISetHttpUrlConnectAttribute() {
                            public void a(@NonNull HttpURLConnection httpURLConnection) {
                                if (httpConfig != null) {
                                    httpURLConnection.setReadTimeout(httpConfig.k);
                                    httpURLConnection.setConnectTimeout(httpConfig.j);
                                    if (httpConfig.n != null) {
                                        for (Map.Entry next : httpConfig.n.entrySet()) {
                                            httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
                                        }
                                    }
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            };
        }
        return httpConfig;
    }
}
