package com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors;

import android.webkit.WebView;
import com.xiaomi.smarthome.operation.js_sdk.intercept.IUrlInterceptorAdapter;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;

public class CloudStorageGoodInterceptor extends IUrlInterceptorAdapter {
    public boolean a(WebView webView, String str) {
        if (!str.contains("home.mi.com/shop/shexiangyun")) {
            return false;
        }
        UrlDispatchManger.a().c(str);
        return true;
    }
}
