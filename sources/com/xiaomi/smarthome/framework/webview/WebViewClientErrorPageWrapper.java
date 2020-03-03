package com.xiaomi.smarthome.framework.webview;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors.ErrorInterceptor;

public class WebViewClientErrorPageWrapper extends WebViewClient {

    /* renamed from: a  reason: collision with root package name */
    private WebViewClient f17903a;
    private final ErrorInterceptor b = new ErrorInterceptor();

    public WebViewClientErrorPageWrapper(WebViewClient webViewClient) {
        this.f17903a = webViewClient;
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f17903a.onReceivedError(webView, i, str, str2);
        this.b.a(webView, i, str, str2);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.f17903a.onPageFinished(webView, str);
        this.b.b(webView, str);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return this.b.a(webView, str) || this.f17903a.shouldOverrideUrlLoading(webView, str) || super.shouldOverrideUrlLoading(webView, str);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        this.f17903a.onPageStarted(webView, str, bitmap);
        super.onPageStarted(webView, str, bitmap);
    }

    public void onLoadResource(WebView webView, String str) {
        this.f17903a.onLoadResource(webView, str);
        super.onLoadResource(webView, str);
    }

    public void onPageCommitVisible(WebView webView, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.f17903a.onPageCommitVisible(webView, str);
        }
        super.onPageCommitVisible(webView, str);
    }

    @Nullable
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        WebResourceResponse shouldInterceptRequest;
        if (Build.VERSION.SDK_INT < 21 || (shouldInterceptRequest = this.f17903a.shouldInterceptRequest(webView, webResourceRequest)) == null) {
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }
        return shouldInterceptRequest;
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        return this.f17903a.shouldOverrideKeyEvent(webView, keyEvent) && super.shouldOverrideKeyEvent(webView, keyEvent);
    }

    public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        this.f17903a.onUnhandledKeyEvent(webView, keyEvent);
        super.onUnhandledKeyEvent(webView, keyEvent);
    }

    public void onScaleChanged(WebView webView, float f, float f2) {
        this.f17903a.onScaleChanged(webView, f, f2);
        super.onScaleChanged(webView, f, f2);
    }

    public void onReceivedLoginRequest(WebView webView, String str, @Nullable String str2, String str3) {
        this.f17903a.onReceivedLoginRequest(webView, str, str2, str3);
        super.onReceivedLoginRequest(webView, str, str2, str3);
    }
}
