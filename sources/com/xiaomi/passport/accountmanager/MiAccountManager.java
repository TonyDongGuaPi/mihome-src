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
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.accountsdk.futureservice.MiAccountManagerFuture;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.passport.LocalFeatures.LocalFeaturesImpl;
import com.xiaomi.passport.LocalFeatures.MiLocalFeaturesManager;
import com.xiaomi.passport.servicetoken.ServiceTokenFuture;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.passport.servicetoken.data.XmAccountVisibility;
import java.io.IOException;

public class MiAccountManager implements IAccountManagerInternal {
    public static final String ACTION_AUTHENTICATOR_INTENT = "android.accounts.AccountAuthenticator";
    public static final String AUTHENTICATOR_ATTRIBUTES_NAME = "account-authenticator";
    public static final String AUTHENTICATOR_META_DATA_NAME = "android.accounts.AccountAuthenticator";
    public static final int ERROR_CODE_BAD_ARGUMENTS = 7;
    public static final int ERROR_CODE_BAD_REQUEST = 8;
    public static final int ERROR_CODE_CANCELED = 4;
    public static final int ERROR_CODE_INVALID_RESPONSE = 5;
    public static final int ERROR_CODE_NETWORK_ERROR = 3;
    public static final int ERROR_CODE_REMOTE_EXCEPTION = 1;
    public static final int ERROR_CODE_UNSUPPORTED_OPERATION = 6;
    public static final String KEY_ACCOUNTS = "accounts";
    public static final String KEY_ACCOUNT_AUTHENTICATOR_RESPONSE = "accountAuthenticatorResponse";
    public static final String KEY_ACCOUNT_MANAGER_RESPONSE = "accountManagerResponse";
    public static final String KEY_ACCOUNT_NAME = "authAccount";
    public static final String KEY_ACCOUNT_TYPE = "accountType";
    public static final String KEY_AUTHENTICATOR_TYPES = "authenticator_types";
    public static final String KEY_AUTHTOKEN = "authtoken";
    public static final String KEY_AUTH_FAILED_MESSAGE = "authFailedMessage";
    public static final String KEY_AUTH_TOKEN_LABEL = "authTokenLabelKey";
    public static final String KEY_BOOLEAN_RESULT = "booleanResult";
    public static final String KEY_ERROR_CODE = "errorCode";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_STS_URL = "sts_url";
    public static final String KEY_USERDATA = "userdata";
    public static final String LOGIN_ACCOUNTS_CHANGED_ACTION = "account-authenticator";
    public static final String XIAOMI_ACCOUNT_TYPE = "com.xiaomi";
    private static volatile MiAccountManager sThis;
    private AccountAuthenticator mAccountAuthenticator;
    private boolean mCanUseServiceTokenUtil;
    private boolean mCanUseSystemAccount;
    private final Context mContext;
    private LocalAccountManagerAdapter mLocalAccountAdapter;
    private SystemAccountManagerAdapter mSystemAccountAdapter;
    private IAccountManagerInternal mXmsfAccountAdapter;

    public enum AccountAuthenticator {
        LOCAL,
        SYSTEM
    }

    public enum SystemAccountVisibility {
        IMPOSSIBLE,
        REQUIRE_LOCAL_APP_PERMISSION,
        CAN_USE_SERVICE_TOKEN_UTIL
    }

    private MiAccountManager(Context context) {
        this.mContext = context.getApplicationContext();
        XMPassportSettings.ensureApplicationContext((Application) this.mContext);
        XMPassportSettings.setNonNullApplicationContextContract(true);
        this.mCanUseSystemAccount = canUseSystemAccount(context);
        this.mCanUseServiceTokenUtil = canUserServiceTokenUtil(context);
        restoreAccountAuthenticator();
    }

    private void restoreAccountAuthenticator() {
        AccountAuthenticator loadAccountAuthenticator = MiAccountManagerSettingsPersistent.getInstance(this.mContext).loadAccountAuthenticator();
        if (loadAccountAuthenticator == null) {
            loadAccountAuthenticator = AccountAuthenticator.SYSTEM;
        }
        setUpAccountManagerConfig(loadAccountAuthenticator);
    }

    public boolean canUseSystem() {
        return this.mCanUseSystemAccount;
    }

