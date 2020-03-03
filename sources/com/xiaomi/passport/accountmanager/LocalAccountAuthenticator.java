package com.xiaomi.passport.accountmanager;

import android.accounts.Account;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.accounts.AbstractAccountAuthenticator;
import com.xiaomi.accounts.AccountAuthenticatorResponse;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.utils.AuthenticatorUtil;

public class LocalAccountAuthenticator extends AbstractAccountAuthenticator {
    private static final boolean DEBUG = true;
    private static final String TAG = "LocalAccountAuthenticat";
    private Context mContext;

    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String str) {
        return null;
    }

    public String getAuthTokenLabel(String str) {
        return null;
    }

    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strArr) throws NetworkErrorException {
        return null;
    }

    public LocalAccountAuthenticator(Context context) {
        super(context);
        this.mContext = context;
    }

    public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) throws NetworkErrorException {
        Account[] accountsByType = AccountManager.get(this.mContext).getAccountsByType("com.xiaomi");
        Bundle bundle2 = new Bundle();
        if (accountsByType.length > 0) {
            AccountLog.d(TAG, "a xiaomi account already exists");
            Account account = accountsByType[0];
            bundle2.putString("authAccount", account.name);
            bundle2.putString("accountType", account.type);
        } else {
            if (TextUtils.isEmpty(str2)) {
                AccountLog.w(TAG, "no service id contained, use passportapi");
                str2 = "passportapi";
            }
            bundle2.putParcelable("intent", AuthenticatorUtil.newAddAccountIntent(this.mContext, str2, bundle, accountAuthenticatorResponse));
        }
        return bundle2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.os.Bundle confirmCredentials(com.xiaomi.accounts.AccountAuthenticatorResponse r9, android.accounts.Account r10, android.os.Bundle r11) throws android.accounts.NetworkErrorException {
        /*
            r8 = this;
            android.os.Bundle r7 = new android.os.Bundle
            r7.<init>()
            r0 = 0
            r1 = 1
            r2 = 0
            if (r11 == 0) goto L_0x008b
            java.lang.String r3 = "password"
            boolean r3 = r11.containsKey(r3)
            if (r3 != 0) goto L_0x0014
            goto L_0x008b
        L_0x0014:
            java.lang.String r9 = r10.name
            java.lang.String r10 = "password"
            java.lang.String r10 = r11.getString(r10)
            java.lang.String r3 = "captcha_code"
            java.lang.String r3 = r11.getString(r3)
            java.lang.String r4 = "captcha_ick"
            java.lang.String r11 = r11.getString(r4)
            com.xiaomi.accountsdk.account.data.AccountInfo r10 = com.xiaomi.passport.utils.AccountHelper.getServiceTokenByPassword(r9, r10, r3, r11, r2)     // Catch:{ IOException -> 0x0082, IllegalDeviceException -> 0x0063, InvalidResponseException -> 0x005e, InvalidCredentialException -> 0x0055, AccessDeniedException -> 0x0050, AuthenticationFailureException -> 0x004b, NeedVerificationException -> 0x003d, NeedCaptchaException -> 0x0034, InvalidUserNameException -> 0x002f }
        L_0x002c:
            r11 = r2
            r2 = r10
            goto L_0x0068
        L_0x002f:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x0067
        L_0x0034:
            r10 = move-exception
            java.lang.String r11 = r10.getCaptchaUrl()
            r10.printStackTrace()
            goto L_0x0068
        L_0x003d:
            com.xiaomi.accountsdk.account.data.AccountInfo$Builder r10 = new com.xiaomi.accountsdk.account.data.AccountInfo$Builder
            r10.<init>()
            com.xiaomi.accountsdk.account.data.AccountInfo$Builder r10 = r10.userId(r9)
            com.xiaomi.accountsdk.account.data.AccountInfo r10 = r10.build()
            goto L_0x002c
        L_0x004b:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x0067
        L_0x0050:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x0067
        L_0x0055:
            r10 = move-exception
            java.lang.String r11 = r10.getCaptchaUrl()
            r10.printStackTrace()
            goto L_0x0068
        L_0x005e:
            r10 = move-exception
            r10.printStackTrace()
            goto L_0x0067
        L_0x0063:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0067:
            r11 = r2
        L_0x0068:
            java.lang.String r10 = "authAccount"
            r7.putString(r10, r9)
            java.lang.String r9 = "accountType"
            java.lang.String r10 = "com.xiaomi"
            r7.putString(r9, r10)
            java.lang.String r9 = "booleanResult"
            if (r2 == 0) goto L_0x0079
            r0 = 1
        L_0x0079:
            r7.putBoolean(r9, r0)
            java.lang.String r9 = "captcha_url"
            r7.putString(r9, r11)
            goto L_0x00c9
        L_0x0082:
            r9 = move-exception
            android.accounts.NetworkErrorException r10 = new android.accounts.NetworkErrorException
            java.lang.String r11 = "IO exception when sending request to passport server"
            r10.<init>(r11, r9)
            throw r10
        L_0x008b:
            android.content.Context r3 = r8.mContext
            com.xiaomi.accounts.AccountManager r3 = com.xiaomi.accounts.AccountManager.get(r3)
            java.lang.String r3 = r3.getPassword(r10)
            if (r11 == 0) goto L_0x00a1
            java.lang.String r4 = "verify_only"
            boolean r4 = r11.getBoolean(r4, r1)
            if (r4 == 0) goto L_0x00a0
            goto L_0x00a1
        L_0x00a0:
            r1 = 0
        L_0x00a1:
            if (r1 == 0) goto L_0x00b5
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 == 0) goto L_0x00b5
            if (r11 != 0) goto L_0x00b0
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>()
        L_0x00b0:
            java.lang.String r1 = "verify_only"
            r11.putBoolean(r1, r0)
        L_0x00b5:
            r6 = r11
            r3 = 0
            if (r6 == 0) goto L_0x00c1
            java.lang.String r11 = "service_id"
            java.lang.String r11 = r6.getString(r11)
            r5 = r11
            goto L_0x00c2
        L_0x00c1:
            r5 = r2
        L_0x00c2:
            r0 = r8
            r1 = r7
            r2 = r9
            r4 = r10
            r0.fillQuickLoginIntent(r1, r2, r3, r4, r5, r6)
        L_0x00c9:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.accountmanager.LocalAccountAuthenticator.confirmCredentials(com.xiaomi.accounts.AccountAuthenticatorResponse, android.accounts.Account, android.os.Bundle):android.os.Bundle");
    }

    private String getRealPasstoken(Account account) {
        return AuthenticatorUtil.getPassToken(this.mContext, account);
    }

    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) {
        return recheckAuthTokenResult(getAuthTokenBundle(accountAuthenticatorResponse, account, str, bundle));
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.os.Bundle getAuthTokenBundle(com.xiaomi.accounts.AccountAuthenticatorResponse r16, android.accounts.Account r17, java.lang.String r18, android.os.Bundle r19) {
        /*
            r15 = this;
            r8 = r15
            r5 = r17
            r0 = r18
            r7 = r19
            java.lang.String r1 = "androidPackageName"
            android.content.Context r2 = r8.mContext
            java.lang.String r2 = r2.getPackageName()
            java.lang.String r1 = r7.getString(r1, r2)
            java.lang.String r2 = "LocalAccountAuthenticat"
            java.lang.String r3 = "getting AuthToken, type: %s, package name: %s"
            r4 = 2
            java.lang.Object[] r6 = new java.lang.Object[r4]
            java.lang.String r9 = "weblogin:"
            boolean r9 = r0.startsWith(r9)
            if (r9 == 0) goto L_0x0025
            java.lang.String r9 = "websso"
            goto L_0x0026
        L_0x0025:
            r9 = r0
        L_0x0026:
            r10 = 0
            r6[r10] = r9
            r9 = 1
            r6[r9] = r1
            java.lang.String r3 = java.lang.String.format(r3, r6)
            com.xiaomi.accountsdk.utils.AccountLog.i(r2, r3)
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>()
            android.content.Context r2 = r8.mContext
            android.accounts.Account r2 = com.xiaomi.passport.utils.AuthenticatorUtil.getXiaomiAccount(r2)
            if (r2 == 0) goto L_0x01fc
            java.lang.String r2 = r2.name
            java.lang.String r3 = r5.name
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x004c
            goto L_0x01fc
        L_0x004c:
            boolean r2 = android.text.TextUtils.isEmpty(r18)
            r3 = 0
            if (r2 == 0) goto L_0x005f
            java.lang.String r2 = "LocalAccountAuthenticat"
            java.lang.String r6 = "getting auth token, but no service url contained, use passportapi"
            com.xiaomi.accountsdk.utils.AccountLog.w((java.lang.String) r2, (java.lang.String) r6)
            java.lang.String r2 = "passportapi"
            r6 = r2
        L_0x005d:
            r2 = r3
            goto L_0x0089
        L_0x005f:
            java.lang.String r2 = "weblogin:"
            boolean r2 = r0.startsWith(r2)
            if (r2 == 0) goto L_0x0087
            java.lang.String r2 = "weblogin:"
            int r2 = r2.length()
            java.lang.String r2 = r0.substring(r2)
            java.lang.String r6 = "passportapi"
            boolean r12 = com.xiaomi.passport.utils.AccountHelper.isTrustedWebSsoUrl(r2)
            if (r12 != 0) goto L_0x0089
            java.lang.String r0 = "errorCode"
            r1 = 7
            r11.putInt(r0, r1)
            java.lang.String r0 = "errorMessage"
            java.lang.String r1 = "untrusted web sso url"
            r11.putString(r0, r1)
            return r11
        L_0x0087:
            r6 = r0
            goto L_0x005d
        L_0x0089:
            java.lang.String r12 = r15.getRealPasstoken(r5)
            boolean r13 = android.text.TextUtils.isEmpty(r12)
            if (r13 == 0) goto L_0x00a7
            r4 = 1
            r1 = r15
            r2 = r11
            r3 = r16
            r5 = r17
            r7 = r19
            r1.fillQuickLoginIntent(r2, r3, r4, r5, r6, r7)
            java.lang.String r0 = "LocalAccountAuthenticat"
            java.lang.String r1 = "passToken is null"
            com.xiaomi.accountsdk.utils.AccountLog.d(r0, r1)
            return r11
        L_0x00a7:
            r13 = 3
            r14 = 5
            if (r2 == 0) goto L_0x00e7
            java.lang.String r0 = r5.name     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            com.xiaomi.accountsdk.account.data.AccountInfo r0 = com.xiaomi.passport.utils.AccountHelper.getServiceTokenByPassToken(r0, r12, r3, r2)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            java.lang.String r1 = "authAccount"
            java.lang.String r3 = r0.getUserId()     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            r11.putString(r1, r3)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            java.lang.String r1 = "accountType"
            r11.putString(r1, r2)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            java.lang.String r1 = "authtoken"
            java.lang.String r0 = r0.getAutoLoginUrl()     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            r11.putString(r1, r0)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            java.lang.String r0 = "LocalAccountAuthenticat"
            java.lang.String r1 = "web sso getAuthToken succeed"
            com.xiaomi.accountsdk.utils.AccountLog.i(r0, r1)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            return r11
        L_0x00d0:
            r0 = move-exception
            goto L_0x0113
        L_0x00d2:
            r0 = move-exception
            goto L_0x0160
        L_0x00d5:
            r0 = move-exception
            goto L_0x0169
        L_0x00d8:
            r0 = move-exception
            goto L_0x0180
        L_0x00db:
            r0 = move-exception
            goto L_0x0196
        L_0x00de:
            r0 = move-exception
            goto L_0x01bc
        L_0x00e1:
            r0 = move-exception
            goto L_0x01d2
        L_0x00e4:
            r0 = move-exception
            goto L_0x01e6
        L_0x00e7:
            java.lang.String r3 = r5.name     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            com.xiaomi.accountsdk.account.data.AccountInfo r3 = com.xiaomi.passport.utils.AccountHelper.getServiceTokenByPassToken(r3, r12, r6)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            android.content.Context r12 = r8.mContext     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            com.xiaomi.passport.utils.AuthenticatorUtil.saveAccountInfoInAM(r12, r5, r3)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            android.content.Context r12 = r8.mContext     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            com.xiaomi.passport.utils.AuthenticatorUtil.updatePassTokenIfNeed(r12, r5, r3)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            android.os.Bundle r3 = com.xiaomi.passport.utils.AccountHelper.getAuthenticatorResponseBundle(r3, r10)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            if (r3 == 0) goto L_0x0100
            r11.putAll(r3)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
        L_0x0100:
            java.lang.String r3 = "LocalAccountAuthenticat"
            java.lang.String r12 = "type: %s, package name: %s, getAuthToken succeed"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            r4[r10] = r0     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            r4[r9] = r1     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            java.lang.String r0 = java.lang.String.format(r12, r4)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            com.xiaomi.accountsdk.utils.AccountLog.i(r3, r0)     // Catch:{ IOException -> 0x00e4, IllegalDeviceException -> 0x00e1, InvalidResponseException -> 0x00de, InvalidCredentialException -> 0x00db, AccessDeniedException -> 0x00d8, AuthenticationFailureException -> 0x00d5, InvalidUserNameException -> 0x00d2, NeedNotificationException -> 0x00d0 }
            goto L_0x01fb
        L_0x0113:
            java.lang.String r1 = "LocalAccountAuthenticat"
            java.lang.String r3 = "need notification "
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r3, r0)
            if (r2 == 0) goto L_0x0148
            java.lang.String r1 = "LocalAccountAuthenticat"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "websso return notification url: "
            r3.append(r4)
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            com.xiaomi.accountsdk.utils.AccountLog.i(r1, r3)
            java.lang.String r1 = "authAccount"
            java.lang.String r3 = r5.name
            r11.putString(r1, r3)
            java.lang.String r1 = "accountType"
            r11.putString(r1, r2)
            java.lang.String r1 = "authtoken"
            java.lang.String r0 = r0.getNotificationUrl()
            r11.putString(r1, r0)
            return r11
        L_0x0148:
            android.content.Context r1 = r8.mContext
            java.lang.String r2 = r0.getNotificationUrl()
            r4 = 1
            r0 = r1
            r1 = r16
            r3 = r6
            r5 = r19
            android.content.Intent r0 = com.xiaomi.passport.utils.AuthenticatorUtil.newNotificationIntent(r0, r1, r2, r3, r4, r5)
            java.lang.String r1 = "intent"
            r11.putParcelable(r1, r0)
            goto L_0x01fb
        L_0x0160:
            java.lang.String r1 = "LocalAccountAuthenticat"
            java.lang.String r2 = "no such a user"
            com.xiaomi.accountsdk.utils.AccountLog.e(r1, r2, r0)
            goto L_0x01fb
        L_0x0169:
            java.lang.String r1 = "LocalAccountAuthenticat"
            java.lang.String r2 = "auth failure"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r0)
            java.lang.String r1 = "errorCode"
            r11.putInt(r1, r14)
            java.lang.String r1 = "errorMessage"
            java.lang.String r0 = r0.toString()
            r11.putString(r1, r0)
            goto L_0x01fb
        L_0x0180:
            java.lang.String r1 = "LocalAccountAuthenticat"
            java.lang.String r2 = "access denied"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r0)
            java.lang.String r1 = "errorCode"
            r11.putInt(r1, r14)
            java.lang.String r1 = "errorMessage"
            java.lang.String r0 = r0.toString()
            r11.putString(r1, r0)
            goto L_0x01fb
        L_0x0196:
            java.lang.String r1 = "LocalAccountAuthenticat"
            java.lang.String r2 = "invalid credential, passToken is invalid"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r0)
            android.content.Context r1 = r8.mContext
            com.xiaomi.accounts.AccountManager r1 = com.xiaomi.accounts.AccountManager.get(r1)
            r1.clearPassword(r5)
            java.lang.String r0 = r0.getCaptchaUrl()
            java.lang.String r1 = "captcha_url"
            r7.putString(r1, r0)
            r4 = 1
            r1 = r15
            r2 = r11
            r3 = r16
            r5 = r17
            r7 = r19
            r1.fillQuickLoginIntent(r2, r3, r4, r5, r6, r7)
            goto L_0x01fb
        L_0x01bc:
            java.lang.String r1 = "LocalAccountAuthenticat"
            java.lang.String r2 = "invalid response received when getting service token"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r0)
            java.lang.String r1 = "errorCode"
            r11.putInt(r1, r14)
            java.lang.String r1 = "errorMessage"
            java.lang.String r0 = r0.toString()
            r11.putString(r1, r0)
            goto L_0x01fb
        L_0x01d2:
            java.lang.String r1 = "LocalAccountAuthenticat"
            java.lang.String r2 = "get device id failed when getting service token"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r0)
            java.lang.String r0 = "errorCode"
            r11.putInt(r0, r13)
            java.lang.String r0 = "errorMessage"
            java.lang.String r1 = "illegal device exception"
            r11.putString(r0, r1)
            goto L_0x01fb
        L_0x01e6:
            java.lang.String r1 = "LocalAccountAuthenticat"
            java.lang.String r2 = "io exception when getting service token"
            com.xiaomi.accountsdk.utils.AccountLog.w(r1, r2, r0)
            java.lang.String r1 = "errorCode"
            r11.putInt(r1, r13)
            java.lang.String r1 = "errorMessage"
            java.lang.String r0 = r0.toString()
            r11.putString(r1, r0)
        L_0x01fb:
            return r11
        L_0x01fc:
            java.lang.String r0 = "booleanResult"
            r11.putBoolean(r0, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.accountmanager.LocalAccountAuthenticator.getAuthTokenBundle(com.xiaomi.accounts.AccountAuthenticatorResponse, android.accounts.Account, java.lang.String, android.os.Bundle):android.os.Bundle");
    }

    /* access modifiers changed from: package-private */
    public Bundle recheckAuthTokenResult(Bundle bundle) {
        if (AuthenticatorUtil.getXiaomiAccount(this.mContext) != null) {
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("booleanResult", false);
        return bundle2;
    }

    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) throws NetworkErrorException {
        throw new UnsupportedOperationException("updateCredentials not supported");
    }

    public Bundle getAccountRemovalAllowed(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account) throws NetworkErrorException {
        Bundle bundle = new Bundle();
        bundle.putBoolean("booleanResult", true);
        return bundle;
    }

    private void fillQuickLoginIntent(Bundle bundle, AccountAuthenticatorResponse accountAuthenticatorResponse, boolean z, Account account, String str, Bundle bundle2) {
        if (!TextUtils.equals(AccountManager.get(this.mContext).getUserData(account, AccountIntent.EXTRA_HAS_PASSWORD), String.valueOf(true)) && !TextUtils.isEmpty(AuthenticatorUtil.getPassToken(this.mContext, account))) {
            AuthenticatorUtil.isSetPasswordAndUpdateAM(this.mContext, account, (String) null);
        }
        String userData = AccountManager.get(this.mContext).getUserData(account, AccountIntent.EXTRA_HAS_PASSWORD);
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("userId", account.name);
        bundle2.putBoolean("need_retry_on_authenticator_response_result", z);
        bundle2.putBoolean(AccountIntent.EXTRA_HAS_PASSWORD, TextUtils.equals(userData, String.valueOf(true)));
        Intent newQuickLoginIntent = AuthenticatorUtil.newQuickLoginIntent(this.mContext, accountAuthenticatorResponse, bundle2);
        newQuickLoginIntent.putExtra("service_id", str);
        bundle.putParcelable("intent", newQuickLoginIntent);
    }
}
