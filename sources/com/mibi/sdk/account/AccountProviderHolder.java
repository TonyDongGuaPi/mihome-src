package com.mibi.sdk.account;

import com.mibi.sdk.IMibiAccountProvider;

public class AccountProviderHolder {

    /* renamed from: a  reason: collision with root package name */
    private static IMibiAccountProvider f7600a;

    public static void a(IMibiAccountProvider iMibiAccountProvider) {
        f7600a = iMibiAccountProvider;
    }

    public static IMibiAccountProvider a() {
        return f7600a;
    }
}
