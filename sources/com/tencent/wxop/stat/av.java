package com.tencent.wxop.stat;

import java.util.List;

class av implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f9302a;
    final /* synthetic */ int b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;
    final /* synthetic */ au e;

    av(au auVar, List list, int i, boolean z, boolean z2) {
        this.e = auVar;
        this.f9302a = list;
        this.b = i;
        this.c = z;
        this.d = z2;
    }

    public void run() {
        this.e.a((List<bd>) this.f9302a, this.b, this.c);
        if (this.d) {
            this.f9302a.clear();
        }
    }
}
