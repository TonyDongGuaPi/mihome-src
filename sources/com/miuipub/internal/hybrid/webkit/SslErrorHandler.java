package com.miuipub.internal.hybrid.webkit;

public class SslErrorHandler extends miuipub.hybrid.SslErrorHandler {

    /* renamed from: a  reason: collision with root package name */
    private android.webkit.SslErrorHandler f8276a;

    public SslErrorHandler(android.webkit.SslErrorHandler sslErrorHandler) {
        this.f8276a = sslErrorHandler;
    }

    public void a() {
        this.f8276a.proceed();
    }

    public void b() {
        this.f8276a.cancel();
    }
}
