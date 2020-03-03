package com.tencent.wxop.stat;

import com.tencent.wxop.stat.a.e;

class ay implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f9305a;
    final /* synthetic */ h b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;
    final /* synthetic */ au e;

    ay(au auVar, e eVar, h hVar, boolean z, boolean z2) {
        this.e = auVar;
        this.f9305a = eVar;
        this.b = hVar;
        this.c = z;
        this.d = z2;
    }

    public void run() {
        this.e.b(this.f9305a, this.b, this.c, this.d);
    }
}
