package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class y implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f23086a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ MiStatParams d;
    final /* synthetic */ e e;

    y(e eVar, boolean z, String str, String str2, MiStatParams miStatParams) {
        this.e = eVar;
        this.f23086a = z;
        this.b = str;
        this.c = str2;
        this.d = miStatParams;
    }

    public void run() {
        if (b.a() && this.e.g(this.f23086a) && b.A()) {
            this.e.a(l.a(this.b, this.c, this.d, this.e.b, this.f23086a));
        }
    }
}
