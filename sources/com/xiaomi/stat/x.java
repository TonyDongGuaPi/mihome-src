package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class x implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f23085a;
    final /* synthetic */ long b;
    final /* synthetic */ long c;
    final /* synthetic */ MiStatParams d;
    final /* synthetic */ e e;

    x(e eVar, String str, long j, long j2, MiStatParams miStatParams) {
        this.e = eVar;
        this.f23085a = str;
        this.b = j;
        this.c = j2;
        this.d = miStatParams;
    }

    public void run() {
        if (b.a() && this.e.g(false) && b.z()) {
            this.e.a(l.a(this.f23085a, this.b - this.c, this.b, this.d, this.e.b));
        }
    }
}
