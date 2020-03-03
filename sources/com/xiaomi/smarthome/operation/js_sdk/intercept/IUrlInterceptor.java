package com.xiaomi.smarthome.operation.js_sdk.intercept;

import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

public interface IUrlInterceptor {
    void a();

    void a(WebView webView, int i, String str, String str2);

    void a(WebView webView, String str, Bitmap bitmap);

    void a(WebView webView, String str, String str2, String str3);

    boolean a(WebView webView, WebResourceRequest webResourceRequest);

    boolean a(WebView webView, String str);

    void b();

    void b(WebView webView, String str);
}
