package com.xiaomi.passport.accountmanager;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OnAccountsUpdateListener;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.account.data.BaseConstants;
import com.xiaomi.accountsdk.futureservice.MiAccountManagerFuture;
import com.xiaomi.accountsdk.utils.MiuiOsBuildReflection;
import com.xiaomi.accountsdk.utils.MiuiVersionStable;
import com.xiaomi.passport.servicetoken.IServiceTokenUtil;
import com.xiaomi.passport.servicetoken.ServiceTokenFuture;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.passport.servicetoken.ServiceTokenUtilFacade;
import com.xiaomi.passport.servicetoken.data.XmAccountVisibility;
import java.io.IOException;

class SystemAccountManagerAdapter implements IAccountManagerInternal {
    private final Context applicationContext;
    private AccountManager mAccountManager;
    private final IServiceTokenUtil mServiceTokenUtil;

    public SystemAccountManagerAdapter(Context context) {
        IServiceTokenUtil iServiceTokenUtil;
        this.mAccountManager = AccountManager.get(context);
        this.applicationContext = context.getApplicationContext();
        boolean z = !isXiaomiAccountApp(context);
        if (z && MiuiOsBuildReflection.isStable(false) && MiuiVersionStable.earlyThan(new MiuiVersionStable(8, 0), false) && context.getPackageManager().checkSignatures(context.getPackageName(), AccountIntent.PACKAGE_XIAOMI_ACCOUNT) == 0) {
            z = false;
        }
        ServiceTokenUtilFacade instance = ServiceTokenUtilFacade.getInstance();
        if (z) {
            iServiceTokenUtil = instance.buildMiuiServiceTokenUtil();
        } else {
            iServiceTokenUtil = instance.buildAMServiceTokenUtil(instance.buildAMUtil());
        }
        this.mServiceTokenUtil = iServiceTokenUtil;
    }

    public AuthenticatorDescription[] getAuthenticatorTypes() {
        return this.mAccountManager.getAuthenticatorTypes();
    }

    public String getPassword(Account account) {
        return this.mAccountManager.getPassword(account);
    }

    public String getUserData(Account account, String str) {
        return this.mAccountManager.getUserData(account, str);
    }

    public Account[] getAccounts() {
        return this.mAccountManager.getAccounts();
    }

    public Account[] getAccountsByType(String str) {
        return this.mAccountManager.getAccountsByType(str);
    }

    public AccountManagerFuture<Boolean> hasFeatures(Account account, String[] strArr, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        return this.mAccountManager.hasFeatures(account, strArr, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Account[]> getAccountsByTypeAndFeatures(String str, String[] strArr, AccountManagerCallback<Account[]> accountManagerCallback, Handler handler) {
        return this.mAccountManager.getAccountsByTypeAndFeatures(str, strArr, accountManagerCallback, handler);
    }

    public boolean addAccountExplicitly(Account account, String str, Bundle bundle) {
        return this.mAccountManager.addAccountExplicitly(account, str, bundle);
    }

    public AccountManagerFuture<Boolean> removeAccount(Account account, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        return this.mAccountManager.removeAccount(account, accountManagerCallback, handler);
    }

    public void invalidateAuthToken(String str, String str2) {
        this.mAccountManager.invalidateAuthToken(str, str2);
    }

    public String peekAuthToken(Account account, String str) {
        return this.mAccountManager.peekAuthToken(account, str);
    }

    public void setPassword(Account account, String str) {
        this.mAccountManager.setPassword(account, str);
    }

    public void clearPassword(Account account) {
        this.mAccountManager.clearPassword(account);
    }

    public void setUserData(Account account, String str, String str2) {
        this.mAccountManager.setUserData(account, str, str2);
    }

    public void setAuthToken(Account account, String str, String str2) {
        this.mAccountManager.setAuthToken(account, str, str2);
    }

    public String blockingGetAuthToken(Account account, String str, boolean z) throws OperationCanceledException, IOException, AuthenticatorException {
        return this.mAccountManager.blockingGetAuthToken(account, str, z);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (!shouldUseGetAuthTokenVer2(bundle, accountManagerCallback, handler) || activity != null) {
            return this.mAccountManager.getAuthToken(account, str, bundle, activity, accountManagerCallback, handler);
        }
        return AMFutureConverter.fromServiceTokenFuture(getServiceToken(this.applicationContext, str));
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (!shouldUseGetAuthTokenVer2(bundle, accountManagerCallback, handler) || z) {
            return this.mAccountManager.getAuthToken(account, str, bundle, z, accountManagerCallback, handler);
        }
        return AMFutureConverter.fromServiceTokenFuture(getServiceToken(this.applicationContext, str));
    }

    public AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mAccountManager.addAccount(str, str2, strArr, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> confirmCredentials(Account account, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mAccountManager.confirmCredentials(account, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> updateCredentials(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mAccountManager.updateCredentials(account, str, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> editProperties(String str, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mAccountManager.editProperties(str, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthTokenByFeatures(String str, String str2, String[] strArr, Activity activity, Bundle bundle, Bundle bundle2, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mAccountManager.getAuthTokenByFeatures(str, str2, strArr, activity, bundle, bundle2, accountManagerCallback, handler);
    }

    public void addOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener, Handler handler, boolean z) {
        this.mAccountManager.addOnAccountsUpdatedListener(onAccountsUpdateListener, handler, z);
    }

    public void removeOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener) {
        this.mAccountManager.removeOnAccountsUpdatedListener(onAccountsUpdateListener);
    }

    public ServiceTokenFuture getServiceToken(Context context, String str) {
        return this.mServiceTokenUtil.getServiceToken(context, str);
    }

    public ServiceTokenFuture invalidateServiceToken(Context context, ServiceTokenResult serviceTokenResult) {
        return this.mServiceTokenUtil.invalidateServiceToken(context, serviceTokenResult);
    }

    private boolean isXiaomiAccountApp(Context context) {
        return TextUtils.equals(context.getPackageName(), AccountIntent.PACKAGE_XIAOMI_ACCOUNT);
    }

    private boolean shouldUseGetAuthTokenVer2(Bundle bundle, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (accountManagerCallback == null && handler == null && bundle != null && bundle.getBoolean(BaseConstants.EXTRA_USE_GET_AUTH_TOKEN_IMPL_VER_2, false) && bundle.keySet().size() == 1) {
            return true;
        }
        return false;
    }

    public MiAccountManagerFuture<XmAccountVisibility> canAccessAccount(Context context) {
        return this.mServiceTokenUtil.canAccessAccount(context);
    }
}
