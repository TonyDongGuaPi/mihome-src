package com.tencent.wxop.stat;

class bb implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f9309a;
    final /* synthetic */ au b;

    bb(au auVar, int i) {
        this.b = auVar;
        this.f9309a = i;
    }

    public void run() {
        this.b.b(this.f9309a, true);
        this.b.b(this.f9309a, false);
    }
}
