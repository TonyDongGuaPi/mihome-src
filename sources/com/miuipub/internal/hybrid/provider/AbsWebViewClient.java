package com.miuipub.internal.hybrid.provider;

import android.graphics.Bitmap;
import android.net.http.SslError;
import miuipub.hybrid.HybridResourceResponse;
import miuipub.hybrid.HybridView;
import miuipub.hybrid.HybridViewClient;
import miuipub.hybrid.SslErrorHandler;

public abstract class AbsWebViewClient {

    /* renamed from: a  reason: collision with root package name */
    protected HybridViewClient f8272a;
    protected HybridView b;

    public Object a() {
        return null;
    }

    public void a(HybridView hybridView, int i, String str, String str2) {
    }

    public void a(HybridView hybridView, String str) {
    }

    public void a(HybridView hybridView, String str, Bitmap bitmap) {
    }

    public void a(HybridView hybridView, String str, String str2, String str3) {
    }

    public void a(HybridView hybridView, SslErrorHandler sslErrorHandler, SslError sslError) {
    }

    public HybridResourceResponse b(HybridView hybridView, String str) {
        return null;
    }

    public boolean c(HybridView hybridView, String str) {
        return false;
    }

    public AbsWebViewClient(HybridViewClient hybridViewClient, HybridView hybridView) {
        this.f8272a = hybridViewClient;
        this.b = hybridView;
    }
}
