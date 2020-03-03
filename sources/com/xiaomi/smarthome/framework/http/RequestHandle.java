package com.xiaomi.smarthome.framework.http;

import java.lang.ref.WeakReference;
import okhttp3.Call;

public class RequestHandle {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Call> f16497a;

    public RequestHandle(Call call) {
        this.f16497a = new WeakReference<>(call);
    }

    public void a() {
        Call call = (Call) this.f16497a.get();
        if (call != null) {
            call.cancel();
        }
    }
}
