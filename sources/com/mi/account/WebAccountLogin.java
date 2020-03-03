package com.mi.account;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.mi.log.LogUtil;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import java.lang.ref.WeakReference;

public class WebAccountLogin {
    private static final String c = "WebAccountLogin";

    /* renamed from: a  reason: collision with root package name */
    protected Activity f6702a;
    protected MiAccountManager b;
    private AccountManagerCallback<Bundle> d;

    public void a() {
    }

    public void a(String str) {
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }

    public WebAccountLogin(Activity activity) {
        this.f6702a = activity;
        this.b = MiAccountManager.get(activity);
        LoginManager b2 = LoginManager.b();
        if (b2 == null) {
            LogUtil.b(c, "LoginManager null.");
        } else if (b2.g()) {
            this.b.setUseSystem();
        } else {
            this.b.setUseLocal();
        }
        this.d = new LoginCallback(this);
    }

    public void a(String str, String str2, String str3) {
        Account account;
        Account[] accountsByType = this.b.getAccountsByType(str);
        if (accountsByType.length == 0) {
            c();
            return;
        }
        int i = 0;
        if (!TextUtils.isEmpty(str2)) {
            int length = accountsByType.length;
            while (true) {
                if (i >= length) {
                    account = null;
                    break;
                }
                Account account2 = accountsByType[i];
                if (account2.name.equals(str2)) {
                    account = account2;
                    break;
                }
                i++;
            }
        } else {
            account = accountsByType[0];
        }
        if (account != null) {
            a();
            this.b.getAuthToken(account, "weblogin:" + str3, (Bundle) null, (Activity) null, this.d, (Handler) null);
            return;
        }
        c();
    }

    private static class LoginCallback implements AccountManagerCallback<Bundle> {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<WebAccountLogin> f6703a;

        public LoginCallback(WebAccountLogin webAccountLogin) {
            this.f6703a = new WeakReference<>(webAccountLogin);
        }

        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            WebAccountLogin webAccountLogin = (WebAccountLogin) this.f6703a.get();
            if (webAccountLogin != null) {
                try {
                    String string = accountManagerFuture.getResult().getString("authtoken");
                    if (string == null) {
                        webAccountLogin.b();
                    } else {
                        webAccountLogin.a(string);
                    }
                } catch (Exception e) {
                    Log.e(WebAccountLogin.c, "Fail to login", e);
                    webAccountLogin.b();
                }
            }
        }
    }
}
