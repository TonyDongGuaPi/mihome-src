package com.miuipub.internal.hybrid.webkit;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import com.miuipub.internal.hybrid.provider.AbsWebChromeClient;
import com.miuipub.internal.hybrid.provider.AbsWebView;
import com.miuipub.internal.hybrid.provider.AbsWebViewClient;
import miuipub.hybrid.HybridBackForwardList;
import miuipub.hybrid.HybridSettings;
import miuipub.hybrid.HybridView;

public class WebView extends AbsWebView {
    protected android.webkit.WebView c = new android.webkit.WebView(this.f8271a);

    public WebView(Context context, HybridView hybridView) {
        super(context, hybridView);
    }

    public View a() {
        return this.c;
    }

    public void a(AbsWebViewClient absWebViewClient) {
        this.c.setWebViewClient((WebViewClient) absWebViewClient.a());
    }

    public void a(AbsWebChromeClient absWebChromeClient) {
        this.c.setWebChromeClient((WebChromeClient) absWebChromeClient.a());
    }

    public void a(String str) {
        this.c.loadUrl(str);
    }

    public void a(Object obj, String str) {
        this.c.addJavascriptInterface(obj, str);
    }

    public HybridSettings b() {
        return new WebSettings(this.c.getSettings());
    }

    public void c() {
        this.c.destroy();
    }

    public void d() {
        this.c.reload();
    }

    public void a(boolean z) {
        this.c.clearCache(z);
    }

    public boolean e() {
        return this.c.canGoBack();
    }

    public boolean f() {
        return this.c.canGoForward();
    }

    public void g() {
        this.c.goBack();
    }

    public String h() {
        return this.c.getUrl();
    }

    public String i() {
        return this.c.getTitle();
    }

    public int j() {
        return this.c.getContentHeight();
    }

    public float k() {
        return this.c.getScale();
    }

    public Context l() {
        return this.c.getContext();
    }

    public void a(int i) {
        this.c.setVisibility(i);
    }

    public View m() {
        return this.c.getRootView();
    }

    public HybridBackForwardList n() {
        return new WebBackForwardList(this.c.copyBackForwardList());
    }
}
