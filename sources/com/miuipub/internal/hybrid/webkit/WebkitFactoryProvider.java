package com.miuipub.internal.hybrid.webkit;

import android.content.Context;
import com.miuipub.internal.hybrid.provider.AbsWebChromeClient;
import com.miuipub.internal.hybrid.provider.AbsWebView;
import com.miuipub.internal.hybrid.provider.AbsWebViewClient;
import com.miuipub.internal.hybrid.provider.WebViewFactoryProvider;
import miuipub.hybrid.HybridChromeClient;
import miuipub.hybrid.HybridView;
import miuipub.hybrid.HybridViewClient;

public class WebkitFactoryProvider extends WebViewFactoryProvider {
    public AbsWebView a(Context context, HybridView hybridView) {
        return new WebView(context, hybridView);
    }

    public AbsWebViewClient a(HybridViewClient hybridViewClient, HybridView hybridView) {
        return new WebViewClient(hybridViewClient, hybridView);
    }

    public AbsWebChromeClient a(HybridChromeClient hybridChromeClient, HybridView hybridView) {
        return new WebChromeClient(hybridChromeClient, hybridView);
    }
}
