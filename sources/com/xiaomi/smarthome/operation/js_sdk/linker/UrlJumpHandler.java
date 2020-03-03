package com.xiaomi.smarthome.operation.js_sdk.linker;

import android.net.Uri;
import android.util.Log;
import android.webkit.WebView;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.intercept.IUrlInterceptorAdapter;

public class UrlJumpHandler extends IUrlInterceptorAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21085a = "UrlJumpHandler";

    public boolean a(WebView webView, String str) {
        try {
            return DeepLinkDelegate.a().a(Uri.parse(str));
        } catch (Exception e) {
            LogUtil.b(f21085a, "shouldOverrideUrlLoading: " + Log.getStackTraceString(e));
            return false;
        }
    }
}
