package com.xiaomi.mishopsdk.account.adapter;

import android.accounts.AccountManagerCallback;
import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.passport.accountmanager.MiAccountManager;

public class AppAccountManager extends ShopAccountManager {
    private static final String TAG = "HostAccountManager";

    public int getLoginType() {
        return 2;
    }

    public AppAccountManager(Application application) {
        super(application);
    }

    public boolean hostAccountHasLogin() {
        return LoginManager.getInstance().hasLogin();
    }

    public void gotoLocalLoginUI(Activity activity, AccountManagerCallback<Bundle> accountManagerCallback) {
        LoginManager.getInstance().setSystemLogin(false);
        MiAccountManager miAccountManager = MiAccountManager.get(ShopApp.instance);
        miAccountManager.setUseLocal();
        miAccountManager.addAccount("com.xiaomi", "eshopmobile", (String[]) null, new Bundle(), (Activity) null, accountManagerCallback, (Handler) null);
    }
}
