package com.xiaomi.smarthome.framework.login.ui;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.snscorelib.SNSManager;
import com.xiaomi.passport.snscorelib.internal.entity.SNSBindParameter;
import com.xiaomi.passport.snscorelib.internal.entity.SNSLoginParameter;
import com.xiaomi.passport.snscorelib.internal.utils.SNSCookieManager;
import com.xiaomi.passport.ui.internal.SnsWebLoginBaseFragment;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.webview.SmartHomeWebView;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

public class BindFbActivity extends BaseActivity {
    public static final String JUMP_MJ = "bindwx_jump_mj";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public SmartHomeWebView f16565a;
    private Context b;
    private boolean c = true;
    private boolean d = false;
    private Handler e;
    /* access modifiers changed from: private */
    public LoginManager f;
    /* access modifiers changed from: private */
    public XQProgressDialog g;
    CallbackManager mFBCallbackManager;
    WebViewClient mWebViewClient = new WebViewClient() {
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String path = Uri.parse(SNSManager.URL_SNS_BIND_CANCLED).getPath();
            String path2 = Uri.parse(SNSManager.URL_SNS_BIND_FINISH).getPath();
            String path3 = Uri.parse(str).getPath();
            boolean equals = path2.equals(path3);
            boolean equals2 = path.equals(path3);
            if (equals) {
                BindFbActivity.this.setResult(-1);
                BindFbActivity.this.finish();
                return true;
            } else if (!equals2) {
                return false;
            } else {
                BindFbActivity.this.setResult(0);
                BindFbActivity.this.finish();
                return true;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if ("com.xiaomi".equals(str)) {
                MiAccountManager miAccountManager = MiAccountManager.get(BindFbActivity.this);
                Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
                if (accountsByType.length != 0) {
                    miAccountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, (Bundle) null, (Activity) BindFbActivity.this, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
                        /* JADX WARNING: Removed duplicated region for block: B:10:0x001f  */
                        /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public void run(android.accounts.AccountManagerFuture<android.os.Bundle> r2) {
                            /*
                                r1 = this;
                                java.lang.Object r2 = r2.getResult()     // Catch:{ OperationCanceledException -> 0x0017, IOException -> 0x0012, AuthenticatorException -> 0x000d }
                                android.os.Bundle r2 = (android.os.Bundle) r2     // Catch:{ OperationCanceledException -> 0x0017, IOException -> 0x0012, AuthenticatorException -> 0x000d }
                                java.lang.String r0 = "authtoken"
                                java.lang.String r2 = r2.getString(r0)     // Catch:{ OperationCanceledException -> 0x0017, IOException -> 0x0012, AuthenticatorException -> 0x000d }
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
                                goto L_0x002a
                            L_0x001f:
                                com.xiaomi.smarthome.framework.login.ui.BindFbActivity$6 r0 = com.xiaomi.smarthome.framework.login.ui.BindFbActivity.AnonymousClass6.this
                                com.xiaomi.smarthome.framework.login.ui.BindFbActivity r0 = com.xiaomi.smarthome.framework.login.ui.BindFbActivity.this
                                com.xiaomi.smarthome.framework.webview.SmartHomeWebView r0 = r0.f16565a
                                r0.loadUrl(r2)
                            L_0x002a:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.login.ui.BindFbActivity.AnonymousClass6.AnonymousClass1.run(android.accounts.AccountManagerFuture):void");
                        }
                    }, (Handler) null);
                }
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cs_binwx_acitivity);
        this.d = getIntent().getBooleanExtra(JUMP_MJ, true);
        this.e = new Handler();
        a();
    }

    private void a() {
        this.f16565a = (SmartHomeWebView) findViewById(R.id.webview);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BindFbActivity.this.finish();
            }
        });
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        b();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f16565a != null) {
            this.f16565a.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.f16565a != null) {
            this.f16565a.onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.c) {
            this.c = false;
        }
    }

    private void b() {
        this.g = new XQProgressDialog(this);
        this.g.setCancelable(true);
        this.g.setMessage(getString(R.string.account_binding_waiting));
        this.g.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                BindFbActivity.this.finish();
            }
        });
        if (!isFinishing()) {
            this.g.show();
        }
        this.e.postDelayed(new Runnable() {
            public void run() {
                BindFbActivity.this.g.dismiss();
            }
        }, 3000);
        if (this.f == null) {
            this.f = LoginManager.getInstance();
        }
        this.mFBCallbackManager = CallbackManager.Factory.create();
        this.b = MiLoginApi.a().o();
        this.f.registerCallback(this.mFBCallbackManager, new FacebookCallback<LoginResult>() {
            /* renamed from: a */
            public void onSuccess(LoginResult loginResult) {
                BindFbActivity.this.a(loginResult.getAccessToken());
                BindFbActivity.this.f.logOut();
                BindFbActivity.this.f.registerCallback(BindFbActivity.this.mFBCallbackManager, (FacebookCallback<LoginResult>) null);
            }

            public void onCancel() {
                BindFbActivity.this.f.registerCallback(BindFbActivity.this.mFBCallbackManager, (FacebookCallback<LoginResult>) null);
            }

            public void onError(FacebookException facebookException) {
                BindFbActivity.this.f.registerCallback(BindFbActivity.this.mFBCallbackManager, (FacebookCallback<LoginResult>) null);
            }
        });
        this.f.logInWithReadPermissions((Activity) this, (Collection<String>) Arrays.asList(new String[]{"public_profile"}));
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.g != null && this.g.isShowing()) {
            this.g.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        this.mFBCallbackManager.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: private */
    public void a(AccessToken accessToken) {
        String str;
        SNSManager sNSManager = new SNSManager(this);
        SNSLoginParameter build = new SNSLoginParameter.Builder().sid(MiLoginApi.a().d()).appid(MiLoginApi.a().f()).token(accessToken.getToken()).expires_in(String.valueOf(accessToken.getExpires().getTime())).build();
        LoginMiAccount y = CoreApi.a().y();
        if (y == null) {
            str = "";
        } else {
            str = y.c();
        }
        if (TextUtils.isEmpty(str)) {
            str = b(SHApplication.getAppContext());
        }
        final AccountInfo build2 = new AccountInfo.Builder().userId(CoreApi.a().s()).encryptedUserId(CoreApi.a().u()).passToken(str).build();
        sNSManager.snsBindByAccountInfo(build, build2, new SNSManager.SNSBindByAccountCallback() {
            /* access modifiers changed from: protected */
            public void onSNSBindFailed(int i, String str) {
                if (GlobalSetting.q) {
                    ToastUtil.a((CharSequence) "2131493063," + i + "," + str, 0);
                } else {
                    ToastUtil.a((int) R.string.account_bind_failed, 0);
                }
                BindFbActivity.this.setResult(0);
                BindFbActivity.this.finish();
            }

            /* access modifiers changed from: protected */
            public void onNetWorkErrorException() {
                ToastUtil.a((int) R.string.status_error_cable_not_plugin_body, 0);
                BindFbActivity.this.setResult(0);
                BindFbActivity.this.finish();
            }

            /* access modifiers changed from: protected */
            public void onImplementSNSBind(SNSBindParameter sNSBindParameter) {
                BindFbActivity.this.f16565a.setWebViewClient(BindFbActivity.this.mWebViewClient);
                String access$400 = BindFbActivity.this.a((Context) BindFbActivity.this);
                WebSettings settings = BindFbActivity.this.f16565a.getSettings();
                settings.setUserAgentString(access$400 + " AndroidSnsSDK/" + "1.0" + " XiaoMi/MiuiBrowser/4.3");
                BindFbActivity.this.f16565a.getSettings().setJavaScriptEnabled(true);
                HashMap hashMap = new HashMap();
                hashMap.put("userId", build2.userId);
                hashMap.put("cUserId", build2.encryptedUserId);
                hashMap.put(SnsWebLoginBaseFragment.SNS_TOKEN_PH, sNSBindParameter.sns_token_ph);
                hashMap.put(SnsWebLoginBaseFragment.SNS_WEIXIN_OPENID, sNSBindParameter.sns_weixin_openId);
                SNSCookieManager.setupCookiesForAccountWeb(BindFbActivity.this.f16565a, hashMap);
                BindFbActivity.this.f16565a.loadUrl(sNSBindParameter.snsBindUrl);
                String access$500 = BindFbActivity.this.a(LocaleUtil.a());
                SmartHomeWebView access$300 = BindFbActivity.this.f16565a;
                access$300.loadUrl(sNSBindParameter.snsBindUrl + "&_locale=" + access$500);
            }
        });
    }

    /* access modifiers changed from: private */
    public String a(Locale locale) {
        if (locale == null) {
            return "zh_CN";
        }
        String language = locale.getLanguage();
        String country = locale.getCountry();
        if (TextUtils.isEmpty(country)) {
            return language;
        }
        return String.format("%s_%s", new Object[]{language, country});
    }

    /* access modifiers changed from: private */
    public String a(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return this.f16565a.getSettings().getUserAgentString();
        }
        return WebSettings.getDefaultUserAgent(context);
    }

    private String b(Context context) {
        ExtendedAuthToken parse;
        String password = MiAccountManager.get(context).getPassword(MiAccountManager.get(context).getXiaomiAccount());
        if (!TextUtils.isEmpty(password) && (parse = ExtendedAuthToken.parse(password)) != null) {
            return parse.authToken;
        }
        return null;
    }
}
