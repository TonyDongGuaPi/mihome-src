package com.xiaomi.smarthome.framework.webview;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;

public class WebViewUtils {
    public static void a(WebView webView, String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            webView.evaluateJavascript(str, (ValueCallback) null);
            return;
        }
        webView.loadUrl("javascript:" + str);
    }
}
