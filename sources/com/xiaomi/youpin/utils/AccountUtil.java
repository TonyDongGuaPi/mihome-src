package com.xiaomi.youpin.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import com.xiaomi.plugin.XmPluginHostApi;

public class AccountUtil {
    public static String a() {
        AccountManager accountManager = AccountManager.get(XmPluginHostApi.instance().context());
        if (accountManager == null) {
            return "";
        }
        Account[] accountsByType = accountManager.getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return accountsByType[0].name;
        }
        return "";
    }
}
