package com.xiaomi.youpin.login.okhttpApi;

import com.xiaomi.smarthome.application.CommonApplication;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;
import okhttp3.Dispatcher;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

public class OkHttpApi {

    /* renamed from: a  reason: collision with root package name */
    private static volatile OkHttpApi f23537a;
    private OkHttpClient b;
    private CookieManager c = new CookieManager();

    private OkHttpApi() {
    }

    public static OkHttpApi a() {
        if (f23537a == null) {
            synchronized (OkHttpApi.class) {
                if (f23537a == null) {
                    f23537a = new OkHttpApi();
                }
            }
        }
        return f23537a;
    }

    public OkHttpClient b() {
        if (this.b == null) {
            synchronized (OkHttpApi.class) {
                if (this.b == null) {
                    OkHttpClient.Builder writeTimeout = new OkHttpClient.Builder().dispatcher(new Dispatcher(CommonApplication.getNetworkThreadPool())).connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS);
                    CookieManager cookieManager = new CookieManager();
                    this.c = cookieManager;
                    this.b = writeTimeout.cookieJar(new JavaNetCookieJar(cookieManager)).build();
                }
            }
        }
        return this.b;
    }

    public CookieManager c() {
        return this.c;
    }
}
