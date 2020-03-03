package com.xiaomi.youpin.login.ui.web;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.util.i;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.snscorelib.internal.entity.PassportSnsConstant;
import com.xiaomi.youpin.login.LoginDependencyApi;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.ui.baseui.ToastManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LoginBindBaseWebActivity extends LoginWebActivity {
    public static final String ARGS_OPENID = "args_openid";
    public static final String ARGS_PASSTOKEN = "args_passtoken";
    public static final String ARGS_SOURCE = "args_source";
    public static final String ARGS_TITLE = "common_web_title";
    public static final String ARGS_URL = "common_web_url";
    public static final String ARGS_USERID = "args_userid";
    public static final String BIND_SUCCESS = "bind_success";
    public static final String CUSER_ID = "cUserId";
    public static final String PASSTOKEN = "passToken";
    public static final int REQUEST_PERMISSION = 1000;
    public static final String SOURCE_BIND = "bind";
    public static final String SOURCE_LOGIN = "login";
    public static final String USER_ID = "userId";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Handler f23614a;
    private Context b;
    private String c;
    private String d;
    private String e;
    private String f;
    private boolean g = false;
    /* access modifiers changed from: private */
    public WebView h;
    /* access modifiers changed from: private */
    public String i;
    /* access modifiers changed from: private */
    public String j;
    /* access modifiers changed from: private */
    public String k;

    /* access modifiers changed from: protected */
    public abstract String getCompleteAction();

    @Nullable
    private static Map<String, String> a(String str) {
        String cookie = CookieManager.getInstance().getCookie(str);
        if (TextUtils.isEmpty(cookie)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String split : cookie.split(i.b)) {
            String[] split2 = split.split("=");
            if (split2.length > 1) {
                hashMap.put(split2[0].trim(), split2[1].trim());
            }
        }
        return hashMap;
    }

    private void a(@NonNull String str, String str2) {
        if (Build.VERSION.SDK_INT < 21) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            arrayList.add(str2);
            new CookieSetAsyncTask(CookieManager.getInstance()).execute(new List[]{arrayList});
            return;
        }
        b(str, str2);
    }

    @TargetApi(21)
    private void b(String str, String str2) {
        CookieManager.getInstance().setCookie(str, str2, (ValueCallback) null);
    }

    private void c(String str, String str2) {
        String cookie = CookieManager.getInstance().getCookie(str2);
        if (!TextUtils.isEmpty(cookie)) {
            String[] split = cookie.split(i.b);
            if (split.length > 0) {
                for (String trim : split) {
                    String[] split2 = trim.trim().split("=");
                    if (split2.length > 0 && !TextUtils.isEmpty(split2[0]) && split2[0].equals(str)) {
                        a(str2, split2[0] + "=EXPIRED; domain=" + str2 + "; expires=Thu, 01-Dec-1994 16:00:00 GMT");
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = this;
        this.f23614a = new Handler(Looper.getMainLooper());
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.f23614a.removeCallbacksAndMessages((Object) null);
        super.onDestroy();
    }

    private void a() {
        finish();
        sendCompleteBroadcast(false, (String) null, (String) null, (String) null);
    }

    private void b() {
        sendCompleteBroadcast(false, (String) null, (String) null, (String) null);
        finish();
    }

    /* access modifiers changed from: private */
    public void c() {
        Map<String, String> a2 = a("https://account.xiaomi.com");
        if (a2 != null && !a2.isEmpty()) {
            String str = a2.get("sns-bind-step");
            if (PassportSnsConstant.SNS_BIND_FINISH.equalsIgnoreCase(str)) {
                String str2 = a2.get("userId");
                String str3 = a2.get("cUserId");
                String str4 = a2.get("passToken");
                c("sns-bind-step", "https://account.xiaomi.com");
                if (this.d.equalsIgnoreCase("login")) {
                    if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str4)) {
                        sendCompleteBroadcast(false, (String) null, (String) null, (String) null);
                        finish();
                        return;
                    }
                    sendCompleteBroadcast(true, str2, "", str4);
                    finish();
                } else if (!this.d.equalsIgnoreCase(SOURCE_BIND)) {
                } else {
                    if (TextUtils.isEmpty(str3)) {
                        sendCompleteBroadcast(false, (String) null, (String) null, (String) null);
                        finish();
                        return;
                    }
                    sendCompleteBroadcast(true, "", str3, "");
                    finish();
                }
            } else if (PassportSnsConstant.SNS_BIND_CANCEL.equalsIgnoreCase(str)) {
                c("sns-bind-step", "https://account.xiaomi.com");
                a();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void sendCompleteBroadcast(boolean z, String str, String str2, String str3) {
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.b.getApplicationContext());
        Intent intent = new Intent(getCompleteAction());
        intent.putExtra("bind_success", z);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("userId", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            intent.putExtra("cUserId", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            intent.putExtra("passToken", str3);
        }
        instance.sendBroadcast(intent);
    }

    /* access modifiers changed from: protected */
    public boolean parseIntent(Intent intent) {
        if (!super.parseIntent(intent)) {
            return false;
        }
        this.c = intent.getStringExtra(ARGS_OPENID);
        this.d = intent.getStringExtra(ARGS_SOURCE);
        this.e = intent.getStringExtra(ARGS_PASSTOKEN);
        this.f = intent.getStringExtra(ARGS_USERID);
        return true;
    }

    /* access modifiers changed from: protected */
    public void initWebSettings(WebSettings webSettings) {
        super.initWebSettings(webSettings);
        String userAgentString = webSettings.getUserAgentString();
        webSettings.setUserAgentString(userAgentString + " XiaoMi/MiuiBrowser/4.3");
        webSettings.setCacheMode(-1);
        if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 17) {
            this.vWebView.removeJavascriptInterface("searchBoxJavaBridge_");
        }
    }

    /* access modifiers changed from: protected */
    public WebViewClient getWebViewClient() {
        return new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                if (TextUtils.isEmpty(str)) {
                    return true;
                }
                LoginBindBaseWebActivity.this.f23614a.post(new Runnable() {
                    public void run() {
                        LoginBindBaseWebActivity.this.c();
                    }
                });
                return true;
            }

            @TargetApi(24)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
            }

            public void onReceivedLoginRequest(final WebView webView, String str, String str2, String str3) {
                WebView unused = LoginBindBaseWebActivity.this.h = webView;
                String unused2 = LoginBindBaseWebActivity.this.i = str;
                String unused3 = LoginBindBaseWebActivity.this.j = str2;
                String unused4 = LoginBindBaseWebActivity.this.k = str3;
                LoginDependencyApi h = MiLoginApi.a().h();
                if (h != null) {
                    h.a(webView, str, str2, str3, new LoginDependencyApi.OnReceivedLoginRequestCallback() {
                        public void a(Intent intent) {
                            LoginBindBaseWebActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                } else if ("com.xiaomi".equals(str)) {
                    MiAccountManager miAccountManager = MiAccountManager.get(LoginBindBaseWebActivity.this);
                    Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
                    if (accountsByType.length != 0) {
                        miAccountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, (Bundle) null, (Activity) LoginBindBaseWebActivity.this, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
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
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.ui.web.LoginBindBaseWebActivity.AnonymousClass1.AnonymousClass2.run(android.accounts.AccountManagerFuture):void");
                            }
                        }, (Handler) null);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        LoginDependencyApi h2;
        super.onActivityResult(i2, i3, intent);
        if (i2 != 1000) {
            return;
        }
        if (i3 == -1) {
            ToastManager.a((Context) this).a("申请权限成功");
            if (!this.g && (h2 = MiLoginApi.a().h()) != null) {
                h2.a(this.h, this.i, this.j, this.k, new LoginDependencyApi.OnReceivedLoginRequestCallback() {
                    public void a(Intent intent) {
                        LoginBindBaseWebActivity.this.startActivityForResult(intent, 1000);
                    }
                });
                this.g = true;
            }
        } else if (i3 == 0) {
            ToastManager.a((Context) this).a("取消申请权限，请手动输入小米帐号密码");
        } else {
            ToastManager.a((Context) this).a("申请权限失败，请手动输入小米帐号密码");
        }
    }

    /* access modifiers changed from: protected */
    public void onWebViewFinish() {
        b();
    }

    private static class CookieSetAsyncTask extends AsyncTask<List<String>, Void, Void> {

        /* renamed from: a  reason: collision with root package name */
        private CookieManager f23620a;

        public CookieSetAsyncTask(CookieManager cookieManager) {
            this.f23620a = cookieManager;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(List<String>... listArr) {
            List<String> list = listArr[0];
            String str = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                this.f23620a.setCookie(str, list.get(i));
            }
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.h != null) {
            this.h.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.h != null) {
            this.h.onPause();
        }
    }
}
