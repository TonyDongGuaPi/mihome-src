package com.tencent.smtt.sdk;

import android.webkit.WebView;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;

class bn implements WebView.FindListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ IX5WebViewBase.FindListener f9167a;
    final /* synthetic */ WebView b;

    bn(WebView webView, IX5WebViewBase.FindListener findListener) {
        this.b = webView;
        this.f9167a = findListener;
    }

    public void onFindResultReceived(int i, int i2, boolean z) {
        this.f9167a.onFindResultReceived(i, i2, z);
    }
}
