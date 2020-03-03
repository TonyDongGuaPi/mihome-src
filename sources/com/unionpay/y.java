package com.unionpay;

final class y implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9851a;
    final /* synthetic */ WebViewJavascriptBridge b;

    y(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.b = webViewJavascriptBridge;
        this.f9851a = str;
    }

    public final void run() {
        this.b.mWebView.loadUrl(this.f9851a);
    }
}
