package com.mi.account.sdk;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.mi.account.sdk.db.DBContract;
import com.mi.account.sdk.util.Constants;
import com.mi.log.LogUtil;
import com.mi.util.Constants;
import com.mi.util.Utils;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.account.IXiaomiAccountService;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.utils.AccountHelper;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import miuipub.content.ExtraIntent;

public class LoginManager {
    public static final int ERROR_LOGIN_COMMON = 0;
    public static final int ERROR_LOGIN_NETWORK = 3;
    public static final int ERROR_LOGIN_NO_ACCOUNT = 2;
    public static final int ERROR_LOGIN_SERVER = 4;
    public static final int ERROR_LOGIN_UNACTIVE = 5;
    public static final int ERROR_LOGIN_USER_CANCELED = 1;
    public static final int STATUS_LOCAL_LOGIN = 3;
    public static final int STATUS_LOCAL_LOGOUT = 4;
    public static final int STATUS_SYSTEM_LOGIN = 1;
    public static final int STATUS_SYSTEM_LOGOUT = 2;
    public static final int STATUS_SYSTEM_UNACTIVE = 5;
    protected static final String TAG = "LoginManager";
    protected static Set<AccountListener> mAccountLsteners = Collections.newSetFromMap(new ConcurrentHashMap());
    protected static LoginManager sLoginManager;
    private BroadcastReceiver mAccountChangedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED")) {
                int intExtra = intent.getIntExtra("extra_update_type", -1);
                if (TextUtils.equals(((Account) intent.getParcelableExtra("extra_account")).type, "com.xiaomi") && intExtra != 2 && intExtra == 1) {
                    Utils.Preference.removePref(LoginManager.this.mContext, "pref_system_uid");
                    Utils.Preference.removePref(LoginManager.this.mContext, "pref_system_extended_token");
                    if (Utils.Preference.getBooleanPref(LoginManager.this.mContext, "pref_login_system", false)) {
                        LoginManager.this.setSystemLogin(false);
                        LoginManager.this.onAccountLogout();
                    }
                }
            }
        }
    };
    protected Context mContext;
    private LoginCallback mLoginCallback;
    private LogoutCallback mLogoutCallback;
    private MiAccountManager miAccountManager;

    public interface AccountListener {
        void onInvalidAuthonToken();

        void onLogin(String str, String str2, String str3);

        void onLogout();
    }

    public interface LoginCallback {
        void a(int i);

        void a(String str, String str2, String str3);
    }

    public interface LogoutCallback {
        void a();
    }

    private Map<String, String> getSidsMap() {
        return null;
    }

    protected LoginManager(Context context) {
        this.mContext = context.getApplicationContext();
        context.registerReceiver(this.mAccountChangedReceiver, new IntentFilter("android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED"));
        this.miAccountManager = MiAccountManager.get(this.mContext);
    }

    public MiAccountManager getMiAccountManager() {
        return this.miAccountManager;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        this.mContext.unregisterReceiver(this.mAccountChangedReceiver);
    }

    public static void init(Context context) {
        MiAccountManager.get(context);
        if (Constants.b) {
            URLs.setLocalUsePreview(context, true);
        } else {
            URLs.setLocalUsePreview(context, false);
        }
        Constants.Account.init();
        if (sLoginManager == null) {
            sLoginManager = new LoginManager(context);
        }
    }

    public static LoginManager getInstance() {
        return sLoginManager;
    }

    public String getUserId() {
        if (Utils.Preference.getBooleanPref(this.mContext, "pref_login_system", false)) {
            return Utils.Preference.getStringPref(this.mContext, "pref_system_uid", (String) null);
        }
        return Utils.Preference.getStringPref(this.mContext, "pref_uid", (String) null);
    }

    public String getEncryptedUserId() {
        if (Utils.Preference.getBooleanPref(this.mContext, "pref_login_system", false)) {
            return Utils.Preference.getStringPref(this.mContext, "pref_system_c_uid", (String) null);
        }
        return Utils.Preference.getStringPref(this.mContext, "pref_c_uid", (String) null);
    }

    public String getServiceToken() {
        String stringPref = Utils.Preference.getStringPref(this.mContext, "pref_extended_token", (String) null);
        if (ExtendedAuthToken.parse(stringPref) != null) {
            return ExtendedAuthToken.parse(stringPref).authToken;
        }
        return null;
    }

    public String getSecurity() {
        String stringPref = Utils.Preference.getStringPref(this.mContext, "pref_extended_token", (String) null);
        if (ExtendedAuthToken.parse(stringPref) != null) {
            return ExtendedAuthToken.parse(stringPref).security;
        }
        return null;
    }

    public ExtendedAuthToken getExtendedAuthToken(String str) {
        String str2;
        if (!Utils.Preference.getBooleanPref(this.mContext, "pref_login_system", false)) {
            str2 = TextUtils.equals(str, Constants.Account.getInstance().getServiceID()) ? Utils.Preference.getStringPref(this.mContext, "pref_extended_token", "") : null;
        } else if (TextUtils.equals(str, Constants.Account.getInstance().getServiceID())) {
            String stringPref = Utils.Preference.getStringPref(this.mContext, "pref_system_extended_token", "");
            str2 = TextUtils.isEmpty(stringPref) ? getSystemAccountAuthToken(str) : stringPref;
        } else {
            str2 = getSystemAccountAuthToken(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return ExtendedAuthToken.parse(str2);
    }

    public void setSystemLogin(boolean z) {
        Utils.Preference.setBooleanPref(this.mContext, "pref_login_system", z);
    }

    public boolean isSystemLogin() {
        return Utils.Preference.getBooleanPref(this.mContext, "pref_login_system", true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addLoginListener(com.mi.account.sdk.LoginManager.AccountListener r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 != 0) goto L_0x0005
            monitor-exit(r1)
            return
        L_0x0005:
            java.util.Set<com.mi.account.sdk.LoginManager$AccountListener> r0 = mAccountLsteners     // Catch:{ all -> 0x0014 }
            boolean r0 = r0.contains(r2)     // Catch:{ all -> 0x0014 }
            if (r0 != 0) goto L_0x0012
            java.util.Set<com.mi.account.sdk.LoginManager$AccountListener> r0 = mAccountLsteners     // Catch:{ all -> 0x0014 }
            r0.add(r2)     // Catch:{ all -> 0x0014 }
        L_0x0012:
            monitor-exit(r1)
            return
        L_0x0014:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.account.sdk.LoginManager.addLoginListener(com.mi.account.sdk.LoginManager$AccountListener):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeLoginListener(com.mi.account.sdk.LoginManager.AccountListener r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 != 0) goto L_0x0005
            monitor-exit(r1)
            return
        L_0x0005:
            java.util.Set<com.mi.account.sdk.LoginManager$AccountListener> r0 = mAccountLsteners     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x000e
            java.util.Set<com.mi.account.sdk.LoginManager$AccountListener> r0 = mAccountLsteners     // Catch:{ all -> 0x0010 }
            r0.remove(r2)     // Catch:{ all -> 0x0010 }
        L_0x000e:
            monitor-exit(r1)
            return
        L_0x0010:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.account.sdk.LoginManager.removeLoginListener(com.mi.account.sdk.LoginManager$AccountListener):void");
    }

    public void onAccountLoginSucceed(String str, String str2, String str3) {
        if (this.mLoginCallback != null) {
            this.mLoginCallback.a(str, str2, str3);
        }
        LogUtil.b("LoginManager", "mAccountLsteners:" + mAccountLsteners + ",mAccountLsteners.size:" + mAccountLsteners.size());
        if (mAccountLsteners != null && !mAccountLsteners.isEmpty()) {
            for (AccountListener onLogin : mAccountLsteners) {
                onLogin.onLogin(str, str2, str3);
            }
        }
    }

    public void onAccountLoginFailed(int i) {
        if (this.mLoginCallback != null) {
            this.mLoginCallback.a(i);
        }
        Log.d("LoginManager", "account login failed: " + i);
    }

    /* access modifiers changed from: protected */
    public void onAccountLogout() {
        if (this.mLogoutCallback != null) {
            this.mLogoutCallback.a();
        }
        if (mAccountLsteners != null && !mAccountLsteners.isEmpty()) {
            for (AccountListener onLogout : mAccountLsteners) {
                onLogout.onLogout();
            }
        }
        Log.d("LoginManager", "account has logout");
    }

    public boolean hasLogin() {
        if (Utils.Preference.getBooleanPref(this.mContext, "pref_login_system", false)) {
            return !TextUtils.isEmpty(getSystemAccountId());
        }
        return !TextUtils.isEmpty(Utils.Preference.getStringPref(this.mContext, "pref_uid", "")) && !TextUtils.isEmpty(Utils.Preference.getStringPref(this.mContext, "pref_extended_token", ""));
    }

    public boolean hasSystemAccount() {
        return !TextUtils.isEmpty(getSystemAccountId());
    }

    public String getSystemAccountId() {
        Account[] accountsByType = this.miAccountManager.getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return accountsByType[0].name;
        }
        return null;
    }

    public String getSystemEncryptedUserId(final boolean z) {
        final Account[] accountsByType = this.miAccountManager.getAccountsByType("com.xiaomi");
        if (accountsByType.length <= 0) {
            return null;
        }
        try {
            String userData = this.miAccountManager.getUserData(accountsByType[0], "encrypted_user_id");
            if (z) {
                Utils.Preference.setStringPref(this.mContext, "pref_system_c_uid", userData);
            } else {
                Utils.Preference.setStringPref(this.mContext, "pref_c_uid", userData);
            }
            return userData;
        } catch (SecurityException unused) {
            Intent intent = new Intent(ExtraIntent.V);
            intent.setPackage(AccountIntent.PACKAGE_XIAOMI_ACCOUNT);
            this.mContext.bindService(intent, new ServiceConnection() {
                public void onServiceDisconnected(ComponentName componentName) {
                }

                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    try {
                        String encryptedUserId = IXiaomiAccountService.Stub.asInterface(iBinder).getEncryptedUserId(accountsByType[0]);
                        if (z) {
                            Utils.Preference.setStringPref(LoginManager.this.mContext, "pref_system_c_uid", encryptedUserId);
                        } else {
                            Utils.Preference.setStringPref(LoginManager.this.mContext, "pref_c_uid", encryptedUserId);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }, 1);
            return null;
        }
    }

    public String getPassToken() {
        if (!hasLogin() || Utils.Preference.getBooleanPref(this.mContext, "pref_login_system", false)) {
            return null;
        }
        return Utils.Preference.getStringPref(this.mContext, "pref_pass_token", "");
    }

    public void invalidAuthToken() {
        if (Utils.Preference.getBooleanPref(this.mContext, "pref_login_system", false)) {
            Utils.Preference.removePref(this.mContext, "pref_extended_token");
        } else {
            Utils.Preference.removePref(this.mContext, "pref_system_extended_token");
        }
        if (mAccountLsteners != null && !mAccountLsteners.isEmpty()) {
            for (AccountListener onInvalidAuthonToken : mAccountLsteners) {
                onInvalidAuthonToken.onInvalidAuthonToken();
            }
        }
    }

    public String getLocalAccountAuthToken(String str, boolean z) {
        AccountInfo serviceTokenByPassToken;
        try {
            Account xiaomiAccount = this.miAccountManager.getXiaomiAccount();
            if (xiaomiAccount != null) {
                String realPasstoken = getRealPasstoken(xiaomiAccount);
                LogUtil.b("LoginManager", "passToken  loginSystem save :" + realPasstoken);
                if (!TextUtils.isEmpty(realPasstoken) && (serviceTokenByPassToken = AccountHelper.getServiceTokenByPassToken(getUserId(), realPasstoken, str)) != null) {
                    return serviceTokenByPassToken.getServiceToken();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (z) {
                logout((LogoutCallback) null);
            }
        }
        return null;
    }

    public AccountInfo getLocalAccountInfo(String str) {
        try {
            Account xiaomiAccount = this.miAccountManager.getXiaomiAccount();
            if (xiaomiAccount == null) {
                return null;
            }
            String realPasstoken = getRealPasstoken(xiaomiAccount);
            LogUtil.b("LoginManager", "passToken  loginSystem save :" + realPasstoken);
            if (!TextUtils.isEmpty(realPasstoken)) {
                return AccountHelper.getServiceTokenByPassToken(getUserId(), realPasstoken, str);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getRealPasstoken(Account account) {
        ExtendedAuthToken parse;
        String password = MiAccountManager.get(this.mContext).getPassword(account);
        if (!TextUtils.isEmpty(password) && (parse = ExtendedAuthToken.parse(password)) != null) {
            return parse.authToken;
        }
        return null;
    }

    public String getSystemAccountAuthToken(String str) {
        return getSystemAccountAuthToken(str, true);
    }

    public String getSystemAccountAuthToken(String str, boolean z) {
        Bundle result;
        try {
            AccountManagerFuture<Bundle> authToken = this.miAccountManager.getAuthToken(this.miAccountManager.getXiaomiAccount(), str, (Bundle) null, true, (AccountManagerCallback<Bundle>) null, (Handler) null);
            if (!(authToken == null || (result = authToken.getResult()) == null)) {
                print(result);
                String string = result.getString("authtoken");
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (z) {
                logout((LogoutCallback) null);
            }
        }
        return null;
    }

    public String getSystemAccountUserDataByKey(String str) {
        if (this.miAccountManager.getXiaomiAccount() == null) {
            return "";
        }
        return this.miAccountManager.getUserData(this.miAccountManager.getXiaomiAccount(), str);
    }

    private void print(Bundle bundle) {
        for (String str : bundle.keySet()) {
            LogUtil.b("LoginManager", "bundle-key:" + str + ", value:" + bundle.get(str) + "|||||");
        }
    }

    public void loginLocal() {
        Bundle result;
        LogUtil.b("LoginManager", "local login");
        try {
            setSystemLogin(false);
            AccountManagerFuture<Bundle> authToken = this.miAccountManager.getAuthToken(this.miAccountManager.getXiaomiAccount(), Constants.Account.getInstance().getServiceID(), (Bundle) null, (Activity) null, (AccountManagerCallback<Bundle>) null, (Handler) null);
            if (authToken != null && (result = authToken.getResult()) != null) {
                String string = result.getString("authAccount");
                String string2 = result.getString("authtoken");
                getSystemEncryptedUserId(false);
                ExtendedAuthToken parse = ExtendedAuthToken.parse(string2);
                Utils.Preference.setStringPref(this.mContext, "pref_uid", string);
                Utils.Preference.setStringPref(this.mContext, "pref_extended_token", parse.toPlain());
                onAccountLoginSucceed(string, parse.authToken, parse.security);
            }
        } catch (OperationCanceledException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (AuthenticatorException e3) {
            e3.printStackTrace();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public void loginSystem(String str) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.b("LoginManager", "system login");
            setSystemLogin(true);
            String systemAccountId = getSystemAccountId();
            ExtendedAuthToken parse = ExtendedAuthToken.parse(str);
            Utils.Preference.setStringPref(this.mContext, "pref_system_uid", systemAccountId);
            Utils.Preference.setStringPref(this.mContext, "pref_system_extended_token", str);
            Utils.Preference.setStringPref(this.mContext, "pref_extended_token", str);
            onAccountLoginSucceed(systemAccountId, parse.authToken, parse.security);
            try {
                String systemEncryptedUserId = getSystemEncryptedUserId(true);
                LogUtil.b("LoginManager", "system login userId:" + systemAccountId + ",cUid:" + systemEncryptedUserId + ",authToken:" + str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeLoginCallback() {
        this.mLoginCallback = null;
    }

    public void logout() {
        logout((LogoutCallback) null);
    }

    public void logout(LogoutCallback logoutCallback) {
        this.mLogoutCallback = logoutCallback;
        Utils.Preference.removePref(this.mContext, "pref_c_uid");
        Utils.Preference.removePref(this.mContext, "pref_system_c_uid");
        Utils.Preference.removePref(this.mContext, "pref_uid");
        Utils.Preference.removePref(this.mContext, "pref_extended_token");
        Utils.Preference.removePref(this.mContext, "pref_pass_token");
        Utils.Preference.removePref(this.mContext, "pref_system_uid");
        Utils.Preference.removePref(this.mContext, "pref_system_extended_token");
        Utils.Preference.removePref(this.mContext, "pref_key_user_ecryption_id");
        Utils.Preference.removePref(this.mContext, "pref_last_refresh_serviceToken_time");
        removeXiaomiAccount();
        onAccountLogout();
        Utils.Preference.removePref(this.mContext, "pref_login_system");
    }

    public void removeXiaomiAccount() {
        if (isSystemLogin()) {
            this.miAccountManager.setUseSystem();
        } else {
            this.miAccountManager.setUseLocal();
        }
        try {
            invalidateServiceToken();
            removeXiaomiAccount(new AccountManagerCallback<Boolean>() {
                public void run(AccountManagerFuture<Boolean> accountManagerFuture) {
                    try {
                        LogUtil.b("LoginManager", "future.getResult():" + accountManagerFuture.getResult().booleanValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, (Handler) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeXiaomiAccount(AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        for (Account account : this.miAccountManager.getAccounts()) {
            LogUtil.b("LoginManager", "removeXiaomiAccount:" + account.toString());
            if (TextUtils.equals(account.type, "com.xiaomi")) {
                this.miAccountManager.removeAccount(account, accountManagerCallback, handler);
            }
        }
    }

    public void invalidateServiceToken() {
        String stringPref = Utils.Preference.getStringPref(this.mContext, "pref_extended_token", (String) null);
        LogUtil.b("LoginManager", "old extended token plain:" + stringPref);
        getMiAccountManager().invalidateAuthToken("com.xiaomi", stringPref);
    }

    public String refreshServiceToken() {
        MiAccountManager miAccountManager2 = getMiAccountManager();
        if (isSystemLogin()) {
            miAccountManager2.setUseSystem();
        } else {
            miAccountManager2.setUseLocal();
        }
        return getInstance().getSystemAccountAuthToken(Constants.Account.getInstance().getServiceID());
    }

    public Map<String, ExtendedAuthToken> getWebRequiredCachedServiceTokens() {
        Cursor query = this.mContext.getContentResolver().query(DBContract.DataStats.CONTENT_URI, new String[]{"stats"}, "type='service_token'", (String[]) null, (String) null);
        HashMap hashMap = null;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        if (!TextUtils.isEmpty(string)) {
                            Pair<String, String> parseServiceToken = DBContract.DataMimeType.parseServiceToken(string);
                            if (hashMap == null) {
                                hashMap = new HashMap();
                            }
                            hashMap.put(parseServiceToken.first, ExtendedAuthToken.parse((String) parseServiceToken.second));
                            LogUtil.b("LoginManager", "The cached serviceToken is:" + ((String) parseServiceToken.second));
                        }
                    } while (query.moveToNext());
                }
            } finally {
                query.close();
            }
        }
        return hashMap;
    }

    public Map<String, ExtendedAuthToken> getWebRequiredServiceTokens() {
        Map<String, ExtendedAuthToken> webRequiredCachedServiceTokens = getWebRequiredCachedServiceTokens();
        if (webRequiredCachedServiceTokens != null) {
            for (Map.Entry next : webRequiredCachedServiceTokens.entrySet()) {
                LogUtil.b("LoginManager", "Invalide serviceToken:" + next.getValue());
                this.miAccountManager.invalidateAuthToken("com.xiaomi", ((ExtendedAuthToken) next.getValue()).toPlain());
            }
            webRequiredCachedServiceTokens.clear();
        }
        Map<String, String> sidsMap = getSidsMap();
        if (sidsMap != null) {
            for (Map.Entry next2 : sidsMap.entrySet()) {
                ExtendedAuthToken extendedAuthToken = getExtendedAuthToken((String) next2.getValue());
                if (extendedAuthToken != null) {
                    if (webRequiredCachedServiceTokens == null) {
                        webRequiredCachedServiceTokens = new HashMap<>();
                    }
                    webRequiredCachedServiceTokens.put(next2.getKey(), extendedAuthToken);
                    saveServiceToken((String) next2.getValue(), extendedAuthToken.toPlain());
                }
                StringBuilder sb = new StringBuilder();
                sb.append("The sid ");
                sb.append((String) next2.getValue());
                sb.append(" 's serviceToken is ");
                sb.append(extendedAuthToken == null ? "null" : extendedAuthToken.authToken);
                LogUtil.b("LoginManager", sb.toString());
            }
        }
        return webRequiredCachedServiceTokens;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0082 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void saveServiceToken(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            android.content.Context r0 = r8.mContext
            android.content.ContentResolver r1 = r0.getContentResolver()
            android.net.Uri r2 = com.mi.account.sdk.db.DBContract.DataStats.CONTENT_URI
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r0 = "_id"
            r7 = 0
            r3[r7] = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "type='service_token' AND stats LIKE '"
            r0.append(r4)
            r0.append(r9)
            java.lang.String r4 = "%'"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r5 = 0
            r6 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)
            android.content.ContentValues r1 = new android.content.ContentValues
            r1.<init>()
            java.lang.String r2 = "stats"
            java.lang.String r9 = com.mi.account.sdk.db.DBContract.DataMimeType.formatServiceToken(r9, r10)
            r1.put(r2, r9)
            if (r0 == 0) goto L_0x006d
            int r9 = r0.getCount()     // Catch:{ all -> 0x006b }
            if (r9 <= 0) goto L_0x006d
            boolean r9 = r0.moveToFirst()     // Catch:{ all -> 0x006b }
            if (r9 == 0) goto L_0x0080
            long r9 = r0.getLong(r7)     // Catch:{ all -> 0x006b }
            android.content.Context r2 = r8.mContext     // Catch:{ all -> 0x006b }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ all -> 0x006b }
            android.net.Uri r3 = com.mi.account.sdk.db.DBContract.DataStats.CONTENT_URI     // Catch:{ all -> 0x006b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x006b }
            r4.<init>()     // Catch:{ all -> 0x006b }
            java.lang.String r5 = "_id="
            r4.append(r5)     // Catch:{ all -> 0x006b }
            r4.append(r9)     // Catch:{ all -> 0x006b }
            java.lang.String r9 = r4.toString()     // Catch:{ all -> 0x006b }
            r10 = 0
            r2.update(r3, r1, r9, r10)     // Catch:{ all -> 0x006b }
            goto L_0x0080
        L_0x006b:
            r9 = move-exception
            goto L_0x0086
        L_0x006d:
            java.lang.String r9 = "type"
            java.lang.String r10 = "service_token"
            r1.put(r9, r10)     // Catch:{ all -> 0x006b }
            android.content.Context r9 = r8.mContext     // Catch:{ all -> 0x006b }
            android.content.ContentResolver r9 = r9.getContentResolver()     // Catch:{ all -> 0x006b }
            android.net.Uri r10 = com.mi.account.sdk.db.DBContract.DataStats.CONTENT_URI     // Catch:{ all -> 0x006b }
            r9.insert(r10, r1)     // Catch:{ all -> 0x006b }
        L_0x0080:
            if (r0 == 0) goto L_0x0085
            r0.close()
        L_0x0085:
            return
        L_0x0086:
            if (r0 == 0) goto L_0x008b
            r0.close()
        L_0x008b:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.account.sdk.LoginManager.saveServiceToken(java.lang.String, java.lang.String):void");
    }
}
