package com.xiaomi.youpin.login.okhttpApi.api;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.xiaomi.accountsdk.futureservice.MiAccountManagerFuture;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.servicetoken.data.XmAccountVisibility;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.LoginRouter;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;

public class AccountManagerUtil {
    public static boolean a(Context context) {
        return c(context) != null;
    }

    public static String b(Context context) {
        Account c = c(context);
        if (c != null) {
            return c.name;
        }
        return "";
    }

    @Nullable
    public static Account c(Context context) {
        if (!MiAccountManager.get(context.getApplicationContext()).canUseSystem()) {
            return null;
        }
        return a(context, true);
    }

    @Nullable
    public static Account d(Context context) {
        return a(context, false);
    }

    @Nullable
    private static Account a(Context context, boolean z) {
        MiAccountManager miAccountManager = MiAccountManager.get(context.getApplicationContext());
        if (miAccountManager == null) {
            return null;
        }
        boolean isUseSystem = miAccountManager.isUseSystem();
        boolean isUseLocal = miAccountManager.isUseLocal();
        if (z) {
            miAccountManager.setUseSystem();
        } else {
            miAccountManager.setUseLocal();
        }
        Account[] accountsByType = miAccountManager.getAccountsByType("com.xiaomi");
        if (isUseSystem) {
            miAccountManager.setUseSystem();
        } else if (isUseLocal) {
            miAccountManager.setUseLocal();
        }
        if (accountsByType.length > 0) {
            return accountsByType[0];
        }
        return null;
    }

    public static void e(Context context) {
        Account a2 = a(context, false);
        if (a2 != null) {
            MiAccountManager.get(context).removeAccount(a2, (AccountManagerCallback<Boolean>) null, (Handler) null);
        }
        AuthenticatorUtil.clearAllXiaomiAccountCookies(context);
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void a(final Context context, final boolean z, final AccessAccountCallback accessAccountCallback) {
        MiAccountManager miAccountManager = MiAccountManager.get(context.getApplicationContext());
        if (miAccountManager == null) {
            accessAccountCallback.a(-9, "");
            return;
        }
        miAccountManager.setUseSystem();
        final MiAccountManagerFuture<XmAccountVisibility> canAccessAccount = miAccountManager.canAccessAccount(context);
        AsyncTaskUtils.a(new AsyncTask<Void, Void, Pair<XmAccountVisibility, Exception>>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.InterruptedException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.util.concurrent.ExecutionException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.InterruptedException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.InterruptedException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.InterruptedException} */
            /* access modifiers changed from: protected */
            /* JADX WARNING: Multi-variable type inference failed */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.util.Pair<com.xiaomi.passport.servicetoken.data.XmAccountVisibility, java.lang.Exception> doInBackground(java.lang.Void... r4) {
                /*
                    r3 = this;
                    r4 = 0
                    com.xiaomi.accountsdk.futureservice.MiAccountManagerFuture r0 = r0     // Catch:{ InterruptedException -> 0x0012, ExecutionException -> 0x000d }
                    java.lang.Object r0 = r0.get()     // Catch:{ InterruptedException -> 0x0012, ExecutionException -> 0x000d }
                    com.xiaomi.passport.servicetoken.data.XmAccountVisibility r0 = (com.xiaomi.passport.servicetoken.data.XmAccountVisibility) r0     // Catch:{ InterruptedException -> 0x0012, ExecutionException -> 0x000d }
                    r2 = r0
                    r0 = r4
                    r4 = r2
                    goto L_0x0016
                L_0x000d:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x0016
                L_0x0012:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x0016:
                    android.util.Pair r1 = new android.util.Pair
                    r1.<init>(r4, r0)
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil.AnonymousClass1.doInBackground(java.lang.Void[]):android.util.Pair");
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Pair<XmAccountVisibility, Exception> pair) {
                XmAccountVisibility xmAccountVisibility = (XmAccountVisibility) pair.first;
                if (xmAccountVisibility != null) {
                    switch (AnonymousClass2.f23542a[xmAccountVisibility.errorCode.ordinal()]) {
                        case 1:
                            if (xmAccountVisibility.visible) {
                                accessAccountCallback.a(xmAccountVisibility.account);
                                return;
                            }
                            accessAccountCallback.a(-10, "");
                            return;
                        case 2:
                            accessAccountCallback.a(-11, "");
                            return;
                        case 3:
                            accessAccountCallback.a(AccountManagerUtil.c(context));
                            return;
                        case 4:
                            accessAccountCallback.a(-13, "");
                            return;
                        case 5:
                            if (z) {
                                Intent intent = xmAccountVisibility.newChooseAccountIntent;
                                if (intent == null || intent.getComponent() == null) {
                                    accessAccountCallback.a(-14, "");
                                    return;
                                } else if ("android.accounts.ChooseTypeAndAccountActivity".equals(intent.getComponent().getClassName())) {
                                    accessAccountCallback.a(-14, "");
                                    return;
                                } else {
                                    LoginRouter.a(context, xmAccountVisibility, (AccessAccountCallback) new AccessAccountCallback() {
                                        public void a(Account account) {
                                            accessAccountCallback.a(account);
                                        }

                                        public void a(int i, String str) {
                                            accessAccountCallback.a(-14, "");
                                        }
                                    });
                                    return;
                                }
                            } else {
                                accessAccountCallback.a(-14, "");
                                return;
                            }
                        default:
                            return;
                    }
                }
            }
        }, new Void[0]);
    }

    /* renamed from: com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f23542a = new int[XmAccountVisibility.ErrorCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode[] r0 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f23542a = r0
                int[] r0 = f23542a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode r1 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.ERROR_NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f23542a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode r1 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.ERROR_NO_ACCOUNT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f23542a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode r1 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.ERROR_PRE_ANDROID_O     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f23542a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode r1 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.ERROR_NO_PERMISSION     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f23542a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.xiaomi.passport.servicetoken.data.XmAccountVisibility$ErrorCode r1 = com.xiaomi.passport.servicetoken.data.XmAccountVisibility.ErrorCode.ERROR_NOT_SUPPORT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil.AnonymousClass2.<clinit>():void");
        }
    }
}
