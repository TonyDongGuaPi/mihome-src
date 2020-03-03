package com.mipay.account;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.mibi.common.account.IAccountProvider;
import com.xiaomi.passport.accountmanager.MiAccountManager;

public class AccountProviderImpl implements IAccountProvider {
    private MiAccountManager mAccountManager;

    public AccountProviderImpl(Context context) {
        this.mAccountManager = MiAccountManager.get(context);
    }

    public boolean isUseSystem() {
        return this.mAccountManager.isUseSystem();
    }

    public Account[] getAccounts() {
        return this.mAccountManager.getAccounts();
    }

    public Account[] getAccountsByType(String str) {
        return this.mAccountManager.getAccountsByType(str);
    }

    public void invalidateAuthToken(String str, String str2) {
        this.mAccountManager.invalidateAuthToken(str, str2);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mAccountManager.getAuthToken(account, str, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mAccountManager.getAuthToken(account, str, bundle, z, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mAccountManager.addAccount(str, str2, strArr, bundle, activity, accountManagerCallback, handler);
    }
}
