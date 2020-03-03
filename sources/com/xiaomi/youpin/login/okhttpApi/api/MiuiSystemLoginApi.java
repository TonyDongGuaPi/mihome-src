package com.xiaomi.youpin.login.okhttpApi.api;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.servicetoken.ServiceTokenFuture;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.entity.system_account.AuthTokenResult;
import com.xiaomi.youpin.login.okhttpApi.AsyncServiceTimeDiffUtil;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;
import com.xiaomi.youpin.login.setting.LoginConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MiuiSystemLoginApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23555a = "MiuiSystemLoginApi";

    /* access modifiers changed from: private */
    public static void b(String str) {
    }

    private static Context a() {
        return MiLoginApi.a().o();
    }

    public static void a(boolean z, @Nullable Activity activity, String str, boolean z2, AsyncCallback<MiServiceTokenInfo, ExceptionError> asyncCallback) {
        String str2 = "loginMiBySystemAccount " + str;
        b(str2);
        if (!AccountManagerUtil.a(a())) {
            b(str2 + " none system account");
            if (asyncCallback != null) {
                asyncCallback.b(new ExceptionError(-2001, "AccountManager没有系统账户信息"));
            }
        } else if (z) {
            a(a(), activity, AccountManagerUtil.c(a()), str, a(str, z2, asyncCallback));
        } else {
            a(a(), str, a(str, z2, asyncCallback));
        }
    }

    private static AsyncCallback<AuthTokenResult, ExceptionError> a(final String str, final boolean z, final AsyncCallback<MiServiceTokenInfo, ExceptionError> asyncCallback) {
        return new AsyncCallback<AuthTokenResult, ExceptionError>() {
            public void a(AuthTokenResult authTokenResult) {
                final MiServiceTokenInfo miServiceTokenInfo = new MiServiceTokenInfo(str, authTokenResult.e, authTokenResult.c, authTokenResult.d, LoginConstant.a(str), 0);
                if (z) {
                    AsyncServiceTimeDiffUtil.a(new AsyncCallback<Long, Error>() {
                        public void a(Long l) {
                            miServiceTokenInfo.e = l.longValue();
                            if (asyncCallback != null) {
                                asyncCallback.b(miServiceTokenInfo);
                            }
                        }

                        public void a(Error error) {
                            if (asyncCallback != null) {
                                asyncCallback.b(new ExceptionError(LoginErrorCode.i, "获取timeDiff失败"));
                            }
                        }
                    });
                } else if (asyncCallback != null) {
                    asyncCallback.b(miServiceTokenInfo);
                }
                MiuiSystemLoginApi.b(str + " Get serviceToken onSuccess " + authTokenResult);
            }

            public void a(ExceptionError exceptionError) {
                MiuiSystemLoginApi.b(str + " Get serviceToken fail " + exceptionError);
                if (asyncCallback != null) {
                    asyncCallback.a(exceptionError);
                }
            }
        };
    }

    public static void a(boolean z, @Nullable Activity activity, @NonNull List<String> list, final AsyncCallback<List<MiServiceTokenInfo>, Error> asyncCallback) {
        b("loginMiBySystemAccountBatchSDK");
        final ArrayList arrayList = new ArrayList();
        final int size = list.size();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        for (String a2 : list) {
            a(z, activity, a2, false, (AsyncCallback<MiServiceTokenInfo, ExceptionError>) new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
                public void a(MiServiceTokenInfo miServiceTokenInfo) {
                    atomicInteger.incrementAndGet();
                    arrayList.add(miServiceTokenInfo);
                    if (atomicInteger.get() == size && asyncCallback != null) {
                        asyncCallback.b(arrayList);
                    }
                }

                public void a(ExceptionError exceptionError) {
                    atomicInteger.incrementAndGet();
                    if (atomicInteger.get() == size && asyncCallback != null) {
                        asyncCallback.b(arrayList);
                    }
                }
            });
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    private static void a(final Context context, final String str, final AsyncCallback<AuthTokenResult, ExceptionError> asyncCallback) {
        AsyncTaskUtils.a(new AsyncTask<Void, Void, ServiceTokenResult>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public ServiceTokenResult doInBackground(Void... voidArr) {
                MiAccountManager.get(context).setUseSystem();
                ServiceTokenFuture serviceToken = MiAccountManager.get(context).getServiceToken(context, str);
                if (serviceToken == null) {
                    return null;
                }
                return serviceToken.get();
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(ServiceTokenResult serviceTokenResult) {
                String a2 = LoginConstant.a(str);
                if (serviceTokenResult == null) {
                    asyncCallback.a(new ExceptionError(-2002, "serviceTokenResult is null"));
                } else if (TextUtils.isEmpty(serviceTokenResult.serviceToken) || TextUtils.isEmpty(serviceTokenResult.security) || TextUtils.isEmpty(serviceTokenResult.cUserId)) {
                    ServiceTokenResult.ErrorCode errorCode = serviceTokenResult.errorCode;
                    String str = serviceTokenResult.errorMessage;
                    if (errorCode == null) {
                        asyncCallback.a(new ExceptionError(-2002, "no error code"));
                        return;
                    }
                    switch (AnonymousClass5.f23561a[errorCode.ordinal()]) {
                        case 1:
                            asyncCallback.a(new ExceptionError(LoginErrorCode.o, str));
                            return;
                        case 2:
                            asyncCallback.a(new ExceptionError(-2005, str));
                            return;
                        case 3:
                            asyncCallback.a(new ExceptionError(LoginErrorCode.q, str));
                            return;
                        case 4:
                            asyncCallback.a(new ExceptionError(LoginErrorCode.r, str));
                            return;
                        case 5:
                            asyncCallback.a(new ExceptionError(LoginErrorCode.s, str));
                            return;
                        case 6:
                            asyncCallback.a(new ExceptionError(LoginErrorCode.t, str));
                            return;
                        case 7:
                            asyncCallback.a(new ExceptionError(-2010, str));
                            return;
                        case 8:
                            asyncCallback.a(new ExceptionError(LoginErrorCode.v, str));
                            return;
                        case 9:
                            asyncCallback.a(new ExceptionError(LoginErrorCode.w, str));
                            return;
                        case 10:
                            asyncCallback.a(new ExceptionError(LoginErrorCode.x, str));
                            return;
                        default:
                            asyncCallback.a(new ExceptionError(-2003, str));
                            return;
                    }
                } else {
                    AuthTokenResult authTokenResult = new AuthTokenResult();
                    authTokenResult.c = serviceTokenResult.serviceToken;
                    authTokenResult.d = serviceTokenResult.security;
                    authTokenResult.e = serviceTokenResult.cUserId;
                    authTokenResult.f23522a = str;
                    authTokenResult.b = a2;
                    asyncCallback.a(authTokenResult);
                }
            }
        }, new Void[0]);
    }

    /* renamed from: com.xiaomi.youpin.login.okhttpApi.api.MiuiSystemLoginApi$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f23561a = new int[ServiceTokenResult.ErrorCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0056 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode[] r0 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f23561a = r0
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_NONE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_NO_ACCOUNT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_APP_PERMISSION_FORBIDDEN     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_IOERROR     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_OLD_MIUI_ACCOUNT_MANAGER_PERMISSION_ISSUE     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x004b }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_CANCELLED     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_AUTHENTICATOR_ERROR     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x0062 }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_TIME_OUT     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x006e }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_REMOTE_EXCEPTION     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = f23561a     // Catch:{ NoSuchFieldError -> 0x007a }
                com.xiaomi.passport.servicetoken.ServiceTokenResult$ErrorCode r1 = com.xiaomi.passport.servicetoken.ServiceTokenResult.ErrorCode.ERROR_USER_INTERACTION_NEEDED     // Catch:{ NoSuchFieldError -> 0x007a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x007a }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x007a }
            L_0x007a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.okhttpApi.api.MiuiSystemLoginApi.AnonymousClass5.<clinit>():void");
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    private static void a(Context context, @Nullable Activity activity, Account account, String str, AsyncCallback<AuthTokenResult, ExceptionError> asyncCallback) {
        final String str2 = str;
        final Activity activity2 = activity;
        final Context context2 = context;
        final Account account2 = account;
        final AsyncCallback<AuthTokenResult, ExceptionError> asyncCallback2 = asyncCallback;
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Pair<AuthTokenResult, ExceptionError>>() {
            /* access modifiers changed from: protected */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x00d6  */
            /* JADX WARNING: Removed duplicated region for block: B:23:0x00dc  */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.util.Pair<com.xiaomi.youpin.login.entity.system_account.AuthTokenResult, com.xiaomi.youpin.login.entity.error.ExceptionError> doInBackground(java.lang.Object... r12) {
                /*
                    r11 = this;
                    java.lang.String r12 = r1
                    java.lang.String r12 = com.xiaomi.youpin.login.setting.LoginConstant.a(r12)
                    com.xiaomi.youpin.login.entity.system_account.AuthTokenResult r0 = new com.xiaomi.youpin.login.entity.system_account.AuthTokenResult
                    r0.<init>()
                    r1 = 0
                    r2 = -1
                    android.app.Activity r3 = r2     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    if (r3 != 0) goto L_0x0023
                    android.content.Context r3 = r3     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    android.accounts.AccountManager r4 = android.accounts.AccountManager.get(r3)     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    android.accounts.Account r5 = r4     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    java.lang.String r6 = r1     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    r7 = 1
                    r8 = 0
                    r9 = 0
                    android.accounts.AccountManagerFuture r3 = r4.getAuthToken(r5, r6, r7, r8, r9)     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    goto L_0x0036
                L_0x0023:
                    android.content.Context r3 = r3     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    android.accounts.AccountManager r4 = android.accounts.AccountManager.get(r3)     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    android.accounts.Account r5 = r4     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    java.lang.String r6 = r1     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    r7 = 0
                    android.app.Activity r8 = r2     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    r9 = 0
                    r10 = 0
                    android.accounts.AccountManagerFuture r3 = r4.getAuthToken(r5, r6, r7, r8, r9, r10)     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                L_0x0036:
                    if (r3 != 0) goto L_0x0045
                    com.xiaomi.youpin.login.entity.error.ExceptionError r3 = new com.xiaomi.youpin.login.entity.error.ExceptionError     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    java.lang.String r4 = "AuthTokenResult is null"
                    r3.<init>(r2, r4)     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    android.util.Pair r4 = new android.util.Pair     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    r4.<init>(r0, r3)     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    return r4
                L_0x0045:
                    java.lang.Object r3 = r3.getResult()     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    android.os.Bundle r3 = (android.os.Bundle) r3     // Catch:{ OperationCanceledException -> 0x00b3, IOException -> 0x0090, AuthenticatorException -> 0x006f, Exception -> 0x004e }
                    r4 = r1
                    goto L_0x00d4
                L_0x004e:
                    r3 = move-exception
                    r3.printStackTrace()
                    com.xiaomi.youpin.login.entity.error.ExceptionError r4 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    java.lang.String r6 = "UnKnownException "
                    r5.append(r6)
                    java.lang.String r6 = r3.getMessage()
                    r5.append(r6)
                    java.lang.String r5 = r5.toString()
                    r4.<init>(r2, r5)
                    r4.f23518a = r3
                    goto L_0x00d3
                L_0x006f:
                    r3 = move-exception
                    r3.printStackTrace()
                    com.xiaomi.youpin.login.entity.error.ExceptionError r4 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    java.lang.String r6 = "AuthenticatorException "
                    r5.append(r6)
                    java.lang.String r6 = r3.getMessage()
                    r5.append(r6)
                    java.lang.String r5 = r5.toString()
                    r4.<init>(r2, r5)
                    r4.f23518a = r3
                    goto L_0x00d3
                L_0x0090:
                    r3 = move-exception
                    r3.printStackTrace()
                    com.xiaomi.youpin.login.entity.error.ExceptionError r4 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    r5 = -1007(0xfffffffffffffc11, float:NaN)
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder
                    r6.<init>()
                    java.lang.String r7 = "IOException "
                    r6.append(r7)
                    java.lang.String r7 = r3.getMessage()
                    r6.append(r7)
                    java.lang.String r6 = r6.toString()
                    r4.<init>(r5, r6)
                    r4.f23518a = r3
                    goto L_0x00d3
                L_0x00b3:
                    r3 = move-exception
                    r3.printStackTrace()
                    com.xiaomi.youpin.login.entity.error.ExceptionError r4 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    java.lang.String r6 = "OperationCanceledException "
                    r5.append(r6)
                    java.lang.String r6 = r3.getMessage()
                    r5.append(r6)
                    java.lang.String r5 = r5.toString()
                    r4.<init>(r2, r5)
                    r4.f23518a = r3
                L_0x00d3:
                    r3 = r1
                L_0x00d4:
                    if (r4 == 0) goto L_0x00dc
                    android.util.Pair r12 = new android.util.Pair
                    r12.<init>(r0, r4)
                    return r12
                L_0x00dc:
                    java.lang.String r4 = "authtoken"
                    java.lang.String r4 = r3.getString(r4)
                    java.lang.String r5 = "encrypted_user_id"
                    java.lang.String r3 = r3.getString(r5)
                    boolean r5 = android.text.TextUtils.isEmpty(r4)
                    if (r5 != 0) goto L_0x0102
                    java.lang.String r5 = ","
                    java.lang.String[] r4 = r4.split(r5)
                    int r5 = r4.length
                    r6 = 2
                    if (r5 < r6) goto L_0x0102
                    r5 = 0
                    r5 = r4[r5]
                    r0.c = r5
                    r5 = 1
                    r4 = r4[r5]
                    r0.d = r4
                L_0x0102:
                    java.lang.String r4 = r0.c
                    boolean r4 = android.text.TextUtils.isEmpty(r4)
                    if (r4 != 0) goto L_0x013b
                    java.lang.String r4 = r0.d
                    boolean r4 = android.text.TextUtils.isEmpty(r4)
                    if (r4 == 0) goto L_0x0113
                    goto L_0x013b
                L_0x0113:
                    boolean r2 = android.text.TextUtils.isEmpty(r3)
                    if (r2 == 0) goto L_0x012c
                    android.content.Context r2 = r3     // Catch:{ Exception -> 0x0128 }
                    android.accounts.AccountManager r2 = android.accounts.AccountManager.get(r2)     // Catch:{ Exception -> 0x0128 }
                    android.accounts.Account r4 = r4     // Catch:{ Exception -> 0x0128 }
                    java.lang.String r5 = "encrypted_user_id"
                    java.lang.String r2 = r2.getUserData(r4, r5)     // Catch:{ Exception -> 0x0128 }
                    goto L_0x012d
                L_0x0128:
                    r2 = move-exception
                    r2.printStackTrace()
                L_0x012c:
                    r2 = r3
                L_0x012d:
                    java.lang.String r3 = r1
                    r0.f23522a = r3
                    r0.b = r12
                    r0.e = r2
                    android.util.Pair r12 = new android.util.Pair
                    r12.<init>(r0, r1)
                    return r12
                L_0x013b:
                    com.xiaomi.youpin.login.entity.error.ExceptionError r12 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    java.lang.String r1 = "serviceToken or ssecurity empty"
                    r12.<init>(r2, r1)
                    android.util.Pair r1 = new android.util.Pair
                    r1.<init>(r0, r12)
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.okhttpApi.api.MiuiSystemLoginApi.AnonymousClass4.doInBackground(java.lang.Object[]):android.util.Pair");
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Pair<AuthTokenResult, ExceptionError> pair) {
                ExceptionError exceptionError = (ExceptionError) pair.second;
                if (exceptionError != null) {
                    asyncCallback2.b(exceptionError);
                    return;
                }
                asyncCallback2.b((AuthTokenResult) pair.first);
            }
        }, new Object[0]);
    }
}
