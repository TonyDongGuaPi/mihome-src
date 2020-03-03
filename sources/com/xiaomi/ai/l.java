package com.xiaomi.ai;

import com.xiaomi.ai.c;

class l implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c.e f9928a;

    l(c.e eVar) {
        this.f9928a = eVar;
    }

    public void run() {
        while (this.f9928a.c()) {
            c.b unused = this.f9928a.b();
        }
        this.f9928a.b.clear();
    }
}
