package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class n implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ NetAvailableEvent f23075a;
    final /* synthetic */ e b;

    n(e eVar, NetAvailableEvent netAvailableEvent) {
        this.b = eVar;
        this.f23075a = netAvailableEvent;
    }

    public void run() {
        if (b.a() && this.b.g(false) && b.y()) {
            this.b.a(l.a(this.f23075a, this.b.b));
        }
    }
}
