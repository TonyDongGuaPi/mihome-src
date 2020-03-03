package com.xiaomi.smarthome.core.server.internal;

import java.lang.ref.WeakReference;
import okhttp3.Call;

public class CoreAsyncHandle<H> {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<H> f14060a;

    public interface Handle {
        void a();
    }

    public CoreAsyncHandle(H h) {
        this.f14060a = new WeakReference<>(h);
    }

    public final void a() {
        Object obj;
        if (this.f14060a != null && (obj = this.f14060a.get()) != null) {
            if (obj instanceof Handle) {
                ((Handle) obj).a();
            } else if (obj instanceof NetHandle) {
                ((NetHandle) obj).a();
            } else if (obj instanceof Call) {
                ((Call) obj).cancel();
            }
        }
    }
}
