package com.xiaomi.youpin.youpin_network.util;

import com.xiaomi.youpin.common.cache.StringCache;
import com.xiaomi.youpin.common.util.EncryptUtils;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.network.bean.NetRequest;
import com.xiaomi.youpin.youpin_network.NetworkConfigManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class YouPinHttpsAuthCache {

    /* renamed from: a  reason: collision with root package name */
    private static final int f23866a = 100;
    private static volatile YouPinHttpsAuthCache b;
    private ExecutorService c = Executors.newFixedThreadPool(3);
    /* access modifiers changed from: private */
    public StringCache d = StringCache.a(NetworkConfigManager.a().b().b(), "http", 100);

    public interface CacheCallback {
        void onResult(String str);
    }

    private YouPinHttpsAuthCache() {
    }

    public static YouPinHttpsAuthCache a() {
        if (b == null) {
            synchronized (YouPinHttpsAuthCache.class) {
                if (b == null) {
                    b = new YouPinHttpsAuthCache();
                }
            }
        }
        return b;
    }

    public void a(final NetRequest netRequest, final CacheCallback cacheCallback) {
        this.c.submit(new Runnable() {
            public void run() {
                if (cacheCallback != null) {
                    String a2 = YouPinHttpsAuthCache.this.a(netRequest);
                    LogUtils.d("YouPinHttpsAuthCache", a2);
                    String str = null;
                    if (YouPinHttpsAuthCache.this.d.b(a2)) {
                        str = YouPinHttpsAuthCache.this.d.d(a2);
                    }
                    cacheCallback.onResult(str);
                }
            }
        });
    }

    public void a(NetRequest netRequest, String str) {
        this.d.a(a(netRequest), str);
    }

    /* access modifiers changed from: private */
    public String a(NetRequest netRequest) {
        return EncryptUtils.b(netRequest.a() + netRequest.b() + netRequest.c() + netRequest.d());
    }

    public void b() {
        this.d.b();
    }
}
