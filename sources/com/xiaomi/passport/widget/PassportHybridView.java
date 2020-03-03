package com.xiaomi.passport.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.accountsdk.utils.ServerTimeUtil;
import com.xiaomi.accountsdk.utils.VersionUtils;
import com.xiaomi.accountsdk.utils.WebViewCookieUtil;
import com.xiaomi.accountsdk.utils.WebViewDeviceIdUtil;
import com.xiaomi.accountsdk.utils.WebViewFidNonceUtil;
import com.xiaomi.accountsdk.utils.WebViewNativeUserAgentUtil;
import com.xiaomi.accountsdk.utils.WebViewUserSpaceIdUtil;
import com.xiaomi.accountsdk.utils.XMPassportUtil;
import com.xiaomi.passport.uicontroller.PreConditions;
import java.util.Map;

public class PassportHybridView extends WebView {
    private static final String AUTH_END = "auth-end";
    private static final String BIND_PH_END = "bindph-end";
    private static final String LOGIN_END = "login-end";
    private static final String NEED_RELOGIN = "need-relogin";
    private static final String PASS_INFO = "passInfo";
    private String passportUrl;
    private final ServerTimeUtil.ServerTimeAlignedListener serverTimeAlignedListener = new WebViewFidNonceUtil.ServerTimeAlignedListenerImpl(this);

    public Map<String, String> extraAccountWebCookies() {
        return null;
    }

    public boolean needRemoveAllCookies() {
        return true;
    }

    public boolean onAuthEnd(String str) {
        return false;
    }

    public boolean onBindPHEnd() {
        return false;
    }

    public boolean onLoginEnd(String str, String str2) {
        return false;
    }

    public boolean onNeedReLogin() {
        return false;
    }

    public boolean onPageFinished(WebView webView, String str) {
        return false;
    }

    public boolean onPageStarted(WebView webView, String str, Bitmap bitmap) {
        return false;
    }

    public boolean onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        return false;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return false;
    }

    public PassportHybridView(Context context) {
        super(context);
    }

    public void loadUrl(String str) {
        this.passportUrl = str;
        String buildUrlWithLocaleQueryParam = XMPassportUtil.buildUrlWithLocaleQueryParam(this.passportUrl);
        if (needRemoveAllCookies()) {
            CookieSyncManager.createInstance(getContext());
            CookieManager.getInstance().removeAllCookie();
        }
        setWebSettings();
        addWebCookies(extraAccountWebCookies());
        setWebViewClient(new PassportWebViewClient(this));
        super.loadUrl(buildUrlWithLocaleQueryParam);
    }

    private void setWebSettings() {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        String userAgentString = settings.getUserAgentString();
        if (!TextUtils.isEmpty(userAgentString)) {
            settings.setUserAgentString(String.format("%s PassportSDK/PassportHybridView/%s XiaoMi/HybridView/", new Object[]{userAgentString, VersionUtils.getVersion()}));
        }
    }

    private void addWebCookies(Map<String, String> map) {
        new WebViewDeviceIdUtil().setupDeviceIdForAccountWeb(this);
        new WebViewFidNonceUtil().setupFidNonceForAccountWeb(this);
        new WebViewUserSpaceIdUtil().setupUserSpaceIdForAccountWeb(this);
        new WebViewNativeUserAgentUtil().setupUserAgentForAccountWeb(this);
        new WebViewCookieUtil().setupCookiesForAccountWeb(this, map);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ServerTimeUtil.addServerTimeChangedListener(this.serverTimeAlignedListener);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        ServerTimeUtil.removeServerTimeChangedListener(this.serverTimeAlignedListener);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: private */
    public boolean loadPassportUrl(WebView webView, String str) {
        String cookie = CookieManager.getInstance().getCookie(this.passportUrl);
        if (!TextUtils.isEmpty(cookie) && cookie.contains(PASS_INFO)) {
            if (cookie.contains(NEED_RELOGIN) && onNeedReLogin()) {
                return true;
            }
            if (cookie.contains(LOGIN_END)) {
                if (onLoginEnd(XMPassportUtil.extractUserIdFromNotificationLoginEndCookie(cookie), XMPassportUtil.extractPasstokenFromNotificationLoginEndCookie(cookie))) {
                    return true;
                }
            }
            if (cookie.contains(AUTH_END) && onAuthEnd(str)) {
                return true;
            }
            if (cookie.contains(BIND_PH_END) && onBindPHEnd()) {
                return true;
            }
        }
        return shouldOverrideUrlLoading(webView, str);
    }

    public static class PassportWebViewClient extends WebViewClient {
        private final PassportHybridView webView;

        public PassportWebViewClient(PassportHybridView passportHybridView) {
            PreConditions.checkArgumentNotNull(passportHybridView, "PassportWebView should not be null");
            this.webView = passportHybridView;
        }

        public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
            if (!this.webView.onPageStarted(webView2, str, bitmap)) {
                super.onPageStarted(webView2, str, bitmap);
            }
        }

        public void onPageFinished(WebView webView2, String str) {
            if (!this.webView.onPageFinished(webView2, str)) {
                super.onPageFinished(webView2, str);
            }
        }

        public void onReceivedLoginRequest(WebView webView2, String str, String str2, String str3) {
            if (!this.webView.onReceivedLoginRequest(webView2, str, str2, str3)) {
                super.onReceivedLoginRequest(webView2, str, str2, str3);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
            if (this.webView.shouldOverrideUrlLoading(webView2, str)) {
                return true;
            }
            return this.webView.loadPassportUrl(webView2, str);
        }
    }
}
