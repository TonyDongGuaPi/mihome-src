package com.xiaomi.jr.account;

import android.content.Context;

public class PostLogoutTasks {
    public static void a(Context context) {
        XiaomiCookieCleaner.a(context);
        XiaomiAccountManager.a((String) null);
        XiaomiAccountManager.a().f();
    }
}
