package com.xiaomi.passport.utils;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.util.i;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import com.xiaomi.accounts.AccountAuthenticatorResponse;
import com.xiaomi.accounts.AccountManager;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.accountsdk.request.AccessDeniedException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.accountsdk.request.CipherException;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.accountsdk.utils.ActivityExportSafetyGuardian;
import com.xiaomi.accountsdk.utils.CloudCoder;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.passport.AccountChangedBroadcastHelper;
import com.xiaomi.passport.LocalFeatures.LocalFeaturesManagerResponse;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.data.XMPassportInfo;
import com.xiaomi.passport.servicetoken.ServiceTokenUIResponse;
import com.xiaomi.passport.uicontroller.NotificationWebView;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class AuthenticatorUtil {
    private static final Object ACCOUNT_LOCK = new Object();
    public static final String EXTRA_NEED_RETRY_ON_AUTHENTICATOR_RESPONSE_RESULT = "need_retry_on_authenticator_response_result";
    private static final String TAG = "AuthenticatorUtil";
    private static volatile AMPassTokenUpdateUtil mAMPassTokenUpdateUtil;

    public static void handleAccountAuthenticatorResponse(Parcelable parcelable, Bundle bundle) {
        if (parcelable != null) {
            if (parcelable instanceof AccountAuthenticatorResponse) {
                AccountAuthenticatorResponse accountAuthenticatorResponse = (AccountAuthenticatorResponse) parcelable;
                if (bundle == null) {
                    accountAuthenticatorResponse.onError(4, PrinterParmater.q);
                } else {
                    accountAuthenticatorResponse.onResult(bundle);
                }
            } else if (parcelable instanceof ServiceTokenUIResponse) {
                ServiceTokenUIResponse serviceTokenUIResponse = (ServiceTokenUIResponse) parcelable;
                if (bundle == null) {
                    serviceTokenUIResponse.onError(4, PrinterParmater.q);
                } else {
                    serviceTokenUIResponse.onResult(bundle);
                }
            } else if (parcelable instanceof LocalFeaturesManagerResponse) {
                LocalFeaturesManagerResponse localFeaturesManagerResponse = (LocalFeaturesManagerResponse) parcelable;
                if (bundle == null) {
                    localFeaturesManagerResponse.onError(4, PrinterParmater.q);
                } else {
                    localFeaturesManagerResponse.onResult(bundle);
                }
            }
        }
    }

    public static Intent newQuickLoginIntent(Context context, Parcelable parcelable, Bundle bundle) {
        Intent intent = new Intent();
        intent.setComponent(PassportExternal.getAuthenticatorComponentNameInterface(context).getConfirmCredentialActivityComponentName());
        intent.putExtra("accountAuthenticatorResponse", parcelable);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
        return intent;
    }

    public static Intent newAddAccountIntent(Context context, String str, Bundle bundle, Parcelable parcelable) {
        Intent intent = new Intent();
        intent.setComponent(PassportExternal.getAuthenticatorComponentNameInterface(context).getAddAccountActivityComponentName());
        intent.putExtra("service_id", str);
        intent.putExtras(bundle);
        intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
        intent.putExtra("accountAuthenticatorResponse", parcelable);
        return intent;
    }

    public static Intent newNotificationIntent(Context context, Parcelable parcelable, String str, String str2, boolean z, Bundle bundle) {
        Intent intent = new Intent();
        intent.setComponent(PassportExternal.getAuthenticatorComponentNameInterface(context).getNotificationActivityComponentName());
        NotificationWebView.putExtraForNotificationWebView(intent, new NotificationWebView.ExternalParams(str, z));
        intent.putExtra("service_id", str2);
        intent.putExtra("accountAuthenticatorResponse", parcelable);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ActivityExportSafetyGuardian.getInstance().sign(context, intent);
        return intent;
    }

    public static void saveAccountInfoInAM(Context context, AccountInfo accountInfo) {
        if (accountInfo != null && context != null) {
            saveAccountInfoInAM(context, getXiaomiAccount(context), accountInfo);
        }
    }

    public static void saveAccountInfoInAM(Context context, Account account, AccountInfo accountInfo) {
        String str;
        if (accountInfo != null && context != null && account != null) {
            AccountManager accountManager = AccountManager.get(context);
            String encryptedUserId = accountInfo.getEncryptedUserId();
            if (!TextUtils.isEmpty(encryptedUserId)) {
                accountManager.setUserData(account, "encrypted_user_id", encryptedUserId);
            }
            accountManager.setUserData(account, AccountIntent.EXTRA_HAS_PASSWORD, String.valueOf(accountInfo.getHasPwd()));
            String serviceId = accountInfo.getServiceId();
            String serviceToken = accountInfo.getServiceToken();
            if (!TextUtils.isEmpty(serviceId) && !TextUtils.isEmpty(serviceToken)) {
                accountManager.setAuthToken(account, serviceId, ExtendedAuthToken.build(serviceToken, accountInfo.getSecurity()).toPlain());
                String md5DigestUpperCase = CloudCoder.getMd5DigestUpperCase(serviceToken);
                String str2 = null;
                if (TextUtils.isEmpty(accountInfo.getSlh())) {
                    str = null;
                } else {
                    str = md5DigestUpperCase + "," + accountInfo.getSlh();
                }
                if (!TextUtils.isEmpty(accountInfo.getPh())) {
                    str2 = md5DigestUpperCase + "," + accountInfo.getPh();
                }
                accountManager.setUserData(account, serviceId + "_slh", str);
                accountManager.setUserData(account, serviceId + "_ph", str2);
            }
        }
    }

    public static boolean addOrUpdateAccountManager(Context context, AccountInfo accountInfo) {
        String userId = accountInfo.getUserId();
        Account account = new Account(userId, "com.xiaomi");
        Bundle bundle = new Bundle();
        bundle.putString("authAccount", userId);
        bundle.putString("encrypted_user_id", accountInfo.getEncryptedUserId());
        boolean addOrUpdateAccountManager = addOrUpdateAccountManager(context, account, ExtendedAuthToken.build(accountInfo.getPassToken(), accountInfo.getPsecurity()).toPlain(), bundle);
        saveAccountInfoInAM(context, account, accountInfo);
        return addOrUpdateAccountManager;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0042, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0054, code lost:
        return r8;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean addOrUpdateAccountManager(android.content.Context r6, android.accounts.Account r7, java.lang.String r8, android.os.Bundle r9) {
        /*
            java.lang.Object r0 = ACCOUNT_LOCK
            monitor-enter(r0)
            com.xiaomi.accounts.AccountManager r1 = com.xiaomi.accounts.AccountManager.get(r6)     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = "com.xiaomi"
            android.accounts.Account[] r1 = r1.getAccountsByType(r2)     // Catch:{ all -> 0x0055 }
            r2 = 1
            if (r1 == 0) goto L_0x0018
            int r3 = r1.length     // Catch:{ all -> 0x0055 }
            if (r3 >= r2) goto L_0x0014
            goto L_0x0018
        L_0x0014:
            r3 = 0
            r1 = r1[r3]     // Catch:{ all -> 0x0055 }
            goto L_0x0019
        L_0x0018:
            r1 = 0
        L_0x0019:
            com.xiaomi.accounts.AccountManager r3 = com.xiaomi.accounts.AccountManager.get(r6)     // Catch:{ all -> 0x0055 }
            if (r1 == 0) goto L_0x0043
            java.lang.String r9 = r3.getPassword(r1)     // Catch:{ all -> 0x0055 }
            java.lang.String r4 = r1.name     // Catch:{ all -> 0x0055 }
            java.lang.String r5 = r7.name     // Catch:{ all -> 0x0055 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0055 }
            if (r4 == 0) goto L_0x0041
            boolean r4 = android.text.TextUtils.isEmpty(r8)     // Catch:{ all -> 0x0055 }
            if (r4 != 0) goto L_0x0041
            boolean r9 = android.text.TextUtils.equals(r8, r9)     // Catch:{ all -> 0x0055 }
            if (r9 != 0) goto L_0x0041
            r3.setPassword(r1, r8)     // Catch:{ all -> 0x0055 }
            com.xiaomi.passport.AccountChangedBroadcastHelper$UpdateType r8 = com.xiaomi.passport.AccountChangedBroadcastHelper.UpdateType.POST_REFRESH     // Catch:{ all -> 0x0055 }
            com.xiaomi.passport.AccountChangedBroadcastHelper.sendBroadcast(r6, r7, r8)     // Catch:{ all -> 0x0055 }
        L_0x0041:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return r2
        L_0x0043:
            com.xiaomi.passport.AccountChangedBroadcastHelper$UpdateType r1 = com.xiaomi.passport.AccountChangedBroadcastHelper.UpdateType.PRE_ADD     // Catch:{ all -> 0x0055 }
            com.xiaomi.passport.AccountChangedBroadcastHelper.sendBroadcast(r6, r7, r1)     // Catch:{ all -> 0x0055 }
            boolean r8 = r3.addAccountExplicitly(r7, r8, r9)     // Catch:{ all -> 0x0055 }
            if (r8 == 0) goto L_0x0053
            com.xiaomi.passport.AccountChangedBroadcastHelper$UpdateType r9 = com.xiaomi.passport.AccountChangedBroadcastHelper.UpdateType.POST_ADD     // Catch:{ all -> 0x0055 }
            com.xiaomi.passport.AccountChangedBroadcastHelper.sendBroadcast(r6, r7, r9)     // Catch:{ all -> 0x0055 }
        L_0x0053:
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            return r8
        L_0x0055:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0055 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.utils.AuthenticatorUtil.addOrUpdateAccountManager(android.content.Context, android.accounts.Account, java.lang.String, android.os.Bundle):boolean");
    }

    public static Account getXiaomiAccount(Context context) {
        Account[] accountsByType = AccountManager.get(context).getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return accountsByType[0];
        }
        return null;
    }

    public static void removeAccount(Context context, Account account) {
        Boolean bool;
        AccountChangedBroadcastHelper.sendBroadcast(context, account, AccountChangedBroadcastHelper.UpdateType.PRE_REMOVE);
        try {
            bool = AccountManager.get(context).removeAccount(account, (AccountManagerCallback<Boolean>) null, (Handler) null).getResult();
        } catch (Exception e) {
            AccountLog.e(TAG, "error when remove account", e);
            bool = false;
        }
        if (bool.booleanValue()) {
            AccountChangedBroadcastHelper.sendBroadcast(context, account, AccountChangedBroadcastHelper.UpdateType.POST_REMOVE);
        }
    }

    public static void clearAllXiaomiAccountCookies(Context context) {
        if (context != null) {
            CookieSyncManager.createInstance(context);
            CookieManager instance = CookieManager.getInstance();
            try {
                String host = new URL(URLs.ACCOUNT_DOMAIN).getHost();
                for (String str : new String[]{host, "." + host, "c.id.mi.com"}) {
                    String cookie = instance.getCookie(str);
                    if (!TextUtils.isEmpty(cookie)) {
                        String[] split = cookie.split(i.b);
                        for (int i = 0; i < split.length; i++) {
                            instance.setCookie(str, String.format("%s=; Expires=Thu, 01 Jan 1970 00:00:00 GMT", new Object[]{split[i].split("=")[0].trim()}));
                        }
                    }
                }
                CookieSyncManager.getInstance().sync();
            } catch (MalformedURLException unused) {
                throw new IllegalStateException("never happen");
            }
        }
    }

    public static boolean isSetPasswordAndUpdateAM(Context context, Account account, String str) {
        boolean z;
        try {
            z = isSetPassword(context, str);
            try {
                AccountManager.get(context.getApplicationContext()).setUserData(account, AccountIntent.EXTRA_HAS_PASSWORD, String.valueOf(z));
            } catch (AccessDeniedException e) {
                e = e;
            } catch (AuthenticationFailureException e2) {
                e = e2;
                AccountLog.e(TAG, "handleQueryUserPassword error", e);
                return z;
            } catch (InvalidResponseException e3) {
                e = e3;
                AccountLog.e(TAG, "handleQueryUserPassword error", e);
                return z;
            } catch (CipherException e4) {
                e = e4;
                AccountLog.e(TAG, "handleQueryUserPassword error", e);
                return z;
            } catch (IOException e5) {
                e = e5;
                AccountLog.e(TAG, "handleQueryUserPassword error", e);
                return z;
            }
        } catch (AccessDeniedException e6) {
            e = e6;
            z = true;
            AccountLog.e(TAG, "handleQueryUserPassword error", e);
            return z;
        } catch (AuthenticationFailureException e7) {
            e = e7;
            z = true;
            AccountLog.e(TAG, "handleQueryUserPassword error", e);
            return z;
        } catch (InvalidResponseException e8) {
            e = e8;
            z = true;
            AccountLog.e(TAG, "handleQueryUserPassword error", e);
            return z;
        } catch (CipherException e9) {
            e = e9;
            z = true;
            AccountLog.e(TAG, "handleQueryUserPassword error", e);
            return z;
        } catch (IOException e10) {
            e = e10;
            z = true;
            AccountLog.e(TAG, "handleQueryUserPassword error", e);
            return z;
        }
        return z;
    }

    public static boolean isSetPassword(Context context, String str) throws IOException, AccessDeniedException, AuthenticationFailureException, CipherException, InvalidResponseException {
        Context applicationContext = context.getApplicationContext();
        String hashedDeviceIdNoThrow = new HashedDeviceIdUtil(applicationContext).getHashedDeviceIdNoThrow();
        XMPassportInfo build = XMPassportInfo.build(applicationContext, "passportapi");
        if (build == null) {
            AccountLog.w(TAG, "passport info is null");
            return true;
        }
        String substring = UUID.randomUUID().toString().substring(0, 15);
        try {
            return AccountHelper.isSetPassword(build, str, hashedDeviceIdNoThrow, substring);
        } catch (AuthenticationFailureException unused) {
            build.refreshAuthToken(applicationContext);
            return AccountHelper.isSetPassword(build, str, hashedDeviceIdNoThrow, substring);
        }
    }

    public static String getPassToken(Context context) {
        if (context == null) {
            return null;
        }
        return getPassToken(context, getXiaomiAccount(context));
    }

    public static String getPassToken(Context context, Account account) {
        ExtendedAuthToken parse;
        if (context == null || account == null) {
            return null;
        }
        String password = AccountManager.get(context).getPassword(account);
        if (!TextUtils.isEmpty(password) && (parse = ExtendedAuthToken.parse(password)) != null) {
            return parse.authToken;
        }
        return null;
    }

    public static void updatePassTokenIfNeed(Context context, Account account, AccountInfo accountInfo) {
        if (context != null && account != null && accountInfo != null) {
            if (mAMPassTokenUpdateUtil == null) {
                mAMPassTokenUpdateUtil = new AMPassTokenUpdateUtil(context);
            }
            if (mAMPassTokenUpdateUtil.needUpdatePassToken(getPassToken(context, account), accountInfo)) {
                AccountManager.get(context).setPassword(account, ExtendedAuthToken.build(accountInfo.passToken, accountInfo.getPsecurity()).toPlain());
            }
        }
    }
}
