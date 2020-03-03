package com.mi.global.bbs.utils;

import com.mi.global.bbs.view.webview.WebViewCookieManager;

public class WebCookieUtil {
    public static String getXmuuId() {
        return WebViewCookieManager.getCookie(ConnectionHelper.getWebCookieWithPath(), "xmuuid");
    }
}
