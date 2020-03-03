package com.alipay.sdk.app;

class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f1074a;
    final /* synthetic */ boolean b;
    final /* synthetic */ H5PayCallback c;
    final /* synthetic */ PayTask d;

    g(PayTask payTask, String str, boolean z, H5PayCallback h5PayCallback) {
        this.d = payTask;
        this.f1074a = str;
        this.b = z;
        this.c = h5PayCallback;
    }

    public void run() {
        this.c.onPayResult(this.d.h5Pay(this.f1074a, this.b));
    }
}
