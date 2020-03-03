package com.xiaomi.stat;

import com.xiaomi.stat.a.l;
import com.xiaomi.stat.d.r;

class k implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f23072a;

    k(e eVar) {
        this.f23072a = eVar;
    }

    public void run() {
        if (b.a() && this.f23072a.g() && b.z()) {
            long b = r.b();
            if (this.f23072a.a(b.r(), b)) {
                b.a(b);
                this.f23072a.a(l.a());
            }
        }
    }
}
