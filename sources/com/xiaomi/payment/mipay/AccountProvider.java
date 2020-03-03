package com.xiaomi.payment.mipay;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.mibi.common.account.AccountRegistry;
import com.mibi.common.account.IAccountProvider;
import com.mipay.sdk.IMipayAccountProvider;

public class AccountProvider implements IMipayAccountProvider {

    /* renamed from: a  reason: collision with root package name */
    private IAccountProvider f12345a = AccountRegistry.a();

    public AccountProvider(Context context) {
    }

    public boolean isUseSystem() {
        return this.f12345a.isUseSystem();
    }

    public Account[] getAccounts() {
        return this.f12345a.getAccounts();
    }

    public Account[] getAccountsByType(String str) {
        return this.f12345a.getAccountsByType(str);
    }

    public void invalidateAuthToken(String str, String str2) {
        this.f12345a.invalidateAuthToken(str, str2);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.f12345a.getAuthToken(account, str, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.f12345a.getAuthToken(account, str, bundle, z, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.f12345a.addAccount(str, str2, strArr, bundle, activity, accountManagerCallback, handler);
    }
}
