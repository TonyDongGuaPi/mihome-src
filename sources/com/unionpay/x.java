package com.unionpay;

final class x implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ac f9850a;
    final /* synthetic */ String b;
    final /* synthetic */ ad c;
    final /* synthetic */ WebViewJavascriptBridge d;

    x(WebViewJavascriptBridge webViewJavascriptBridge, ac acVar, String str, ad adVar) {
        this.d = webViewJavascriptBridge;
        this.f9850a = acVar;
        this.b = str;
        this.c = adVar;
    }

    public final void run() {
        if (this.f9850a != null) {
            this.f9850a.a(this.b, this.c);
        }
    }
}
