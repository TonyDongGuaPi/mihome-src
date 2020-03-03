package com.xiaomi.jr.web.webkit;

import android.text.TextUtils;
import android.webkit.WebView;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.web.WebFragment;

public class WebChromeClient extends android.webkit.WebChromeClient {
    private static final String b = "WebChromeClient";
    /* access modifiers changed from: protected */

    /* renamed from: a  reason: collision with root package name */
    public WebFragment f11083a;

    public WebChromeClient(WebFragment webFragment) {
        this.f11083a = webFragment;
    }

    public void onProgressChanged(WebView webView, int i) {
        if (i == 100) {
            this.f11083a.j().onPullDownRefreshComplete();
        }
        super.onProgressChanged(webView, i);
    }

    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        MifiLog.b(b, "onReceivedTitle: " + str);
        if ((str == null || !str.startsWith("http")) && TextUtils.isEmpty(this.f11083a.k())) {
            this.f11083a.a(str, (String) null);
        }
    }
}
