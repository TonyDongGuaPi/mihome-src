package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class aa implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f23011a;
    final /* synthetic */ MiStatParams b;
    final /* synthetic */ e c;

    aa(e eVar, boolean z, MiStatParams miStatParams) {
        this.c = eVar;
        this.f23011a = z;
        this.b = miStatParams;
    }

    public void run() {
        if (b.a() && this.c.g(this.f23011a)) {
            this.c.a(l.a(this.b, this.f23011a, this.c.b));
        }
    }
}
