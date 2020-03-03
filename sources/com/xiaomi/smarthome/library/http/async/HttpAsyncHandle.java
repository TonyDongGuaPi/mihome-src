package com.xiaomi.smarthome.library.http.async;

import java.lang.ref.WeakReference;
import okhttp3.Call;

public class HttpAsyncHandle {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Call> f19115a;

    public HttpAsyncHandle(Call call) {
        this.f19115a = new WeakReference<>(call);
    }

    public void a() {
        Call call = (Call) this.f19115a.get();
        if (call != null) {
            call.cancel();
        }
    }
}
