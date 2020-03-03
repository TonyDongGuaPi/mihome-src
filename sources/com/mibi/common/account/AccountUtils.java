package com.mibi.common.account;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import com.mibi.common.data.Utils;
import com.mipay.core.internal.BundleManager;

public class AccountUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7442a = "AccountUtils";

    private AccountUtils() {
    }

    public static AccountLoader a(Account account) {
        return new MiAccountLoader(account);
    }

    public static AccountLoader a() {
        return new GuestAccountLoader();
    }

    public static AccountLoader b() {
        return new FakeAccountLoader();
    }

    public static boolean a(Context context) {
        if (AccountRegistry.a() == null) {
            BundleManager.init(context.getApplicationContext());
        }
        return AccountRegistry.a().getAccountsByType("com.xiaomi").length > 0;
    }

    public static Account b(Context context) {
        if (AccountRegistry.a() == null) {
            BundleManager.init(context.getApplicationContext());
        }
        Account[] accountsByType = AccountRegistry.a().getAccountsByType("com.xiaomi");
        if (accountsByType.length == 0) {
            return null;
        }
        return accountsByType[0];
    }

    public static String c(Context context) {
        Account b = b(context);
        return b != null ? b.name : "";
    }

    public static AccountLoader a(Context context, String str) {
        AccountLevel accountLevel = AccountLevel.get(str);
        Account b = b(context);
        if (b != null) {
            return a(b);
        }
        if (accountLevel != AccountLevel.SYSTEM) {
            return a();
        }
        return null;
    }

    public static void d(Context context) {
        String c = c(context);
        if (!TextUtils.equals(Utils.a(context, "userId", ""), c)) {
            Utils.b(context, "userId", c);
            Log.d(f7442a, "removeAllCookies");
            CookieManager.getInstance().removeAllCookie();
        }
    }
}
