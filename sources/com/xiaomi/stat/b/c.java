package com.xiaomi.stat.b;

import com.xiaomi.stat.d.l;

class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f23025a;

    c(b bVar) {
        this.f23025a = bVar;
    }

    public void run() {
        if (l.a()) {
            this.f23025a.f23024a.a(false, false);
            i.a().a(false);
            d.a().b();
        }
    }
}
