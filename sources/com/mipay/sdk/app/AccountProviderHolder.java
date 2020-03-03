package com.mipay.sdk.app;

import com.mipay.sdk.IMipayAccountProvider;

public class AccountProviderHolder {

    /* renamed from: a  reason: collision with root package name */
    private static IMipayAccountProvider f8153a;

    public static IMipayAccountProvider get() {
        return f8153a;
    }

    public static void put(IMipayAccountProvider iMipayAccountProvider) {
        f8153a = iMipayAccountProvider;
    }
}
