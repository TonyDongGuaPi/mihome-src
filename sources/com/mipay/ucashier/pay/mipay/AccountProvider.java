package com.mipay.ucashier.pay.mipay;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.mipay.sdk.IMipayAccountProvider;

public class AccountProvider implements IMipayAccountProvider {

    /* renamed from: a  reason: collision with root package name */
    private AccountManager f8185a;

    public AccountProvider(Context context) {
        this.f8185a = AccountManager.get(context);
    }

    public AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.f8185a.addAccount(str, str2, strArr, bundle, activity, accountManagerCallback, handler);
    }

    public Account[] getAccounts() {
        return this.f8185a.getAccounts();
    }

    public Account[] getAccountsByType(String str) {
        return this.f8185a.getAccountsByType(str);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.f8185a.getAuthToken(account, str, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.f8185a.getAuthToken(account, str, bundle, z, accountManagerCallback, handler);
    }

    public void invalidateAuthToken(String str, String str2) {
        this.f8185a.invalidateAuthToken(str, str2);
    }

    public boolean isUseSystem() {
        return true;
    }
}
