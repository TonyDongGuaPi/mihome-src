package com.xiaomi.accountsdk.utils;

import android.webkit.CookieManager;
import android.webkit.WebView;
import com.xiaomi.accountsdk.request.SimpleRequestForAccount;

public final class WebViewUserSpaceIdUtil extends AbstractAccountWebViewSingleCookieUtil {
    /* access modifiers changed from: protected */
    public String getCookieName() {
        return SimpleRequestForAccount.COOKIE_NAME_USER_SPACE_ID;
    }

    public void setupUserSpaceIdForAccountWeb(WebView webView) {
        super.setupNonNullCookieForAccountWeb(webView);
    }

    public void setupUserSpaceIdCookie(String str, CookieManager cookieManager) {
        super.setAccountCookie(cookieManager, str);
    }

    /* access modifiers changed from: protected */
    public String getCookieValue() {
        return UserSpaceIdUtil.getNullableUserSpaceIdCookie();
    }
}
