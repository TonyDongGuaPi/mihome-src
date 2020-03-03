package com.xiaomi.smarthome.core.server.internal;

import java.lang.ref.WeakReference;
import okhttp3.Call;

public class NetHandle {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Call> f14062a;

    public NetHandle(Call call) {
        this.f14062a = new WeakReference<>(call);
    }

    public void a() {
        Call call = (Call) this.f14062a.get();
        if (call != null) {
            call.cancel();
        }
    }

    public static class NetHandleWrap extends NetHandle {

        /* renamed from: a  reason: collision with root package name */
        private NetHandle f14063a;
        private boolean b;

        public NetHandleWrap() {
            super((Call) null);
        }

        public void a(NetHandle netHandle) {
            this.f14063a = netHandle;
        }

        public void a() {
            this.b = true;
            if (this.f14063a != null) {
                this.f14063a.a();
            }
        }

        public boolean b() {
            return this.b;
        }
    }
}
