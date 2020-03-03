package com.mipay.sdk;

import android.content.Context;
import android.content.Intent;
import com.mipay.sdk.app.AccountProviderHolder;

public class MipayFactory {
    public static final int MIPAY_REQUEST_CODE = 424;

    /* renamed from: a  reason: collision with root package name */
    private static final String f8151a = "com.xiaomi.action.MIPAY_PAY_ORDER";
    private static final String b = "com.mipay.wallet";

    private static boolean a(Context context) {
        Intent intent = new Intent(f8151a);
        intent.setPackage("com.mipay.wallet");
        return context.getPackageManager().resolveActivity(intent, 0) != null;
    }

    public static IMipay get(Context context) {
        return get(context, (IMipayAccountProvider) null);
    }

    public static IMipay get(Context context, IMipayAccountProvider iMipayAccountProvider) {
        if (context != null) {
            AccountProviderHolder.put(iMipayAccountProvider);
            return (iMipayAccountProvider == null || !iMipayAccountProvider.isUseSystem() || !a(context)) ? new b() : new a(context);
        }
        throw new IllegalArgumentException("context is null");
    }

    public static void release() {
        AccountProviderHolder.put((IMipayAccountProvider) null);
    }
}
