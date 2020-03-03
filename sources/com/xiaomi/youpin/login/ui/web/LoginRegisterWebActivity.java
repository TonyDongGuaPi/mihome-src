package com.xiaomi.youpin.login.ui.web;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginRegisterWebActivity extends LoginWebActivity {
    /* access modifiers changed from: protected */
    public WebViewClient getWebViewClient() {
        return new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                if (str.startsWith("https://account.xiaomi.com/pass/serviceLogin")) {
                    LoginRegisterWebActivity.this.setResult(-1);
                    LoginRegisterWebActivity.this.finish();
                }
            }
        };
    }
}
