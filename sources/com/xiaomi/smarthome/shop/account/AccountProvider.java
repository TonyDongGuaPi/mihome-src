package com.xiaomi.smarthome.shop.account;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.OperationCanceledException;
import com.mipay.sdk.IMipayAccountProvider;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.miio.Miio;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AccountProvider implements IMipayAccountProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final String f22152a = "passportapi";
    public static final String b = "com.xiaomi";
    private static final String c = "AccountProvider";
    private static final String d = "weblogin:";
    /* access modifiers changed from: private */
    public final Context e;
    private final Handler f = new Handler(this.e.getMainLooper());

    private interface IAccountManagerResponse {
        void a();

        void a(int i, String str);

        void a(Bundle bundle);
    }

    public AccountProvider(Context context) {
        this.e = context;
    }

    public boolean isUseSystem() {
        boolean v = CoreApi.a().v();
        Miio.h(c, "isUseSystem(): " + v);
        return v;
    }

    public Account[] getAccounts() {
        String s = CoreApi.a().s();
        Miio.h(c, "getAccounts(): " + s);
        return new Account[]{new Account(s, "com.xiaomi")};
    }

    public Account[] getAccountsByType(String str) {
        Miio.h(c, "getAccountsByType(): " + str);
        return getAccounts();
    }

    public void invalidateAuthToken(String str, String str2) {
        Miio.h(c, "invalidateAuthToken(): " + str + ", " + str2);
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        Account account2 = account;
        String str2 = str;
        Bundle bundle2 = bundle;
        StringBuilder sb = new StringBuilder();
        sb.append("getAuthToken(): 1 ");
        sb.append(account);
        sb.append(", ");
        sb.append(str);
        sb.append(", ");
        Activity activity2 = activity;
        sb.append(activity);
        Miio.h(c, sb.toString());
        if (account2 == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str2 != null) {
            final Bundle bundle3 = new Bundle();
            if (bundle2 != null) {
                bundle3.putAll(bundle);
            }
            bundle3.putString(AccountManager.KEY_ANDROID_PACKAGE_NAME, this.e.getPackageName());
            final Account account3 = account;
            final String str3 = str;
            return new AmsTask(activity, handler, accountManagerCallback) {
                public void a() {
                    AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public Void doInBackground(Void... voidArr) {
                            AccountProvider.this.a(AnonymousClass1.this.f, account3, str3, false, true, bundle3);
                            return null;
                        }
                    }, new Void[0]);
                }
            }.b();
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public AccountManagerFuture<Bundle> getAuthToken(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        Account account2 = account;
        String str2 = str;
        Bundle bundle2 = bundle;
        StringBuilder sb = new StringBuilder();
        sb.append("getAuthToken(): 2 ");
        sb.append(account);
        sb.append(", ");
        sb.append(str);
        sb.append(", ");
        boolean z2 = z;
        sb.append(z);
        Miio.h(c, sb.toString());
        if (account2 == null) {
            throw new IllegalArgumentException("account is null");
        } else if (str2 != null) {
            final Bundle bundle3 = new Bundle();
            if (bundle2 != null) {
                bundle3.putAll(bundle);
            }
            bundle3.putString(AccountManager.KEY_ANDROID_PACKAGE_NAME, this.e.getPackageName());
            final Boolean valueOf = Boolean.valueOf(z);
            final Account account3 = account;
            final String str3 = str;
            return new AmsTask((Activity) null, handler, accountManagerCallback) {
                public void a() {
                    AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                        /* access modifiers changed from: protected */
                        /* renamed from: a */
                        public Void doInBackground(Void... voidArr) {
                            AccountProvider.this.a(AnonymousClass2.this.f, account3, str3, valueOf.booleanValue(), false, bundle3);
                            return null;
                        }
                    }, new Void[0]);
                }
            }.b();
        } else {
            throw new IllegalArgumentException("authTokenType is null");
        }
    }

    public AccountManagerFuture<Bundle> addAccount(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        Miio.h(c, "addAccount(): " + str + ", " + str2);
        return null;
    }

    private String a() {
        String w = CoreApi.a().w();
        Miio.h(c, "getPassToken(): " + w);
        return w;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009b A[Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.smarthome.shop.account.AccountProvider.IAccountManagerResponse r5, android.accounts.Account r6, java.lang.String r7, boolean r8, boolean r9, android.os.Bundle r10) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x017c
            if (r6 == 0) goto L_0x0174
            if (r7 == 0) goto L_0x016c
            android.os.Bundle r8 = new android.os.Bundle
            r8.<init>()
            java.lang.String r9 = r4.a()
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 != 0) goto L_0x015b
            java.lang.String r10 = "com.xiaomi"
            java.lang.String r0 = r6.type
            boolean r10 = r10.equals(r0)
            if (r10 != 0) goto L_0x0021
            goto L_0x015b
        L_0x0021:
            boolean r10 = android.text.TextUtils.isEmpty(r7)
            r0 = 0
            if (r10 == 0) goto L_0x0034
            java.lang.String r7 = "AccountProvider"
            java.lang.String r10 = "getting auth token, but no service url contained, use micloud"
            com.xiaomi.smarthome.miio.Miio.h(r7, r10)
            java.lang.String r7 = "passportapi"
        L_0x0031:
            r10 = r7
            r7 = r0
            goto L_0x005f
        L_0x0034:
            java.lang.String r10 = "weblogin:"
            boolean r10 = r7.startsWith(r10)
            if (r10 == 0) goto L_0x0031
            java.lang.String r10 = "passportapi"
            java.lang.String r1 = "weblogin:"
            int r1 = r1.length()
            java.lang.String r7 = r7.substring(r1)
            boolean r1 = com.xiaomi.smarthome.shop.account.AccountHelper.a(r7)
            if (r1 != 0) goto L_0x005f
            java.lang.String r6 = "errorCode"
            r7 = 7
            r8.putInt(r6, r7)
            java.lang.String r6 = "errorMessage"
            java.lang.String r7 = "untrusted web sso url"
            r8.putString(r6, r7)
            r5.a(r8)
            return
        L_0x005f:
            r1 = 5
            if (r7 == 0) goto L_0x009b
            java.lang.String r10 = "AccountProvider"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            r2.<init>()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r3 = "getAuthToken(): webAutoLoginUrl = "
            r2.append(r3)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            r2.append(r7)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            com.xiaomi.smarthome.miio.Miio.h(r10, r2)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r6 = r6.name     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            com.xiaomi.accountsdk.account.data.AccountInfo r6 = com.xiaomi.smarthome.shop.account.AccountHelper.a(r6, r9, r0, r7)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            if (r6 == 0) goto L_0x009a
            java.lang.String r9 = "authAccount"
            java.lang.String r10 = r6.getUserId()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            r8.putString(r9, r10)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r9 = "accountType"
            r8.putString(r9, r7)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r7 = "authtoken"
            java.lang.String r6 = r6.getAutoLoginUrl()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            r8.putString(r7, r6)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            r5.a(r8)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
        L_0x009a:
            return
        L_0x009b:
            java.lang.String r7 = "AccountProvider"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            r0.<init>()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r2 = "getAuthToken(): serviceId = "
            r0.append(r2)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            r0.append(r10)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            com.xiaomi.smarthome.miio.Miio.h(r7, r0)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r6 = r6.name     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            com.xiaomi.accountsdk.account.data.AccountInfo r6 = com.xiaomi.smarthome.shop.account.AccountHelper.a(r6, r9, r10)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r7 = r6.getServiceToken()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r9 = r6.getSecurity()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            com.xiaomi.accountsdk.account.data.ExtendedAuthToken r7 = com.xiaomi.accountsdk.account.data.ExtendedAuthToken.build(r7, r9)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r7 = r7.toPlain()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r9 = "authAccount"
            java.lang.String r6 = r6.getUserId()     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            r8.putString(r9, r6)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r6 = "accountType"
            java.lang.String r9 = "com.xiaomi"
            r8.putString(r6, r9)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            java.lang.String r6 = "authtoken"
            r8.putString(r6, r7)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            r5.a(r8)     // Catch:{ IOException -> 0x0144, InvalidResponseException -> 0x0130, InvalidCredentialException -> 0x011c, AccessDeniedException -> 0x0108, AuthenticationFailureException -> 0x00f4, InvalidUserNameException -> 0x00e0 }
            return
        L_0x00e0:
            java.lang.String r6 = "AccountProvider"
            java.lang.String r7 = "no such a user"
            com.xiaomi.smarthome.miio.Miio.h(r6, r7)
            java.lang.String r6 = "errorCode"
            r8.putInt(r6, r1)
            java.lang.String r6 = "errorMessage"
            java.lang.String r7 = "no such user"
            r8.putString(r6, r7)
            goto L_0x0157
        L_0x00f4:
            java.lang.String r6 = "AccountProvider"
            java.lang.String r7 = "auth failure"
            com.xiaomi.smarthome.miio.Miio.h(r6, r7)
            java.lang.String r6 = "errorCode"
            r8.putInt(r6, r1)
            java.lang.String r6 = "errorMessage"
            java.lang.String r7 = "auth failure"
            r8.putString(r6, r7)
            goto L_0x0157
        L_0x0108:
            java.lang.String r6 = "AccountProvider"
            java.lang.String r7 = "access denied"
            com.xiaomi.smarthome.miio.Miio.h(r6, r7)
            java.lang.String r6 = "errorCode"
            r8.putInt(r6, r1)
            java.lang.String r6 = "errorMessage"
            java.lang.String r7 = "access denied"
            r8.putString(r6, r7)
            goto L_0x0157
        L_0x011c:
            java.lang.String r6 = "AccountProvider"
            java.lang.String r7 = "invalid credential, passToken is invalid"
            com.xiaomi.smarthome.miio.Miio.h(r6, r7)
            java.lang.String r6 = "errorCode"
            r8.putInt(r6, r1)
            java.lang.String r6 = "errorMessage"
            java.lang.String r7 = "invalid passToken"
            r8.putString(r6, r7)
            goto L_0x0157
        L_0x0130:
            java.lang.String r6 = "AccountProvider"
            java.lang.String r7 = "invalid response received when getting service token"
            com.xiaomi.smarthome.miio.Miio.h(r6, r7)
            java.lang.String r6 = "errorCode"
            r8.putInt(r6, r1)
            java.lang.String r6 = "errorMessage"
            java.lang.String r7 = "invalid response from server"
            r8.putString(r6, r7)
            goto L_0x0157
        L_0x0144:
            java.lang.String r6 = "AccountProvider"
            java.lang.String r7 = "io exception when getting service token"
            com.xiaomi.smarthome.miio.Miio.h(r6, r7)
            java.lang.String r6 = "errorCode"
            r8.putInt(r6, r1)
            java.lang.String r6 = "errorMessage"
            java.lang.String r7 = "io exception when getting service token"
            r8.putString(r6, r7)
        L_0x0157:
            r5.a(r8)
            return
        L_0x015b:
            java.lang.String r6 = "AccountProvider"
            java.lang.String r7 = "getAuthToken(): passToken null or account type error"
            com.xiaomi.smarthome.miio.Miio.h(r6, r7)
            java.lang.String r6 = "booleanResult"
            r7 = 0
            r8.putBoolean(r6, r7)
            r5.a(r8)
            return
        L_0x016c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "authTokenType is null"
            r5.<init>(r6)
            throw r5
        L_0x0174:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "account is null"
            r5.<init>(r6)
            throw r5
        L_0x017c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r6 = "response is null"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.shop.account.AccountProvider.a(com.xiaomi.smarthome.shop.account.AccountProvider$IAccountManagerResponse, android.accounts.Account, java.lang.String, boolean, boolean, android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public void a(Handler handler, final AccountManagerCallback<Bundle> accountManagerCallback, final AccountManagerFuture<Bundle> accountManagerFuture) {
        if (handler == null) {
            handler = this.f;
        }
        handler.post(new Runnable() {
            public void run() {
                accountManagerCallback.run(accountManagerFuture);
            }
        });
    }

    /* access modifiers changed from: private */
    public Exception a(int i, String str) {
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

    private abstract class AmsTask extends FutureTask<Bundle> implements AccountManagerFuture<Bundle> {
        final IAccountManagerResponse f = new Response();
        final Handler g;
        final AccountManagerCallback<Bundle> h;
        final Activity i;

        public abstract void a();

        public AmsTask(Activity activity, Handler handler, AccountManagerCallback<Bundle> accountManagerCallback) {
            super(new Callable<Bundle>() {
                /* renamed from: a */
                public Bundle call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
            this.g = handler;
            this.h = accountManagerCallback;
            this.i = activity;
        }

        public final AccountManagerFuture<Bundle> b() {
            a();
            return this;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void set(Bundle bundle) {
            if (bundle == null) {
                Miio.h(AccountProvider.c, "the bundle must not be null");
            }
            super.set(bundle);
        }

        private void d() {
            Looper myLooper = Looper.myLooper();
            if (myLooper != null && myLooper == AccountProvider.this.e.getMainLooper()) {
                IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
                Miio.h(AccountProvider.c, "calling this from your main thread can lead to deadlock and/or ANRs");
                if (AccountProvider.this.e.getApplicationInfo().targetSdkVersion >= 8) {
                    throw illegalStateException;
                }
            }
        }

        private Bundle a(Long l, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
            if (!isDone()) {
                d();
            }
            Miio.h(AccountProvider.c, "internalGetResult(): " + l + ", " + timeUnit);
            if (l == null) {
                try {
                    Bundle bundle = (Bundle) get();
                    cancel(true);
                    return bundle;
                } catch (InterruptedException | CancellationException | TimeoutException unused) {
                    cancel(true);
                    throw new RuntimeException();
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
                Bundle bundle2 = (Bundle) get(l.longValue(), timeUnit);
                cancel(true);
                return bundle2;
            }
        }

        /* renamed from: c */
        public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException {
            return a((Long) null, (TimeUnit) null);
        }

        /* renamed from: a */
        public Bundle getResult(long j2, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
            return a(Long.valueOf(j2), timeUnit);
        }

        /* access modifiers changed from: protected */
        public void done() {
            Miio.h(AccountProvider.c, "done()");
            if (this.h != null) {
                AccountProvider.this.a(this.g, this.h, (AccountManagerFuture<Bundle>) this);
            }
        }

        private class Response implements IAccountManagerResponse {
            private Response() {
            }

            public void a(Bundle bundle) {
                Intent intent = (Intent) bundle.getParcelable("intent");
                Miio.h(AccountProvider.c, "AmsTask::Response::onResult(): " + bundle.toString());
                if (intent != null && AmsTask.this.i != null) {
                    AmsTask.this.i.startActivity(intent);
                } else if (bundle.getBoolean("retry")) {
                    AmsTask.this.a();
                } else {
                    AmsTask.this.set(bundle);
                }
            }

            public void a(int i, String str) {
                Miio.h(AccountProvider.c, "onError(): " + i + ", " + str);
                if (i == 4) {
                    AmsTask.this.cancel(true);
                } else {
                    AmsTask.this.setException(AccountProvider.this.a(i, str));
                }
            }

            public void a() {
                Miio.h(AccountProvider.c, "onRequestContinued()");
            }
        }
    }
}
