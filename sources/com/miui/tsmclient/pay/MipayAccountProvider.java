package com.miui.tsmclient.pay;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.mipay.sdk.IMipayAccountProvider;
import com.xiaomi.passport.accountmanager.MiAccountManager;

public class MipayAccountProvider implements IMipayAccountProvider {
    private MiAccountManager mMiAccountManager;

    public MipayAccountProvider(Context context) {
        this.mMiAccountManager = MiAccountManager.get(context);
    }

    public boolean isUseSystem() {
        return this.mMiAccountManager.isUseSystem();
    }

    public Account[] getAccounts() {
        return this.mMiAccountManager.getAccounts();
    }

    public Account[] getAccountsByType(String str) {
        return this.mMiAccountManager.getAccountsByType(str);
    }

    public void invalidateAuthToken(String str, String str2) {
        this.mMiAccountManager.invalidateAuthToken(str, str2);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mMiAccountManager.getAuthToken(account, str, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mMiAccountManager.getAuthToken(account, str, bundle, z, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mMiAccountManager.addAccount(str, str2, strArr, bundle, activity, accountManagerCallback, handler);
    }
}
