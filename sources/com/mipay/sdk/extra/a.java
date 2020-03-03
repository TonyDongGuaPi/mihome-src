package com.mipay.sdk.extra;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class a implements IMipayExtra, IMipayIdentityVerify {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8170a = "mipay://walletapp?id=mipay.bindCard";
    private static final String b = "https://app.mipay.com?id=mipay.partnerIdentityVerify";
    private static final String c = "com.mipay.wallet";
    private static final String d = "MipayExtraAppImpl";

    a() {
    }

    private Intent a(String str, String str2, boolean z) {
        String str3;
        try {
            str3 = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(d, "encode request data failed", e);
            str3 = "";
        }
        String str4 = "https://app.mipay.com?id=mipay.partnerIdentityVerify&miref=" + str2 + "&requestData=" + str3 + "&skipIntroduction=" + z;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str4));
        intent.setPackage("com.mipay.wallet");
        return intent;
    }

    public void identityVerify(Activity activity, String str, String str2) {
        identityVerify(activity, str, str2, false);
    }

    public void identityVerify(Activity activity, String str, String str2, boolean z) {
        activity.startActivityForResult(a(str, str2, z), 10001);
    }

    public void identityVerify(Fragment fragment, String str, String str2) {
        identityVerify(fragment, str, str2, false);
    }

    public void identityVerify(Fragment fragment, String str, String str2, boolean z) {
        fragment.startActivityForResult(a(str, str2, z), 10001);
    }

    public void simpleBindCard(Activity activity, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("mipay://walletapp?id=mipay.bindCard&miref=" + str));
        intent.setPackage("com.mipay.wallet");
        activity.startActivityForResult(intent, 10000);
    }

    public void simpleBindCard(Fragment fragment, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("mipay://walletapp?id=mipay.bindCard&miref=" + str));
        intent.setPackage("com.mipay.wallet");
        fragment.startActivityForResult(intent, 10000);
    }
}