    public SystemAccountVisibility canUseSystemMoreDetailed() {
        if (!this.mCanUseSystemAccount) {
            return SystemAccountVisibility.IMPOSSIBLE;
        }
        if (this.mCanUseServiceTokenUtil) {
            return SystemAccountVisibility.CAN_USE_SERVICE_TOKEN_UTIL;
        }
        return SystemAccountVisibility.REQUIRE_LOCAL_APP_PERMISSION;
    }

    public boolean isUseSystem() {
        return this.mAccountAuthenticator == AccountAuthenticator.SYSTEM;
    }

    public boolean isUseLocal() {
        return this.mAccountAuthenticator == AccountAuthenticator.LOCAL;
    }

    public void setUseSystem() {
        setUpAccountManagerConfig(AccountAuthenticator.SYSTEM);
    }

    public void setUseLocal() {
        setUpAccountManagerConfig(AccountAuthenticator.LOCAL);
    }

    private void setUpAccountManagerConfig(AccountAuthenticator accountAuthenticator) {
        setUpAccountAdapter(accountAuthenticator);
    }

    private void setUpDeviceIdPolicy(AccountAuthenticator accountAuthenticator) {
        switch (accountAuthenticator) {
            case SYSTEM:
                HashedDeviceIdUtil.GlobalConfig.getInstance().setPolicy(HashedDeviceIdUtil.DeviceIdPolicy.RUNTIME_DEVICE_ID_ONLY);
                return;
            case LOCAL:
                HashedDeviceIdUtil.GlobalConfig.getInstance().setPolicy(HashedDeviceIdUtil.DeviceIdPolicy.CACHED_THEN_RUNTIME_THEN_PSEUDO);
                return;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void setUpAccountAdapter(AccountAuthenticator accountAuthenticator) {
        switch (accountAuthenticator) {
            case SYSTEM:
                if (!this.mCanUseSystemAccount) {
                    this.mAccountAuthenticator = AccountAuthenticator.LOCAL;
                    break;
                } else {
                    this.mAccountAuthenticator = AccountAuthenticator.SYSTEM;
                    break;
                }
            case LOCAL:
                this.mAccountAuthenticator = AccountAuthenticator.LOCAL;
                break;
            default:
                throw new IllegalArgumentException();
        }
        switch (this.mAccountAuthenticator) {
            case SYSTEM:
                if (this.mSystemAccountAdapter == null) {
                    this.mSystemAccountAdapter = new SystemAccountManagerAdapter(this.mContext);
                }
                this.mXmsfAccountAdapter = this.mSystemAccountAdapter;
                break;
            case LOCAL:
                if (this.mLocalAccountAdapter == null) {
                    this.mLocalAccountAdapter = new LocalAccountManagerAdapter(this.mContext);
                }
                this.mXmsfAccountAdapter = this.mLocalAccountAdapter;
                break;
            default:
                throw new IllegalArgumentException();
        }
        setUpDeviceIdPolicy(this.mAccountAuthenticator);
        MiAccountManagerSettingsPersistent.getInstance(this.mContext).saveAccountAuthenticator(this.mAccountAuthenticator);
    }

    private boolean canUseSystemAccount(Context context) {
        for (AuthenticatorDescription authenticatorDescription : AccountManager.get(context).getAuthenticatorTypes()) {
            if (TextUtils.equals(authenticatorDescription.type, "com.xiaomi") && TextUtils.equals(authenticatorDescription.packageName, AccountIntent.PACKAGE_XIAOMI_ACCOUNT)) {
                return true;
            }
        }
        return false;
    }

    private boolean canUserServiceTokenUtil(Context context) {
        return context.getPackageManager().resolveService(new Intent(AccountIntent.ACTION_SERVICE_TOKEN_OP).setPackage(AccountIntent.PACKAGE_XIAOMI_ACCOUNT), 0) != null;
    }

    @Deprecated
    public MiAccountManagerFuture<XmAccountVisibility> canAccessAccount(Context context) {
        return this.mXmsfAccountAdapter.canAccessAccount(context);
    }

    public MiAccountManagerFuture<XmAccountVisibility> setSystemAccountVisibility(Context context) {
        return new SystemAccountManagerAdapter(context).canAccessAccount(context);
    }

    public static MiAccountManager get(Context context) {
        if (context != null) {
            if (sThis == null) {
                synchronized (MiAccountManager.class) {
                    if (sThis == null) {
                        sThis = new MiAccountManager(context);
                    }
                }
            }
            return sThis;
        }
        throw new IllegalArgumentException("context is null");
    }

    public AuthenticatorDescription[] getAuthenticatorTypes() {
        return this.mXmsfAccountAdapter.getAuthenticatorTypes();
    }

    public Account[] getAccounts() {
        return this.mXmsfAccountAdapter.getAccounts();
    }

    public Account[] getAccountsByType(String str) {
        return this.mXmsfAccountAdapter.getAccountsByType(str);
    }

    public AccountManagerFuture<Boolean> hasFeatures(Account account, String[] strArr, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.hasFeatures(account, strArr, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Account[]> getAccountsByTypeAndFeatures(String str, String[] strArr, AccountManagerCallback<Account[]> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.getAccountsByTypeAndFeatures(str, strArr, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Boolean> removeAccount(Account account, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.removeAccount(account, accountManagerCallback, handler);
    }

    public void invalidateAuthToken(String str, String str2) {
        this.mXmsfAccountAdapter.invalidateAuthToken(str, str2);
    }

    public void clearPassword(Account account) {
        this.mXmsfAccountAdapter.clearPassword(account);
    }

    public String blockingGetAuthToken(Account account, String str, boolean z) throws OperationCanceledException, IOException, AuthenticatorException {
        return this.mXmsfAccountAdapter.blockingGetAuthToken(account, str, z);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.getAuthToken(account, str, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.getAuthToken(account, str, bundle, z, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.addAccount(str, str2, strArr, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> confirmCredentials(Account account, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.confirmCredentials(account, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> updateCredentials(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.updateCredentials(account, str, bundle, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> editProperties(String str, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.editProperties(str, activity, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthTokenByFeatures(String str, String str2, String[] strArr, Activity activity, Bundle bundle, Bundle bundle2, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return this.mXmsfAccountAdapter.getAuthTokenByFeatures(str, str2, strArr, activity, bundle, bundle2, accountManagerCallback, handler);
    }

    public void addOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener, Handler handler, boolean z) {
        this.mXmsfAccountAdapter.addOnAccountsUpdatedListener(onAccountsUpdateListener, handler, z);
    }

    public void removeOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener) {
        this.mXmsfAccountAdapter.removeOnAccountsUpdatedListener(onAccountsUpdateListener);
    }

    public ServiceTokenFuture getServiceToken(Context context, String str) {
        return this.mXmsfAccountAdapter.getServiceToken(context, str);
    }

    public ServiceTokenFuture invalidateServiceToken(Context context, ServiceTokenResult serviceTokenResult) {
        return this.mXmsfAccountAdapter.invalidateServiceToken(context, serviceTokenResult);
    }

    public Account getXiaomiAccount() {
        Account[] accountsByType = this.mXmsfAccountAdapter.getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return accountsByType[0];
        }
        return null;
    }

    public void addXiaomiAccount(String str, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        addXiaomiAccount(str, (String[]) null, (Bundle) null, activity, accountManagerCallback, handler);
    }

    public void addXiaomiAccount(String str, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        Account[] accounts = getAccounts();
        int length = accounts.length;
        int i = 0;
        while (i < length) {
            if (!TextUtils.equals(accounts[i].type, "com.xiaomi")) {
                i++;
            } else {
                return;
            }
        }
        addAccount("com.xiaomi", str, strArr, bundle, activity, accountManagerCallback, handler);
    }

    public void removeXiaomiAccount(AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        for (Account account : getAccounts()) {
            if (TextUtils.equals(account.type, "com.xiaomi")) {
                removeAccount(account, accountManagerCallback, handler);
            }
        }
    }

    public String getPassword(Account account) {
        return this.mXmsfAccountAdapter.getPassword(account);
    }

    public String getUserData(Account account, String str) {
        return this.mXmsfAccountAdapter.getUserData(account, str);
    }

    public boolean addAccountExplicitly(Account account, String str, Bundle bundle) {
        return this.mXmsfAccountAdapter.addAccountExplicitly(account, str, bundle);
    }

    public String peekAuthToken(Account account, String str) {
        return this.mXmsfAccountAdapter.peekAuthToken(account, str);
    }

    public void setPassword(Account account, String str) {
        this.mXmsfAccountAdapter.setPassword(account, str);
    }

    public void setUserData(Account account, String str, String str2) {
        this.mXmsfAccountAdapter.setUserData(account, str, str2);
    }

    public void setAuthToken(Account account, String str, String str2) {
        this.mXmsfAccountAdapter.setAuthToken(account, str, str2);
    }

    public MiLocalFeaturesManager getLocalFeatures() {
        return LocalFeaturesImpl.get(this.mContext);
    }
}
