package com.xiaomi.youpin.login.ui.web;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.youpin.login.LoginDependencyApi;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.ui.baseui.ToastManager;

public class LoginAutoLoginBaseActivity extends LoginWebActivity {
    public static final int REQUEST_PERMISSION = 1000;

    /* renamed from: a  reason: collision with root package name */
    private boolean f23609a = false;
    protected String mAccountName;
    protected String mArgs;
    protected Context mContext;
    protected Handler mHandler;
    protected String mRealm;
    protected WebView mWebView;

    /* access modifiers changed from: protected */
    public boolean onOverrideUrlLoading(WebView webView, String str) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void initWebSettings(WebSettings webSettings) {
        super.initWebSettings(webSettings);
        String userAgentString = webSettings.getUserAgentString();
        String a2 = MiLoginApi.a().h().a(userAgentString);
        if (TextUtils.isEmpty(a2)) {
            webSettings.setUserAgentString(userAgentString + " XiaoMi/MiuiBrowser/4.3");
        } else {
            webSettings.setUserAgentString(a2);
        }
        webSettings.setUserAgentString(webSettings.getUserAgentString() + " MIOT");
        webSettings.setCacheMode(-1);
        if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 17) {
            this.vWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        }
    }

    /* access modifiers changed from: protected */
    public WebViewClient getWebViewClient() {
        return new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return LoginAutoLoginBaseActivity.this.onOverrideUrlLoading(webView, str) || super.shouldOverrideUrlLoading(webView, str);
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
            }

            public void onReceivedLoginRequest(final WebView webView, String str, String str2, String str3) {
                LoginAutoLoginBaseActivity.this.mWebView = webView;
                LoginAutoLoginBaseActivity.this.mRealm = str;
                LoginAutoLoginBaseActivity.this.mAccountName = str2;
                LoginAutoLoginBaseActivity.this.mArgs = str3;
                LoginDependencyApi h = MiLoginApi.a().h();
                if (h != null) {
                    h.a(webView, str, str2, str3, new LoginDependencyApi.OnReceivedLoginRequestCallback() {
                        public void a(Intent intent) {
                            LoginAutoLoginBaseActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                } else if ("com.xiaomi".equals(str)) {
                    MiAccountManager miAccountManager = MiAccountManager.get(LoginAutoLoginBaseActivity.this);
                    Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
                    if (accountsByType.length != 0) {
                        miAccountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, (Bundle) null, (Activity) LoginAutoLoginBaseActivity.this, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
                            /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
                            /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void run(android.accounts.AccountManagerFuture<android.os.Bundle> r2) {
                                /*
                                    r1 = this;
                                    java.lang.Object r2 = r2.getResult()     // Catch:{ OperationCanceledException -> 0x0017, IOException -> 0x0012, AuthenticatorException -> 0x000d, OperationCanceledException -> 0x001b }
                                    android.os.Bundle r2 = (android.os.Bundle) r2     // Catch:{ OperationCanceledException -> 0x0017, IOException -> 0x0012, AuthenticatorException -> 0x000d, OperationCanceledException -> 0x001b }
                                    java.lang.String r0 = "authtoken"
                                    java.lang.String r2 = r2.getString(r0)     // Catch:{ OperationCanceledException -> 0x0017, IOException -> 0x0012, AuthenticatorException -> 0x000d, OperationCanceledException -> 0x001b }
                                    goto L_0x001c
                                L_0x000d:
                                    r2 = move-exception
                                    r2.printStackTrace()
                                    goto L_0x001b
                                L_0x0012:
                                    r2 = move-exception
                                    r2.printStackTrace()
                                    goto L_0x001b
                                L_0x0017:
                                    r2 = move-exception
                                    r2.printStackTrace()
                                L_0x001b:
                                    r2 = 0
                                L_0x001c:
                                    if (r2 != 0) goto L_0x001f
                                    goto L_0x0024
                                L_0x001f:
                                    android.webkit.WebView r0 = r8
                                    r0.loadUrl(r2)
                                L_0x0024:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.ui.web.LoginAutoLoginBaseActivity.AnonymousClass1.AnonymousClass1.run(android.accounts.AccountManagerFuture):void");
                            }
                        }, (Handler) null);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        LoginDependencyApi h;
        super.onActivityResult(i, i2, intent);
        if (i != 1000) {
            return;
        }
        if (i2 == -1) {
            ToastManager.a((Context) this).a("申请权限成功");
            if (!this.f23609a && (h = MiLoginApi.a().h()) != null) {
                h.a(this.mWebView, this.mRealm, this.mAccountName, this.mArgs, new LoginDependencyApi.OnReceivedLoginRequestCallback() {
                    public void a(Intent intent) {
                        LoginAutoLoginBaseActivity.this.startActivityForResult(intent, 1000);
                    }
                });
                this.f23609a = true;
            }
        } else if (i2 == 0) {
            ToastManager.a((Context) this).a("取消申请权限，请手动输入小米帐号密码");
        } else {
            ToastManager.a((Context) this).a("申请权限失败，请手动输入小米帐号密码");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mWebView != null) {
            this.mWebView.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mWebView != null) {
            this.mWebView.onPause();
        }
    }
}
