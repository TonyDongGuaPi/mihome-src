package com.xiaomi.smarthome.framework.login.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;

public class LoginUtil {
    public static boolean a() {
        String str = "";
        AccountManager accountManager = AccountManager.get(SHApplication.getAppContext());
        if (accountManager != null) {
            Account[] accountsByType = accountManager.getAccountsByType("com.xiaomi");
            if (accountsByType.length > 0) {
                str = accountsByType[0].name;
            }
        }
        if (!TextUtils.isEmpty(str)) {
            return true;
        }
        return false;
    }

    public static String b() {
        AccountManager accountManager = AccountManager.get(SHApplication.getAppContext());
        if (accountManager == null) {
            return "";
        }
        Account[] accountsByType = accountManager.getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return accountsByType[0].name;
        }
        return "";
    }

    public static boolean c() {
        if (!CoreApi.a().q()) {
            return false;
        }
        if (CoreApi.a().v()) {
            return true;
        }
        if (a() && TextUtils.equals(CoreApi.a().s(), b())) {
            return true;
        }
        return false;
    }
}
