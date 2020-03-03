package com.unionpay;

final class z implements ad {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebViewJavascriptBridge f9852a;
    private final String b;

    public z(WebViewJavascriptBridge webViewJavascriptBridge, String str) {
        this.f9852a = webViewJavascriptBridge;
        this.b = str;
    }

    public final void a(String str) {
        this.f9852a._callbackJs(this.b, str);
    }
}
