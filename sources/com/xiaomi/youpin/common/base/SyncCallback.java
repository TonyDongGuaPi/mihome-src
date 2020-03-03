package com.xiaomi.youpin.common.base;

import com.xiaomi.youpin.common.base.YouPinError;

public abstract class SyncCallback<R, E extends YouPinError> extends AsyncCallback<R, E> {

    /* renamed from: a  reason: collision with root package name */
    private boolean f23225a = false;
    private R b;
    private E c;
    private Object d = null;

    public boolean a() {
        return this.f23225a;
    }

    public R b() {
        return this.b;
    }

    public E c() {
        return this.c;
    }

    public Object d() {
        return this.d;
    }

    public void e() {
        this.d = new Object();
    }

    public boolean f() {
        return this.d != null;
    }

    public void b(R r) {
        if (f()) {
            this.f23225a = true;
            this.b = r;
            synchronized (this.d) {
                this.d.notify();
            }
            return;
        }
        a(r);
    }

    public void b(E e) {
        if (f()) {
            this.f23225a = false;
            this.c = e;
            synchronized (this.d) {
                this.d.notify();
            }
            return;
        }
        a(e);
    }
}
