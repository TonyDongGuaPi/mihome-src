package com.xiaomi.youpin.login.api.third_part;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.LoginRouter;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.BaseAccount;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.third_part.LoginByThirdPartAccessTokenError;
import com.xiaomi.youpin.login.okhttpApi.LoginApiInternal;
import com.xiaomi.youpin.login.ui.web.LoginFBBindMiWebActivity;
import java.util.Collection;

class FbBindManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Activity f23486a;
    /* access modifiers changed from: private */
    public LoginManager b;
    /* access modifiers changed from: private */
    public CallbackManager c;
    private int d;
    private String e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public String g;
    private boolean h = false;
    private String i = "";
    /* access modifiers changed from: private */
    public AsyncCallback<Void, Error> j;

    public FbBindManager(Activity activity, CallbackManager callbackManager) {
        this.f23486a = activity;
        this.d = MiLoginApi.a().g();
        this.e = MiLoginApi.a().d();
        this.b = LoginManager.getInstance();
        this.c = callbackManager;
    }

    public void a(String str, String str2, AsyncCallback<Void, Error> asyncCallback) {
        this.f = str;
        this.g = str2;
        this.h = true;
        this.j = asyncCallback;
        a();
    }

    public void a(String str, String str2, String str3, AsyncCallback<Void, Error> asyncCallback) {
        this.f = str;
        this.g = str2;
        this.h = false;
        this.i = str3;
        this.j = asyncCallback;
        a();
    }

    private void a() {
        this.b.registerCallback(this.c, new FacebookCallback<LoginResult>() {
            /* renamed from: a */
            public void onSuccess(LoginResult loginResult) {
                FbBindManager.this.a(loginResult.getAccessToken());
                FbBindManager.this.b.logOut();
                FbBindManager.this.b.registerCallback(FbBindManager.this.c, (FacebookCallback<LoginResult>) null);
            }

            public void onCancel() {
                FbBindManager.this.b.registerCallback(FbBindManager.this.c, (FacebookCallback<LoginResult>) null);
                FbBindManager.this.j.b(new Error(LoginErrorCode.aC, "cancel"));
            }

            public void onError(FacebookException facebookException) {
                FbBindManager.this.b.registerCallback(FbBindManager.this.c, (FacebookCallback<LoginResult>) null);
                FbBindManager.this.j.b(new Error(LoginErrorCode.aD, facebookException.toString()));
            }
        });
        this.b.logInWithReadPermissions(this.f23486a, (Collection<String>) null);
    }

    /* access modifiers changed from: private */
    public void a(final AccessToken accessToken) {
        LoginApiInternal.a(this.d, MiLoginApi.a().d(), accessToken.getToken(), accessToken.getExpires().getTime(), (AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError>) new AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError>() {
            public void a(BaseAccount baseAccount) {
                if (TextUtils.equals(FbBindManager.this.f, baseAccount.f23409a)) {
                    FbBindManager.this.j.b(null);
                } else {
                    FbBindManager.this.j.b(new Error(LoginErrorCode.az, "该facebook帐号被其它小米用户绑定"));
                }
            }

            public void a(LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError) {
                FbBindManager.this.a(accessToken, loginByThirdPartAccessTokenError);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(AccessToken accessToken, LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError) {
        if (loginByThirdPartAccessTokenError.a() != -8003) {
            AsyncCallback<Void, Error> asyncCallback = this.j;
            asyncCallback.b(new Error(LoginErrorCode.aw, "返回Status异常 " + loginByThirdPartAccessTokenError.a()));
            return;
        }
        if (this.h) {
            LoginRouter.a((Context) this.f23486a, this.d, this.e, this.f, accessToken);
        } else {
            String str = loginByThirdPartAccessTokenError.b;
            if (TextUtils.isEmpty(str)) {
                this.j.b(new Error(LoginErrorCode.ay, "WebViewCallback is null"));
                return;
            }
            LoginRouter.a((Context) this.f23486a, this.f, this.i, str);
        }
        IntentFilter intentFilter = new IntentFilter(LoginFBBindMiWebActivity.ACTION_COMPLETE);
        LocalBroadcastManager.getInstance(this.f23486a.getApplicationContext()).registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LocalBroadcastManager.getInstance(FbBindManager.this.f23486a.getApplicationContext()).unregisterReceiver(this);
                boolean booleanExtra = intent.getBooleanExtra("bind_success", false);
                boolean equals = TextUtils.equals(intent.getStringExtra("cUserId"), FbBindManager.this.g);
                if (!booleanExtra || !equals) {
                    if (FbBindManager.this.j != null) {
                        FbBindManager.this.j.b(new Error(-999, ""));
                    }
                } else if (FbBindManager.this.j != null) {
                    FbBindManager.this.j.b(null);
                }
            }
        }, intentFilter);
    }
}
