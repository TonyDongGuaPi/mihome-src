package com.xiaomi.jr.account;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;

public interface IAccountProvider {
    public static final String b = "serviceToken";
    public static final String c = "security";
    public static final String d = "slh";
    public static final String e = "ph";
    public static final String f = "cUserId";
    public static final String g = "intent";
    public static final String h = "errorCode";
    public static final String i = "errorMessage";
    public static final String p_ = "sid";

    AccountManagerFuture<Bundle> a(Activity activity, AccountManagerCallback<Bundle> accountManagerCallback);

    AccountManagerFuture<Bundle> a(String str);

    void a(Account account, AccountManagerCallback<Boolean> accountManagerCallback);

    void a(Bundle bundle);

    boolean a();

    boolean b();

    Account c();
}
