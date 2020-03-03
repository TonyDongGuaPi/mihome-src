package com.xiaomi.accounts;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorDescription;
import android.accounts.AuthenticatorException;
import android.accounts.OnAccountsUpdateListener;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.accounts.IAccountManagerResponse;
import com.xiaomi.accountsdk.utils.AccountLog;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AccountManager {
    public static final String ACTION_AUTHENTICATOR_INTENT = "com.xiaomi.accounts.AccountAuthenticator";
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
    public static final String KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
    public static final String KEY_AUTHENTICATOR_TYPES = "authenticator_types";
    public static final String KEY_AUTHTOKEN = "authtoken";
    public static final String KEY_AUTH_FAILED_MESSAGE = "authFailedMessage";
    public static final String KEY_AUTH_FAILED_TITLE = "authFailedTitle";
    public static final String KEY_AUTH_TOKEN_LABEL = "authTokenLabelKey";
    public static final String KEY_BOOLEAN_RESULT = "booleanResult";
    public static final String KEY_CALLER_PID = "callerPid";
    public static final String KEY_CALLER_UID = "callerUid";
    public static final String KEY_ERROR_CODE = "errorCode";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_INTENT = "intent";
    public static final String KEY_NOTIFY_ON_FAILURE = "notifyOnAuthFailure";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERDATA = "userdata";
    public static final String LOGIN_ACCOUNTS_CHANGED_ACTION = "com.xiaomi.accounts.LOGIN_ACCOUNTS_CHANGED";
    private static final String TAG = "AccountManager";
    private static volatile AccountManager sThis;
    private final BroadcastReceiver mAccountsChangedBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Account[] accounts = AccountManager.this.getAccounts();
            synchronized (AccountManager.this.mAccountsUpdatedListeners) {
                for (Map.Entry entry : AccountManager.this.mAccountsUpdatedListeners.entrySet()) {
                    AccountManager.this.postToHandler((Handler) entry.getValue(), (OnAccountsUpdateListener) entry.getKey(), accounts);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final HashMap<OnAccountsUpdateListener, Handler> mAccountsUpdatedListeners = new HashMap<>();
    private final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mMainHandler;
    /* access modifiers changed from: private */
    public AccountManagerService mService;

    private AccountManager(Context context) {
        this.mContext = context;
        this.mMainHandler = new Handler(this.mContext.getMainLooper());
        this.mService = new AccountManagerService(context);
    }

    public static Bundle sanitizeResult(Bundle bundle) {
        if (bundle == null || !bundle.containsKey("authtoken") || TextUtils.isEmpty(bundle.getString("authtoken"))) {
            return bundle;
        }
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putString("authtoken", "<omitted for logging purposes>");
        return bundle2;
    }

    public static AccountManager get(Context context) {
        if (context != null) {
            if (sThis == null) {
                synchronized (AccountManager.class) {
                    if (sThis == null) {
                        sThis = new AccountManager(context.getApplicationContext());
                    }
                }
            }
            return sThis;
        }
        throw new IllegalArgumentException("context is null");
    }

    public String getPassword(Account account) {
        if (account != null) {
            return this.mService.getPassword(account);
        }
        throw new IllegalArgumentException("account is null");
    }

    public String getUserData(Account account, String str) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            return this.mService.getUserData(account, str);
        } else {
            throw new IllegalArgumentException("key is null");
        }
    }

    public AuthenticatorDescription[] getAuthenticatorTypes() {
        return this.mService.getAuthenticatorTypes();
    }

    public Account[] getAccounts() {
        return this.mService.getAccounts((String) null);
    }

    public Account[] getAccountsByType(String str) {
        return this.mService.getAccounts(str);
    }

    public void updateAppPermission(Account account, String str, int i, boolean z) {
        try {
            this.mService.updateAppPermission(account, str, i, z);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public AccountManagerFuture<String> getAuthTokenLabel(String str, String str2, AccountManagerCallback<String> accountManagerCallback, Handler handler) {
        if (str == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (str2 != null) {
            final String str3 = str;
            final String str4 = str2;
            return new Future2Task<String>(handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.getAuthTokenLabel(this.mResponse, str3, str4);
                }

                public String bundleToResult(Bundle bundle) throws AuthenticatorException {
                    if (bundle.containsKey("authTokenLabelKey")) {
                        return bundle.getString("authTokenLabelKey");
                    }
                    throw new AuthenticatorException("no result in response");
                }
            }.start();
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public AccountManagerFuture<Boolean> hasFeatures(Account account, String[] strArr, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (strArr != null) {
            final Account account2 = account;
            final String[] strArr2 = strArr;
            return new Future2Task<Boolean>(handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.hasFeatures(this.mResponse, account2, strArr2);
                }

                public Boolean bundleToResult(Bundle bundle) throws AuthenticatorException {
                    if (bundle.containsKey("booleanResult")) {
                        return Boolean.valueOf(bundle.getBoolean("booleanResult"));
                    }
                    throw new AuthenticatorException("no result in response");
                }
            }.start();
        } else {
            throw new IllegalArgumentException("features is null");
        }
    }

    public AccountManagerFuture<Account[]> getAccountsByTypeAndFeatures(String str, String[] strArr, AccountManagerCallback<Account[]> accountManagerCallback, Handler handler) {
        if (str != null) {
            final String str2 = str;
            final String[] strArr2 = strArr;
            return new Future2Task<Account[]>(handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.getAccountsByFeatures(this.mResponse, str2, strArr2);
                }

                public Account[] bundleToResult(Bundle bundle) throws AuthenticatorException {
                    if (bundle.containsKey("accounts")) {
                        Parcelable[] parcelableArray = bundle.getParcelableArray("accounts");
                        Account[] accountArr = new Account[parcelableArray.length];
                        for (int i = 0; i < parcelableArray.length; i++) {
                            accountArr[i] = (Account) parcelableArray[i];
                        }
                        return accountArr;
                    }
                    throw new AuthenticatorException("no result in response");
                }
            }.start();
        }
        throw new IllegalArgumentException("type is null");
    }

    public boolean addAccountExplicitly(Account account, String str, Bundle bundle) {
        if (account != null) {
            return this.mService.addAccount(account, str, bundle);
        }
        throw new IllegalArgumentException("account is null");
    }

    public AccountManagerFuture<Boolean> removeAccount(final Account account, AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        if (account != null) {
            return new Future2Task<Boolean>(handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.removeAccount(this.mResponse, account);
                }

                public Boolean bundleToResult(Bundle bundle) throws AuthenticatorException {
                    if (bundle.containsKey("booleanResult")) {
                        return Boolean.valueOf(bundle.getBoolean("booleanResult"));
                    }
                    throw new AuthenticatorException("no result in response");
                }
            }.start();
        }
        throw new IllegalArgumentException("account is null");
    }

    public void invalidateAuthToken(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("accountType is null");
        } else if (str2 != null) {
            this.mService.invalidateAuthToken(str, str2);
        }
    }

    public String peekAuthToken(Account account, String str) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            return this.mService.peekAuthToken(account, str);
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public void setPassword(Account account, String str) {
        if (account != null) {
            this.mService.setPassword(account, str);
            return;
        }
        throw new IllegalArgumentException("account is null");
    }

    public void clearPassword(Account account) {
        if (account != null) {
            this.mService.clearPassword(account);
            return;
        }
        throw new IllegalArgumentException("account is null");
    }

    public void setUserData(Account account, String str, String str2) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            this.mService.setUserData(account, str, str2);
        } else {
            throw new IllegalArgumentException("key is null");
        }
    }

    public void setAuthToken(Account account, String str, String str2) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            this.mService.setAuthToken(account, str, str2);
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public String blockingGetAuthToken(Account account, String str, boolean z) throws OperationCanceledException, IOException, AuthenticatorException {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            Bundle result = getAuthToken(account, str, z, (AccountManagerCallback<Bundle>) null, (Handler) null).getResult();
            if (result != null) {
                return result.getString("authtoken");
            }
            AccountLog.e(TAG, "blockingGetAuthToken: null was returned from getResult() for " + account + ", authTokenType " + str);
            return null;
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            final Bundle bundle2 = new Bundle();
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            bundle2.putString(KEY_ANDROID_PACKAGE_NAME, this.mContext.getPackageName());
            final Account account2 = account;
            final String str2 = str;
            return new AmsTask(activity, handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.getAuthToken(this.mResponse, account2, str2, false, true, bundle2);
                }
            }.start();
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    @Deprecated
    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return getAuthToken(account, str, (Bundle) null, z, accountManagerCallback, handler);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (account == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str != null) {
            final Bundle bundle2 = new Bundle();
            if (bundle != null) {
                bundle2.putAll(bundle);
            }
            bundle2.putString(KEY_ANDROID_PACKAGE_NAME, this.mContext.getPackageName());
            final Account account2 = account;
            final String str2 = str;
            final boolean z2 = z;
            return new AmsTask((Activity) null, handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.getAuthToken(this.mResponse, account2, str2, z2, false, bundle2);
                }
            }.start();
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        Bundle bundle2 = bundle;
        if (str != null) {
            final Bundle bundle3 = new Bundle();
            if (bundle2 != null) {
                bundle3.putAll(bundle2);
            }
            bundle3.putString(KEY_ANDROID_PACKAGE_NAME, this.mContext.getPackageName());
            final String str3 = str;
            final String str4 = str2;
            final String[] strArr2 = strArr;
            final Activity activity2 = activity;
            return new AmsTask(activity, handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.addAcount(this.mResponse, str3, str4, strArr2, activity2 != null, bundle3);
                }
            }.start();
        }
        throw new IllegalArgumentException("accountType is null");
    }

    public AccountManagerFuture<Bundle> confirmCredentials(Account account, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (account != null) {
            final Account account2 = account;
            final Bundle bundle2 = bundle;
            final Activity activity2 = activity;
            return new AmsTask(activity, handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.confirmCredentials(this.mResponse, account2, bundle2, activity2 != null);
                }
            }.start();
        }
        throw new IllegalArgumentException("account is null");
    }

    public AccountManagerFuture<Bundle> updateCredentials(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (account != null) {
            final Account account2 = account;
            final String str2 = str;
            final Activity activity2 = activity;
            final Bundle bundle2 = bundle;
            return new AmsTask(activity, handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.updateCredentials(this.mResponse, account2, str2, activity2 != null, bundle2);
                }
            }.start();
        }
        throw new IllegalArgumentException("account is null");
    }

    public AccountManagerFuture<Bundle> editProperties(String str, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (str != null) {
            final String str2 = str;
            final Activity activity2 = activity;
            return new AmsTask(activity, handler, accountManagerCallback) {
                public void doWork() throws RemoteException {
                    AccountManager.this.mService.editProperties(this.mResponse, str2, activity2 != null);
                }
            }.start();
        }
        throw new IllegalArgumentException("accountType is null");
    }

    /* access modifiers changed from: private */
    public void ensureNotOnMainThread() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == this.mContext.getMainLooper()) {
            IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
            AccountLog.e(TAG, "calling this from your main thread can lead to deadlock and/or ANRs", illegalStateException);
            if (this.mContext.getApplicationInfo().targetSdkVersion >= 8) {
                throw illegalStateException;
            }
        }
    }

    /* access modifiers changed from: private */
    public void postToHandler(Handler handler, final AccountManagerCallback<Bundle> accountManagerCallback, final AccountManagerFuture<Bundle> accountManagerFuture) {
        if (handler == null) {
            handler = this.mMainHandler;
        }
        handler.post(new Runnable() {
            public void run() {
                accountManagerCallback.run(accountManagerFuture);
            }
        });
    }

    /* access modifiers changed from: private */
    public void postToHandler(Handler handler, final OnAccountsUpdateListener onAccountsUpdateListener, Account[] accountArr) {
        final Account[] accountArr2 = new Account[accountArr.length];
        System.arraycopy(accountArr, 0, accountArr2, 0, accountArr2.length);
        if (handler == null) {
            handler = this.mMainHandler;
        }
        handler.post(new Runnable() {
            public void run() {
                try {
                    onAccountsUpdateListener.onAccountsUpdated(accountArr2);
                } catch (SQLException e) {
                    AccountLog.e(AccountManager.TAG, "Can't update accounts", e);
                }
            }
        });
    }

    private abstract class AmsTask extends FutureTask<Bundle> implements AccountManagerFuture<Bundle> {
        final WeakReference<Activity> mActivity;
        final AccountManagerCallback<Bundle> mCallback;
        final Handler mHandler;
        final IAccountManagerResponse mResponse = new Response();

        public abstract void doWork() throws RemoteException;

        public AmsTask(Activity activity, Handler handler, AccountManagerCallback<Bundle> accountManagerCallback) {
            super(new Callable<Bundle>() {
                public Bundle call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
            this.mHandler = handler;
            this.mCallback = accountManagerCallback;
            this.mActivity = new WeakReference<>(activity);
        }

        public final AccountManagerFuture<Bundle> start() {
            try {
                doWork();
            } catch (RemoteException e) {
                setException(e);
            }
            return this;
        }

        /* access modifiers changed from: protected */
        public void set(Bundle bundle) {
            if (bundle == null) {
                AccountLog.e(AccountManager.TAG, "the bundle must not be null", new Exception());
            }
            super.set(bundle);
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0063 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.os.Bundle internalGetResult(java.lang.Long r4, java.util.concurrent.TimeUnit r5) throws android.accounts.OperationCanceledException, java.io.IOException, android.accounts.AuthenticatorException {
            /*
                r3 = this;
                boolean r0 = r3.isDone()
                if (r0 != 0) goto L_0x000b
                com.xiaomi.accounts.AccountManager r0 = com.xiaomi.accounts.AccountManager.this
                r0.ensureNotOnMainThread()
            L_0x000b:
                r0 = 1
                if (r4 != 0) goto L_0x001c
                java.lang.Object r4 = r3.get()     // Catch:{ CancellationException -> 0x0063, InterruptedException | TimeoutException -> 0x005a, ExecutionException -> 0x001a }
                android.os.Bundle r4 = (android.os.Bundle) r4     // Catch:{ CancellationException -> 0x0063, InterruptedException | TimeoutException -> 0x005a, ExecutionException -> 0x001a }
                r3.cancel(r0)
                return r4
            L_0x0018:
                r4 = move-exception
                goto L_0x0069
            L_0x001a:
                r4 = move-exception
                goto L_0x002a
            L_0x001c:
                long r1 = r4.longValue()     // Catch:{ CancellationException -> 0x0063, InterruptedException | TimeoutException -> 0x005a, ExecutionException -> 0x001a }
                java.lang.Object r4 = r3.get(r1, r5)     // Catch:{ CancellationException -> 0x0063, InterruptedException | TimeoutException -> 0x005a, ExecutionException -> 0x001a }
                android.os.Bundle r4 = (android.os.Bundle) r4     // Catch:{ CancellationException -> 0x0063, InterruptedException | TimeoutException -> 0x005a, ExecutionException -> 0x001a }
                r3.cancel(r0)
                return r4
            L_0x002a:
                java.lang.Throwable r4 = r4.getCause()     // Catch:{ all -> 0x0018 }
                boolean r5 = r4 instanceof java.io.IOException     // Catch:{ all -> 0x0018 }
                if (r5 != 0) goto L_0x0057
                boolean r5 = r4 instanceof java.lang.UnsupportedOperationException     // Catch:{ all -> 0x0018 }
                if (r5 != 0) goto L_0x0051
                boolean r5 = r4 instanceof android.accounts.AuthenticatorException     // Catch:{ all -> 0x0018 }
                if (r5 != 0) goto L_0x004e
                boolean r5 = r4 instanceof java.lang.RuntimeException     // Catch:{ all -> 0x0018 }
                if (r5 != 0) goto L_0x004b
                boolean r5 = r4 instanceof java.lang.Error     // Catch:{ all -> 0x0018 }
                if (r5 == 0) goto L_0x0045
                java.lang.Error r4 = (java.lang.Error) r4     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x0045:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0018 }
                r5.<init>(r4)     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x004b:
                java.lang.RuntimeException r4 = (java.lang.RuntimeException) r4     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x004e:
                android.accounts.AuthenticatorException r4 = (android.accounts.AuthenticatorException) r4     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x0051:
                android.accounts.AuthenticatorException r5 = new android.accounts.AuthenticatorException     // Catch:{ all -> 0x0018 }
                r5.<init>(r4)     // Catch:{ all -> 0x0018 }
                throw r5     // Catch:{ all -> 0x0018 }
            L_0x0057:
                java.io.IOException r4 = (java.io.IOException) r4     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x005a:
                r3.cancel(r0)
                android.accounts.OperationCanceledException r4 = new android.accounts.OperationCanceledException
                r4.<init>()
                throw r4
            L_0x0063:
                android.accounts.OperationCanceledException r4 = new android.accounts.OperationCanceledException     // Catch:{ all -> 0x0018 }
                r4.<init>()     // Catch:{ all -> 0x0018 }
                throw r4     // Catch:{ all -> 0x0018 }
            L_0x0069:
                r3.cancel(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accounts.AccountManager.AmsTask.internalGetResult(java.lang.Long, java.util.concurrent.TimeUnit):android.os.Bundle");
        }

        public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException {
            return internalGetResult((Long) null, (TimeUnit) null);
        }

        public Bundle getResult(long j, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
            return internalGetResult(Long.valueOf(j), timeUnit);
        }

        /* access modifiers changed from: protected */
        public void done() {
            if (this.mCallback != null) {
                AccountManager.this.postToHandler(this.mHandler, this.mCallback, (AccountManagerFuture<Bundle>) this);
            }
        }

        private class Response extends IAccountManagerResponse.Stub {
            public void onRequestContinued() throws RemoteException {
            }

            private Response() {
            }

            public void onResult(Bundle bundle) {
                Intent intent = (Intent) bundle.getParcelable("intent");
                if (intent != null && AmsTask.this.mActivity.get() != null) {
                    ((Activity) AmsTask.this.mActivity.get()).startActivity(intent);
                } else if (bundle.getBoolean("retry")) {
                    try {
                        AmsTask.this.doWork();
                    } catch (RemoteException unused) {
                    }
                } else {
                    AmsTask.this.set(bundle);
                }
            }

            public void onError(int i, String str) {
                if (i == 4) {
                    AmsTask.this.cancel(true);
                } else {
                    AmsTask.this.setException(AccountManager.this.convertErrorToException(i, str));
                }
            }
        }
    }

    private abstract class BaseFutureTask<T> extends FutureTask<T> {
        final Handler mHandler;
        public final IAccountManagerResponse mResponse = new Response();

        public abstract T bundleToResult(Bundle bundle) throws AuthenticatorException;

        public abstract void doWork() throws RemoteException;

        public BaseFutureTask(Handler handler) {
            super(new Callable<T>() {
                public T call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
            this.mHandler = handler;
        }

        /* access modifiers changed from: protected */
        public void postRunnableToHandler(Runnable runnable) {
            (this.mHandler == null ? AccountManager.this.mMainHandler : this.mHandler).post(runnable);
        }

        /* access modifiers changed from: protected */
        public void startTask() {
            try {
                doWork();
            } catch (RemoteException e) {
                setException(e);
            }
        }

        protected class Response extends IAccountManagerResponse.Stub {
            public void onRequestContinued() throws RemoteException {
            }

            protected Response() {
            }

            public void onResult(Bundle bundle) {
                try {
                    Object bundleToResult = BaseFutureTask.this.bundleToResult(bundle);
                    if (bundleToResult != null) {
                        BaseFutureTask.this.set(bundleToResult);
                    }
                } catch (AuthenticatorException | ClassCastException unused) {
                    onError(5, "no result in response");
                }
            }

            public void onError(int i, String str) {
                if (i == 4) {
                    BaseFutureTask.this.cancel(true);
                } else {
                    BaseFutureTask.this.setException(AccountManager.this.convertErrorToException(i, str));
                }
            }
        }
    }

    private abstract class Future2Task<T> extends BaseFutureTask<T> implements AccountManagerFuture<T> {
        final AccountManagerCallback<T> mCallback;

        public Future2Task(Handler handler, AccountManagerCallback<T> accountManagerCallback) {
            super(handler);
            this.mCallback = accountManagerCallback;
        }

        /* access modifiers changed from: protected */
        public void done() {
            if (this.mCallback != null) {
                postRunnableToHandler(new Runnable() {
                    public void run() {
                        Future2Task.this.mCallback.run(Future2Task.this);
                    }
                });
            }
        }

        public Future2Task<T> start() {
            startTask();
            return this;
        }

        private T internalGetResult(Long l, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
            if (!isDone()) {
                AccountManager.this.ensureNotOnMainThread();
            }
            if (l == null) {
                try {
                    T t = get();
                    cancel(true);
                    return t;
                } catch (InterruptedException | CancellationException | TimeoutException unused) {
                    cancel(true);
                    throw new OperationCanceledException();
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof IOException) {
                        throw ((IOException) cause);
                    } else if (cause instanceof UnsupportedOperationException) {
                        throw new AuthenticatorException(cause);
                    } else if (cause instanceof AuthenticatorException) {
                        throw ((AuthenticatorException) cause);
                    } else if (cause instanceof RuntimeException) {
                        throw ((RuntimeException) cause);
                    } else if (cause instanceof Error) {
                        throw ((Error) cause);
                    } else {
                        throw new IllegalStateException(cause);
                    }
                } catch (Throwable th) {
                    cancel(true);
                    throw th;
                }
            } else {
                T t2 = get(l.longValue(), timeUnit);
                cancel(true);
                return t2;
            }
        }

        public T getResult() throws OperationCanceledException, IOException, AuthenticatorException {
            return internalGetResult((Long) null, (TimeUnit) null);
        }

        public T getResult(long j, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
            return internalGetResult(Long.valueOf(j), timeUnit);
        }
    }

    /* access modifiers changed from: private */
    public Exception convertErrorToException(int i, String str) {
        if (i == 3) {
            return new IOException(str);
        }
        if (i == 6) {
            return new UnsupportedOperationException(str);
        }
        if (i == 5) {
            return new AuthenticatorException(str);
        }
        if (i == 7) {
            return new IllegalArgumentException(str);
        }
        return new AuthenticatorException(str);
    }

    private class GetAuthTokenByTypeAndFeaturesTask extends AmsTask implements AccountManagerCallback<Bundle> {
        final String mAccountType;
        final Bundle mAddAccountOptions;
        final String mAuthTokenType;
        final String[] mFeatures;
        volatile AccountManagerFuture<Bundle> mFuture = null;
        final Bundle mLoginOptions;
        final AccountManagerCallback<Bundle> mMyCallback;
        /* access modifiers changed from: private */
        public volatile int mNumAccounts = 0;

        GetAuthTokenByTypeAndFeaturesTask(String str, String str2, String[] strArr, Activity activity, Bundle bundle, Bundle bundle2, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
            super(activity, handler, accountManagerCallback);
            if (str != null) {
                this.mAccountType = str;
                this.mAuthTokenType = str2;
                this.mFeatures = strArr;
                this.mAddAccountOptions = bundle;
                this.mLoginOptions = bundle2;
                this.mMyCallback = this;
                return;
            }
            throw new IllegalArgumentException("account type is null");
        }

        public void doWork() throws RemoteException {
            AccountManager.this.getAccountsByTypeAndFeatures(this.mAccountType, this.mFeatures, new AccountManagerCallback<Account[]>() {
                public void run(AccountManagerFuture<Account[]> accountManagerFuture) {
                    try {
                        Account[] result = accountManagerFuture.getResult();
                        int unused = GetAuthTokenByTypeAndFeaturesTask.this.mNumAccounts = result.length;
                        if (result.length == 0) {
                            if (GetAuthTokenByTypeAndFeaturesTask.this.mActivity.get() != null) {
                                GetAuthTokenByTypeAndFeaturesTask.this.mFuture = AccountManager.this.addAccount(GetAuthTokenByTypeAndFeaturesTask.this.mAccountType, GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, GetAuthTokenByTypeAndFeaturesTask.this.mFeatures, GetAuthTokenByTypeAndFeaturesTask.this.mAddAccountOptions, (Activity) GetAuthTokenByTypeAndFeaturesTask.this.mActivity.get(), GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                                return;
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("authAccount", (String) null);
                            bundle.putString("accountType", (String) null);
                            bundle.putString("authtoken", (String) null);
                            try {
                                GetAuthTokenByTypeAndFeaturesTask.this.mResponse.onResult(bundle);
                            } catch (RemoteException unused2) {
                            }
                        } else if (result.length == 1) {
                            if (GetAuthTokenByTypeAndFeaturesTask.this.mActivity == null) {
                                GetAuthTokenByTypeAndFeaturesTask.this.mFuture = AccountManager.this.getAuthToken(result[0], GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, false, GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                                return;
                            }
                            GetAuthTokenByTypeAndFeaturesTask.this.mFuture = AccountManager.this.getAuthToken(result[0], GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, GetAuthTokenByTypeAndFeaturesTask.this.mLoginOptions, (Activity) GetAuthTokenByTypeAndFeaturesTask.this.mActivity.get(), GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                        } else if (GetAuthTokenByTypeAndFeaturesTask.this.mActivity != null) {
                            AnonymousClass1 r0 = new IAccountManagerResponse.Stub() {
                                public void onRequestContinued() throws RemoteException {
                                }

                                public void onResult(Bundle bundle) throws RemoteException {
                                    Account account = new Account(bundle.getString("authAccount"), bundle.getString("accountType"));
                                    GetAuthTokenByTypeAndFeaturesTask.this.mFuture = AccountManager.this.getAuthToken(account, GetAuthTokenByTypeAndFeaturesTask.this.mAuthTokenType, GetAuthTokenByTypeAndFeaturesTask.this.mLoginOptions, (Activity) GetAuthTokenByTypeAndFeaturesTask.this.mActivity.get(), GetAuthTokenByTypeAndFeaturesTask.this.mMyCallback, GetAuthTokenByTypeAndFeaturesTask.this.mHandler);
                                }

                                public void onError(int i, String str) throws RemoteException {
                                    GetAuthTokenByTypeAndFeaturesTask.this.mResponse.onError(i, str);
                                }
                            };
                            Intent intent = new Intent();
                            intent.setClassName("android", "android.accounts.ChooseAccountActivity");
                            intent.putExtra("accounts", result);
                            intent.putExtra("accountManagerResponse", new AccountManagerResponse((IAccountManagerResponse) r0));
                            ((Activity) GetAuthTokenByTypeAndFeaturesTask.this.mActivity.get()).startActivity(intent);
                        } else {
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("accounts", (String) null);
                            GetAuthTokenByTypeAndFeaturesTask.this.mResponse.onResult(bundle2);
                        }
                    } catch (OperationCanceledException e) {
                        GetAuthTokenByTypeAndFeaturesTask.this.setException(e);
                    } catch (IOException e2) {
                        GetAuthTokenByTypeAndFeaturesTask.this.setException(e2);
                    } catch (AuthenticatorException e3) {
                        GetAuthTokenByTypeAndFeaturesTask.this.setException(e3);
                    }
                }
            }, this.mHandler);
        }

        public void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            try {
                Bundle result = accountManagerFuture.getResult();
                if (this.mNumAccounts == 0) {
                    String string = result.getString("authAccount");
                    String string2 = result.getString("accountType");
                    if (!TextUtils.isEmpty(string)) {
                        if (!TextUtils.isEmpty(string2)) {
                            Account account = new Account(string, string2);
                            this.mNumAccounts = 1;
                            AccountManager.this.getAuthToken(account, this.mAuthTokenType, (Bundle) null, (Activity) this.mActivity.get(), this.mMyCallback, this.mHandler);
                            return;
                        }
                    }
                    setException(new AuthenticatorException("account not in result"));
                    return;
                }
                set(result);
            } catch (OperationCanceledException unused) {
                cancel(true);
            } catch (IOException e) {
                setException(e);
            } catch (AuthenticatorException e2) {
                setException(e2);
            }
        }
    }

    public AccountManagerFuture<Bundle> getAuthTokenByFeatures(String str, String str2, String[] strArr, Activity activity, Bundle bundle, Bundle bundle2, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (str == null) {
            throw new IllegalArgumentException("account type is null");
        } else if (str2 != null) {
            GetAuthTokenByTypeAndFeaturesTask getAuthTokenByTypeAndFeaturesTask = new GetAuthTokenByTypeAndFeaturesTask(str, str2, strArr, activity, bundle, bundle2, accountManagerCallback, handler);
            getAuthTokenByTypeAndFeaturesTask.start();
            return getAuthTokenByTypeAndFeaturesTask;
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public void addOnAccountsUpdatedListener(OnAccountsUpdateListener onAccountsUpdateListener, Handler handler, boolean z) {
        if (onAccountsUpdateListener != null) {
            synchronized (this.mAccountsUpdatedListeners) {
                if (!this.mAccountsUpdatedListeners.containsKey(onAccountsUpdateListener)) {
                    boolean isEmpty = this.mAccountsUpdatedListeners.isEmpty();
                    this.mAccountsUpdatedListeners.put(onAccountsUpdateListener, handler);
                    if (isEmpty) {
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction(LOGIN_ACCOUNTS_CHANGED_ACTION);
                        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
                        this.mContext.registerReceiver(this.mAccountsChangedBroadcastReceiver, intentFilter);
                    }
                } else {
                    throw new IllegalStateException("this listener is already added");
                }
            }
            if (z) {
                postToHandler(handler, onAccountsUpdateListener, getAccounts());
                return;
            }
            return;
        }
        throw new IllegalArgumentException("the listener is null");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeOnAccountsUpdatedListener(android.accounts.OnAccountsUpdateListener r3) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x002f
            java.util.HashMap<android.accounts.OnAccountsUpdateListener, android.os.Handler> r0 = r2.mAccountsUpdatedListeners
            monitor-enter(r0)
            java.util.HashMap<android.accounts.OnAccountsUpdateListener, android.os.Handler> r1 = r2.mAccountsUpdatedListeners     // Catch:{ all -> 0x002c }
            boolean r1 = r1.containsKey(r3)     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0016
            java.lang.String r3 = "AccountManager"
            java.lang.String r1 = "Listener was not previously added"
            com.xiaomi.accountsdk.utils.AccountLog.e(r3, r1)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x0016:
            java.util.HashMap<android.accounts.OnAccountsUpdateListener, android.os.Handler> r1 = r2.mAccountsUpdatedListeners     // Catch:{ all -> 0x002c }
            r1.remove(r3)     // Catch:{ all -> 0x002c }
            java.util.HashMap<android.accounts.OnAccountsUpdateListener, android.os.Handler> r3 = r2.mAccountsUpdatedListeners     // Catch:{ all -> 0x002c }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x002c }
            if (r3 == 0) goto L_0x002a
            android.content.Context r3 = r2.mContext     // Catch:{ all -> 0x002c }
            android.content.BroadcastReceiver r1 = r2.mAccountsChangedBroadcastReceiver     // Catch:{ all -> 0x002c }
            r3.unregisterReceiver(r1)     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r3
        L_0x002f:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "listener is null"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accounts.AccountManager.removeOnAccountsUpdatedListener(android.accounts.OnAccountsUpdateListener):void");
    }
}
