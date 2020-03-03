package com.tencent.tinker.loader.hotplug.interceptor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public abstract class Interceptor<T_TARGET> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9244a = "Tinker.Interceptor";
    private T_TARGET b = null;
    private volatile boolean c = false;

    protected interface ITinkerHotplugProxy {
    }

    /* access modifiers changed from: protected */
    public abstract void a(@Nullable T_TARGET t_target) throws Throwable;

    /* access modifiers changed from: protected */
    @Nullable
    public abstract T_TARGET b() throws Throwable;

    /* access modifiers changed from: protected */
    @NonNull
    public T_TARGET b(@Nullable T_TARGET t_target) throws Throwable {
        return t_target;
    }

    public synchronized void c() throws InterceptFailedException {
        try {
            T_TARGET b2 = b();
            this.b = b2;
            T_TARGET b3 = b(b2);
            if (b3 != b2) {
                a(b3);
            } else {
                Log.w(f9244a, "target: " + b2 + " was already hooked.");
            }
            this.c = true;
        } catch (Throwable th) {
            this.b = null;
            throw new InterceptFailedException(th);
        }
    }

    public synchronized void d() throws InterceptFailedException {
        if (this.c) {
            try {
                a(this.b);
                this.b = null;
                this.c = false;
            } catch (Throwable th) {
                throw new InterceptFailedException(th);
            }
        }
    }
}
