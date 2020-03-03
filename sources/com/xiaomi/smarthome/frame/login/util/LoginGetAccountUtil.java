package com.xiaomi.smarthome.frame.login.util;

import android.accounts.Account;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;

public class LoginGetAccountUtil {
    public static void a(final Context context, final String str, @NonNull final AsyncCallback<AccountInfo, Error> asyncCallback) {
        if (CoreApi.a().v()) {
            final Account c = AccountManagerUtil.c(context);
            AsyncTaskUtils.a(new AsyncTask<Object, Object, Void>() {
                /* access modifiers changed from: protected */
                /* JADX WARNING: Removed duplicated region for block: B:17:0x0049  */
                /* JADX WARNING: Removed duplicated region for block: B:19:0x0057  */
                @android.annotation.SuppressLint({"MissingPermission"})
                /* renamed from: a */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Void doInBackground(java.lang.Object... r9) {
                    /*
                        r8 = this;
                        com.xiaomi.accountsdk.account.data.AccountInfo$Builder r9 = new com.xiaomi.accountsdk.account.data.AccountInfo$Builder
                        r9.<init>()
                        java.lang.String r0 = r3
                        r9.serviceId(r0)
                        r0 = 0
                        android.content.Context r1 = r2     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        android.accounts.AccountManager r2 = android.accounts.AccountManager.get(r1)     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        android.accounts.Account r3 = r0     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        java.lang.String r4 = r3     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        r5 = 1
                        r6 = 0
                        r7 = 0
                        android.accounts.AccountManagerFuture r1 = r2.getAuthToken(r3, r4, r5, r6, r7)     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        if (r1 != 0) goto L_0x002c
                        com.xiaomi.smarthome.frame.AsyncCallback r1 = r4     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        com.xiaomi.smarthome.frame.Error r2 = new com.xiaomi.smarthome.frame.Error     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        r3 = -1
                        java.lang.String r4 = "AuthTokenResult is null"
                        r2.<init>(r3, r4)     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        r1.sendFailureMessage(r2)     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        return r0
                    L_0x002c:
                        java.lang.Object r1 = r1.getResult()     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        android.os.Bundle r1 = (android.os.Bundle) r1     // Catch:{ OperationCanceledException -> 0x0042, IOException -> 0x003d, AuthenticatorException -> 0x0038, Exception -> 0x0033 }
                        goto L_0x0047
                    L_0x0033:
                        r1 = move-exception
                        r1.printStackTrace()
                        goto L_0x0046
                    L_0x0038:
                        r1 = move-exception
                        r1.printStackTrace()
                        goto L_0x0046
                    L_0x003d:
                        r1 = move-exception
                        r1.printStackTrace()
                        goto L_0x0046
                    L_0x0042:
                        r1 = move-exception
                        r1.printStackTrace()
                    L_0x0046:
                        r1 = r0
                    L_0x0047:
                        if (r1 != 0) goto L_0x0057
                        com.xiaomi.smarthome.frame.AsyncCallback r9 = r4
                        com.xiaomi.smarthome.frame.Error r1 = new com.xiaomi.smarthome.frame.Error
                        r2 = -2
                        java.lang.String r3 = "bundle is null"
                        r1.<init>(r2, r3)
                        r9.sendFailureMessage(r1)
                        return r0
                    L_0x0057:
                        java.lang.String r2 = "authtoken"
                        java.lang.String r2 = r1.getString(r2)
                        java.lang.String r3 = "encrypted_user_id"
                        java.lang.String r1 = r1.getString(r3)
                        boolean r3 = android.text.TextUtils.isEmpty(r2)
                        if (r3 != 0) goto L_0x0080
                        java.lang.String r3 = ","
                        java.lang.String[] r2 = r2.split(r3)
                        int r3 = r2.length
                        r4 = 2
                        if (r3 < r4) goto L_0x0080
                        r3 = 0
                        r3 = r2[r3]
                        com.xiaomi.accountsdk.account.data.AccountInfo$Builder r3 = r9.serviceToken(r3)
                        r4 = 1
                        r2 = r2[r4]
                        r3.security(r2)
                    L_0x0080:
                        boolean r2 = android.text.TextUtils.isEmpty(r1)
                        if (r2 == 0) goto L_0x009a
                        android.content.Context r2 = r2     // Catch:{ Exception -> 0x0096 }
                        android.accounts.AccountManager r2 = android.accounts.AccountManager.get(r2)     // Catch:{ Exception -> 0x0096 }
                        android.accounts.Account r3 = r0     // Catch:{ Exception -> 0x0096 }
                        java.lang.String r4 = "encrypted_user_id"
                        java.lang.String r2 = r2.getUserData(r3, r4)     // Catch:{ Exception -> 0x0096 }
                        r1 = r2
                        goto L_0x009a
                    L_0x0096:
                        r2 = move-exception
                        r2.printStackTrace()
                    L_0x009a:
                        android.content.Context r2 = r2     // Catch:{ Exception -> 0x00ed }
                        android.accounts.AccountManager r2 = android.accounts.AccountManager.get(r2)     // Catch:{ Exception -> 0x00ed }
                        android.accounts.Account r3 = r0     // Catch:{ Exception -> 0x00ed }
                        java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ed }
                        r4.<init>()     // Catch:{ Exception -> 0x00ed }
                        java.lang.String r5 = r3     // Catch:{ Exception -> 0x00ed }
                        r4.append(r5)     // Catch:{ Exception -> 0x00ed }
                        java.lang.String r5 = "_slh"
                        r4.append(r5)     // Catch:{ Exception -> 0x00ed }
                        java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x00ed }
                        java.lang.String r2 = r2.getUserData(r3, r4)     // Catch:{ Exception -> 0x00ed }
                        android.content.Context r3 = r2     // Catch:{ Exception -> 0x00ed }
                        android.accounts.AccountManager r3 = android.accounts.AccountManager.get(r3)     // Catch:{ Exception -> 0x00ed }
                        android.accounts.Account r4 = r0     // Catch:{ Exception -> 0x00ed }
                        java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ed }
                        r5.<init>()     // Catch:{ Exception -> 0x00ed }
                        java.lang.String r6 = r3     // Catch:{ Exception -> 0x00ed }
                        r5.append(r6)     // Catch:{ Exception -> 0x00ed }
                        java.lang.String r6 = "_ph"
                        r5.append(r6)     // Catch:{ Exception -> 0x00ed }
                        java.lang.String r5 = r5.toString()     // Catch:{ Exception -> 0x00ed }
                        java.lang.String r3 = r3.getUserData(r4, r5)     // Catch:{ Exception -> 0x00ed }
                        com.xiaomi.accountsdk.account.data.AccountInfo$Builder r1 = r9.encryptedUserId(r1)     // Catch:{ Exception -> 0x00ed }
                        com.xiaomi.accountsdk.account.data.AccountInfo$Builder r1 = r1.ph(r3)     // Catch:{ Exception -> 0x00ed }
                        r1.slh(r2)     // Catch:{ Exception -> 0x00ed }
                        com.xiaomi.smarthome.frame.AsyncCallback r1 = r4     // Catch:{ Exception -> 0x00ed }
                        com.xiaomi.accountsdk.account.data.AccountInfo r9 = r9.build()     // Catch:{ Exception -> 0x00ed }
                        r1.sendSuccessMessage(r9)     // Catch:{ Exception -> 0x00ed }
                        goto L_0x00f4
                    L_0x00ed:
                        java.lang.String r9 = r3
                        com.xiaomi.smarthome.frame.AsyncCallback r1 = r4
                        com.xiaomi.smarthome.frame.login.util.LoginGetAccountUtil.b(r9, r1)
                    L_0x00f4:
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.login.util.LoginGetAccountUtil.AnonymousClass1.doInBackground(java.lang.Object[]):java.lang.Void");
                }
            }, new Object[0]);
            return;
        }
        b(str, asyncCallback);
    }

    /* access modifiers changed from: private */
    public static void b(final String str, @NonNull final AsyncCallback<AccountInfo, Error> asyncCallback) {
        final String s = CoreApi.a().s();
        final String w = CoreApi.a().w();
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Void>() {
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.xiaomi.accountsdk.request.InvalidResponseException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: com.xiaomi.accountsdk.account.exception.InvalidCredentialException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: com.xiaomi.accountsdk.request.AccessDeniedException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: com.xiaomi.accountsdk.request.AuthenticationFailureException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: com.xiaomi.accountsdk.account.exception.InvalidUserNameException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: com.xiaomi.accountsdk.account.exception.IllegalDeviceException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.xiaomi.accountsdk.account.exception.NeedNotificationException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v15, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v16, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v18, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v19, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: java.io.IOException} */
            /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v24, resolved type: java.io.IOException} */
            /* access modifiers changed from: protected */
            /* JADX WARNING: Multi-variable type inference failed */
            /* JADX WARNING: Removed duplicated region for block: B:25:0x0047  */
            /* JADX WARNING: Removed duplicated region for block: B:26:0x004d  */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void doInBackground(java.lang.Object... r6) {
                /*
                    r5 = this;
                    r6 = 0
                    java.lang.String r0 = r0     // Catch:{ IOException -> 0x0036, InvalidResponseException -> 0x0031, InvalidCredentialException -> 0x002c, AccessDeniedException -> 0x0027, AuthenticationFailureException -> 0x0022, InvalidUserNameException -> 0x001d, IllegalDeviceException -> 0x0018, NeedNotificationException -> 0x0013, Exception -> 0x000e }
                    java.lang.String r1 = r1     // Catch:{ IOException -> 0x0036, InvalidResponseException -> 0x0031, InvalidCredentialException -> 0x002c, AccessDeniedException -> 0x0027, AuthenticationFailureException -> 0x0022, InvalidUserNameException -> 0x001d, IllegalDeviceException -> 0x0018, NeedNotificationException -> 0x0013, Exception -> 0x000e }
                    java.lang.String r2 = r3     // Catch:{ IOException -> 0x0036, InvalidResponseException -> 0x0031, InvalidCredentialException -> 0x002c, AccessDeniedException -> 0x0027, AuthenticationFailureException -> 0x0022, InvalidUserNameException -> 0x001d, IllegalDeviceException -> 0x0018, NeedNotificationException -> 0x0013, Exception -> 0x000e }
                    com.xiaomi.accountsdk.account.data.AccountInfo r0 = com.xiaomi.passport.utils.AccountHelper.getServiceTokenByPassToken(r0, r1, r2)     // Catch:{ IOException -> 0x0036, InvalidResponseException -> 0x0031, InvalidCredentialException -> 0x002c, AccessDeniedException -> 0x0027, AuthenticationFailureException -> 0x0022, InvalidUserNameException -> 0x001d, IllegalDeviceException -> 0x0018, NeedNotificationException -> 0x0013, Exception -> 0x000e }
                    r1 = r0
                    r0 = r6
                    goto L_0x003b
                L_0x000e:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x003a
                L_0x0013:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x003a
                L_0x0018:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x003a
                L_0x001d:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x003a
                L_0x0022:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x003a
                L_0x0027:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x003a
                L_0x002c:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x003a
                L_0x0031:
                    r0 = move-exception
                    r0.printStackTrace()
                    goto L_0x003a
                L_0x0036:
                    r0 = move-exception
                    r0.printStackTrace()
                L_0x003a:
                    r1 = r6
                L_0x003b:
                    com.xiaomi.youpin.login.entity.error.ExceptionError r2 = new com.xiaomi.youpin.login.entity.error.ExceptionError
                    java.lang.String r3 = ""
                    r4 = -1
                    r2.<init>(r4, r3)
                    r2.f23518a = r0
                    if (r1 == 0) goto L_0x004d
                    com.xiaomi.smarthome.frame.AsyncCallback r0 = r4
                    r0.sendSuccessMessage(r1)
                    goto L_0x0059
                L_0x004d:
                    com.xiaomi.smarthome.frame.AsyncCallback r0 = r4
                    com.xiaomi.smarthome.frame.Error r1 = new com.xiaomi.smarthome.frame.Error
                    java.lang.String r2 = ""
                    r1.<init>(r4, r2)
                    r0.sendFailureMessage(r1)
                L_0x0059:
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.frame.login.util.LoginGetAccountUtil.AnonymousClass2.doInBackground(java.lang.Object[]):java.lang.Void");
            }
        }, new Object[0]);
    }
}
