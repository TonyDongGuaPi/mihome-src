package com.tencent.wxop.stat;

class az implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f9306a;
    final /* synthetic */ au b;

    az(au auVar, f fVar) {
        this.b = auVar;
        this.f9306a = fVar;
    }

    public void run() {
        this.b.b(this.f9306a);
    }
}
