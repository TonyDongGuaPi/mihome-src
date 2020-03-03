package com.xiaomi.jr.appbase.accounts;

import com.xiaomi.jr.account.IAccountProvider;

public class MiFiAccountProvider {

    /* renamed from: a  reason: collision with root package name */
    private static IAccountProvider f10313a;

    private MiFiAccountProvider() {
    }

    public static void a(IAccountProvider iAccountProvider) {
        f10313a = iAccountProvider;
    }

    public static IAccountProvider a() {
        return f10313a;
    }
}
