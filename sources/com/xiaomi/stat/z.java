package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class z implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Throwable f23087a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;
    final /* synthetic */ e d;

    z(e eVar, Throwable th, String str, boolean z) {
        this.d = eVar;
        this.f23087a = th;
        this.b = str;
        this.c = z;
    }

    public void run() {
        if (b.a() && this.d.g(false)) {
            this.d.a(l.a(this.f23087a, this.b, this.c, this.d.b));
        }
    }
}
