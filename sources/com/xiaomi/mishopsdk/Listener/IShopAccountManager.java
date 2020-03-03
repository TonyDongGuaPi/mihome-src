package com.xiaomi.mishopsdk.Listener;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public interface IShopAccountManager {
    public static final int ADD_ACOUNT_REQUEST_CODE = 10223;
    public static final int LOGIN_TYPE_OAUTH = 3;
    public static final int LOGIN_TYPE_PASSPORT_APP = 2;
    public static final int LOGIN_TYPE_PASSPORT_SYSTEM = 1;

    AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    boolean canUseSystem();

    String getAccountAuthToken(String str);

    Account[] getAccounts();

    Account[] getAccountsByType(String str);

    AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler);

    String getEncryptedUserId();

    int getLoginType();

    String getUserId();

    void gotoAccount(Activity activity);

    void gotoLocalLoginUI(Activity activity, AccountManagerCallback<Bundle> accountManagerCallback);

    boolean hostAccountHasLogin();

    void invalidateAuthToken(String str, String str2);

    boolean isUseSystem();

    void onActivityResult(int i, int i2, Intent intent);

    void onLoginResult(boolean z);

    void setUseLocal();

    void setUseSystem();
}
