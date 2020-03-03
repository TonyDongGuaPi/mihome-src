package com.xiaomi.youpin.mipay;

import com.xiaomi.smarthome.frame.core.CoreApi;

public class MiPayAccountUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23647a = "MiPayAccountUtil";
    private static final String b = "com.xiaomi";
    private static final String c = "passportapi";
    private static final String d = "weblogin:";

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x008b A[Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a6 A[Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00cf A[Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(com.xiaomi.youpin.mipay.IYPAccountManagerResponse r7, android.accounts.Account r8, java.lang.String r9) {
        /*
            if (r7 == 0) goto L_0x0194
            if (r8 == 0) goto L_0x018c
            if (r9 == 0) goto L_0x0184
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = a()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0173
            java.lang.String r2 = "com.xiaomi"
            java.lang.String r3 = r8.type
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0021
            goto L_0x0173
        L_0x0021:
            boolean r2 = android.text.TextUtils.isEmpty(r9)
            r3 = 0
            if (r2 == 0) goto L_0x0034
            java.lang.String r9 = "MiPayAccountUtil"
            java.lang.String r2 = "getting auth token, but no service url contained, use micloud"
            com.xiaomi.youpin.utils.LogUtils.b(r9, r2)
            java.lang.String r9 = "passportapi"
        L_0x0031:
            r2 = r9
            r9 = r3
            goto L_0x005f
        L_0x0034:
            java.lang.String r2 = "weblogin:"
            boolean r2 = r9.startsWith(r2)
            if (r2 == 0) goto L_0x0031
            java.lang.String r2 = "passportapi"
            java.lang.String r4 = "weblogin:"
            int r4 = r4.length()
            java.lang.String r9 = r9.substring(r4)
            boolean r4 = com.xiaomi.passport.utils.AccountHelper.isTrustedWebSsoUrl(r9)
            if (r4 != 0) goto L_0x005f
            java.lang.String r8 = "errorCode"
            r9 = 7
            r0.putInt(r8, r9)
            java.lang.String r8 = "errorMessage"
            java.lang.String r9 = "untrusted web sso url"
            r0.putString(r8, r9)
            r7.a(r0)
            return
        L_0x005f:
            r4 = 5
            if (r9 == 0) goto L_0x00a6
            java.lang.String r2 = "MiPayAccountUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            r5.<init>()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r6 = "getAuthToken(): webAutoLoginUrl = "
            r5.append(r6)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            r5.append(r9)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            com.xiaomi.youpin.utils.LogUtils.b(r2, r5)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r8 = r8.name     // Catch:{ IllegalDeviceException -> 0x0084, NeedNotificationException -> 0x007f }
            com.xiaomi.accountsdk.account.data.AccountInfo r8 = com.xiaomi.passport.utils.AccountHelper.getServiceTokenByPassToken(r8, r1, r3, r9)     // Catch:{ IllegalDeviceException -> 0x0084, NeedNotificationException -> 0x007f }
            goto L_0x0089
        L_0x007f:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            goto L_0x0088
        L_0x0084:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
        L_0x0088:
            r8 = r3
        L_0x0089:
            if (r8 == 0) goto L_0x00a5
            java.lang.String r1 = "authAccount"
            java.lang.String r2 = r8.getUserId()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            r0.putString(r1, r2)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r1 = "accountType"
            r0.putString(r1, r9)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r9 = "authtoken"
            java.lang.String r8 = r8.getAutoLoginUrl()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            r0.putString(r9, r8)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            r7.a(r0)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
        L_0x00a5:
            return
        L_0x00a6:
            java.lang.String r9 = "MiPayAccountUtil"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            r5.<init>()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r6 = "getAuthToken(): serviceId = "
            r5.append(r6)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            r5.append(r2)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            com.xiaomi.youpin.utils.LogUtils.b(r9, r5)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r8 = r8.name     // Catch:{ IllegalDeviceException -> 0x00c8, NeedNotificationException -> 0x00c3 }
            com.xiaomi.accountsdk.account.data.AccountInfo r8 = com.xiaomi.passport.utils.AccountHelper.getServiceTokenByPassToken(r8, r1, r2)     // Catch:{ IllegalDeviceException -> 0x00c8, NeedNotificationException -> 0x00c3 }
            goto L_0x00cd
        L_0x00c3:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            goto L_0x00cc
        L_0x00c8:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
        L_0x00cc:
            r8 = r3
        L_0x00cd:
            if (r8 == 0) goto L_0x016f
            java.lang.String r9 = r8.getServiceToken()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r1 = r8.getSecurity()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            com.xiaomi.accountsdk.account.data.ExtendedAuthToken r9 = com.xiaomi.accountsdk.account.data.ExtendedAuthToken.build(r9, r1)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r9 = r9.toPlain()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r1 = "authAccount"
            java.lang.String r8 = r8.getUserId()     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            r0.putString(r1, r8)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r8 = "accountType"
            java.lang.String r1 = "com.xiaomi"
            r0.putString(r8, r1)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            java.lang.String r8 = "authtoken"
            r0.putString(r8, r9)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            r7.a(r0)     // Catch:{ IOException -> 0x015c, InvalidResponseException -> 0x0148, InvalidCredentialException -> 0x0134, AccessDeniedException -> 0x0120, AuthenticationFailureException -> 0x010c, InvalidUserNameException -> 0x00f8 }
            return
        L_0x00f8:
            java.lang.String r8 = "MiPayAccountUtil"
            java.lang.String r9 = "no such a user"
            com.xiaomi.youpin.utils.LogUtils.b(r8, r9)
            java.lang.String r8 = "errorCode"
            r0.putInt(r8, r4)
            java.lang.String r8 = "errorMessage"
            java.lang.String r9 = "no such user"
            r0.putString(r8, r9)
            goto L_0x016f
        L_0x010c:
            java.lang.String r8 = "MiPayAccountUtil"
            java.lang.String r9 = "auth failure"
            com.xiaomi.youpin.utils.LogUtils.b(r8, r9)
            java.lang.String r8 = "errorCode"
            r0.putInt(r8, r4)
            java.lang.String r8 = "errorMessage"
            java.lang.String r9 = "auth failure"
            r0.putString(r8, r9)
            goto L_0x016f
        L_0x0120:
            java.lang.String r8 = "MiPayAccountUtil"
            java.lang.String r9 = "access denied"
            com.xiaomi.youpin.utils.LogUtils.b(r8, r9)
            java.lang.String r8 = "errorCode"
            r0.putInt(r8, r4)
            java.lang.String r8 = "errorMessage"
            java.lang.String r9 = "access denied"
            r0.putString(r8, r9)
            goto L_0x016f
        L_0x0134:
            java.lang.String r8 = "MiPayAccountUtil"
            java.lang.String r9 = "invalid credential, passToken is invalid"
            com.xiaomi.youpin.utils.LogUtils.b(r8, r9)
            java.lang.String r8 = "errorCode"
            r0.putInt(r8, r4)
            java.lang.String r8 = "errorMessage"
            java.lang.String r9 = "invalid passToken"
            r0.putString(r8, r9)
            goto L_0x016f
        L_0x0148:
            java.lang.String r8 = "MiPayAccountUtil"
            java.lang.String r9 = "invalid response received when getting service token"
            com.xiaomi.youpin.utils.LogUtils.b(r8, r9)
            java.lang.String r8 = "errorCode"
            r0.putInt(r8, r4)
            java.lang.String r8 = "errorMessage"
            java.lang.String r9 = "invalid response from server"
            r0.putString(r8, r9)
            goto L_0x016f
        L_0x015c:
            java.lang.String r8 = "MiPayAccountUtil"
            java.lang.String r9 = "io exception when getting service token"
            com.xiaomi.youpin.utils.LogUtils.b(r8, r9)
            java.lang.String r8 = "errorCode"
            r0.putInt(r8, r4)
            java.lang.String r8 = "errorMessage"
            java.lang.String r9 = "io exception when getting service token"
            r0.putString(r8, r9)
        L_0x016f:
            r7.a(r0)
            return
        L_0x0173:
            java.lang.String r8 = "MiPayAccountUtil"
            java.lang.String r9 = "getAuthToken(): passToken null or account type error"
            com.xiaomi.youpin.utils.LogUtils.b(r8, r9)
            java.lang.String r8 = "booleanResult"
            r9 = 0
            r0.putBoolean(r8, r9)
            r7.a(r0)
            return
        L_0x0184:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "authTokenType is null"
            r7.<init>(r8)
            throw r7
        L_0x018c:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "account is null"
            r7.<init>(r8)
            throw r7
        L_0x0194:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "response is null"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.mipay.MiPayAccountUtil.a(com.xiaomi.youpin.mipay.IYPAccountManagerResponse, android.accounts.Account, java.lang.String):void");
    }

    private static String a() {
        return CoreApi.a().w();
    }
}
