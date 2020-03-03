package com.miuipub.internal.hybrid.webkit;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.miuipub.internal.hybrid.provider.AbsWebViewClient;
import miuipub.hybrid.HybridResourceResponse;
import miuipub.hybrid.HybridView;
import miuipub.hybrid.HybridViewClient;
import miuipub.hybrid.SslErrorHandler;

public class WebViewClient extends AbsWebViewClient {
    public WebViewClient(HybridViewClient hybridViewClient, HybridView hybridView) {
        super(hybridViewClient, hybridView);
    }

    public Object a() {
        return new InternalWebViewClient();
    }

    public void a(HybridView hybridView, String str, Bitmap bitmap) {
        this.f8272a.a(hybridView, str, bitmap);
    }

    public void a(HybridView hybridView, String str) {
        this.f8272a.b(hybridView, str);
    }

    public HybridResourceResponse b(HybridView hybridView, String str) {
        return this.f8272a.c(hybridView, str);
    }

    public boolean c(HybridView hybridView, String str) {
        return this.f8272a.a(hybridView, str);
    }

    public void a(HybridView hybridView, SslErrorHandler sslErrorHandler, SslError sslError) {
        this.f8272a.a(hybridView, sslErrorHandler, sslError);
    }

    public void a(HybridView hybridView, int i, String str, String str2) {
        this.f8272a.a(hybridView, i, str, str2);
    }

    public void a(HybridView hybridView, String str, String str2, String str3) {
        this.f8272a.a(hybridView, str, str2, str3);
    }

    class InternalWebViewClient extends android.webkit.WebViewClient {
        InternalWebViewClient() {
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            WebViewClient.this.a(WebViewClient.this.b, str, bitmap);
        }

        public void onPageFinished(WebView webView, String str) {
            WebViewClient.this.a(WebViewClient.this.b, str);
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            HybridResourceResponse b = WebViewClient.this.b(WebViewClient.this.b, str);
            if (b == null) {
                return null;
            }
            return new WebResourceResponce(b);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return WebViewClient.this.c(WebViewClient.this.b, str);
        }

        public void onReceivedSslError(WebView webView, android.webkit.SslErrorHandler sslErrorHandler, SslError sslError) {
            WebViewClient.this.a(WebViewClient.this.b, (SslErrorHandler) new SslErrorHandler(sslErrorHandler), sslError);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            WebViewClient.this.a(WebViewClient.this.b, i, str, str2);
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            WebViewClient.this.a(WebViewClient.this.b, str, str2, str3);
        }
    }
}
