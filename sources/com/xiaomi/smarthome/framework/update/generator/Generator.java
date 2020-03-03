package com.xiaomi.smarthome.framework.update.generator;

import android.support.annotation.CallSuper;

public abstract class Generator {

    /* renamed from: a  reason: collision with root package name */
    protected OnProgressUpdateListener f17783a;

    public interface OnProgressUpdateListener {
        void b(int i);
    }

    public abstract void a();

    public abstract void a(int i);

    public abstract void b(int i);

    public void a(OnProgressUpdateListener onProgressUpdateListener) {
        this.f17783a = onProgressUpdateListener;
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void c(int i) {
        if (this.f17783a != null) {
            this.f17783a.b(i);
        }
    }
}
