package com.mibi.sdk;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public interface IMibiAccountProvider {
    AccountManagerFuture<Bundle> a(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    AccountManagerFuture<Bundle> a(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    AccountManagerFuture<Bundle> a(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    void a(String str, String str2);

    Account[] a();

    Account[] a(String str);
}
