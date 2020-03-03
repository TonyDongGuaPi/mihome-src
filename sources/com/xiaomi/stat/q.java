package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class q implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f23078a;
    final /* synthetic */ String b;
    final /* synthetic */ e c;

    q(e eVar, String str, String str2) {
        this.c = eVar;
        this.f23078a = str;
        this.b = str2;
    }

    public void run() {
        if (b.a() && this.c.g(false)) {
            this.c.a(l.a(this.f23078a, this.b, this.c.b));
        }
    }
}
