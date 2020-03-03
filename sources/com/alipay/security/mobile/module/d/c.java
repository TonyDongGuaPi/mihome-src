package com.alipay.security.mobile.module.d;

final class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f1169a;

    c(b bVar) {
        this.f1169a = bVar;
    }

    public final void run() {
        try {
            this.f1169a.b();
        } catch (Exception e) {
            d.a((Throwable) e);
        }
    }
}
