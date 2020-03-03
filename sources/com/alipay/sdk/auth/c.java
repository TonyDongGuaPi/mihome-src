package com.alipay.sdk.auth;

import android.webkit.WebView;

class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f1089a;
    final /* synthetic */ AuthActivity b;

    c(AuthActivity authActivity, String str) {
        this.b = authActivity;
        this.f1089a = str;
    }

    public void run() {
        try {
            WebView f = this.b.c;
            f.loadUrl("javascript:" + this.f1089a);
        } catch (Exception unused) {
        }
    }
}
