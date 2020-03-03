package com.xiaomi.youpin.core.net;

import java.lang.ref.WeakReference;
import okhttp3.Call;

public class NetHandle {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Call> f23341a;

    public NetHandle(Call call) {
        this.f23341a = new WeakReference<>(call);
    }

    public void a() {
        Call call = (Call) this.f23341a.get();
        if (call != null) {
            call.cancel();
        }
    }
}
