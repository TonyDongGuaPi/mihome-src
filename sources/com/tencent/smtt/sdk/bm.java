package com.tencent.smtt.sdk;

class bm extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebView f9166a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bm(WebView webView, String str) {
        super(str);
        this.f9166a = webView;
    }

    public void run() {
        this.f9166a.tbsWebviewDestroy(false);
    }
}
