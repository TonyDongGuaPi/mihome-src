package com.xiaomi.jr.http;

import okhttp3.Interceptor;
import okhttp3.Response;

/* renamed from: com.xiaomi.jr.http.-$$Lambda$HttpManager$qMRyJfqAYPM3M2kWQUvdcisgj9I  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$HttpManager$qMRyJfqAYPM3M2kWQUvdcisgj9I implements Interceptor {
    public static final /* synthetic */ $$Lambda$HttpManager$qMRyJfqAYPM3M2kWQUvdcisgj9I INSTANCE = new $$Lambda$HttpManager$qMRyJfqAYPM3M2kWQUvdcisgj9I();

    private /* synthetic */ $$Lambda$HttpManager$qMRyJfqAYPM3M2kWQUvdcisgj9I() {
    }

    public final Response intercept(Interceptor.Chain chain) {
        return HttpManager.a(chain);
    }
}
