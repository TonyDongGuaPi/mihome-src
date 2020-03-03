package com.xiaomi.passport.servicetoken;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.xiaomi.accountsdk.utils.AccountLog;

final class AMUtilImpl implements IAMUtil {
    private static final String TAG = "AMUtilImpl";
    private final AMKeys amKeys;

    AMUtilImpl(AMKeys aMKeys) {
        if (aMKeys != null) {
            this.amKeys = aMKeys;
            return;
        }
        throw new IllegalArgumentException("amKeys == null");
    }

    public Account getXiaomiAccount(Context context) {
        Account[] accountsByType = AccountManager.get(context).getAccountsByType(this.amKeys.getType());
        if (accountsByType == null || accountsByType.length <= 0) {
            return null;
        }
        return accountsByType[0];
    }

    public void invalidateAuthToken(Context context, String str) {
        AccountManager.get(context).invalidateAuthToken(this.amKeys.getType(), str);
    }

    public String getCUserId(Context context, Account account) {
        try {
            return AccountManager.get(context).getUserData(account, this.amKeys.getAmUserDataKeyCUserId());
        } catch (SecurityException e) {
            AccountLog.d(TAG, "getSlh", e);
            return null;
        }
    }

    public String getSlh(Context context, String str, Account account) {
        try {
            return AccountManager.get(context).getUserData(account, this.amKeys.getAmUserDataKeySlh(str));
        } catch (SecurityException e) {
            AccountLog.d(TAG, "getSlh", e);
            return null;
        }
    }

    public String getPh(Context context, String str, Account account) {
        try {
            return AccountManager.get(context).getUserData(account, this.amKeys.getAmUserDataKeyPh(str));
        } catch (SecurityException e) {
            AccountLog.d(TAG, "getSlh", e);
            return null;
        }
    }

    public AccountManagerFuture<Bundle> getAuthToken(Context context, String str, Account account) {
        return AccountManager.get(context).getAuthToken(account, str, (Bundle) null, (Activity) null, (AccountManagerCallback) null, (Handler) null);
    }

    public String peekAuthToken(Context context, String str, Account account) {
        try {
            return AccountManager.get(context).peekAuthToken(account, str);
        } catch (SecurityException e) {
            AccountLog.d(TAG, "peedAuthToken", e);
            return null;
        }
    }
}
