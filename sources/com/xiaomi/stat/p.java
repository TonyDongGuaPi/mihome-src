package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class p implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f23077a;
    final /* synthetic */ int b;
    final /* synthetic */ long c;
    final /* synthetic */ long d;
    final /* synthetic */ e e;

    p(e eVar, int i, int i2, long j, long j2) {
        this.e = eVar;
        this.f23077a = i;
        this.b = i2;
        this.c = j;
        this.d = j2;
    }

    public void run() {
        if (b.a() && this.e.g()) {
            this.e.a(l.a(this.f23077a, this.b, this.c, this.d));
        }
    }
}
