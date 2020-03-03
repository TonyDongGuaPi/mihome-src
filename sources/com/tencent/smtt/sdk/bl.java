package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.extension.proxy.X5ProxyWebViewClientExtension;

class bl extends X5ProxyWebViewClientExtension {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ WebView f9165a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bl(WebView webView, IX5WebViewClientExtension iX5WebViewClientExtension) {
        super(iX5WebViewClientExtension);
        this.f9165a = webView;
    }

    public void invalidate() {
    }

    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.f9165a.onScrollChanged(i3, i4, i, i2);
    }
}
