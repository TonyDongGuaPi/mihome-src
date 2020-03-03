package com.xiaomi.youpin.login.okhttpApi.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.accountsdk.account.exception.IllegalDeviceException;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.InvalidUserNameException;
import com.xiaomi.accountsdk.account.exception.NeedVerificationException;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.passport.utils.AccountHelper;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;
import com.xiaomi.youpin.login.other.common.Executors;
import com.xiaomi.youpin.login.setting.LoginConstant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseLoginApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23549a = "BaseLoginApi";
    private static final int b = 10000;
    private static final Random c = new Random();

    /* access modifiers changed from: private */
    public static void b(String str) {
    }

    public static void a(String str, String str2, String str3, boolean z, AsyncCallback<Pair<AccountInfo, Long>, ExceptionError> asyncCallback) {
        b("loginMiByPasstokenSDK " + str + " ");
        final String str4 = str2;
        final String str5 = str3;
        final String str6 = str;
        final AsyncCallback<Pair<AccountInfo, Long>, ExceptionError> asyncCallback2 = asyncCallback;
        final boolean z2 = z;
        Executors.c(new Runnable() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: com.xiaomi.accountsdk.request.InvalidResponseException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.xiaomi.accountsdk.account.exception.InvalidCredentialException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: com.xiaomi.accountsdk.request.AccessDeniedException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: com.xiaomi.accountsdk.request.AuthenticationFailureException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.xiaomi.accountsdk.account.exception.InvalidUserNameException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: com.xiaomi.accountsdk.account.exception.IllegalDeviceException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: com.xiaomi.accountsdk.account.exception.NeedNotificationException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v16, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v23, resolved type: java.io.IOException} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: Removed duplicated region for block: B:25:0x003f  */
            /* JADX WARNING: Removed duplicated region for block: B:30:0x0057  */
            /* JADX WARNING: Removed duplicated region for block: B:38:0x007e  */
            /* JADX WARNING: Removed duplicated region for block: B:40:0x0086  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r5 = this;
                    r0 = 0
                    java.lang.String r1 = r2     // Catch:{ IOException -> 0x0035, InvalidResponseException -> 0x0030, InvalidCredentialException -> 0x002b, AccessDeniedException -> 0x0026, AuthenticationFailureException -> 0x0021, InvalidUserNameException -> 0x001c, IllegalDeviceException -> 0x0017, NeedNotificationException -> 0x0012, Exception -> 0x000d }
                    java.lang.String r2 = r3     // Catch:{ IOException -> 0x0035, InvalidResponseException -> 0x0030, InvalidCredentialException -> 0x002b, AccessDeniedException -> 0x0026, AuthenticationFailureException -> 0x0021, InvalidUserNameException -> 0x001c, IllegalDeviceException -> 0x0017, NeedNotificationException -> 0x0012, Exception -> 0x000d }
                    java.lang.String r3 = r4     // Catch:{ IOException -> 0x0035, InvalidResponseException -> 0x0030, InvalidCredentialException -> 0x002b, AccessDeniedException -> 0x0026, AuthenticationFailureException -> 0x0021, InvalidUserNameException -> 0x001c, IllegalDeviceException -> 0x0017, NeedNotificationException -> 0x0012, Exception -> 0x000d }
                    com.xiaomi.accountsdk.account.data.AccountInfo r1 = com.xiaomi.passport.utils.AccountHelper.getServiceTokenByPassToken(r1, r2, r3)     // Catch:{ IOException -> 0x0035, InvalidResponseException -> 0x0030, InvalidCredentialException -> 0x002b, AccessDeniedException -> 0x0026, AuthenticationFailureException -> 0x0021, InvalidUserNameException -> 0x001c, IllegalDeviceException -> 0x0017, NeedNotificationException -> 0x0012, Exception -> 0x000d }
                    r2 = r0
                    goto L_0x003b
                L_0x000d:
                    r1 = move-exception
                    r1.printStackTrace()
                    goto L_0x0039
                L_0x0012:
                    r1 = move-exception
                    r1.printStackTrace()
                    goto L_0x0039
                L_0x0017:
                    r1 = move-exception
                    r1.printStackTrace()
                    goto L_0x0039
                L_0x001c:
                    r1 = move-exception
                    r1.printStackTrace()
                    goto L_0x0039
                L_0x0021:
                    r1 = move-exception
                    r1.printStackTrace()
                    goto L_0x0039
                L_0x0026:
                    r1 = move-exception
                    r1.printStackTrace()
                    goto L_0x0039
                L_0x002b:
                    r1 = move-exception
                    r1.printStackTrace()
                    goto L_0x0039
                L_0x0030:
                    r1 = move-exception
                    r1.printStackTrace()
                    goto L_0x0039
                L_0x0035:
                    r1 = move-exception
                    r1.printStackTrace()
                L_0x0039:
                    r2 = r1
                    r1 = r0
                L_0x003b:
                    r3 = -1003(0xfffffffffffffc15, float:NaN)
                    if (r1 != 0) goto L_0x0057
                    if (r2 == 0) goto L_0x004f
                    boolean r0 = r2 instanceof java.io.IOException
                    if (r0 == 0) goto L_0x004f
                    com.xiaomi.youpin.login.entity.error.ExceptionError r0 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    r3 = -1007(0xfffffffffffffc11, float:NaN)
                    java.lang.String r4 = "accountInfo is null"
                    r0.<init>(r3, r4)
                    goto L_0x007c
                L_0x004f:
                    com.xiaomi.youpin.login.entity.error.ExceptionError r0 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    java.lang.String r4 = "accountInfo is null"
                    r0.<init>(r3, r4)
                    goto L_0x007c
                L_0x0057:
                    java.lang.String r4 = r1.getUserId()
                    boolean r4 = android.text.TextUtils.isEmpty(r4)
                    if (r4 != 0) goto L_0x0075
                    java.lang.String r4 = r1.getSecurity()
                    boolean r4 = android.text.TextUtils.isEmpty(r4)
                    if (r4 != 0) goto L_0x0075
                    java.lang.String r4 = r1.getServiceToken()
                    boolean r4 = android.text.TextUtils.isEmpty(r4)
                    if (r4 == 0) goto L_0x007c
                L_0x0075:
                    com.xiaomi.youpin.login.entity.error.ExceptionError r0 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    java.lang.String r4 = "accountInfo empty"
                    r0.<init>(r3, r4)
                L_0x007c:
                    if (r0 == 0) goto L_0x0086
                    r0.f23518a = r2
                    com.xiaomi.youpin.login.AsyncCallback r1 = r5
                    r1.b(r0)
                    return
                L_0x0086:
                    r2 = 0
                    boolean r0 = r6
                    if (r0 == 0) goto L_0x00b9
                    com.xiaomi.youpin.login.api.LoginConfig r0 = com.xiaomi.youpin.login.api.MiLoginApi.a()
                    com.xiaomi.youpin.login.LoginDependencyApi r0 = r0.h()
                    android.util.Pair r0 = r0.c()
                    java.lang.Object r2 = r0.first
                    java.lang.Long r2 = (java.lang.Long) r2
                    long r2 = r2.longValue()
                    java.lang.Object r0 = r0.second
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r0 = r0.booleanValue()
                    if (r0 != 0) goto L_0x00b9
                    com.xiaomi.youpin.login.AsyncCallback r0 = r5
                    com.xiaomi.youpin.login.entity.error.ExceptionError r1 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    r2 = -1006(0xfffffffffffffc12, float:NaN)
                    java.lang.String r3 = "获取timeDiff失败"
                    r1.<init>(r2, r3)
                    r0.b(r1)
                    return
                L_0x00b9:
                    com.xiaomi.youpin.login.AsyncCallback r0 = r5
                    android.util.Pair r4 = new android.util.Pair
                    java.lang.Long r2 = java.lang.Long.valueOf(r2)
                    r4.<init>(r1, r2)
                    r0.b(r4)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.okhttpApi.api.BaseLoginApi.AnonymousClass1.run():void");
            }
        });
    }

    public static void a(@NonNull List<String> list, String str, String str2, AsyncCallback<List<AccountInfo>, Error> asyncCallback) {
        b("loginMiByPasstokenBatchSDK");
        ArrayList arrayList = new ArrayList();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (String next : list) {
            final String str3 = next;
            final ArrayList arrayList2 = arrayList;
            final AtomicInteger atomicInteger2 = atomicInteger;
            final List<String> list2 = list;
            final AsyncCallback<List<AccountInfo>, Error> asyncCallback2 = asyncCallback;
            a(next, str, str2, false, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
                public void a(Pair<AccountInfo, Long> pair) {
                    BaseLoginApi.b(str3 + " Get serviceToken onSuccess " + pair.first);
                    arrayList2.add(pair.first);
                    if (atomicInteger2.incrementAndGet() == list2.size() && asyncCallback2 != null) {
                        asyncCallback2.b(arrayList2);
                    }
                }

                public void a(ExceptionError exceptionError) {
                    BaseLoginApi.b(str3 + " Get serviceToken fail " + exceptionError);
                    if (atomicInteger2.incrementAndGet() == list2.size() && asyncCallback2 != null) {
                        asyncCallback2.b(arrayList2);
                    }
                }
            });
        }
    }

    public static void a(String str, String str2, MetaLoginData metaLoginData, boolean z, String str3, String str4, boolean z2, AsyncCallback<Pair<AccountInfo, Long>, ExceptionError> asyncCallback) {
        b("loginMiByDynamicTokenSDK ");
        final String str5 = str;
        final String str6 = str2;
        final MetaLoginData metaLoginData2 = metaLoginData;
        final boolean z3 = z;
        final String str7 = str3;
        final String str8 = str4;
        final AsyncCallback<Pair<AccountInfo, Long>, ExceptionError> asyncCallback2 = asyncCallback;
        final boolean z4 = z2;
        Executors.c(new Runnable() {
            public void run() {
                AccountInfo accountInfo;
                Exception exc = null;
                try {
                    accountInfo = AccountHelper.getServiceTokenByStep2(str5, str6, metaLoginData2, z3, str7, str8);
                } catch (IllegalDeviceException | InvalidCredentialException | InvalidUserNameException | NeedVerificationException | AccessDeniedException | AuthenticationFailureException | InvalidResponseException | IOException e2) {
                    Exception exc2 = e2;
                    accountInfo = null;
                    exc = exc2;
                }
                if (exc != null) {
                    ExceptionError exceptionError = new ExceptionError(-1, "");
                    exceptionError.f23518a = exc;
                    BaseLoginApi.b("loginMiByDynamicTokenSDK  failed " + exc.getMessage());
                    if (asyncCallback2 != null) {
                        asyncCallback2.b(exceptionError);
                    }
                } else if (accountInfo == null) {
                    BaseLoginApi.b("loginMiByDynamicTokenSDK  failed accountInfo == null");
                    if (asyncCallback2 != null) {
                        asyncCallback2.b(new ExceptionError(-1, "accountInfo is null"));
                    }
                } else if (z4) {
                    Pair<Long, Boolean> c2 = MiLoginApi.a().h().c();
                    if (c2 == null) {
                        asyncCallback2.b(new ExceptionError(LoginErrorCode.i, "获取timeDiff失败"));
                        return;
                    }
                    long longValue = ((Long) c2.first).longValue();
                    if (!((Boolean) c2.second).booleanValue()) {
                        asyncCallback2.b(new ExceptionError(LoginErrorCode.i, "获取timeDiff失败"));
                        return;
                    }
                    BaseLoginApi.b("loginMiByDynamicTokenSDK  success");
                    if (asyncCallback2 != null) {
                        asyncCallback2.b(new Pair(accountInfo, Long.valueOf(longValue)));
                    }
                } else {
                    BaseLoginApi.b("loginMiByDynamicTokenSDK  success");
                    if (asyncCallback2 != null) {
                        asyncCallback2.b(new Pair(accountInfo, 0L));
                    }
                }
            }
        });
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void a(final Context context, final String str, final boolean z, @NonNull final AsyncCallback<MiServiceTokenInfo, ExceptionError> asyncCallback) {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Pair<MiServiceTokenInfo, ExceptionError>>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Pair<MiServiceTokenInfo, ExceptionError> doInBackground(Object... objArr) {
                MiAccountManager miAccountManager = MiAccountManager.get(context);
                miAccountManager.setUseLocal();
                ServiceTokenResult serviceTokenResult = miAccountManager.getServiceToken(context, str).get();
                if (serviceTokenResult == null) {
                    return new Pair<>((Object) null, new ExceptionError(-1, "serviceTokenResult is null"));
                }
                MiServiceTokenInfo miServiceTokenInfo = new MiServiceTokenInfo();
                if (TextUtils.isEmpty(serviceTokenResult.serviceToken) || TextUtils.isEmpty(serviceTokenResult.security)) {
                    return new Pair<>((Object) null, new ExceptionError(-1, "serviceTokenResult invalid"));
                }
                miServiceTokenInfo.b = serviceTokenResult.cUserId;
                miServiceTokenInfo.f23514a = str;
                miServiceTokenInfo.f = LoginConstant.a(str);
                miServiceTokenInfo.c = serviceTokenResult.serviceToken;
                miServiceTokenInfo.d = serviceTokenResult.security;
                if (!z) {
                    return new Pair<>(miServiceTokenInfo, (Object) null);
                }
                Pair<Long, Boolean> c2 = MiLoginApi.a().h().c();
                miServiceTokenInfo.e = ((Long) c2.first).longValue();
                if (((Boolean) c2.second).booleanValue()) {
                    return new Pair<>(miServiceTokenInfo, (Object) null);
                }
                return new Pair<>(miServiceTokenInfo, new ExceptionError(-2, "get time diff fail"));
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Pair<MiServiceTokenInfo, ExceptionError> pair) {
                MiServiceTokenInfo miServiceTokenInfo = (MiServiceTokenInfo) pair.first;
                ExceptionError exceptionError = (ExceptionError) pair.second;
                if (exceptionError != null) {
                    asyncCallback.a(exceptionError);
                } else {
                    asyncCallback.a(miServiceTokenInfo);
                }
            }
        }, new Object[0]);
    }

    public static void a(Context context, @NonNull final List<String> list, @NonNull final AsyncCallback<List<MiServiceTokenInfo>, ExceptionError> asyncCallback) {
        final ArrayList arrayList = new ArrayList();
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        for (String a2 : list) {
            a(context, a2, false, (AsyncCallback<MiServiceTokenInfo, ExceptionError>) new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
                public void a(MiServiceTokenInfo miServiceTokenInfo) {
                    arrayList.add(miServiceTokenInfo);
                    if (atomicInteger.incrementAndGet() == list.size()) {
                        asyncCallback.b(arrayList);
                    }
                }

                public void a(ExceptionError exceptionError) {
                    if (atomicInteger.incrementAndGet() == list.size()) {
                        asyncCallback.b(arrayList);
                    }
                }
            });
        }
    }
}
