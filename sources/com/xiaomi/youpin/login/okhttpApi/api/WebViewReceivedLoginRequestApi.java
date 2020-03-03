package com.xiaomi.youpin.login.okhttpApi.api;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.servicetoken.ServiceTokenFuture;
import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import com.xiaomi.youpin.login.api.callback.GetServiceTokenOnReceivedLoginRequestCallback;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;

public class WebViewReceivedLoginRequestApi {
    @SuppressLint({"StaticFieldLeak"})
    public static void a(boolean z, Context context, String str, @NonNull final GetServiceTokenOnReceivedLoginRequestCallback getServiceTokenOnReceivedLoginRequestCallback) {
        Account d;
        String str2 = "weblogin:" + str;
        MiAccountManager miAccountManager = MiAccountManager.get(context);
        if (z) {
            miAccountManager.setUseSystem();
            d = AccountManagerUtil.c(context);
        } else {
            miAccountManager.setUseLocal();
            d = AccountManagerUtil.d(context);
        }
        miAccountManager.getAuthToken(d, str2, (Bundle) null, false, (AccountManagerCallback<Bundle>) new AccountManagerCallback<Bundle>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: android.accounts.OperationCanceledException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: android.accounts.AuthenticatorException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: android.accounts.OperationCanceledException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: android.accounts.OperationCanceledException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: android.accounts.OperationCanceledException} */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: Removed duplicated region for block: B:12:0x001b  */
            /* JADX WARNING: Removed duplicated region for block: B:13:0x0021  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run(android.accounts.AccountManagerFuture<android.os.Bundle> r3) {
                /*
                    r2 = this;
                    r0 = 0
                    java.lang.Object r3 = r3.getResult()     // Catch:{ OperationCanceledException -> 0x0013, IOException -> 0x000e, AuthenticatorException -> 0x0009 }
                    android.os.Bundle r3 = (android.os.Bundle) r3     // Catch:{ OperationCanceledException -> 0x0013, IOException -> 0x000e, AuthenticatorException -> 0x0009 }
                    r1 = r0
                    goto L_0x0019
                L_0x0009:
                    r3 = move-exception
                    r3.printStackTrace()
                    goto L_0x0017
                L_0x000e:
                    r3 = move-exception
                    r3.printStackTrace()
                    goto L_0x0017
                L_0x0013:
                    r3 = move-exception
                    r3.printStackTrace()
                L_0x0017:
                    r1 = r3
                    r3 = r0
                L_0x0019:
                    if (r3 != 0) goto L_0x0021
                    com.xiaomi.youpin.login.api.callback.GetServiceTokenOnReceivedLoginRequestCallback r3 = r12
                    r3.a((java.lang.Exception) r1)
                    goto L_0x0038
                L_0x0021:
                    java.lang.String r1 = "authtoken"
                    java.lang.String r3 = r3.getString(r1)
                    boolean r1 = android.text.TextUtils.isEmpty(r3)
                    if (r1 == 0) goto L_0x0033
                    com.xiaomi.youpin.login.api.callback.GetServiceTokenOnReceivedLoginRequestCallback r3 = r12
                    r3.a((java.lang.Exception) r0)
                    goto L_0x0038
                L_0x0033:
                    com.xiaomi.youpin.login.api.callback.GetServiceTokenOnReceivedLoginRequestCallback r0 = r12
                    r0.a((java.lang.String) r3)
                L_0x0038:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.login.okhttpApi.api.WebViewReceivedLoginRequestApi.AnonymousClass1.run(android.accounts.AccountManagerFuture):void");
            }
        }, (Handler) null);
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void b(final boolean z, final Context context, String str, @NonNull final GetServiceTokenOnReceivedLoginRequestCallback getServiceTokenOnReceivedLoginRequestCallback) {
        final String str2 = "weblogin:" + str;
        AsyncTaskUtils.a(new AsyncTask<Void, Void, ServiceTokenResult>() {
            private ServiceTokenFuture e;

            /* access modifiers changed from: protected */
            public void onPreExecute() {
                MiAccountManager miAccountManager = MiAccountManager.get(context);
                if (z) {
                    miAccountManager.setUseSystem();
                } else {
                    miAccountManager.setUseLocal();
                }
                this.e = miAccountManager.getServiceToken(context, str2);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public ServiceTokenResult doInBackground(Void... voidArr) {
                return this.e.get();
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(ServiceTokenResult serviceTokenResult) {
                if (serviceTokenResult.errorCode == ServiceTokenResult.ErrorCode.ERROR_USER_INTERACTION_NEEDED) {
                    Intent intent = serviceTokenResult.intent;
                    if (intent != null) {
                        intent.setFlags(intent.getFlags() & -268435457);
                        getServiceTokenOnReceivedLoginRequestCallback.a(intent);
                        return;
                    }
                    return;
                }
                String str = serviceTokenResult.serviceToken;
                if (TextUtils.isEmpty(str)) {
                    getServiceTokenOnReceivedLoginRequestCallback.a(serviceTokenResult.errorCode, serviceTokenResult.errorMessage);
                } else {
                    getServiceTokenOnReceivedLoginRequestCallback.a(str);
                }
            }
        }, new Void[0]);
    }
}
