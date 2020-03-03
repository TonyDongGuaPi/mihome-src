package com.alipay.sdk.widget;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

class s extends WebChromeClient {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebViewWindow f1158a;

    s(WebViewWindow webViewWindow) {
        this.f1158a = webViewWindow;
    }

    public void onProgressChanged(WebView webView, int i) {
        if (i == 100) {
            this.f1158a.d.setVisibility(4);
            return;
        }
        if (4 == this.f1158a.d.getVisibility()) {
            this.f1158a.d.setVisibility(0);
        }
        this.f1158a.d.setProgress(i);
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return this.f1158a.g.a(this.f1158a, str, str2, str3, jsPromptResult);
    }

    public void onReceivedTitle(WebView webView, String str) {
        this.f1158a.g.a(this.f1158a, str);
    }
}
