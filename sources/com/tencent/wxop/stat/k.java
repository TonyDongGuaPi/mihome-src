package com.tencent.wxop.stat;

import java.util.List;

class k implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f9330a;
    final /* synthetic */ h b;
    final /* synthetic */ i c;

    k(i iVar, List list, h hVar) {
        this.c = iVar;
        this.f9330a = list;
        this.b = hVar;
    }

    public void run() {
        this.c.a((List<?>) this.f9330a, this.b);
    }
}
