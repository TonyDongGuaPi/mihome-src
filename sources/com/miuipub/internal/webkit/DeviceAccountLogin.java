package com.miuipub.internal.webkit;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import java.lang.ref.WeakReference;

public class DeviceAccountLogin {
    private static final String c = "DeviceAccountLogin";

    /* renamed from: a  reason: collision with root package name */
    protected Activity f8314a;
    protected AccountManager b = AccountManager.get(this.f8314a.getApplicationContext());
    private AccountManagerCallback<Bundle> d = new LoginCallback(this);

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

    public DeviceAccountLogin(Activity activity) {
        this.f8314a = activity;
    }

    public void a(String str, String str2, String str3) {
        Account account;
        Account[] accountsByType = this.b.getAccountsByType(str);
        if (accountsByType.length == 0) {
            c();
            return;
        }
        Account account2 = null;
        int i = 0;
        if (!TextUtils.isEmpty(str2)) {
            int length = accountsByType.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                Account account3 = accountsByType[i];
                if (account3.name.equals(str2)) {
                    account = account3;
                    break;
                }
                i++;
            }
        } else {
            account2 = accountsByType[0];
        }
        account = account2;
        if (account != null) {
            a();
            this.b.getAuthToken(account, "weblogin:" + str3, (Bundle) null, (Activity) null, this.d, (Handler) null);
            return;
        }
        c();
    }

    private static class LoginCallback implements AccountManagerCallback<Bundle> {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<DeviceAccountLogin> f8315a;

        public LoginCallback(DeviceAccountLogin deviceAccountLogin) {
            this.f8315a = new WeakReference<>(deviceAccountLogin);
        }

        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            DeviceAccountLogin deviceAccountLogin = (DeviceAccountLogin) this.f8315a.get();
            if (deviceAccountLogin != null) {
                try {
                    String string = accountManagerFuture.getResult().getString("authtoken");
                    if (string == null) {
                        deviceAccountLogin.b();
                    } else {
                        deviceAccountLogin.a(string);
                    }
                } catch (Exception e) {
                    Log.e(DeviceAccountLogin.c, "Fail to login", e);
                    deviceAccountLogin.b();
                }
            }
        }
    }
}
