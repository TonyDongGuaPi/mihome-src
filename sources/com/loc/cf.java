package com.loc;

public abstract class cf {

    /* renamed from: a  reason: collision with root package name */
    cf f6531a;

    public cf() {
    }

    public cf(cf cfVar) {
        this.f6531a = cfVar;
    }

    public void a(int i) {
        if (this.f6531a != null) {
            this.f6531a.a(i);
        }
    }

    public void a(boolean z) {
        if (this.f6531a != null) {
            this.f6531a.a(z);
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean a();

    public int b() {
        return Math.min(Integer.MAX_VALUE, this.f6531a != null ? this.f6531a.b() : Integer.MAX_VALUE);
    }

    public final boolean c() {
        if (!(this.f6531a != null ? this.f6531a.c() : true)) {
            return false;
        }
        return a();
    }
}
