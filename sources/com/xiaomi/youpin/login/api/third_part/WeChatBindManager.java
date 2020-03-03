package com.xiaomi.youpin.login.api.third_part;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.LoginRouter;
import com.xiaomi.youpin.login.R;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.BaseAccount;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.third_part.LoginByThirdPartAccessTokenError;
import com.xiaomi.youpin.login.entity.wx.GetWXAccessTokenByAuthCodeResult;
import com.xiaomi.youpin.login.okhttpApi.LoginApiInternal;
import com.xiaomi.youpin.login.ui.baseui.ToastManager;
import com.xiaomi.youpin.login.ui.web.LoginWXBindMiWebActivity;

class WeChatBindManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f23500a = MiLoginApi.a().o();
    private IWXAPI b = MiLoginApi.a().n();
    private int c = MiLoginApi.a().g();
    private String d = MiLoginApi.a().d();
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public String f;
    private boolean g = false;
    private String h = "";
    /* access modifiers changed from: private */
    public AsyncCallback<Void, Error> i;

    public void a(String str, String str2, @NonNull AsyncCallback<Void, Error> asyncCallback) {
        this.e = str;
        this.f = str2;
        this.g = true;
        this.i = asyncCallback;
        a();
    }

    public void a(String str, String str2, String str3, @NonNull AsyncCallback<Void, Error> asyncCallback) {
        this.e = str;
        this.f = str2;
        this.g = false;
        this.h = str3;
        this.i = asyncCallback;
        a();
    }

    private void a() {
        final String str;
        if (!this.b.isWXAppInstalled()) {
            ToastManager.a(this.f23500a).a(R.string.milogin_wx_not_installed);
            if (this.i != null) {
                this.i.b(new Error(-7001, this.f23500a.getString(R.string.milogin_wx_not_installed)));
                return;
            }
            return;
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        if (this.c == 0) {
            str = "smarthome_" + System.currentTimeMillis();
        } else {
            str = "youpin_" + System.currentTimeMillis();
        }
        req.state = str;
        this.b.sendReq(req);
        LocalBroadcastManager.getInstance(this.f23500a.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(WeChatBindManager.this.f23500a.getApplicationContext()).unregisterReceiver(this);
                boolean booleanExtra = intent.getBooleanExtra("login_success", false);
                int intExtra = intent.getIntExtra("error_code", -1);
                String stringExtra = intent.getStringExtra("auth_code");
                String stringExtra2 = intent.getStringExtra("state");
                if (booleanExtra) {
                    WeChatBindManager.this.a(stringExtra, stringExtra2, str);
                    return;
                }
                switch (intExtra) {
                    case -6:
                        WeChatBindManager.this.i.b(new Error(LoginErrorCode.ag, ""));
                        return;
                    case -5:
                        WeChatBindManager.this.i.b(new Error(LoginErrorCode.af, ""));
                        return;
                    case -4:
                        WeChatBindManager.this.i.b(new Error(LoginErrorCode.ae, ""));
                        return;
                    case -3:
                        WeChatBindManager.this.i.b(new Error(LoginErrorCode.ad, ""));
                        return;
                    case -2:
                        WeChatBindManager.this.i.b(new Error(LoginErrorCode.ac, ""));
                        return;
                    case -1:
                        WeChatBindManager.this.i.b(new Error(-7001, ""));
                        return;
                    default:
                        WeChatBindManager.this.i.b(new Error(-7001, ""));
                        return;
                }
            }
        }, new IntentFilter("action.wx.login.complete"));
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            this.i.b(new Error(LoginErrorCode.ai, "AuthCode为空"));
        } else if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || str3.equalsIgnoreCase(str2)) {
            LoginApiInternal.a(this.c, str, new AsyncCallback<GetWXAccessTokenByAuthCodeResult, Error>() {
                public void a(GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult) {
                    WeChatBindManager.this.a(getWXAccessTokenByAuthCodeResult);
                }

                public void a(Error error) {
                    WeChatBindManager.this.i.b(new Error(LoginErrorCode.aj, "获取AccessToken失败"));
                }
            });
        } else {
            this.i.b(new Error(LoginErrorCode.ah, "Auth Code返回Data格式不合法"));
        }
    }

    /* access modifiers changed from: private */
    public void a(final GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult) {
        LoginApiInternal.a(this.c, MiLoginApi.a().d(), getWXAccessTokenByAuthCodeResult.f23524a, getWXAccessTokenByAuthCodeResult.b, getWXAccessTokenByAuthCodeResult.c, new AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError>() {
            public void a(BaseAccount baseAccount) {
                if (baseAccount == null || !TextUtils.equals(baseAccount.f23409a, WeChatBindManager.this.e)) {
                    WeChatBindManager.this.i.b(new Error(LoginErrorCode.ap, "该微信帐号被其它小米用户绑定"));
                } else {
                    WeChatBindManager.this.i.b(null);
                }
            }

            public void a(LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError) {
                WeChatBindManager.this.a(getWXAccessTokenByAuthCodeResult, loginByThirdPartAccessTokenError);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult, LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError) {
        if (loginByThirdPartAccessTokenError.f23523a != 1) {
            AsyncCallback<Void, Error> asyncCallback = this.i;
            asyncCallback.b(new Error(LoginErrorCode.an, "status not 1 " + loginByThirdPartAccessTokenError.b()));
            return;
        }
        if (this.g) {
            LoginRouter.a(this.f23500a, this.c, this.d, this.e, getWXAccessTokenByAuthCodeResult);
        } else {
            String str = loginByThirdPartAccessTokenError.b;
            if (TextUtils.isEmpty(str)) {
                this.i.b(new Error(LoginErrorCode.ao, "WebViewCallback is null"));
                return;
            }
            LoginRouter.a(this.f23500a, this.e, this.h, getWXAccessTokenByAuthCodeResult, str);
        }
        IntentFilter intentFilter = new IntentFilter(LoginWXBindMiWebActivity.ACTION_COMPLETE);
        LocalBroadcastManager.getInstance(this.f23500a.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(WeChatBindManager.this.f23500a.getApplicationContext()).unregisterReceiver(this);
                boolean booleanExtra = intent.getBooleanExtra("bind_success", false);
                boolean equals = TextUtils.equals(intent.getStringExtra("cUserId"), WeChatBindManager.this.f);
                if (!booleanExtra || !equals) {
                    if (WeChatBindManager.this.i != null) {
                        WeChatBindManager.this.i.b(new Error(-999, ""));
                    }
                } else if (WeChatBindManager.this.i != null) {
                    WeChatBindManager.this.i.b(null);
                }
            }
        }, intentFilter);
    }
}
