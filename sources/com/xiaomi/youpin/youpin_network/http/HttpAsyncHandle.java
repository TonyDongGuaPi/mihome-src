package com.xiaomi.youpin.youpin_network.http;

import java.lang.ref.WeakReference;
import okhttp3.Call;

public class HttpAsyncHandle {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Call> f23861a;

    public HttpAsyncHandle(Call call) {
        this.f23861a = new WeakReference<>(call);
    }

    public void a() {
        Call call = (Call) this.f23861a.get();
        if (call != null) {
            call.cancel();
        }
    }
}
