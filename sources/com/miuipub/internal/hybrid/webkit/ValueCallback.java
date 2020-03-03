package com.miuipub.internal.hybrid.webkit;

public class ValueCallback<T> implements miuipub.hybrid.ValueCallback<T> {

    /* renamed from: a  reason: collision with root package name */
    private android.webkit.ValueCallback<T> f8277a;

    public ValueCallback(android.webkit.ValueCallback<T> valueCallback) {
        this.f8277a = valueCallback;
    }

    public void a(T t) {
        this.f8277a.onReceiveValue(t);
    }
}
