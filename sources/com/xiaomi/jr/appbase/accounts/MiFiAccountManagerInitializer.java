package com.xiaomi.jr.appbase.accounts;

import android.content.Context;
import com.xiaomi.jr.account.AccountNotifier;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.appbase.CustomizedSnippets;

public class MiFiAccountManagerInitializer {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1390a = "MiFiAccountManagerInitializer";

    public static void a(Context context) {
        CustomizedSnippets.a(CustomizedSnippets.m, context);
        MiFiAccountMonitor.a().a(context);
        XiaomiAccountManager.a(MiFiAccountProvider.a());
        XiaomiAccountManager.a((AccountNotifier) MiFiAccountNotifier.a());
    }

    public static void a() {
        MiFiAccountMonitor.a().b();
    }
}
