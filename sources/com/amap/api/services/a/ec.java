package com.amap.api.services.a;

public abstract class ec {

    /* renamed from: a  reason: collision with root package name */
    ec f4419a;

    /* access modifiers changed from: protected */
    public abstract boolean a();

    public ec() {
    }

    public ec(ec ecVar) {
        this.f4419a = ecVar;
    }

    public boolean c() {
        if (!d()) {
            return false;
        }
        return a();
    }

    private boolean d() {
        if (this.f4419a != null) {
            return this.f4419a.c();
        }
        return true;
    }

    public void a(boolean z) {
        if (this.f4419a != null) {
            this.f4419a.a(z);
        }
    }

    public int b() {
        return Math.min(Integer.MAX_VALUE, this.f4419a != null ? this.f4419a.b() : Integer.MAX_VALUE);
    }

    public void a(int i) {
        if (this.f4419a != null) {
            this.f4419a.a(i);
        }
    }
}
