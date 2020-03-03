package com.xiaomi.passport.ui.internal;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.accountsdk.account.XMPassportUserAgent;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.utils.WebViewDeviceIdUtil;
import com.xiaomi.accountsdk.utils.WebViewFidNonceUtil;
import com.xiaomi.accountsdk.utils.WebViewNativeUserAgentUtil;
import com.xiaomi.accountsdk.utils.WebViewUserSpaceIdUtil;
import com.xiaomi.accountsdk.utils.XMPassportUtil;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.snscorelib.internal.entity.PassportSnsConstant;
import com.xiaomi.passport.snscorelib.internal.utils.SNSCookieManager;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\u001c\u0010\u0013\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\b\u0010\r\u001a\u0004\u0018\u00010\bH\u0016J\u001a\u0010\u0015\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\bJ\u0012\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u0019\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u001a\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\u0006\u0010\r\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PassportWebView;", "Landroid/webkit/WebView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mCookieManager", "Landroid/webkit/CookieManager;", "originLoadUrl", "", "getExistedAccountInfo", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "loadUrl", "", "url", "onAuthEnd", "", "onLoginEnd", "accountInfo", "onNeedReLogin", "onPageFinished", "view", "onReceivedLoginRequest", "realm", "args", "onSnsBindCancel", "onSnsBindFinished", "shouldOverrideUrlLoading", "passportui_release"}, k = 1, mv = {1, 1, 10})
public class PassportWebView extends WebView {
    private HashMap _$_findViewCache;
    private final CookieManager mCookieManager;
    private String originLoadUrl;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public boolean onAuthEnd(@NotNull String str) {
        Intrinsics.f(str, "url");
        return false;
    }

    public boolean onLoginEnd(@NotNull AccountInfo accountInfo) {
        Intrinsics.f(accountInfo, "accountInfo");
        return false;
    }

    public boolean onNeedReLogin() {
        return false;
    }

    public void onPageFinished(@Nullable WebView webView, @Nullable String str) {
    }

    public boolean onSnsBindCancel(@Nullable AccountInfo accountInfo) {
        return false;
    }

    public boolean onSnsBindFinished(@Nullable AccountInfo accountInfo) {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PassportWebView(@NotNull Context context) {
        super(context);
        Intrinsics.f(context, "context");
        CookieSyncManager.createInstance(context);
        CookieManager instance = CookieManager.getInstance();
        Intrinsics.b(instance, "CookieManager.getInstance()");
        this.mCookieManager = instance;
        this.mCookieManager.removeAllCookie();
        WebSettings settings = getSettings();
        Intrinsics.b(settings, "settings");
        WebView webView = this;
        settings.setUserAgentString(XMPassportUserAgent.getWebViewUserAgent(webView, context));
        WebSettings settings2 = getSettings();
        Intrinsics.b(settings2, "settings");
        settings2.setJavaScriptEnabled(true);
        WebSettings settings3 = getSettings();
        Intrinsics.b(settings3, "settings");
        settings3.setBuiltInZoomControls(true);
        getSettings().setSupportZoom(true);
        setWebChromeClient(new WebChromeClient());
        setWebViewClient(new PassportWebViewClient(this));
        new WebViewDeviceIdUtil().setupDeviceIdForAccountWeb(webView);
        new WebViewFidNonceUtil().setupFidNonceForAccountWeb(webView);
        new WebViewUserSpaceIdUtil().setupUserSpaceIdForAccountWeb(webView);
        new WebViewNativeUserAgentUtil().setupUserAgentForAccountWeb(webView);
    }

    public final void onReceivedLoginRequest(@Nullable String str, @Nullable String str2) {
        if (Intrinsics.a((Object) "com.xiaomi", (Object) str)) {
            Source.Companion.from(new PassportWebView$onReceivedLoginRequest$1(MiAccountManager.get(getContext()).getServiceToken(getContext(), "weblogin:" + str2))).getSuccess(new PassportWebView$onReceivedLoginRequest$2(this));
        }
    }

    public boolean shouldOverrideUrlLoading(@Nullable WebView webView, @NotNull String str) {
        Intrinsics.f(str, "url");
        String cookie = this.mCookieManager.getCookie(URLs.ACCOUNT_DOMAIN);
        CharSequence charSequence = cookie;
        if (!TextUtils.isEmpty(charSequence)) {
            Intrinsics.b(cookie, "cookieStr");
            if (StringsKt.e(charSequence, (CharSequence) "sns-bind-step", false, 2, (Object) null)) {
                if (StringsKt.e(charSequence, (CharSequence) PassportSnsConstant.SNS_BIND_FINISH, false, 2, (Object) null)) {
                    AccountInfo existedAccountInfo = getExistedAccountInfo();
                    if (existedAccountInfo == null) {
                        existedAccountInfo = new AccountInfo.Builder().userId(SNSCookieManager.extractFromCookieString(cookie, "userId")).passToken(SNSCookieManager.extractFromCookieString(cookie, "passToken")).build();
                    }
                    if (onSnsBindFinished(existedAccountInfo)) {
                        return true;
                    }
                } else if (StringsKt.e(charSequence, (CharSequence) PassportSnsConstant.SNS_BIND_CANCEL, false, 2, (Object) null) && onSnsBindCancel(getExistedAccountInfo())) {
                    return true;
                }
            }
        }
        if (!TextUtils.isEmpty(charSequence)) {
            Intrinsics.b(cookie, "cookieStr");
            if (StringsKt.e(charSequence, (CharSequence) "passInfo", false, 2, (Object) null)) {
                if (StringsKt.e(charSequence, (CharSequence) "need-relogin", false, 2, (Object) null) && onNeedReLogin()) {
                    return true;
                }
                if (StringsKt.e(charSequence, (CharSequence) "login-end", false, 2, (Object) null)) {
                    AccountInfo build = new AccountInfo.Builder().userId(XMPassportUtil.extractUserIdFromNotificationLoginEndCookie(cookie)).passToken(XMPassportUtil.extractPasstokenFromNotificationLoginEndCookie(cookie)).build();
                    Intrinsics.b(build, "accountInfo");
                    if (onLoginEnd(build)) {
                        return true;
                    }
                }
                return StringsKt.e(charSequence, (CharSequence) "auth-end", false, 2, (Object) null) && onAuthEnd(str);
            }
        }
    }

    private final AccountInfo getExistedAccountInfo() {
        String passToken = AuthenticatorUtil.getPassToken(getContext());
        Account xiaomiAccount = AuthenticatorUtil.getXiaomiAccount(getContext());
        if (xiaomiAccount == null) {
            return null;
        }
        return new AccountInfo.Builder().userId(xiaomiAccount.name).passToken(passToken).build();
    }

    public void loadUrl(@NotNull String str) {
        Intrinsics.f(str, "url");
        this.originLoadUrl = str;
        super.loadUrl(str);
    }
}
