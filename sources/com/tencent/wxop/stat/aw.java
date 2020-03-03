package com.tencent.wxop.stat;

import java.util.List;

class aw implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f9303a;
    final /* synthetic */ boolean b;
    final /* synthetic */ boolean c;
    final /* synthetic */ au d;

    aw(au auVar, List list, boolean z, boolean z2) {
        this.d = auVar;
        this.f9303a = list;
        this.b = z;
        this.c = z2;
    }

    public void run() {
        this.d.a((List<bd>) this.f9303a, this.b);
        if (this.c) {
            this.f9303a.clear();
        }
    }
}
