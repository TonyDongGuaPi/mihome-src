package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class j implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f23071a;
    final /* synthetic */ int b;
    final /* synthetic */ e c;

    j(e eVar, int i, int i2) {
        this.c = eVar;
        this.f23071a = i;
        this.b = i2;
    }

    public void run() {
        if (b.a() && this.c.g()) {
            b.e(this.f23071a);
            this.c.a(l.a(this.b));
        }
    }
}
