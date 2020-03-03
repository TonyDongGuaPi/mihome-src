package com.xiaomi.channel.sdk;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.channel.gamesdk.GameServiceClient;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class AccountManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10054a = "authtoken";
    public static final String b = "authAccount";
    public static final String c = "errorCode";
    public static final String d = "errorMessage";
    private static final String e = "service_token_pref";
    private static AccountManager f = null;
    private static final int i = 20001;
    private static final int j = 20002;
    private Context g;
    private int h = 0;

    public AccountManagerFuture<Bundle> a(String str, String str2, String[] strArr, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        return null;
    }

    public static synchronized AccountManager a(Context context) {
        AccountManager accountManager;
        synchronized (AccountManager.class) {
            if (f == null) {
                f = new AccountManager(context);
            }
            accountManager = f;
        }
        return accountManager;
    }

    public AccountManager(Context context) {
        this.g = context;
    }

    public Account[] a(String str) {
        if (GameServiceClient.a(this.g)) {
            return b(str);
        }
        return c(str);
    }

    private Account[] b(String str) {
        Bundle bundle;
        try {
            bundle = GameServiceClient.b(this.g).c();
        } catch (RemoteException e2) {
            Log.e(Constants.f10065a, e2.toString());
            bundle = null;
        }
        if (bundle != null) {
            int i2 = bundle.getInt("code");
            int i3 = bundle.getInt("flag");
            if (i2 == 0) {
                this.h = i3;
                String string = bundle.getString("data");
                if (!TextUtils.isEmpty(string)) {
                    return new Account[]{new Account(string, str)};
                }
            }
        }
        return new Account[0];
    }

    private Account[] c(String str) {
        Uri parse = Uri.parse("content://com.xiaomi.channel.providers.AccountProvider/getAccount");
        Cursor cursor = null;
        try {
            Cursor query = this.g.getContentResolver().query(parse, new String[]{"code", "name", "flag"}, (String) null, (String[]) null, (String) null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        int i2 = query.getInt(0);
                        int i3 = query.getInt(2);
                        if (i2 == 10001) {
                            this.h = i3;
                            String string = query.getString(1);
                            if (!TextUtils.isEmpty(string)) {
                                Account[] accountArr = {new Account(string, str)};
                                if (query != null && !query.isClosed()) {
                                    query.close();
                                }
                                return accountArr;
                            }
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = query;
                    try {
                        Log.e(Constants.f10065a, e.toString());
                        cursor.close();
                        return new Account[0];
                    } catch (Throwable th) {
                        th = th;
                        cursor.close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null && !query.isClosed()) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            Log.e(Constants.f10065a, e.toString());
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            return new Account[0];
        }
        return new Account[0];
    }

    public void a(String str, String str2) {
        SharedPreferences sharedPreferences = this.g.getSharedPreferences(e, 0);
        Map<String, ?> all = sharedPreferences.getAll();
        if (all != null) {
            for (String next : all.keySet()) {
                Object obj = all.get(next);
                if (obj != null && (obj instanceof String) && ((String) obj).equals(str2)) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.remove(next);
                    edit.commit();
                }
            }
        }
        android.accounts.AccountManager.get(this.g).invalidateAuthToken(str, str2);
    }

    public AccountManagerFuture<Bundle> a(Account account, String str, Bundle bundle, Activity activity, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (this.h == 20001) {
            final Account account2 = account;
            final String str2 = str;
            final Activity activity2 = activity;
            return new AccountManagerTask((Activity) null, handler, accountManagerCallback) {
                public void a() throws RemoteException {
                    final Account account = account2;
                    final String str = str2;
                    final Activity activity = activity2;
                    new Thread(new Runnable() {
                        public void run() {
                            AnonymousClass1.this.set(AccountManager.this.a(account.name, str, activity));
                        }
                    }).run();
                }
            }.b();
        } else if (this.h == 20002) {
            return android.accounts.AccountManager.get(this.g).getAuthToken(account, str, bundle, activity, accountManagerCallback, handler);
        } else {
            final Account account3 = account;
            return new AccountManagerTask((Activity) null, handler, accountManagerCallback) {
                public void a() throws RemoteException {
                    final Account account = account3;
                    new Thread(new Runnable() {
                        public void run() {
                            Bundle bundle = new Bundle();
                            bundle.putString("authtoken", (String) null);
                            bundle.putString("authAccount", account.name);
                            AnonymousClass2.this.set(bundle);
                        }
                    }).run();
                }
            }.b();
        }
    }

    public AccountManagerFuture<Bundle> a(Account account, String str, Bundle bundle, boolean z, AccountManagerCallback<Bundle> accountManagerCallback, Handler handler) {
        if (this.h == 20001) {
            final Account account2 = account;
            final String str2 = str;
            return new AccountManagerTask((Activity) null, handler, accountManagerCallback) {
                public void a() throws RemoteException {
                    final Account account = account2;
                    final String str = str2;
                    new Thread(new Runnable() {
                        public void run() {
                            AnonymousClass3.this.set(AccountManager.this.a(account.name, str, (Activity) null));
                        }
                    }).run();
                }
            }.b();
        } else if (this.h == 20002) {
            return android.accounts.AccountManager.get(this.g).getAuthToken(account, str, bundle, z, accountManagerCallback, handler);
        } else {
            final Account account3 = account;
            return new AccountManagerTask((Activity) null, handler, accountManagerCallback) {
                public void a() throws RemoteException {
                    final Account account = account3;
                    new Thread(new Runnable() {
                        public void run() {
                            Bundle bundle = new Bundle();
                            bundle.putString("authtoken", (String) null);
                            bundle.putString("authAccount", account.name);
                            AnonymousClass4.this.set(bundle);
                        }
                    }).run();
                }
            }.b();
        }
    }

    /* access modifiers changed from: private */
    public Bundle a(String str, String str2, Activity activity) {
        Bundle bundle = new Bundle();
        String b2 = b(str, str2);
        bundle.putString("authtoken", b2);
        bundle.putString("authAccount", str);
        if (TextUtils.isEmpty(b2)) {
            bundle = b(str, str2, activity);
            String string = bundle.getString("authtoken");
            if (!TextUtils.isEmpty(string)) {
                SharedPreferences.Editor edit = this.g.getSharedPreferences(e, 0).edit();
                edit.putString(String.valueOf(str2) + str, string);
                edit.commit();
            }
        }
        return bundle;
    }

    private String b(String str, String str2) {
        SharedPreferences sharedPreferences = this.g.getSharedPreferences(e, 0);
        return sharedPreferences.getString(String.valueOf(str2) + str, (String) null);
    }

    private Bundle b(String str, String str2, Activity activity) {
        if (GameServiceClient.a(this.g)) {
            return c(str, str2, activity);
        }
        return d(str, str2, activity);
    }

    private Bundle c(String str, String str2, final Activity activity) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        bundle2.putString("authAccount", str);
        bundle2.putString("authtoken", (String) null);
        try {
            bundle = GameServiceClient.b(this.g).b(str, str2);
        } catch (RemoteException e2) {
            Log.e(Constants.f10065a, e2.toString());
            bundle = null;
        }
        if (bundle != null) {
            int i2 = bundle.getInt("code");
            int i3 = bundle.getInt("flag");
            final String string = bundle.getString("data");
            if (i2 == 0) {
                bundle2.putString("authtoken", string);
            } else if (i3 == 20004) {
                if (activity != null && !activity.isFinishing()) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            Intent intent = new Intent("com.xiaomi.channel.VIEW_NOTIFICATION");
                            intent.putExtra("notification_url", string);
                            activity.startActivity(intent);
                        }
                    });
                }
                bundle2.putInt("errorCode", 20004);
                bundle2.putString("errorMessage", "xiaomi account needs upgrade");
            } else {
                bundle2.putInt("errorCode", i3);
                bundle2.putString("errorMessage", "xiaomi account error");
            }
        }
        return bundle2;
    }

    private Bundle d(String str, String str2, Activity activity) {
        Uri parse = Uri.parse("content://com.xiaomi.channel.providers.AccountProvider/getAuthToken");
        Log.v(Constants.f10065a, "get auth token from miliao...");
        Bundle bundle = new Bundle();
        Cursor cursor = null;
        bundle.putString("authtoken", (String) null);
        bundle.putString("authAccount", str);
        try {
            Cursor query = this.g.getContentResolver().query(parse, new String[]{"code", "token", "flag"}, (String) null, new String[]{str, str2}, (String) null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        if (query.getInt(0) == 10001) {
                            bundle.putString("authtoken", query.getString(1));
                            if (query != null && !query.isClosed()) {
                                query.close();
                            }
                            return bundle;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = query;
                    try {
                        Log.e(Constants.f10065a, e.toString());
                        cursor.close();
                        return bundle;
                    } catch (Throwable th) {
                        th = th;
                        cursor.close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null && !query.isClosed()) {
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            Log.e(Constants.f10065a, e.toString());
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            return bundle;
        }
        return bundle;
    }

    /* access modifiers changed from: private */
    public void a() {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && myLooper == this.g.getMainLooper()) {
            IllegalStateException illegalStateException = new IllegalStateException("calling this from your main thread can lead to deadlock");
            Log.e(Constants.f10065a, "calling this from your main thread can lead to deadlock and/or ANRs", illegalStateException);
            if (this.g.getApplicationInfo().targetSdkVersion >= 8) {
                throw illegalStateException;
            }
        }
    }

    private abstract class AccountManagerTask extends FutureTask<Bundle> implements AccountManagerFuture<Bundle> {
        final Handler b;
        final AccountManagerCallback<Bundle> c;
        final Activity d;

        public abstract void a() throws RemoteException;

        public AccountManagerTask(Activity activity, Handler handler, AccountManagerCallback<Bundle> accountManagerCallback) {
            super(new Callable<Bundle>() {
                /* renamed from: a */
                public Bundle call() throws Exception {
                    throw new IllegalStateException("this should never be called");
                }
            });
            this.b = handler;
            this.c = accountManagerCallback;
            this.d = activity;
        }

        public final AccountManagerFuture<Bundle> b() {
            try {
                a();
            } catch (RemoteException e2) {
                setException(e2);
            }
            return this;
        }

        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0063 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.os.Bundle a(java.lang.Long r4, java.util.concurrent.TimeUnit r5) throws android.accounts.OperationCanceledException, java.io.IOException, android.accounts.AuthenticatorException {
            /*
                r3 = this;
                boolean r0 = r3.isDone()
                if (r0 != 0) goto L_0x000b
                com.xiaomi.channel.sdk.AccountManager r0 = com.xiaomi.channel.sdk.AccountManager.this
                r0.a()
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
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.channel.sdk.AccountManager.AccountManagerTask.a(java.lang.Long, java.util.concurrent.TimeUnit):android.os.Bundle");
        }

        /* renamed from: c */
        public Bundle getResult() throws OperationCanceledException, IOException, AuthenticatorException {
            return a((Long) null, (TimeUnit) null);
        }

        /* renamed from: a */
        public Bundle getResult(long j, TimeUnit timeUnit) throws OperationCanceledException, IOException, AuthenticatorException {
            return a(Long.valueOf(j), timeUnit);
        }
    }
}
