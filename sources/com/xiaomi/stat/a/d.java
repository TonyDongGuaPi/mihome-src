package com.xiaomi.stat.a;

import com.xiaomi.stat.d.k;

class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ l f23001a;
    final /* synthetic */ c b;

    d(c cVar, l lVar) {
        this.b = cVar;
        this.f23001a = lVar;
    }

    public void run() {
        try {
            this.b.b(this.f23001a);
        } catch (Exception e) {
            k.e("EventManager", "addEvent exception: " + e.toString());
        }
    }
}
