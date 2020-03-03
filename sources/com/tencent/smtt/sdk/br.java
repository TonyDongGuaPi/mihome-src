package com.tencent.smtt.sdk;

class br extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebView f9171a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    br(WebView webView, String str) {
        super(str);
        this.f9171a = webView;
    }

    public void run() {
        this.f9171a.a();
    }
}
