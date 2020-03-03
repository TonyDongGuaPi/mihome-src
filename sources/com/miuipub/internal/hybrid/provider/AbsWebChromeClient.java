package com.miuipub.internal.hybrid.provider;

import android.net.Uri;
import miuipub.hybrid.GeolocationPermissions;
import miuipub.hybrid.HybridChromeClient;
import miuipub.hybrid.HybridView;
import miuipub.hybrid.JsResult;
import miuipub.hybrid.ValueCallback;

public abstract class AbsWebChromeClient {

    /* renamed from: a  reason: collision with root package name */
    protected HybridChromeClient f8270a;
    protected HybridView b;

    public Object a() {
        return null;
    }

    public void a(String str, GeolocationPermissions.Callback callback) {
    }

    public void a(HybridView hybridView, int i) {
    }

    public void a(HybridView hybridView, String str) {
    }

    public void a(ValueCallback<Uri> valueCallback, String str, String str2) {
    }

    public boolean a(HybridView hybridView, String str, String str2, JsResult jsResult) {
        return false;
    }

    public boolean b(HybridView hybridView, String str, String str2, JsResult jsResult) {
        return false;
    }

    public AbsWebChromeClient(HybridChromeClient hybridChromeClient, HybridView hybridView) {
        this.f8270a = hybridChromeClient;
        this.b = hybridView;
    }
}
