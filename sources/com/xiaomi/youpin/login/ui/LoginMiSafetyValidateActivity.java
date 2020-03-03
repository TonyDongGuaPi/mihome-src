package com.xiaomi.youpin.login.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.xiaomi.accountsdk.utils.XMPassportUtil;
import com.xiaomi.youpin.login.R;
import com.xiaomi.youpin.login.ui.web.LoginWebActivity;

public class LoginMiSafetyValidateActivity extends LoginWebActivity {
    public static final String ACTION_VALIDATE_COMPLETE = "action.mi.validate.complete";
    public static final String ACTION_VALIDATE_COMPLETE_PASSTOKEN = "passtoken";
    public static final String ACTION_VALIDATE_COMPLETE_RESULT = "result";
    public static final String ACTION_VALIDATE_COMPLETE_USERID = "userid";
    public static final String NOTIFICATION_URL = "common_web_url";

    /* renamed from: a  reason: collision with root package name */
    private static final String f23603a = "passInfo";
    private static final String b = "login-end";
    private static final String c = "need-relogin";
    private static final String d = "auth-end";
    /* access modifiers changed from: private */
    public String e;

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getApplicationContext());
        Intent intent = new Intent(ACTION_VALIDATE_COMPLETE);
        intent.putExtra("result", true);
        intent.putExtra("userid", str);
        intent.putExtra(ACTION_VALIDATE_COMPLETE_PASSTOKEN, str2);
        instance.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void a() {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getApplicationContext());
        Intent intent = new Intent(ACTION_VALIDATE_COMPLETE);
        intent.putExtra("result", false);
        instance.sendBroadcast(intent);
    }

    /* access modifiers changed from: protected */
    public boolean parseIntent(Intent intent) {
        if (!super.parseIntent(intent)) {
            return false;
        }
        this.e = intent.getStringExtra("common_web_url");
        if (TextUtils.isEmpty(this.e)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void initWebView() {
        if (Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().removeAllCookies((ValueCallback) null);
        } else {
            CookieManager.getInstance().removeAllCookie();
        }
        super.initWebView();
    }

    /* access modifiers changed from: protected */
    public WebViewClient getWebViewClient() {
        return new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                String cookie = CookieManager.getInstance().getCookie(str);
                if (TextUtils.isEmpty(cookie) || !cookie.contains(LoginMiSafetyValidateActivity.f23603a)) {
                    cookie = CookieManager.getInstance().getCookie(LoginMiSafetyValidateActivity.this.e);
                }
                if (!TextUtils.isEmpty(cookie) && cookie.contains(LoginMiSafetyValidateActivity.f23603a)) {
                    if (cookie.contains(LoginMiSafetyValidateActivity.c)) {
                        LoginMiSafetyValidateActivity.this.setResult(0);
                        LoginMiSafetyValidateActivity.this.finish();
                        Toast.makeText(LoginMiSafetyValidateActivity.this, R.string.milogin_validate_relogin, 0).show();
                        LoginMiSafetyValidateActivity.this.a();
                        return true;
                    } else if (cookie.contains(LoginMiSafetyValidateActivity.b)) {
                        Intent intent = new Intent();
                        intent.putExtra("location", str);
                        String extractUserIdFromNotificationLoginEndCookie = XMPassportUtil.extractUserIdFromNotificationLoginEndCookie(cookie);
                        String extractPasstokenFromNotificationLoginEndCookie = XMPassportUtil.extractPasstokenFromNotificationLoginEndCookie(cookie);
                        LoginMiSafetyValidateActivity.this.setResult(-1, intent);
                        LoginMiSafetyValidateActivity.this.finish();
                        if (TextUtils.isEmpty(extractPasstokenFromNotificationLoginEndCookie) || TextUtils.isEmpty(extractUserIdFromNotificationLoginEndCookie)) {
                            LoginMiSafetyValidateActivity.this.a();
                        } else {
                            LoginMiSafetyValidateActivity.this.a(extractUserIdFromNotificationLoginEndCookie, extractPasstokenFromNotificationLoginEndCookie);
                        }
                        return true;
                    } else if (cookie.contains(LoginMiSafetyValidateActivity.d)) {
                        LoginMiSafetyValidateActivity.this.setResult(-1, new Intent());
                        LoginMiSafetyValidateActivity.this.finish();
                        LoginMiSafetyValidateActivity.this.a();
                        return true;
                    }
                }
                webView.loadUrl(str);
                return true;
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onWebViewFinish() {
        a();
    }
}
