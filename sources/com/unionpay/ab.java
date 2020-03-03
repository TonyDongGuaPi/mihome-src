package com.unionpay;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.unionpay.utils.j;

final class ab extends WebViewClient {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebViewJavascriptBridge f9540a;

    private ab(WebViewJavascriptBridge webViewJavascriptBridge) {
        this.f9540a = webViewJavascriptBridge;
    }

    /* synthetic */ ab(WebViewJavascriptBridge webViewJavascriptBridge, byte b) {
        this(webViewJavascriptBridge);
    }

    public final void onPageFinished(WebView webView, String str) {
        j.a("test", "onPageFinished");
    }
}
