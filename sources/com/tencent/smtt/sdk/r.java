package com.tencent.smtt.sdk;

import android.os.Message;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.WebView;

class r implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebView.WebViewTransport f9188a;
    final /* synthetic */ Message b;
    final /* synthetic */ q c;

    r(q qVar, WebView.WebViewTransport webViewTransport, Message message) {
        this.c = qVar;
        this.f9188a = webViewTransport;
        this.b = message;
    }

    public void run() {
        WebView webView = this.f9188a.getWebView();
        if (webView != null) {
            ((IX5WebViewBase.WebViewTransport) this.b.obj).setWebView(webView.c());
        }
        this.b.sendToTarget();
    }
}
