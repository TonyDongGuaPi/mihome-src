package com.xiaomi.youpin.login.okhttpApi;

import java.lang.ref.WeakReference;
import okhttp3.Call;

public class AsyncHandle<H> {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<H> f23529a;

    public interface Handle {
        void a();
    }

    public AsyncHandle(H h) {
        this.f23529a = new WeakReference<>(h);
    }

    public final void a() {
        Object obj;
        if (this.f23529a != null && (obj = this.f23529a.get()) != null) {
            if (obj instanceof Handle) {
                ((Handle) obj).a();
            } else if (obj instanceof Call) {
                ((Call) obj).cancel();
            }
        }
    }
}
