package com.xiaomi.jr.appbase.app;

import com.xiaomi.jr.http.SimpleHttpRequest;
import com.xiaomi.jr.stats.HttpRequester;
import java.util.Map;

/* renamed from: com.xiaomi.jr.appbase.app.-$$Lambda$MiFiAppLifecycleImpl$kk18lHYGpEwv0TNsvPd7fjttuK4  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$MiFiAppLifecycleImpl$kk18lHYGpEwv0TNsvPd7fjttuK4 implements HttpRequester {
    public static final /* synthetic */ $$Lambda$MiFiAppLifecycleImpl$kk18lHYGpEwv0TNsvPd7fjttuK4 INSTANCE = new $$Lambda$MiFiAppLifecycleImpl$kk18lHYGpEwv0TNsvPd7fjttuK4();

    private /* synthetic */ $$Lambda$MiFiAppLifecycleImpl$kk18lHYGpEwv0TNsvPd7fjttuK4() {
    }

    public final void get(String str, Map map) {
        SimpleHttpRequest.a(str, map, (SimpleHttpRequest.Listener) null);
    }
}
