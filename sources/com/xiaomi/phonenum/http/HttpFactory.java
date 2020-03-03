package com.xiaomi.phonenum.http;

import com.xiaomi.phonenum.http.HttpClientConfig;

public abstract class HttpFactory {
    public abstract HttpClient a(HttpClientConfig httpClientConfig);

    public HttpClient a() {
        return a(new HttpClientConfig.Builder().a());
    }

    public HttpClient a(int i) {
        return a(new HttpClientConfig.Builder().a(i).a());
    }
}
