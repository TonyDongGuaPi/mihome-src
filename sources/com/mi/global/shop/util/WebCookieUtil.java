package com.mi.global.shop.util;

import com.mi.global.shop.webview.WebViewCookieManager;

public class WebCookieUtil {
    public static String a() {
        return WebViewCookieManager.a(ConnectionHelper.G, "xmuuid");
    }
}
