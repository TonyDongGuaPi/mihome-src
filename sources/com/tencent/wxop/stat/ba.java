package com.tencent.wxop.stat;

import java.util.List;

class ba implements h {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f9308a;
    final /* synthetic */ boolean b;
    final /* synthetic */ au c;

    ba(au auVar, List list, boolean z) {
        this.c = auVar;
        this.f9308a = list;
        this.b = z;
    }

    public void a() {
        StatServiceImpl.d();
        this.c.a((List<bd>) this.f9308a, this.b, true);
    }

    public void b() {
        StatServiceImpl.e();
        this.c.a((List<bd>) this.f9308a, 1, this.b, true);
    }
}
