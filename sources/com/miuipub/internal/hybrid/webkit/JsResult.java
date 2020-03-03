package com.miuipub.internal.hybrid.webkit;

public class JsResult extends miuipub.hybrid.JsResult {

    /* renamed from: a  reason: collision with root package name */
    private android.webkit.JsResult f8275a;

    public JsResult(android.webkit.JsResult jsResult) {
        this.f8275a = jsResult;
    }

    public void a() {
        this.f8275a.confirm();
    }

    public void b() {
        this.f8275a.cancel();
    }
}
