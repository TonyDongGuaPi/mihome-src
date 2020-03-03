package com.alipay.zoloz.toyger.workspace;

class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ double f1226a;
    final /* synthetic */ double b;
    final /* synthetic */ ToygerWorkspace c;

    i(ToygerWorkspace toygerWorkspace, double d, double d2) {
        this.c = toygerWorkspace;
        this.f1226a = d;
        this.b = d2;
    }

    public void run() {
        this.c.mToygerCirclePattern.onPreviewChanged(this.f1226a, this.b);
    }
}
