package com.xiaomi.accountsdk.utils;

import android.webkit.CookieManager;
import android.webkit.WebView;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;

public final class WebViewDeviceIdUtil extends AbstractAccountWebViewSingleCookieUtil {
    /* access modifiers changed from: protected */
    public String getCookieName() {
        return "deviceId";
    }

    public void setupDeviceIdForAccountWeb(WebView webView) {
        super.setupNonNullCookieForAccountWeb(webView);
    }

    public void setDeviceIdCookie(String str, CookieManager cookieManager) {
        super.setAccountCookie(cookieManager, str);
    }

    /* access modifiers changed from: protected */
    public String getCookieValue() {
        return new HashedDeviceIdUtil(XMPassportSettings.getApplicationContext()).getHashedDeviceIdNoThrow();
    }
}
