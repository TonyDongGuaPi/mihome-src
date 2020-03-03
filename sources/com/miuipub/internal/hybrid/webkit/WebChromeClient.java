package com.miuipub.internal.hybrid.webkit;

import android.net.Uri;
import android.webkit.GeolocationPermissions;
import android.webkit.WebView;
import com.miuipub.internal.hybrid.provider.AbsWebChromeClient;
import miuipub.hybrid.GeolocationPermissions;
import miuipub.hybrid.HybridChromeClient;
import miuipub.hybrid.HybridView;
import miuipub.hybrid.JsResult;
import miuipub.hybrid.ValueCallback;

public class WebChromeClient extends AbsWebChromeClient {
    public WebChromeClient(HybridChromeClient hybridChromeClient, HybridView hybridView) {
        super(hybridChromeClient, hybridView);
    }

    public Object a() {
        return new InternalWebChromeClient();
    }

    public boolean a(HybridView hybridView, String str, String str2, JsResult jsResult) {
        return this.f8270a.a(hybridView, str, str2, jsResult);
    }

    public boolean b(HybridView hybridView, String str, String str2, JsResult jsResult) {
        return this.f8270a.b(hybridView, str, str2, jsResult);
    }

    public void a(HybridView hybridView, int i) {
        this.f8270a.a(hybridView, i);
    }

    public void a(String str, GeolocationPermissions.Callback callback) {
        this.f8270a.a(str, callback);
    }

    public void a(HybridView hybridView, String str) {
        this.f8270a.a(hybridView, str);
    }

    public void a(ValueCallback<Uri> valueCallback, String str, String str2) {
        this.f8270a.a(valueCallback, str, str2);
    }

    class InternalWebChromeClient extends android.webkit.WebChromeClient {
        InternalWebChromeClient() {
        }

        public boolean onJsAlert(WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
            return WebChromeClient.this.a(WebChromeClient.this.b, str, str2, new JsResult(jsResult));
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, android.webkit.JsResult jsResult) {
            return WebChromeClient.this.b(WebChromeClient.this.b, str, str2, new JsResult(jsResult));
        }

        public void onProgressChanged(WebView webView, int i) {
            WebChromeClient.this.a(WebChromeClient.this.b, i);
        }

        public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
            WebChromeClient.this.a(str, (GeolocationPermissions.Callback) new GeolocationPermissionsCallback(callback));
        }

        public void onReceivedTitle(WebView webView, String str) {
            WebChromeClient.this.a(WebChromeClient.this.b, str);
        }

        public void openFileChooser(android.webkit.ValueCallback<Uri> valueCallback, String str, String str2) {
            WebChromeClient.this.a(new ValueCallback(valueCallback), str, str2);
        }
    }
}
