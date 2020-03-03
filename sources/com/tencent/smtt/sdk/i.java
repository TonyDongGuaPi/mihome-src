package com.tencent.smtt.sdk;

class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f9180a;
    final /* synthetic */ h b;

    i(h hVar, boolean z) {
        this.b = hVar;
        this.f9180a = z;
    }

    public void run() {
        this.b.c.onReceiveValue(Boolean.valueOf(this.f9180a));
    }
}
