package com.xiaomi.stat;

import com.xiaomi.stat.a.c;
import com.xiaomi.stat.b.g;
import com.xiaomi.stat.d.e;

class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f23067a;
    final /* synthetic */ boolean b;
    final /* synthetic */ e c;

    f(e eVar, String str, boolean z) {
        this.c = eVar;
        this.f23067a = str;
        this.b = z;
    }

    public void run() {
        e.a();
        if (this.c.f23066a) {
            b.h(this.f23067a);
        }
        b.d();
        g.a().a(b.f());
        b.a(this.c.c, this.b);
        b.n();
        if (!this.c.f23066a) {
            b.f(this.c.b);
        }
        this.c.f();
        c.a().b();
        com.xiaomi.stat.b.e.a().execute(new g(this));
    }
}
