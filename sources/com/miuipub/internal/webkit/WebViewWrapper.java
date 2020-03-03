package com.miuipub.internal.webkit;

import com.miuipub.internal.hybrid.webkit.WebView;
import miuipub.hybrid.HybridView;

public class WebViewWrapper extends WebView {
    public WebViewWrapper(android.webkit.WebView webView) {
        super(webView.getContext(), (HybridView) null);
        this.c = webView;
    }
}
