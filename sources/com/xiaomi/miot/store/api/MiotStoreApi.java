package com.xiaomi.miot.store.api;

import com.xiaomi.miot.store.common.MiotStoreApiImpl;

public class MiotStoreApi {

    /* renamed from: a  reason: collision with root package name */
    static IMiotStoreApi f11367a;

    public static synchronized void a(RNStoreApiProvider rNStoreApiProvider) {
        synchronized (MiotStoreApi.class) {
            if (f11367a == null) {
                f11367a = new MiotStoreApiImpl();
                f11367a.initial(rNStoreApiProvider);
            }
        }
    }

    public static IMiotStoreApi a() {
        return f11367a;
    }
}
