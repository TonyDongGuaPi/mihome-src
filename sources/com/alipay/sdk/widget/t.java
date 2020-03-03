package com.alipay.sdk.widget;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class t extends WebViewClient {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebViewWindow f1159a;

    t(WebViewWindow webViewWindow) {
        this.f1159a = webViewWindow;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!this.f1159a.h.b(this.f1159a, str)) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        return true;
    }

    public void onPageFinished(WebView webView, String str) {
        if (!this.f1159a.h.c(this.f1159a, str)) {
            super.onPageFinished(webView, str);
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        if (!this.f1159a.h.a(this.f1159a, i, str, str2)) {
            super.onReceivedError(webView, i, str, str2);
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (!this.f1159a.h.a(this.f1159a, sslErrorHandler, sslError)) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }
}
