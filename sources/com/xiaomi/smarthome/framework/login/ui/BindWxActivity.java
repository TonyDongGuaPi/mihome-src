package com.xiaomi.smarthome.framework.login.ui;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
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
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.wx.GetWXAccessTokenByAuthCodeResult;
import com.xiaomi.youpin.login.okhttpApi.LoginApiInternal;
import java.util.HashMap;
import java.util.Locale;

public class BindWxActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public WebView f16573a;
    /* access modifiers changed from: private */
    public Context b;
    private IWXAPI c;
    final BroadcastReceiver callbackReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            final String s = CoreApi.a().s();
            final String u = CoreApi.a().u();
            final String l = MiLoginApi.a().l();
            if (BindWxActivity.this.d) {
                LocalBroadcastManager.getInstance(BindWxActivity.this.b).unregisterReceiver(this);
                boolean unused = BindWxActivity.this.d = false;
            }
            boolean booleanExtra = intent.getBooleanExtra("login_success", false);
            String stringExtra = intent.getStringExtra("auth_code");
            if (booleanExtra) {
                BindWxActivity.this.c();
                LoginApiInternal.a(0, stringExtra, new AsyncCallback<GetWXAccessTokenByAuthCodeResult, Error>("xiaomiio") {
                    public void a(GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult) {
                        String str;
                        String str2 = getWXAccessTokenByAuthCodeResult.b;
                        LoginMiAccount y = CoreApi.a().y();
                        if (y == null) {
                            str = "";
                        } else {
                            str = y.c();
                        }
                        SNSManager sNSManager = new SNSManager(BindWxActivity.this);
                        if (TextUtils.isEmpty(str)) {
                            str = BindWxActivity.this.b(SHApplication.getAppContext());
                        }
                        if (!TextUtils.isEmpty(str)) {
                            SNSLoginParameter build = new SNSLoginParameter.Builder().sid("xiaomiio").appid(l).enToken(getWXAccessTokenByAuthCodeResult.f23524a).expires_in(String.valueOf(getWXAccessTokenByAuthCodeResult.c)).openId(str2).build();
                            final AccountInfo build2 = new AccountInfo.Builder().userId(s).encryptedUserId(u).passToken(str).build();
                            sNSManager.snsBindByAccountInfo(build, build2, new SNSManager.SNSBindByAccountCallback() {
                                /* access modifiers changed from: protected */
                                public void onSNSBindFailed(int i, String str) {
                                    if (BindWxActivity.this.mHandler != null) {
                                        BindWxActivity.this.mHandler.sendEmptyMessage(101);
                                    }
                                }

                                /* access modifiers changed from: protected */
                                public void onNetWorkErrorException() {
                                    if (BindWxActivity.this.mHandler != null) {
                                        BindWxActivity.this.mHandler.sendEmptyMessage(101);
                                    }
                                }

                                /* access modifiers changed from: protected */
                                public void onImplementSNSBind(SNSBindParameter sNSBindParameter) {
                                    BindWxActivity.this.f16573a.setWebViewClient(BindWxActivity.this.mWebViewClient);
                                    String access$500 = BindWxActivity.this.a((Context) BindWxActivity.this);
                                    WebSettings settings = BindWxActivity.this.f16573a.getSettings();
                                    settings.setUserAgentString(access$500 + " AndroidSnsSDK/" + "1.0" + " XiaoMi/MiuiBrowser/4.3");
                                    BindWxActivity.this.f16573a.getSettings().setJavaScriptEnabled(true);
                                    HashMap hashMap = new HashMap();
                                    hashMap.put("userId", build2.userId);
                                    hashMap.put("cUserId", build2.encryptedUserId);
                                    hashMap.put(SnsWebLoginBaseFragment.SNS_TOKEN_PH, sNSBindParameter.sns_token_ph);
                                    hashMap.put(SnsWebLoginBaseFragment.SNS_WEIXIN_OPENID, sNSBindParameter.sns_weixin_openId);
                                    SNSCookieManager.setupCookiesForAccountWeb(BindWxActivity.this.f16573a, hashMap);
                                    BindWxActivity.this.f16573a.loadUrl(sNSBindParameter.snsBindUrl);
                                    String access$600 = BindWxActivity.this.a(LocaleUtil.a());
                                    WebView access$400 = BindWxActivity.this.f16573a;
                                    access$400.loadUrl(sNSBindParameter.snsBindUrl + "&_locale=" + access$600);
                                }
                            });
                        } else if (BindWxActivity.this.mHandler != null) {
                            BindWxActivity.this.mHandler.sendEmptyMessage(101);
                        }
                    }

                    public void a(Error error) {
                        if (BindWxActivity.this.mHandler != null) {
                            BindWxActivity.this.mHandler.sendEmptyMessage(101);
                        }
                    }
                });
            } else if (BindWxActivity.this.mHandler != null) {
                BindWxActivity.this.mHandler.sendEmptyMessage(101);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean d = true;
    private final int e = 101;
    private final int f = 102;
    private final int g = 103;
    private final int h = 104;
    private XQProgressDialog i = null;
    WebViewClient mWebViewClient = new WebViewClient() {
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (BindWxActivity.this.mHandler != null) {
                BindWxActivity.this.mHandler.sendEmptyMessage(104);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            String path = Uri.parse(SNSManager.URL_SNS_BIND_CANCLED).getPath();
            String path2 = Uri.parse(SNSManager.URL_SNS_BIND_FINISH).getPath();
            String path3 = Uri.parse(str).getPath();
            boolean equals = path2.equals(path3);
            boolean equals2 = path.equals(path3);
            if (equals) {
                if (BindWxActivity.this.mHandler != null) {
                    BindWxActivity.this.mHandler.sendEmptyMessage(102);
                }
                return true;
            } else if (!equals2) {
                return false;
            } else {
                if (BindWxActivity.this.mHandler != null) {
                    BindWxActivity.this.mHandler.sendEmptyMessage(103);
                }
                return true;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }

        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if ("com.xiaomi".equals(str)) {
                MiAccountManager miAccountManager = MiAccountManager.get(BindWxActivity.this);
                Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
                if (accountsByType.length != 0) {
                    miAccountManager.getAuthToken(accountsByType[0], "weblogin:" + str3, (Bundle) null, (Activity) BindWxActivity.this, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
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
                                com.xiaomi.smarthome.framework.login.ui.BindWxActivity$3 r0 = com.xiaomi.smarthome.framework.login.ui.BindWxActivity.AnonymousClass3.this
                                com.xiaomi.smarthome.framework.login.ui.BindWxActivity r0 = com.xiaomi.smarthome.framework.login.ui.BindWxActivity.this
                                android.webkit.WebView r0 = r0.f16573a
                                r0.loadUrl(r2)
                            L_0x002a:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.login.ui.BindWxActivity.AnonymousClass3.AnonymousClass1.run(android.accounts.AccountManagerFuture):void");
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
        a();
    }

    private void a() {
        this.f16573a = (WebView) findViewById(R.id.webview);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BindWxActivity.this.finish();
            }
        });
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        b();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 101:
                ToastManager.a().a((int) R.string.account_bind_failed);
                finish();
                return;
            case 102:
                setResult(-1);
                finish();
                return;
            case 103:
                finish();
                return;
            case 104:
                d();
                return;
            default:
                return;
        }
    }

    private void b() {
        this.b = MiLoginApi.a().o();
        this.c = MiLoginApi.a().n();
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "smarthome_" + System.currentTimeMillis();
        this.c.sendReq(req);
        if (!this.c.isWXAppInstalled()) {
            ToastManager.a().a((int) R.string.wx_not_installed);
            finish();
            return;
        }
        LocalBroadcastManager.getInstance(this.b).registerReceiver(this.callbackReceiver, new IntentFilter("action.wx.login.complete"));
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
            return this.f16573a.getSettings().getUserAgentString();
        }
        return WebSettings.getDefaultUserAgent(context);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f16573a != null) {
            this.f16573a.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.f16573a != null) {
            this.f16573a.onPause();
        }
    }

    /* access modifiers changed from: private */
    public String b(Context context) {
        ExtendedAuthToken parse;
        try {
            String password = MiAccountManager.get(context).getPassword(MiAccountManager.get(context).getXiaomiAccount());
            if (!TextUtils.isEmpty(password) && (parse = ExtendedAuthToken.parse(password)) != null) {
                return parse.authToken;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        this.i = new XQProgressDialog(this);
        this.i.setCancelable(true);
        this.i.setMessage(getString(R.string.account_binding_waiting));
        if (!isFinishing()) {
            this.i.show();
        }
    }

    private void d() {
        if (this.i != null && this.i.isShowing()) {
            this.i.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.d) {
            LocalBroadcastManager.getInstance(this.b).unregisterReceiver(this.callbackReceiver);
            this.d = false;
        }
        d();
        this.mHandler = null;
    }
}
