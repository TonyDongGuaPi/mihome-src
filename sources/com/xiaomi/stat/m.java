package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class m implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ HttpEvent f23074a;
    final /* synthetic */ e b;

    m(e eVar, HttpEvent httpEvent) {
        this.b = eVar;
        this.f23074a = httpEvent;
    }

    public void run() {
        if (b.a() && this.b.g(false)) {
            this.b.a(l.a(this.f23074a, this.b.b));
        }
    }
}
