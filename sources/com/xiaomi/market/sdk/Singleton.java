package com.xiaomi.market.sdk;

public abstract class Singleton<T> {

    /* renamed from: a  reason: collision with root package name */
    private T f11113a;

    /* access modifiers changed from: protected */
    public abstract T a();

    public final T b() {
        T t;
        synchronized (this) {
            if (this.f11113a == null) {
                this.f11113a = a();
            }
            t = this.f11113a;
        }
        return t;
    }
}
