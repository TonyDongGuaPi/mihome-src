package com.xiaomi.accounts;

import android.accounts.Account;
import android.accounts.AuthenticatorDescription;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accounts.AccountAuthenticatorCache;
import com.xiaomi.accounts.IAccountAuthenticator;
import com.xiaomi.accounts.IAccountAuthenticatorResponse;
import com.xiaomi.accountsdk.utils.AccountLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class AccountManagerService {
    private static final Intent ACCOUNTS_CHANGED_INTENT = new Intent(AccountManager.LOGIN_ACCOUNTS_CHANGED_ACTION);
    private static final String ACCOUNTS_ID = "_id";
    private static final String ACCOUNTS_NAME = "name";
    private static final String ACCOUNTS_PASSWORD = "password";
    private static final String ACCOUNTS_TYPE = "type";
    private static final String ACCOUNTS_TYPE_COUNT = "count(type)";
    private static final String AUTHTOKENS_ACCOUNTS_ID = "accounts_id";
    private static final String AUTHTOKENS_AUTHTOKEN = "authtoken";
    private static final String AUTHTOKENS_ID = "_id";
    private static final String AUTHTOKENS_TYPE = "type";
    private static final String[] COLUMNS_AUTHTOKENS_TYPE_AND_AUTHTOKEN = {"type", "authtoken"};
    private static final String[] COLUMNS_EXTRAS_KEY_AND_VALUE = {"key", "value"};
    private static final String DATABASE_NAME = "accounts.db";
    private static final int DATABASE_VERSION = 4;
    private static final Account[] EMPTY_ACCOUNT_ARRAY = new Account[0];
    private static final String EXTRAS_ACCOUNTS_ID = "accounts_id";
    private static final String EXTRAS_ID = "_id";
    private static final String EXTRAS_KEY = "key";
    private static final String EXTRAS_VALUE = "value";
    private static final String GRANTS_ACCOUNTS_ID = "accounts_id";
    private static final String GRANTS_AUTH_TOKEN_TYPE = "auth_token_type";
    private static final String GRANTS_GRANTEE_UID = "uid";
    private static final int MESSAGE_TIMED_OUT = 3;
    private static final String META_KEY = "key";
    private static final String META_VALUE = "value";
    /* access modifiers changed from: private */
    public static final ExecutorService REMOTE_THREAD_POOL_EXECUTOR = Executors.newCachedThreadPool();
    private static final String SELECTION_AUTHTOKENS_BY_ACCOUNT = "accounts_id=(select _id FROM accounts WHERE name=? AND type=?)";
    private static final String SELECTION_USERDATA_BY_ACCOUNT = "accounts_id=(select _id FROM accounts WHERE name=? AND type=?)";
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String TABLE_AUTHTOKENS = "authtokens";
    private static final String TABLE_EXTRAS = "extras";
    private static final String TABLE_GRANTS = "grants";
    private static final String TABLE_META = "meta";
    private static final String TAG = "AccountManagerService";
    private static final int TIMEOUT_DELAY_MS = 60000;
    private static AtomicReference<AccountManagerService> sThis = new AtomicReference<>();
    /* access modifiers changed from: private */
    public final AccountAuthenticatorCache mAuthenticatorCache;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final MessageHandler mMessageHandler;
    private HandlerThread mMessageThread;
    private final PackageManager mPackageManager;
    /* access modifiers changed from: private */
    public final LinkedHashMap<String, Session> mSessions;
    private final SparseArray<UserAccounts> mUsers;

    private long clearCallingIdentity() {
        return 0;
    }

    private void restoreCallingIdentity(long j) {
    }

    static class UserAccounts {
        /* access modifiers changed from: private */
        public final HashMap<String, Account[]> accountCache = new LinkedHashMap();
        /* access modifiers changed from: private */
        public HashMap<Account, HashMap<String, String>> authTokenCache = new HashMap<>();
        /* access modifiers changed from: private */
        public final Object cacheLock = new Object();
        /* access modifiers changed from: private */
        public final DatabaseHelper openHelper;
        /* access modifiers changed from: private */
        public HashMap<Account, HashMap<String, String>> userDataCache = new HashMap<>();
        /* access modifiers changed from: private */
        public final int userId;

        UserAccounts(Context context, int i) {
            this.userId = i;
            synchronized (this.cacheLock) {
                this.openHelper = new DatabaseHelper(context, i);
            }
        }
    }

    public static AccountManagerService getSingleton() {
        return sThis.get();
    }

    public AccountManagerService(Context context) {
        this(context, context.getPackageManager(), new AccountAuthenticatorCache(context));
    }

    public AccountManagerService(Context context, PackageManager packageManager, AccountAuthenticatorCache accountAuthenticatorCache) {
        this.mSessions = new LinkedHashMap<>();
        this.mUsers = new SparseArray<>();
        this.mContext = context;
        this.mPackageManager = packageManager;
        this.mMessageThread = new HandlerThread(TAG);
        this.mMessageThread.start();
        this.mMessageHandler = new MessageHandler(this.mMessageThread.getLooper());
        this.mAuthenticatorCache = accountAuthenticatorCache;
        sThis.set(this);
        initUser(0);
    }

    private UserAccounts initUser(int i) {
        UserAccounts userAccounts;
        synchronized (this.mUsers) {
            userAccounts = this.mUsers.get(i);
            if (userAccounts == null) {
                userAccounts = new UserAccounts(this.mContext, i);
                this.mUsers.append(i, userAccounts);
                purgeOldGrants(userAccounts);
                validateAccountsAndPopulateCache(userAccounts);
            }
        }
        return userAccounts;
    }

    private void purgeOldGrantsAll() {
        synchronized (this.mUsers) {
            for (int i = 0; i < this.mUsers.size(); i++) {
                purgeOldGrants(this.mUsers.valueAt(i));
            }
        }
    }

    private void purgeOldGrants(UserAccounts userAccounts) {
        synchronized (userAccounts.cacheLock) {
            SQLiteDatabase writableDatabase = userAccounts.openHelper.getWritableDatabase();
            Cursor query = writableDatabase.query(TABLE_GRANTS, new String[]{"uid"}, (String) null, (String[]) null, "uid", (String) null, (String) null);
            while (query.moveToNext()) {
                try {
                    int i = query.getInt(0);
                    if (!(this.mPackageManager.getPackagesForUid(i) != null)) {
                        AccountLog.d(TAG, "deleting grants for UID " + i + " because its package is no longer installed");
                        writableDatabase.delete(TABLE_GRANTS, "uid=?", new String[]{Integer.toString(i)});
                    }
                } finally {
                    query.close();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x011f A[Catch:{ all -> 0x0116 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void validateAccountsAndPopulateCache(com.xiaomi.accounts.AccountManagerService.UserAccounts r17) {
        /*
            r16 = this;
            r1 = r16
            java.lang.Object r2 = r17.cacheLock
            monitor-enter(r2)
            com.xiaomi.accounts.AccountManagerService$DatabaseHelper r0 = r17.openHelper     // Catch:{ all -> 0x0127 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ all -> 0x0127 }
            java.lang.String r4 = "accounts"
            r3 = 3
            java.lang.String[] r5 = new java.lang.String[r3]     // Catch:{ all -> 0x0127 }
            java.lang.String r3 = "_id"
            r11 = 0
            r5[r11] = r3     // Catch:{ all -> 0x0127 }
            java.lang.String r3 = "type"
            r12 = 1
            r5[r12] = r3     // Catch:{ all -> 0x0127 }
            java.lang.String r3 = "name"
            r13 = 2
            r5[r13] = r3     // Catch:{ all -> 0x0127 }
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r3 = r0
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0127 }
            java.util.HashMap r4 = r17.accountCache     // Catch:{ all -> 0x0118 }
            r4.clear()     // Catch:{ all -> 0x0118 }
            java.util.LinkedHashMap r4 = new java.util.LinkedHashMap     // Catch:{ all -> 0x0118 }
            r4.<init>()     // Catch:{ all -> 0x0118 }
            r5 = 0
        L_0x003a:
            boolean r6 = r3.moveToNext()     // Catch:{ all -> 0x0116 }
            if (r6 == 0) goto L_0x00bf
            long r6 = r3.getLong(r11)     // Catch:{ all -> 0x0116 }
            java.lang.String r8 = r3.getString(r12)     // Catch:{ all -> 0x0116 }
            java.lang.String r9 = r3.getString(r13)     // Catch:{ all -> 0x0116 }
            com.xiaomi.accounts.AccountAuthenticatorCache r10 = r1.mAuthenticatorCache     // Catch:{ all -> 0x0116 }
            android.accounts.AuthenticatorDescription r14 = android.accounts.AuthenticatorDescription.newKey(r8)     // Catch:{ all -> 0x0116 }
            com.xiaomi.accounts.AccountAuthenticatorCache$ServiceInfo r10 = r10.getServiceInfo(r14)     // Catch:{ all -> 0x0116 }
            if (r10 != 0) goto L_0x00aa
            java.lang.String r10 = "AccountManagerService"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0116 }
            r14.<init>()     // Catch:{ all -> 0x0116 }
            java.lang.String r15 = "deleting account "
            r14.append(r15)     // Catch:{ all -> 0x0116 }
            r14.append(r9)     // Catch:{ all -> 0x0116 }
            java.lang.String r15 = " because type "
            r14.append(r15)     // Catch:{ all -> 0x0116 }
            r14.append(r8)     // Catch:{ all -> 0x0116 }
            java.lang.String r15 = " no longer has a registered authenticator"
            r14.append(r15)     // Catch:{ all -> 0x0116 }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x0116 }
            com.xiaomi.accountsdk.utils.AccountLog.d(r10, r14)     // Catch:{ all -> 0x0116 }
            java.lang.String r10 = "accounts"
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x0116 }
            r14.<init>()     // Catch:{ all -> 0x0116 }
            java.lang.String r15 = "_id="
            r14.append(r15)     // Catch:{ all -> 0x0116 }
            r14.append(r6)     // Catch:{ all -> 0x0116 }
            java.lang.String r6 = r14.toString()     // Catch:{ all -> 0x0116 }
            r7 = 0
            r0.delete(r10, r6, r7)     // Catch:{ all -> 0x0116 }
            android.accounts.Account r5 = new android.accounts.Account     // Catch:{ all -> 0x00a7 }
            r5.<init>(r9, r8)     // Catch:{ all -> 0x00a7 }
            java.util.HashMap r6 = r17.userDataCache     // Catch:{ all -> 0x00a7 }
            r6.remove(r5)     // Catch:{ all -> 0x00a7 }
            java.util.HashMap r6 = r17.authTokenCache     // Catch:{ all -> 0x00a7 }
            r6.remove(r5)     // Catch:{ all -> 0x00a7 }
            r5 = 1
            goto L_0x003a
        L_0x00a7:
            r0 = move-exception
            r5 = 1
            goto L_0x011a
        L_0x00aa:
            java.lang.Object r6 = r4.get(r8)     // Catch:{ all -> 0x0116 }
            java.util.ArrayList r6 = (java.util.ArrayList) r6     // Catch:{ all -> 0x0116 }
            if (r6 != 0) goto L_0x00ba
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0116 }
            r6.<init>()     // Catch:{ all -> 0x0116 }
            r4.put(r8, r6)     // Catch:{ all -> 0x0116 }
        L_0x00ba:
            r6.add(r9)     // Catch:{ all -> 0x0116 }
            goto L_0x003a
        L_0x00bf:
            java.util.Set r0 = r4.entrySet()     // Catch:{ all -> 0x0116 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0116 }
        L_0x00c7:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x0116 }
            if (r4 == 0) goto L_0x0108
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x0116 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x0116 }
            java.lang.Object r6 = r4.getKey()     // Catch:{ all -> 0x0116 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0116 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x0116 }
            java.util.ArrayList r4 = (java.util.ArrayList) r4     // Catch:{ all -> 0x0116 }
            int r7 = r4.size()     // Catch:{ all -> 0x0116 }
            android.accounts.Account[] r7 = new android.accounts.Account[r7]     // Catch:{ all -> 0x0116 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0116 }
            r8 = 0
        L_0x00ea:
            boolean r9 = r4.hasNext()     // Catch:{ all -> 0x0116 }
            if (r9 == 0) goto L_0x0100
            java.lang.Object r9 = r4.next()     // Catch:{ all -> 0x0116 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0116 }
            android.accounts.Account r10 = new android.accounts.Account     // Catch:{ all -> 0x0116 }
            r10.<init>(r9, r6)     // Catch:{ all -> 0x0116 }
            r7[r8] = r10     // Catch:{ all -> 0x0116 }
            int r8 = r8 + 1
            goto L_0x00ea
        L_0x0100:
            java.util.HashMap r4 = r17.accountCache     // Catch:{ all -> 0x0116 }
            r4.put(r6, r7)     // Catch:{ all -> 0x0116 }
            goto L_0x00c7
        L_0x0108:
            r3.close()     // Catch:{ all -> 0x0127 }
            if (r5 == 0) goto L_0x0114
            int r0 = r17.userId     // Catch:{ all -> 0x0127 }
            r1.sendAccountsChangedBroadcast(r0)     // Catch:{ all -> 0x0127 }
        L_0x0114:
            monitor-exit(r2)     // Catch:{ all -> 0x0127 }
            return
        L_0x0116:
            r0 = move-exception
            goto L_0x011a
        L_0x0118:
            r0 = move-exception
            r5 = 0
        L_0x011a:
            r3.close()     // Catch:{ all -> 0x0127 }
            if (r5 == 0) goto L_0x0126
            int r3 = r17.userId     // Catch:{ all -> 0x0127 }
            r1.sendAccountsChangedBroadcast(r3)     // Catch:{ all -> 0x0127 }
        L_0x0126:
            throw r0     // Catch:{ all -> 0x0127 }
        L_0x0127:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0127 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accounts.AccountManagerService.validateAccountsAndPopulateCache(com.xiaomi.accounts.AccountManagerService$UserAccounts):void");
    }

    private UserAccounts getUserAccountsForCaller() {
        return getUserAccounts(UserId.getCallingUserId());
    }

    /* access modifiers changed from: protected */
    public UserAccounts getUserAccounts(int i) {
        UserAccounts userAccounts;
        synchronized (this.mUsers) {
            userAccounts = this.mUsers.get(i);
            if (userAccounts == null) {
                userAccounts = initUser(i);
                this.mUsers.append(i, userAccounts);
            }
        }
        return userAccounts;
    }

    public String getPassword(Account account) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "getPassword: " + account + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                return readPasswordInternal(userAccountsForCaller, account);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    private String readPasswordInternal(UserAccounts userAccounts, Account account) {
        if (account == null) {
            return null;
        }
        synchronized (userAccounts.cacheLock) {
            Cursor query = userAccounts.openHelper.getReadableDatabase().query("accounts", new String[]{"password"}, "name=? AND type=?", new String[]{account.name, account.type}, (String) null, (String) null, (String) null);
            try {
                if (query.moveToNext()) {
                    String string = query.getString(0);
                    return string;
                }
                query.close();
                return null;
            } finally {
                query.close();
            }
        }
    }

    public String getUserData(Account account, String str) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "getUserData: " + account + ", key " + str + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                return readUserDataInternal(userAccountsForCaller, account, str);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("key is null");
        }
    }

    public AuthenticatorDescription[] getAuthenticatorTypes() {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "getAuthenticatorTypes: caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        long clearCallingIdentity = clearCallingIdentity();
        try {
            Collection<AccountAuthenticatorCache.ServiceInfo<AuthenticatorDescription>> allServices = this.mAuthenticatorCache.getAllServices();
            AuthenticatorDescription[] authenticatorDescriptionArr = new AuthenticatorDescription[allServices.size()];
            int i = 0;
            for (AccountAuthenticatorCache.ServiceInfo<AuthenticatorDescription> serviceInfo : allServices) {
                authenticatorDescriptionArr[i] = (AuthenticatorDescription) serviceInfo.type;
                i++;
            }
            return authenticatorDescriptionArr;
        } finally {
            restoreCallingIdentity(clearCallingIdentity);
        }
    }

    public boolean addAccount(Account account, String str, Bundle bundle) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "addAccount: " + account + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                return addAccountInternal(userAccountsForCaller, account, str, bundle);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    private boolean addAccountInternal(UserAccounts userAccounts, Account account, String str, Bundle bundle) {
        Account account2 = account;
        Bundle bundle2 = bundle;
        if (account2 == null) {
            return false;
        }
        synchronized (userAccounts.cacheLock) {
            try {
                SQLiteDatabase writableDatabase = userAccounts.openHelper.getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    if (DatabaseUtils.longForQuery(writableDatabase, "select count(*) from accounts WHERE name=? AND type=?", new String[]{account2.name, account2.type}) > 0) {
                        AccountLog.w(TAG, "insertAccountIntoDatabase: " + account2 + ", skipping since the account already exists");
                        writableDatabase.endTransaction();
                        return false;
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", account2.name);
                    contentValues.put("type", account2.type);
                    contentValues.put("password", str);
                    long insert = writableDatabase.insert("accounts", "name", contentValues);
                    if (insert < 0) {
                        AccountLog.w(TAG, "insertAccountIntoDatabase: " + account2 + ", skipping the DB insert failed");
                        writableDatabase.endTransaction();
                        return false;
                    }
                    if (bundle2 != null) {
                        for (String str2 : bundle.keySet()) {
                            String str3 = str2;
                            if (insertExtraLocked(writableDatabase, insert, str2, bundle2.getString(str2)) < 0) {
                                AccountLog.w(TAG, "insertAccountIntoDatabase: " + account2 + ", skipping since insertExtra failed for key " + str3);
                                writableDatabase.endTransaction();
                                return false;
                            }
                        }
                    }
                    writableDatabase.setTransactionSuccessful();
                    insertAccountIntoCacheLocked(userAccounts, account);
                    writableDatabase.endTransaction();
                    sendAccountsChangedBroadcast(userAccounts.userId);
                    return true;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    private long insertExtraLocked(SQLiteDatabase sQLiteDatabase, long j, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", str);
        contentValues.put("accounts_id", Long.valueOf(j));
        contentValues.put("value", str2);
        return sQLiteDatabase.insert("extras", "key", contentValues);
    }

    public void hasFeatures(IAccountManagerResponse iAccountManagerResponse, Account account, String[] strArr) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "hasFeatures: " + account + ", response " + iAccountManagerResponse + ", features " + stringArrayToString(strArr) + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (strArr != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                new TestFeaturesSession(userAccountsForCaller, iAccountManagerResponse, account, strArr).bind();
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("features is null");
        }
    }

    private class TestFeaturesSession extends Session {
        private final Account mAccount;
        private final String[] mFeatures;

        public TestFeaturesSession(UserAccounts userAccounts, IAccountManagerResponse iAccountManagerResponse, Account account, String[] strArr) {
            super(userAccounts, iAccountManagerResponse, account.type, false, true);
            this.mFeatures = strArr;
            this.mAccount = account;
        }

        public void run() throws RemoteException {
            try {
                this.mAuthenticator.hasFeatures(this, this.mAccount, this.mFeatures);
            } catch (RemoteException unused) {
                onError(1, "remote exception");
            }
        }

        public void onResult(Bundle bundle) {
            IAccountManagerResponse responseAndClose = getResponseAndClose();
            if (responseAndClose == null) {
                return;
            }
            if (bundle == null) {
                try {
                    responseAndClose.onError(5, "null bundle");
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        AccountLog.v(AccountManagerService.TAG, "failure while notifying response", e);
                    }
                }
            } else {
                if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                    AccountLog.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + responseAndClose);
                }
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("booleanResult", bundle.getBoolean("booleanResult", false));
                responseAndClose.onResult(bundle2);
            }
        }

        /* access modifiers changed from: protected */
        public String toDebugString(long j) {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toDebugString(j));
            sb.append(", hasFeatures, ");
            sb.append(this.mAccount);
            sb.append(", ");
            sb.append(this.mFeatures != null ? TextUtils.join(",", this.mFeatures) : null);
            return sb.toString();
        }
    }

    public void removeAccount(IAccountManagerResponse iAccountManagerResponse, Account account) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "removeAccount: " + account + ", response " + iAccountManagerResponse + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                new RemoveAccountSession(userAccountsForCaller, iAccountManagerResponse, account).bind();
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    private class RemoveAccountSession extends Session {
        final Account mAccount;

        public RemoveAccountSession(UserAccounts userAccounts, IAccountManagerResponse iAccountManagerResponse, Account account) {
            super(userAccounts, iAccountManagerResponse, account.type, false, true);
            this.mAccount = account;
        }

        /* access modifiers changed from: protected */
        public String toDebugString(long j) {
            return super.toDebugString(j) + ", removeAccount, account " + this.mAccount;
        }

        public void run() throws RemoteException {
            this.mAuthenticator.getAccountRemovalAllowed(this, this.mAccount);
        }

        public void onResult(Bundle bundle) {
            if (bundle != null && bundle.containsKey("booleanResult") && !bundle.containsKey("intent")) {
                boolean z = bundle.getBoolean("booleanResult");
                if (z) {
                    AccountManagerService.this.removeAccountInternal(this.mAccounts, this.mAccount);
                }
                IAccountManagerResponse responseAndClose = getResponseAndClose();
                if (responseAndClose != null) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        AccountLog.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + responseAndClose);
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean("booleanResult", z);
                    try {
                        responseAndClose.onResult(bundle2);
                    } catch (RemoteException unused) {
                    }
                }
            }
            super.onResult(bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void removeAccountInternal(Account account) {
        removeAccountInternal(getUserAccountsForCaller(), account);
    }

    /* access modifiers changed from: private */
    public void removeAccountInternal(UserAccounts userAccounts, Account account) {
        synchronized (userAccounts.cacheLock) {
            userAccounts.openHelper.getWritableDatabase().delete("accounts", "name=? AND type=?", new String[]{account.name, account.type});
            removeAccountFromCacheLocked(userAccounts, account);
            sendAccountsChangedBroadcast(userAccounts.userId);
        }
    }

    public void invalidateAuthToken(String str, String str2) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "invalidateAuthToken: accountType " + str + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (str == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (str2 != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                synchronized (userAccountsForCaller.cacheLock) {
                    SQLiteDatabase writableDatabase = userAccountsForCaller.openHelper.getWritableDatabase();
                    writableDatabase.beginTransaction();
                    try {
                        invalidateAuthTokenLocked(userAccountsForCaller, writableDatabase, str, str2);
                        writableDatabase.setTransactionSuccessful();
                    } finally {
                        writableDatabase.endTransaction();
                    }
                }
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("authToken is null");
        }
    }

    private void invalidateAuthTokenLocked(UserAccounts userAccounts, SQLiteDatabase sQLiteDatabase, String str, String str2) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        String str3 = str;
        if (str2 != null && str3 != null) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT authtokens._id, accounts.name, authtokens.type FROM accounts JOIN authtokens ON accounts._id = accounts_id WHERE authtoken = ? AND accounts.type = ?", new String[]{str2, str3});
            while (rawQuery.moveToNext()) {
                try {
                    long j = rawQuery.getLong(0);
                    String string = rawQuery.getString(1);
                    String string2 = rawQuery.getString(2);
                    sQLiteDatabase.delete(TABLE_AUTHTOKENS, "_id=" + j, (String[]) null);
                    writeAuthTokenIntoCacheLocked(userAccounts, sQLiteDatabase, new Account(string, str3), string2, (String) null);
                } finally {
                    rawQuery.close();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean saveAuthTokenToDatabase(UserAccounts userAccounts, Account account, String str, String str2) {
        if (account == null || str == null) {
            return false;
        }
        synchronized (userAccounts.cacheLock) {
            SQLiteDatabase writableDatabase = userAccounts.openHelper.getWritableDatabase();
            writableDatabase.beginTransaction();
            try {
                long accountIdLocked = getAccountIdLocked(writableDatabase, account);
                if (accountIdLocked < 0) {
                    return false;
                }
                writableDatabase.delete(TABLE_AUTHTOKENS, "accounts_id=" + accountIdLocked + " AND " + "type" + "=?", new String[]{str});
                ContentValues contentValues = new ContentValues();
                contentValues.put("accounts_id", Long.valueOf(accountIdLocked));
                contentValues.put("type", str);
                contentValues.put("authtoken", str2);
                if (writableDatabase.insert(TABLE_AUTHTOKENS, "authtoken", contentValues) >= 0) {
                    writableDatabase.setTransactionSuccessful();
                    writeAuthTokenIntoCacheLocked(userAccounts, writableDatabase, account, str, str2);
                    writableDatabase.endTransaction();
                    return true;
                }
                writableDatabase.endTransaction();
                return false;
            } finally {
                writableDatabase.endTransaction();
            }
        }
    }

    public String peekAuthToken(Account account, String str) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "peekAuthToken: " + account + ", authTokenType " + str + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                return readAuthTokenInternal(userAccountsForCaller, account, str);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public void setAuthToken(Account account, String str, String str2) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "setAuthToken: " + account + ", authTokenType " + str + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                saveAuthTokenToDatabase(userAccountsForCaller, account, str, str2);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public void setPassword(Account account, String str) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "setPassword: " + account + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                setPasswordInternal(userAccountsForCaller, account, str);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    /* JADX INFO: finally extract failed */
    private void setPasswordInternal(UserAccounts userAccounts, Account account, String str) {
        if (account != null) {
            synchronized (userAccounts.cacheLock) {
                SQLiteDatabase writableDatabase = userAccounts.openHelper.getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("password", str);
                    long accountIdLocked = getAccountIdLocked(writableDatabase, account);
                    if (accountIdLocked >= 0) {
                        String[] strArr = {String.valueOf(accountIdLocked)};
                        writableDatabase.update("accounts", contentValues, "_id=?", strArr);
                        writableDatabase.delete(TABLE_AUTHTOKENS, "accounts_id=?", strArr);
                        userAccounts.authTokenCache.remove(account);
                        writableDatabase.setTransactionSuccessful();
                    }
                    writableDatabase.endTransaction();
                    sendAccountsChangedBroadcast(userAccounts.userId);
                } catch (Throwable th) {
                    writableDatabase.endTransaction();
                    throw th;
                }
            }
        }
    }

    private void sendAccountsChangedBroadcast(int i) {
        AccountLog.i(TAG, "the accounts changed, sending broadcast of " + ACCOUNTS_CHANGED_INTENT.getAction());
        ACCOUNTS_CHANGED_INTENT.setPackage(this.mContext.getPackageName());
        this.mContext.sendBroadcast(ACCOUNTS_CHANGED_INTENT);
    }

    public void clearPassword(Account account) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "clearPassword: " + account + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (account != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                setPasswordInternal(userAccountsForCaller, account, (String) null);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    public void setUserData(Account account, String str, String str2) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "setUserData: " + account + ", key " + str + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (str == null) {
            throw new IllegalArgumentException("key is null");
        } else if (account != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                setUserdataInternal(userAccountsForCaller, account, str, str2);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    private void setUserdataInternal(UserAccounts userAccounts, Account account, String str, String str2) {
        if (account != null && str != null) {
            synchronized (userAccounts.cacheLock) {
                SQLiteDatabase writableDatabase = userAccounts.openHelper.getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    long accountIdLocked = getAccountIdLocked(writableDatabase, account);
                    if (accountIdLocked >= 0) {
                        long extrasIdLocked = getExtrasIdLocked(writableDatabase, accountIdLocked, str);
                        if (extrasIdLocked >= 0) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("value", str2);
                            if (1 != writableDatabase.update("extras", contentValues, "_id=" + extrasIdLocked, (String[]) null)) {
                                writableDatabase.endTransaction();
                                return;
                            }
                        } else if (insertExtraLocked(writableDatabase, accountIdLocked, str, str2) < 0) {
                            writableDatabase.endTransaction();
                            return;
                        }
                        writeUserDataIntoCacheLocked(userAccounts, writableDatabase, account, str, str2);
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                    }
                } finally {
                    writableDatabase.endTransaction();
                }
            }
        }
    }

    private void onResult(IAccountManagerResponse iAccountManagerResponse, Bundle bundle) {
        if (bundle == null) {
            AccountLog.e(TAG, "the result is unexpectedly null", new Exception());
        }
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, getClass().getSimpleName() + " calling onResult() on response " + iAccountManagerResponse);
        }
        try {
            iAccountManagerResponse.onResult(bundle);
        } catch (RemoteException e) {
            if (Log.isLoggable(TAG, 2)) {
                AccountLog.v(TAG, "failure while notifying response", e);
            }
        }
    }

    public void getAuthTokenLabel(IAccountManagerResponse iAccountManagerResponse, String str, String str2) throws RemoteException {
        if (str == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (str2 != null) {
            int callingUid = getCallingUid();
            clearCallingIdentity();
            if (callingUid == 1000) {
                UserAccounts userAccounts = getUserAccounts(UserId.getUserId(callingUid));
                long clearCallingIdentity = clearCallingIdentity();
                try {
                    final String str3 = str;
                    final String str4 = str2;
                    new Session(userAccounts, iAccountManagerResponse, str, false, false) {
                        /* access modifiers changed from: protected */
                        public String toDebugString(long j) {
                            return super.toDebugString(j) + ", getAuthTokenLabel, " + str3 + ", authTokenType " + str4;
                        }

                        public void run() throws RemoteException {
                            this.mAuthenticator.getAuthTokenLabel(this, str4);
                        }

                        public void onResult(Bundle bundle) {
                            if (bundle != null) {
                                String string = bundle.getString("authTokenLabelKey");
                                Bundle bundle2 = new Bundle();
                                bundle2.putString("authTokenLabelKey", string);
                                super.onResult(bundle2);
                                return;
                            }
                            super.onResult(bundle);
                        }
                    }.bind();
                } finally {
                    restoreCallingIdentity(clearCallingIdentity);
                }
            } else {
                throw new SecurityException("can only call from system");
            }
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public void getAuthToken(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, boolean z2, Bundle bundle) {
        IAccountManagerResponse iAccountManagerResponse2 = iAccountManagerResponse;
        Account account2 = account;
        String str2 = str;
        boolean z3 = z;
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "getAuthToken: " + account2 + ", response " + iAccountManagerResponse2 + ", authTokenType " + str2 + ", notifyOnAuthFailure " + z3 + ", expectActivityLaunch " + z2 + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        } else {
            boolean z4 = z2;
        }
        if (iAccountManagerResponse2 == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account2 == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str2 != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            this.mAuthenticatorCache.getServiceInfo(AuthenticatorDescription.newKey(account2.type));
            int callingUid = Binder.getCallingUid();
            final Bundle bundle2 = bundle == null ? new Bundle() : bundle;
            bundle2.putInt(AccountManager.KEY_CALLER_UID, callingUid);
            bundle2.putInt(AccountManager.KEY_CALLER_PID, Binder.getCallingPid());
            if (z3) {
                bundle2.putBoolean(AccountManager.KEY_NOTIFY_ON_FAILURE, true);
            }
            long clearCallingIdentity = clearCallingIdentity();
            try {
                String readAuthTokenInternal = readAuthTokenInternal(userAccountsForCaller, account2, str2);
                if (readAuthTokenInternal != null) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("authtoken", readAuthTokenInternal);
                    bundle3.putString("authAccount", account2.name);
                    bundle3.putString("accountType", account2.type);
                    onResult(iAccountManagerResponse2, bundle3);
                    return;
                }
                final Account account3 = account;
                final String str3 = str;
                final boolean z5 = z;
                new Session(userAccountsForCaller, iAccountManagerResponse, account2.type, z2, false) {
                    /* access modifiers changed from: protected */
                    public String toDebugString(long j) {
                        if (bundle2 != null) {
                            bundle2.keySet();
                        }
                        return super.toDebugString(j) + ", getAuthToken, " + account3 + ", authTokenType " + str3 + ", loginOptions " + bundle2 + ", notifyOnAuthFailure " + z5;
                    }

                    public void run() throws RemoteException {
                        this.mAuthenticator.getAuthToken(this, account3, str3, bundle2);
                    }

                    public void onResult(Bundle bundle) {
                        String string;
                        if (!(bundle == null || (string = bundle.getString("authtoken")) == null)) {
                            String string2 = bundle.getString("authAccount");
                            String string3 = bundle.getString("accountType");
                            if (TextUtils.isEmpty(string3) || TextUtils.isEmpty(string2)) {
                                onError(5, "the type and name should not be empty");
                                return;
                            }
                            boolean unused = AccountManagerService.this.saveAuthTokenToDatabase(this.mAccounts, new Account(string2, string3), str3, string);
                        }
                        super.onResult(bundle);
                    }
                }.bind();
                restoreCallingIdentity(clearCallingIdentity);
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public void addAcount(IAccountManagerResponse iAccountManagerResponse, String str, String str2, String[] strArr, boolean z, Bundle bundle) {
        IAccountManagerResponse iAccountManagerResponse2 = iAccountManagerResponse;
        String str3 = str;
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "addAccount: accountType " + str3 + ", response " + iAccountManagerResponse2 + ", authTokenType " + str2 + ", requiredFeatures " + stringArrayToString(strArr) + ", expectActivityLaunch " + z + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        } else {
            String str4 = str2;
            boolean z2 = z;
        }
        if (iAccountManagerResponse2 == null) {
            throw new IllegalArgumentException("response is null");
        } else if (str3 != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            int callingPid = Binder.getCallingPid();
            int callingUid = Binder.getCallingUid();
            final Bundle bundle2 = bundle == null ? new Bundle() : bundle;
            bundle2.putInt(AccountManager.KEY_CALLER_UID, callingUid);
            bundle2.putInt(AccountManager.KEY_CALLER_PID, callingPid);
            long clearCallingIdentity = clearCallingIdentity();
            try {
                final String str5 = str2;
                final String[] strArr2 = strArr;
                final String str6 = str;
                new Session(userAccountsForCaller, iAccountManagerResponse, str, z, true) {
                    public void run() throws RemoteException {
                        this.mAuthenticator.addAccount(this, this.mAccountType, str5, strArr2, bundle2);
                    }

                    /* access modifiers changed from: protected */
                    public String toDebugString(long j) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(super.toDebugString(j));
                        sb.append(", addAccount, accountType ");
                        sb.append(str6);
                        sb.append(", requiredFeatures ");
                        sb.append(strArr2 != null ? TextUtils.join(",", strArr2) : null);
                        return sb.toString();
                    }
                }.bind();
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("accountType is null");
        }
    }

    public void confirmCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, Bundle bundle, boolean z) {
        IAccountManagerResponse iAccountManagerResponse2 = iAccountManagerResponse;
        Account account2 = account;
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "confirmCredentials: " + account2 + ", response " + iAccountManagerResponse + ", expectActivityLaunch " + z + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        } else {
            boolean z2 = z;
        }
        if (iAccountManagerResponse2 == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account2 != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                final Account account3 = account;
                final Bundle bundle2 = bundle;
                new Session(userAccountsForCaller, iAccountManagerResponse, account2.type, z, true) {
                    public void run() throws RemoteException {
                        this.mAuthenticator.confirmCredentials(this, account3, bundle2);
                    }

                    /* access modifiers changed from: protected */
                    public String toDebugString(long j) {
                        return super.toDebugString(j) + ", confirmCredentials, " + account3;
                    }
                }.bind();
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("account is null");
        }
    }

    public void updateCredentials(IAccountManagerResponse iAccountManagerResponse, Account account, String str, boolean z, Bundle bundle) {
        IAccountManagerResponse iAccountManagerResponse2 = iAccountManagerResponse;
        Account account2 = account;
        String str2 = str;
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "updateCredentials: " + account2 + ", response " + iAccountManagerResponse2 + ", authTokenType " + str2 + ", expectActivityLaunch " + z + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        } else {
            boolean z2 = z;
        }
        if (iAccountManagerResponse2 == null) {
            throw new IllegalArgumentException("response is null");
        } else if (account2 == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str2 != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                final Account account3 = account;
                final String str3 = str;
                final Bundle bundle2 = bundle;
                new Session(userAccountsForCaller, iAccountManagerResponse, account2.type, z, true) {
                    public void run() throws RemoteException {
                        this.mAuthenticator.updateCredentials(this, account3, str3, bundle2);
                    }

                    /* access modifiers changed from: protected */
                    public String toDebugString(long j) {
                        if (bundle2 != null) {
                            bundle2.keySet();
                        }
                        return super.toDebugString(j) + ", updateCredentials, " + account3 + ", authTokenType " + str3 + ", loginOptions " + bundle2;
                    }
                }.bind();
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public void editProperties(IAccountManagerResponse iAccountManagerResponse, String str, boolean z) {
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "editProperties: accountType " + str + ", response " + iAccountManagerResponse + ", expectActivityLaunch " + z + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (str != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            try {
                final String str2 = str;
                new Session(userAccountsForCaller, iAccountManagerResponse, str, z, true) {
                    public void run() throws RemoteException {
                        this.mAuthenticator.editProperties(this, this.mAccountType);
                    }

                    /* access modifiers changed from: protected */
                    public String toDebugString(long j) {
                        return super.toDebugString(j) + ", editProperties, accountType " + str2;
                    }
                }.bind();
            } finally {
                restoreCallingIdentity(clearCallingIdentity);
            }
        } else {
            throw new IllegalArgumentException("accountType is null");
        }
    }

    private class GetAccountsByTypeAndFeatureSession extends Session {
        private volatile Account[] mAccountsOfType = null;
        private volatile ArrayList<Account> mAccountsWithFeatures = null;
        private volatile int mCurrentAccount = 0;
        private final String[] mFeatures;

        public GetAccountsByTypeAndFeatureSession(UserAccounts userAccounts, IAccountManagerResponse iAccountManagerResponse, String str, String[] strArr) {
            super(userAccounts, iAccountManagerResponse, str, false, true);
            this.mFeatures = strArr;
        }

        public void run() throws RemoteException {
            synchronized (this.mAccounts.cacheLock) {
                this.mAccountsOfType = AccountManagerService.this.getAccountsFromCacheLocked(this.mAccounts, this.mAccountType);
            }
            this.mAccountsWithFeatures = new ArrayList<>(this.mAccountsOfType.length);
            this.mCurrentAccount = 0;
            checkAccount();
        }

        public void checkAccount() {
            if (this.mCurrentAccount >= this.mAccountsOfType.length) {
                sendResult();
                return;
            }
            IAccountAuthenticator iAccountAuthenticator = this.mAuthenticator;
            if (iAccountAuthenticator != null) {
                try {
                    iAccountAuthenticator.hasFeatures(this, this.mAccountsOfType[this.mCurrentAccount], this.mFeatures);
                } catch (RemoteException unused) {
                    onError(1, "remote exception");
                }
            } else if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                AccountLog.v(AccountManagerService.TAG, "checkAccount: aborting session since we are no longer connected to the authenticator, " + toDebugString());
            }
        }

        public void onResult(Bundle bundle) {
            this.mNumResults++;
            if (bundle == null) {
                onError(5, "null bundle");
                return;
            }
            if (bundle.getBoolean("booleanResult", false)) {
                this.mAccountsWithFeatures.add(this.mAccountsOfType[this.mCurrentAccount]);
            }
            this.mCurrentAccount++;
            checkAccount();
        }

        public void sendResult() {
            IAccountManagerResponse responseAndClose = getResponseAndClose();
            if (responseAndClose != null) {
                try {
                    Account[] accountArr = new Account[this.mAccountsWithFeatures.size()];
                    for (int i = 0; i < accountArr.length; i++) {
                        accountArr[i] = this.mAccountsWithFeatures.get(i);
                    }
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        AccountLog.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + responseAndClose);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArray("accounts", accountArr);
                    responseAndClose.onResult(bundle);
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        AccountLog.v(AccountManagerService.TAG, "failure while notifying response", e);
                    }
                }
            }
        }

        /* access modifiers changed from: protected */
        public String toDebugString(long j) {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toDebugString(j));
            sb.append(", getAccountsByTypeAndFeatures, ");
            sb.append(this.mFeatures != null ? TextUtils.join(",", this.mFeatures) : null);
            return sb.toString();
        }
    }

    public Account[] getAccounts(int i) {
        Account[] accountsFromCacheLocked;
        UserAccounts userAccounts = getUserAccounts(i);
        long clearCallingIdentity = clearCallingIdentity();
        try {
            synchronized (userAccounts.cacheLock) {
                accountsFromCacheLocked = getAccountsFromCacheLocked(userAccounts, (String) null);
            }
            restoreCallingIdentity(clearCallingIdentity);
            return accountsFromCacheLocked;
        } catch (Throwable th) {
            restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    public Account[] getAccounts(String str) {
        Account[] accountsFromCacheLocked;
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "getAccounts: accountType " + str + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        UserAccounts userAccountsForCaller = getUserAccountsForCaller();
        long clearCallingIdentity = clearCallingIdentity();
        try {
            synchronized (userAccountsForCaller.cacheLock) {
                accountsFromCacheLocked = getAccountsFromCacheLocked(userAccountsForCaller, str);
            }
            restoreCallingIdentity(clearCallingIdentity);
            return accountsFromCacheLocked;
        } catch (Throwable th) {
            restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    public void getAccountsByFeatures(IAccountManagerResponse iAccountManagerResponse, String str, String[] strArr) {
        Account[] accountsFromCacheLocked;
        if (Log.isLoggable(TAG, 2)) {
            AccountLog.v(TAG, "getAccounts: accountType " + str + ", response " + iAccountManagerResponse + ", features " + stringArrayToString(strArr) + ", caller's uid " + Binder.getCallingUid() + ", pid " + Binder.getCallingPid());
        }
        if (iAccountManagerResponse == null) {
            throw new IllegalArgumentException("response is null");
        } else if (str != null) {
            UserAccounts userAccountsForCaller = getUserAccountsForCaller();
            long clearCallingIdentity = clearCallingIdentity();
            if (strArr != null) {
                try {
                    if (strArr.length != 0) {
                        new GetAccountsByTypeAndFeatureSession(userAccountsForCaller, iAccountManagerResponse, str, strArr).bind();
                        restoreCallingIdentity(clearCallingIdentity);
                        return;
                    }
                } catch (Throwable th) {
                    restoreCallingIdentity(clearCallingIdentity);
                    throw th;
                }
            }
            synchronized (userAccountsForCaller.cacheLock) {
                accountsFromCacheLocked = getAccountsFromCacheLocked(userAccountsForCaller, str);
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArray("accounts", accountsFromCacheLocked);
            onResult(iAccountManagerResponse, bundle);
            restoreCallingIdentity(clearCallingIdentity);
        } else {
            throw new IllegalArgumentException("accountType is null");
        }
    }

    private long getAccountIdLocked(SQLiteDatabase sQLiteDatabase, Account account) {
        Cursor query = sQLiteDatabase.query("accounts", new String[]{"_id"}, "name=? AND type=?", new String[]{account.name, account.type}, (String) null, (String) null, (String) null);
        try {
            if (query.moveToNext()) {
                return query.getLong(0);
            }
            query.close();
            return -1;
        } finally {
            query.close();
        }
    }

    private long getExtrasIdLocked(SQLiteDatabase sQLiteDatabase, long j, String str) {
        Cursor query = sQLiteDatabase.query("extras", new String[]{"_id"}, "accounts_id=" + j + " AND " + "key" + "=?", new String[]{str}, (String) null, (String) null, (String) null);
        try {
            if (query.moveToNext()) {
                return query.getLong(0);
            }
            query.close();
            return -1;
        } finally {
            query.close();
        }
    }

    private abstract class Session extends IAccountAuthenticatorResponse.Stub implements ServiceConnection, IBinder.DeathRecipient {
        final String mAccountType;
        protected final UserAccounts mAccounts;
        IAccountAuthenticator mAuthenticator = null;
        final long mCreationTime;
        final boolean mExpectActivityLaunch;
        private int mNumErrors = 0;
        private int mNumRequestContinued = 0;
        public int mNumResults = 0;
        IAccountManagerResponse mResponse;
        private final boolean mStripAuthTokenFromResult;

        public abstract void run() throws RemoteException;

        public Session(UserAccounts userAccounts, IAccountManagerResponse iAccountManagerResponse, String str, boolean z, boolean z2) {
            if (iAccountManagerResponse == null) {
                throw new IllegalArgumentException("response is null");
            } else if (str != null) {
                this.mAccounts = userAccounts;
                this.mStripAuthTokenFromResult = z2;
                this.mResponse = iAccountManagerResponse;
                this.mAccountType = str;
                this.mExpectActivityLaunch = z;
                this.mCreationTime = SystemClock.elapsedRealtime();
                synchronized (AccountManagerService.this.mSessions) {
                    AccountManagerService.this.mSessions.put(toString(), this);
                }
                try {
                    iAccountManagerResponse.asBinder().linkToDeath(this, 0);
                } catch (RemoteException unused) {
                    this.mResponse = null;
                    binderDied();
                }
            } else {
                throw new IllegalArgumentException("accountType is null");
            }
        }

        /* access modifiers changed from: package-private */
        public IAccountManagerResponse getResponseAndClose() {
            if (this.mResponse == null) {
                return null;
            }
            IAccountManagerResponse iAccountManagerResponse = this.mResponse;
            close();
            return iAccountManagerResponse;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
            r3.mResponse.asBinder().unlinkToDeath(r3, 0);
            r3.mResponse = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
            cancelTimeout();
            unbind();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
            if (r3.mResponse == null) goto L_0x002b;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void close() {
            /*
                r3 = this;
                com.xiaomi.accounts.AccountManagerService r0 = com.xiaomi.accounts.AccountManagerService.this
                java.util.LinkedHashMap r0 = r0.mSessions
                monitor-enter(r0)
                com.xiaomi.accounts.AccountManagerService r1 = com.xiaomi.accounts.AccountManagerService.this     // Catch:{ all -> 0x0032 }
                java.util.LinkedHashMap r1 = r1.mSessions     // Catch:{ all -> 0x0032 }
                java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0032 }
                java.lang.Object r1 = r1.remove(r2)     // Catch:{ all -> 0x0032 }
                if (r1 != 0) goto L_0x0019
                monitor-exit(r0)     // Catch:{ all -> 0x0032 }
                return
            L_0x0019:
                monitor-exit(r0)     // Catch:{ all -> 0x0032 }
                com.xiaomi.accounts.IAccountManagerResponse r0 = r3.mResponse
                if (r0 == 0) goto L_0x002b
                com.xiaomi.accounts.IAccountManagerResponse r0 = r3.mResponse
                android.os.IBinder r0 = r0.asBinder()
                r1 = 0
                r0.unlinkToDeath(r3, r1)
                r0 = 0
                r3.mResponse = r0
            L_0x002b:
                r3.cancelTimeout()
                r3.unbind()
                return
            L_0x0032:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0032 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accounts.AccountManagerService.Session.close():void");
        }

        public void binderDied() {
            this.mResponse = null;
            close();
        }

        /* access modifiers changed from: protected */
        public String toDebugString() {
            return toDebugString(SystemClock.elapsedRealtime());
        }

        /* access modifiers changed from: protected */
        public String toDebugString(long j) {
            StringBuilder sb = new StringBuilder();
            sb.append("Session: expectLaunch ");
            sb.append(this.mExpectActivityLaunch);
            sb.append(", connected ");
            sb.append(this.mAuthenticator != null);
            sb.append(", stats (");
            sb.append(this.mNumResults);
            sb.append("/");
            sb.append(this.mNumRequestContinued);
            sb.append("/");
            sb.append(this.mNumErrors);
            sb.append("), lifetime ");
            double d = (double) (j - this.mCreationTime);
            Double.isNaN(d);
            sb.append(d / 1000.0d);
            return sb.toString();
        }

        /* access modifiers changed from: package-private */
        public void bind() {
            if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                AccountLog.v(AccountManagerService.TAG, "initiating bind to authenticator type " + this.mAccountType);
            }
            if (!bindToAuthenticator(this.mAccountType)) {
                AccountLog.d(AccountManagerService.TAG, "bind attempt failed for " + toDebugString());
                onError(1, "bind failure");
            }
        }

        private void unbind() {
            if (this.mAuthenticator != null) {
                this.mAuthenticator = null;
                AccountManagerService.this.mContext.unbindService(this);
            }
        }

        public void scheduleTimeout() {
            AccountManagerService.this.mMessageHandler.sendMessageDelayed(AccountManagerService.this.mMessageHandler.obtainMessage(3, this), 60000);
        }

        public void cancelTimeout() {
            AccountManagerService.this.mMessageHandler.removeMessages(3, this);
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.mAuthenticator = IAccountAuthenticator.Stub.asInterface(iBinder);
            AccountManagerService.REMOTE_THREAD_POOL_EXECUTOR.execute(new Runnable() {
                public void run() {
                    try {
                        Session.this.run();
                    } catch (RemoteException unused) {
                        Session.this.onError(1, "remote exception");
                    }
                }
            });
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.mAuthenticator = null;
            IAccountManagerResponse responseAndClose = getResponseAndClose();
            if (responseAndClose != null) {
                try {
                    responseAndClose.onError(1, "disconnected");
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        AccountLog.v(AccountManagerService.TAG, "Session.onServiceDisconnected: caught RemoteException while responding", e);
                    }
                }
            }
        }

        public void onTimedOut() {
            IAccountManagerResponse responseAndClose = getResponseAndClose();
            if (responseAndClose != null) {
                try {
                    responseAndClose.onError(1, "timeout");
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        AccountLog.v(AccountManagerService.TAG, "Session.onTimedOut: caught RemoteException while responding", e);
                    }
                }
            }
        }

        public void onResult(Bundle bundle) {
            IAccountManagerResponse iAccountManagerResponse;
            this.mNumResults++;
            if (!this.mExpectActivityLaunch || bundle == null || !bundle.containsKey("intent")) {
                iAccountManagerResponse = getResponseAndClose();
            } else {
                iAccountManagerResponse = this.mResponse;
            }
            if (iAccountManagerResponse == null) {
                return;
            }
            if (bundle == null) {
                try {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        AccountLog.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onError() on response " + iAccountManagerResponse);
                    }
                    iAccountManagerResponse.onError(5, "null bundle returned");
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        AccountLog.v(AccountManagerService.TAG, "failure while notifying response", e);
                    }
                }
            } else {
                if (this.mStripAuthTokenFromResult) {
                    bundle.remove("authtoken");
                }
                if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                    AccountLog.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onResult() on response " + iAccountManagerResponse);
                }
                iAccountManagerResponse.onResult(bundle);
            }
        }

        public void onRequestContinued() {
            this.mNumRequestContinued++;
        }

        public void onError(int i, String str) {
            this.mNumErrors++;
            IAccountManagerResponse responseAndClose = getResponseAndClose();
            if (responseAndClose != null) {
                if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                    AccountLog.v(AccountManagerService.TAG, getClass().getSimpleName() + " calling onError() on response " + responseAndClose);
                }
                try {
                    responseAndClose.onError(i, str);
                } catch (RemoteException e) {
                    if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                        AccountLog.v(AccountManagerService.TAG, "Session.onError: caught RemoteException while responding", e);
                    }
                }
            } else if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                AccountLog.v(AccountManagerService.TAG, "Session.onError: already closed");
            }
        }

        private boolean bindToAuthenticator(String str) {
            AccountAuthenticatorCache.ServiceInfo<AuthenticatorDescription> serviceInfo = AccountManagerService.this.mAuthenticatorCache.getServiceInfo(AuthenticatorDescription.newKey(str));
            if (serviceInfo == null) {
                if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                    AccountLog.v(AccountManagerService.TAG, "there is no authenticator for " + str + ", bailing out");
                }
                return false;
            }
            Intent intent = new Intent();
            intent.setAction(AccountManager.ACTION_AUTHENTICATOR_INTENT);
            intent.setComponent(serviceInfo.componentName);
            if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                AccountLog.v(AccountManagerService.TAG, "performing bindService to " + serviceInfo.componentName);
            }
            if (AccountManagerService.this.mContext.bindService(intent, this, 1)) {
                return true;
            }
            if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                AccountLog.v(AccountManagerService.TAG, "bindService to " + serviceInfo.componentName + " failed");
            }
            return false;
        }
    }

    private class MessageHandler extends Handler {
        MessageHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 3) {
                ((Session) message.obj).onTimedOut();
                return;
            }
            throw new IllegalStateException("unhandled message: " + message.what);
        }
    }

    /* access modifiers changed from: private */
    public static String getDatabaseName(Context context, int i) {
        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "users/" + i);
        file.mkdirs();
        return new File(file, DATABASE_NAME).getPath();
    }

    static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, int i) {
            super(context, AccountManagerService.getDatabaseName(context, i), (SQLiteDatabase.CursorFactory) null, 4);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE accounts ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, type TEXT NOT NULL, password TEXT, UNIQUE(name,type))");
            sQLiteDatabase.execSQL("CREATE TABLE authtokens (  _id INTEGER PRIMARY KEY AUTOINCREMENT,  accounts_id INTEGER NOT NULL, type TEXT NOT NULL,  authtoken TEXT,  UNIQUE (accounts_id,type))");
            createGrantsTable(sQLiteDatabase);
            sQLiteDatabase.execSQL("CREATE TABLE extras ( _id INTEGER PRIMARY KEY AUTOINCREMENT, accounts_id INTEGER, key TEXT NOT NULL, value TEXT, UNIQUE(accounts_id,key))");
            sQLiteDatabase.execSQL("CREATE TABLE meta ( key TEXT PRIMARY KEY NOT NULL, value TEXT)");
            createAccountsDeletionTrigger(sQLiteDatabase);
        }

        private void createAccountsDeletionTrigger(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(" CREATE TRIGGER accountsDelete DELETE ON accounts BEGIN   DELETE FROM authtokens     WHERE accounts_id=OLD._id ;   DELETE FROM extras     WHERE accounts_id=OLD._id ;   DELETE FROM grants     WHERE accounts_id=OLD._id ; END");
        }

        private void createGrantsTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE grants (  accounts_id INTEGER NOT NULL, auth_token_type STRING NOT NULL,  uid INTEGER NOT NULL,  UNIQUE (accounts_id,auth_token_type,uid))");
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            AccountLog.e(AccountManagerService.TAG, "upgrade from version " + i + " to version " + i2);
            if (i == 1) {
                i++;
            }
            if (i == 2) {
                createGrantsTable(sQLiteDatabase);
                sQLiteDatabase.execSQL("DROP TRIGGER accountsDelete");
                createAccountsDeletionTrigger(sQLiteDatabase);
                i++;
            }
            if (i == 3) {
                sQLiteDatabase.execSQL("UPDATE accounts SET type = 'com.google' WHERE type == 'com.google.GAIA'");
            }
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (Log.isLoggable(AccountManagerService.TAG, 2)) {
                AccountLog.v(AccountManagerService.TAG, "opened database accounts.db");
            }
        }
    }

    public void updateAppPermission(Account account, String str, int i, boolean z) throws RemoteException {
        if (getCallingUid() != 1000) {
            throw new SecurityException();
        } else if (z) {
            grantAppPermission(account, str, i);
        } else {
            revokeAppPermission(account, str, i);
        }
    }

    private void grantAppPermission(Account account, String str, int i) {
        if (account == null || str == null) {
            AccountLog.e(TAG, "grantAppPermission: called with invalid arguments", new Exception());
            return;
        }
        UserAccounts userAccounts = getUserAccounts(UserId.getUserId(i));
        synchronized (userAccounts.cacheLock) {
            SQLiteDatabase writableDatabase = userAccounts.openHelper.getWritableDatabase();
            writableDatabase.beginTransaction();
            try {
                long accountIdLocked = getAccountIdLocked(writableDatabase, account);
                if (accountIdLocked >= 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("accounts_id", Long.valueOf(accountIdLocked));
                    contentValues.put(GRANTS_AUTH_TOKEN_TYPE, str);
                    contentValues.put("uid", Integer.valueOf(i));
                    writableDatabase.insert(TABLE_GRANTS, "accounts_id", contentValues);
                    writableDatabase.setTransactionSuccessful();
                }
            } finally {
                writableDatabase.endTransaction();
            }
        }
    }

    private void revokeAppPermission(Account account, String str, int i) {
        if (account == null || str == null) {
            AccountLog.e(TAG, "revokeAppPermission: called with invalid arguments", new Exception());
            return;
        }
        UserAccounts userAccounts = getUserAccounts(UserId.getUserId(i));
        synchronized (userAccounts.cacheLock) {
            SQLiteDatabase writableDatabase = userAccounts.openHelper.getWritableDatabase();
            writableDatabase.beginTransaction();
            try {
                long accountIdLocked = getAccountIdLocked(writableDatabase, account);
                if (accountIdLocked >= 0) {
                    writableDatabase.delete(TABLE_GRANTS, "accounts_id=? AND auth_token_type=? AND uid=?", new String[]{String.valueOf(accountIdLocked), str, String.valueOf(i)});
                    writableDatabase.setTransactionSuccessful();
                }
            } finally {
                writableDatabase.endTransaction();
            }
        }
    }

    private static final String stringArrayToString(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        return Operators.ARRAY_START_STR + TextUtils.join(",", strArr) + Operators.ARRAY_END_STR;
    }

    private void removeAccountFromCacheLocked(UserAccounts userAccounts, Account account) {
        Account[] accountArr = (Account[]) userAccounts.accountCache.get(account.type);
        if (accountArr != null) {
            ArrayList arrayList = new ArrayList();
            for (Account account2 : accountArr) {
                if (!account2.equals(account)) {
                    arrayList.add(account2);
                }
            }
            if (arrayList.isEmpty()) {
                userAccounts.accountCache.remove(account.type);
            } else {
                userAccounts.accountCache.put(account.type, (Account[]) arrayList.toArray(new Account[arrayList.size()]));
            }
        }
        userAccounts.userDataCache.remove(account);
        userAccounts.authTokenCache.remove(account);
    }

    private void insertAccountIntoCacheLocked(UserAccounts userAccounts, Account account) {
        Account[] accountArr = (Account[]) userAccounts.accountCache.get(account.type);
        int length = accountArr != null ? accountArr.length : 0;
        Account[] accountArr2 = new Account[(length + 1)];
        if (accountArr != null) {
            System.arraycopy(accountArr, 0, accountArr2, 0, length);
        }
        accountArr2[length] = account;
        userAccounts.accountCache.put(account.type, accountArr2);
    }

    /* access modifiers changed from: protected */
    public Account[] getAccountsFromCacheLocked(UserAccounts userAccounts, String str) {
        validateAccountsAndPopulateCache(userAccounts);
        if (str != null) {
            Account[] accountArr = (Account[]) userAccounts.accountCache.get(str);
            if (accountArr == null) {
                return EMPTY_ACCOUNT_ARRAY;
            }
            return (Account[]) Arrays.copyOf(accountArr, accountArr.length);
        }
        int i = 0;
        for (Account[] length : userAccounts.accountCache.values()) {
            i += length.length;
        }
        if (i == 0) {
            return EMPTY_ACCOUNT_ARRAY;
        }
        Account[] accountArr2 = new Account[i];
        int i2 = 0;
        for (Account[] accountArr3 : userAccounts.accountCache.values()) {
            System.arraycopy(accountArr3, 0, accountArr2, i2, accountArr3.length);
            i2 += accountArr3.length;
        }
        return accountArr2;
    }

    /* access modifiers changed from: protected */
    public void writeUserDataIntoCacheLocked(UserAccounts userAccounts, SQLiteDatabase sQLiteDatabase, Account account, String str, String str2) {
        HashMap<String, String> hashMap = (HashMap) userAccounts.userDataCache.get(account);
        if (hashMap == null) {
            hashMap = readUserDataForAccountFromDatabaseLocked(sQLiteDatabase, account);
            userAccounts.userDataCache.put(account, hashMap);
        }
        if (str2 == null) {
            hashMap.remove(str);
        } else {
            hashMap.put(str, str2);
        }
    }

    /* access modifiers changed from: protected */
    public void writeAuthTokenIntoCacheLocked(UserAccounts userAccounts, SQLiteDatabase sQLiteDatabase, Account account, String str, String str2) {
        HashMap<String, String> hashMap = (HashMap) userAccounts.authTokenCache.get(account);
        if (hashMap == null) {
            hashMap = readAuthTokensForAccountFromDatabaseLocked(sQLiteDatabase, account);
            userAccounts.authTokenCache.put(account, hashMap);
        }
        if (str2 == null) {
            hashMap.remove(str);
        } else {
            hashMap.put(str, str2);
        }
    }

    /* access modifiers changed from: protected */
    public String readAuthTokenInternal(UserAccounts userAccounts, Account account, String str) {
        String str2;
        synchronized (userAccounts.cacheLock) {
            HashMap<String, String> hashMap = (HashMap) userAccounts.authTokenCache.get(account);
            if (hashMap == null) {
                hashMap = readAuthTokensForAccountFromDatabaseLocked(userAccounts.openHelper.getReadableDatabase(), account);
                userAccounts.authTokenCache.put(account, hashMap);
            }
            str2 = hashMap.get(str);
        }
        return str2;
    }

    /* access modifiers changed from: protected */
    public String readUserDataInternal(UserAccounts userAccounts, Account account, String str) {
        String str2;
        synchronized (userAccounts.cacheLock) {
            HashMap<String, String> hashMap = (HashMap) userAccounts.userDataCache.get(account);
            if (hashMap == null || TextUtils.isEmpty(hashMap.get(str))) {
                hashMap = readUserDataForAccountFromDatabaseLocked(userAccounts.openHelper.getReadableDatabase(), account);
                userAccounts.userDataCache.put(account, hashMap);
            }
            str2 = hashMap.get(str);
        }
        return str2;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, String> readUserDataForAccountFromDatabaseLocked(SQLiteDatabase sQLiteDatabase, Account account) {
        HashMap<String, String> hashMap = new HashMap<>();
        Cursor query = sQLiteDatabase.query("extras", COLUMNS_EXTRAS_KEY_AND_VALUE, "accounts_id=(select _id FROM accounts WHERE name=? AND type=?)", new String[]{account.name, account.type}, (String) null, (String) null, (String) null);
        while (query.moveToNext()) {
            try {
                hashMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, String> readAuthTokensForAccountFromDatabaseLocked(SQLiteDatabase sQLiteDatabase, Account account) {
        HashMap<String, String> hashMap = new HashMap<>();
        Cursor query = sQLiteDatabase.query(TABLE_AUTHTOKENS, COLUMNS_AUTHTOKENS_TYPE_AND_AUTHTOKEN, "accounts_id=(select _id FROM accounts WHERE name=? AND type=?)", new String[]{account.name, account.type}, (String) null, (String) null, (String) null);
        while (query.moveToNext()) {
            try {
                hashMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return hashMap;
    }

    private int getCallingUid() {
        return this.mContext.getApplicationInfo().uid;
    }
}
