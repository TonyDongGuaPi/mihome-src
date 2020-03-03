package com.xiaomi.stat;

import com.xiaomi.stat.a.l;

class u implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f23082a;
    final /* synthetic */ long b;
    final /* synthetic */ long c;
    final /* synthetic */ e d;

    u(e eVar, String str, long j, long j2) {
        this.d = eVar;
        this.f23082a = str;
        this.b = j;
        this.c = j2;
    }

    public void run() {
        if (b.a() && this.d.g() && b.z()) {
            this.d.a(l.a(this.f23082a, this.b, this.c));
        }
    }
}
