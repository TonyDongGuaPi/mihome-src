package com.mi.global.shop.componentService;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mi.global.shop.base.service.WebViewCookieManagerService;
import com.mi.global.shop.webview.WebViewCookieManager;

@Route(path = "/shopSdk/WebViewCookieManagerService")
public class WebViewCookieManagerServiceImpl implements WebViewCookieManagerService {
    public void init(Context context) {
    }

    public void a(Context context) {
        WebViewCookieManager.e(context);
    }

    public void b(Context context) {
        WebViewCookieManager.d(context);
    }

    public void c(Context context) {
        WebViewCookieManager.b(context);
    }

    public void d(Context context) {
        WebViewCookieManager.c(context);
    }
}
