package com.mipay.sdk.extra;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import com.mipay.sdk.IMipayAccountProvider;
import com.mipay.sdk.app.AccountProviderHolder;

public class MipayExtraFactory {
    public static final int MIPAY_EXTRA_REQUEST_CODE = 10000;
    public static final int MIPAY_EXTRA_REQUEST_CODE_PARTNER_IDENTITY_VERIFY = 10001;

    /* renamed from: a  reason: collision with root package name */
    private static final String f8169a = "MipayExtraFactory";
    private static final String b = "com.mipay.wallet";

    private MipayExtraFactory() {
    }

    private static IMipayIdentityVerify a(Context context, IMipayAccountProvider iMipayAccountProvider) {
        return (iMipayAccountProvider == null || !iMipayAccountProvider.isUseSystem() || !b(context)) ? new b() : new a();
    }

    private static boolean a(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("mipay://walletapp?id=mipay.bindCard"));
        intent.setPackage("com.mipay.wallet");
        try {
            return (context.getPackageManager().resolveActivity(intent, 0) != null) && context.getPackageManager().getPackageInfo("com.mipay.wallet", 0).versionCode >= 18008;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(f8169a, "open wallet failed NameNotFoundException", e);
            return false;
        }
    }

    private static boolean b(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("mipay://walletapp?id=mipay.partnerVerifyIdentity"));
        intent.setPackage("com.mipay.wallet");
        try {
            return (context.getPackageManager().resolveActivity(intent, 0) != null) && context.getPackageManager().getPackageInfo("com.mipay.wallet", 0).versionCode >= 1001007;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(f8169a, "open wallet failed NameNotFoundException", e);
            return false;
        }
    }

    public static IMipayExtra get(Context context, IMipayAccountProvider iMipayAccountProvider) {
        if (context != null) {
            AccountProviderHolder.put(iMipayAccountProvider);
            return (iMipayAccountProvider == null || !iMipayAccountProvider.isUseSystem() || !a(context)) ? new b() : new a();
        }
        throw new IllegalArgumentException("context is null");
    }

    public static void identityVerify(Activity activity, IMipayAccountProvider iMipayAccountProvider, String str, String str2) {
        identityVerify(activity, iMipayAccountProvider, str, str2, false);
    }

    public static void identityVerify(Activity activity, IMipayAccountProvider iMipayAccountProvider, String str, String str2, boolean z) {
        if (activity != null) {
            AccountProviderHolder.put(iMipayAccountProvider);
            a(activity, iMipayAccountProvider).identityVerify(activity, str, str2, z);
            return;
        }
        throw new IllegalArgumentException("activity is null");
    }

    public static void identityVerify(Fragment fragment, IMipayAccountProvider iMipayAccountProvider, String str, String str2) {
        identityVerify(fragment, iMipayAccountProvider, str, str2, false);
    }

    public static void identityVerify(Fragment fragment, IMipayAccountProvider iMipayAccountProvider, String str, String str2, boolean z) {
        if (fragment == null || fragment.getActivity() == null) {
            throw new IllegalArgumentException("fragment is null");
        }
        AccountProviderHolder.put(iMipayAccountProvider);
        a(fragment.getActivity(), iMipayAccountProvider).identityVerify(fragment, str, str2, z);
    }

    public static void release() {
        AccountProviderHolder.put((IMipayAccountProvider) null);
    }
}
