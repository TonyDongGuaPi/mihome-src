package com.mibi.common.account;

import junit.framework.Assert;

public final class AccountRegistry {

    /* renamed from: a  reason: collision with root package name */
    private static IAccountProvider f7440a;
    private static IActivateProvider b;
    private static IGuestAccountProvider c;
    private static ILoginProvider d;

    private AccountRegistry() {
    }

    public static IAccountProvider a() {
        return f7440a;
    }

    public static void a(IAccountProvider iAccountProvider) {
        Assert.assertNotNull(iAccountProvider);
        f7440a = iAccountProvider;
    }

    public static IGuestAccountProvider b() {
        return c;
    }

    public static void a(IGuestAccountProvider iGuestAccountProvider) {
        Assert.assertNotNull(iGuestAccountProvider);
        c = iGuestAccountProvider;
    }

    public static IActivateProvider c() {
        return b;
    }

    public static void a(IActivateProvider iActivateProvider) {
        Assert.assertNotNull(iActivateProvider);
        b = iActivateProvider;
    }

    public static ILoginProvider d() {
        return d;
    }

    public static void a(ILoginProvider iLoginProvider) {
        Assert.assertNotNull(iLoginProvider);
        d = iLoginProvider;
    }

    public static void e() {
        f7440a = null;
        b = null;
        c = null;
        d = null;
    }
}
