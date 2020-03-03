package com.xiaomi.jr.http;

import com.xiaomi.jr.http.utils.CacheUtils;
import okhttp3.HttpUrl;
import okhttp3.MiFiCache;

/* renamed from: com.xiaomi.jr.http.-$$Lambda$UdnmcVr-MGZQMDOyhxMXO8MVBnQ  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$UdnmcVrMGZQMDOyhxMXO8MVBnQ implements MiFiCache.GetCacheUrlCallback {
    public static final /* synthetic */ $$Lambda$UdnmcVrMGZQMDOyhxMXO8MVBnQ INSTANCE = new $$Lambda$UdnmcVrMGZQMDOyhxMXO8MVBnQ();

    private /* synthetic */ $$Lambda$UdnmcVrMGZQMDOyhxMXO8MVBnQ() {
    }

    public final HttpUrl cacheUrl(HttpUrl httpUrl) {
        return CacheUtils.a(httpUrl);
    }
}
