package com.xiaomi.passport;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.passport.accountmanager.MiAccountManager;

public class AccountChangedBroadcastHelper {
    public static final String EXTRA_ACCOUNT = "extra_account";
    public static final String EXTRA_UPDATE_TYPE = "extra_update_type";
    private static final String LOCAL_LOGIN_ACCOUNTS_POST_CHANGED_ACTION = "com.xiaomi.accounts.LOGIN_ACCOUNTS_POST_CHANGED";
    private static final String LOCAL_LOGIN_ACCOUNTS_PRE_CHANGED_ACTION = "com.xiaomi.accounts.LOGIN_ACCOUNTS_PRE_CHANGED";
    private static final String SYSTEM_LOGIN_ACCOUNTS_POST_CHANGED_ACTION = "android.accounts.LOGIN_ACCOUNTS_POST_CHANGED";
    private static final String SYSTEM_LOGIN_ACCOUNTS_PRE_CHANGED_ACTION = "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED";
    public static final int TYPE_ADD = 2;
    public static final int TYPE_REFRESH = 3;
    public static final int TYPE_REMOVE = 1;

    public enum UpdateType {
        PRE_ADD,
        POST_ADD,
        POST_REFRESH,
        PRE_REMOVE,
        POST_REMOVE
    }

    public static String getAccountsPreChangedAction(Context context) {
        return MiAccountManager.get(context).isUseSystem() ? "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED" : LOCAL_LOGIN_ACCOUNTS_PRE_CHANGED_ACTION;
    }

    public static String getAccountsPostChangedAction(Context context) {
        return MiAccountManager.get(context).isUseSystem() ? SYSTEM_LOGIN_ACCOUNTS_POST_CHANGED_ACTION : LOCAL_LOGIN_ACCOUNTS_POST_CHANGED_ACTION;
    }

    public static void sendBroadcast(Context context, Account account, UpdateType updateType) {
        String str;
        if (context == null || updateType == null) {
            throw new IllegalArgumentException();
        }
        int i = 1;
        switch (updateType) {
            case PRE_ADD:
                str = getAccountsPreChangedAction(context);
                break;
            case POST_ADD:
                str = getAccountsPostChangedAction(context);
                break;
            case POST_REFRESH:
                str = getAccountsPostChangedAction(context);
                i = 3;
                break;
            case PRE_REMOVE:
                str = getAccountsPreChangedAction(context);
                break;
            case POST_REMOVE:
                str = getAccountsPostChangedAction(context);
                break;
            default:
                throw new IllegalStateException("this should not be happen");
        }
        i = 2;
        Intent intent = new Intent(str);
        intent.putExtra("extra_account", account);
        intent.putExtra("extra_update_type", i);
        if (!MiAccountManager.get(context).isUseSystem()) {
            intent.setPackage(context.getPackageName());
        }
        context.sendBroadcast(intent);
    }
}
