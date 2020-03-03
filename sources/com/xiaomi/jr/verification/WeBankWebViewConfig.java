package com.xiaomi.jr.verification;

import android.webkit.WebView;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.verification.sdk.WBH5FaceVerifySDK;
import com.xiaomi.jr.web.ObservableWebView;
import com.xiaomi.jr.web.WebFragment;
import com.xiaomi.jr.web.webkit.WebViewConfig;

public class WeBankWebViewConfig implements WebViewConfig {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1453a = "_wbfaceverify";

    public boolean a(String str) {
        return UrlUtils.a(str, f1453a, false);
    }

    public void a(WebFragment webFragment) {
        ObservableWebView l = webFragment.l();
        WBH5FaceVerifySDK.a().a((WebView) l, webFragment.getActivity().getApplicationContext());
        l.setWebChromeClient(new WeBankWebChromeClient(webFragment));
    }
}
