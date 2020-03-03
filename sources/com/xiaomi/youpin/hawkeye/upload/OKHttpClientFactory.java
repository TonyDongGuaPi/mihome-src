package com.xiaomi.youpin.hawkeye.upload;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

public class OKHttpClientFactory {
    public static OkHttpClient a() {
        return new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();
    }
}
