package com.tencent.smtt.sdk;

import android.os.Message;
import android.webkit.WebView;
import com.tencent.smtt.sdk.WebView;

class z implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebView.WebViewTransport f9196a;
    final /* synthetic */ Message b;
    final /* synthetic */ SystemWebChromeClient c;

    z(SystemWebChromeClient systemWebChromeClient, WebView.WebViewTransport webViewTransport, Message message) {
        this.c = systemWebChromeClient;
        this.f9196a = webViewTransport;
        this.b = message;
    }

    public void run() {
        WebView webView = this.f9196a.getWebView();
        if (webView != null) {
            ((WebView.WebViewTransport) this.b.obj).setWebView(webView.b());
        }
        this.b.sendToTarget();
    }
}
