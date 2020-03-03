package com.mipay.sdk.extra;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import com.mipay.sdk.app.Constants;
import com.mipay.sdk.app.MipayWebActivity;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class b implements IMipayExtra, IMipayIdentityVerify {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8171a = "MipayExtraWebImpl";
    private static final String b = "https://m.pay.xiaomi.com/home/toOnlyBindPage";
    private static final String c = "http://staging.m.pay.xiaomi.com/home/toOnlyBindPage";
    private static final String d = "http://test.m.pay.xiaomi.com/home/toOnlyBindPage";
    private static final String e = "https://app.mipay.com?id=mipay.partnerIdentityVerify";
    private static final String f = "http://staging.mipay.xiaomi.com/identity/partner/upload";
    private static final String g = "http://test.mipay.xiaomi.com/identity/partner/upload";

    b() {
    }

    private Intent a(Activity activity, String str, String str2, boolean z) {
        Intent intent = new Intent(activity, MipayWebActivity.class);
        intent.putExtra(Constants.KEY_URL, a(str, str2, z));
        intent.setPackage(activity.getPackageName());
        return intent;
    }

    private String a(String str) {
        StringBuilder sb;
        String str2;
        if (Constants.STAGING) {
            sb = new StringBuilder();
            str2 = "http://staging.m.pay.xiaomi.com/home/toOnlyBindPage?ref=";
        } else if (Constants.TEST) {
            sb = new StringBuilder();
            str2 = "http://test.m.pay.xiaomi.com/home/toOnlyBindPage?ref=";
        } else {
            sb = new StringBuilder();
            str2 = "https://m.pay.xiaomi.com/home/toOnlyBindPage?ref=";
        }
        sb.append(str2);
        sb.append(str);
        return sb.toString();
    }

    private String a(String str, String str2, boolean z) {
        String str3;
        StringBuilder sb;
        String str4;
        String str5;
        try {
            str3 = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e2) {
            Log.e(f8171a, "encode request data failed", e2);
            str3 = "";
        }
        if (Constants.STAGING) {
            str5 = f;
            sb = new StringBuilder();
        } else if (Constants.TEST) {
            str5 = g;
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder();
            sb.append(e);
            str4 = "&requestData=";
            sb.append(str4);
            sb.append(str3);
            sb.append("&miref=");
            sb.append(str2);
            sb.append("&skipIntroduction=");
            sb.append(z);
            return sb.toString();
        }
        sb.append(str5);
        str4 = "?requestData=";
        sb.append(str4);
        sb.append(str3);
        sb.append("&miref=");
        sb.append(str2);
        sb.append("&skipIntroduction=");
        sb.append(z);
        return sb.toString();
    }

    public void identityVerify(Activity activity, String str, String str2) {
        identityVerify(activity, str, str2, false);
    }

    public void identityVerify(Activity activity, String str, String str2, boolean z) {
        activity.startActivityForResult(a(activity, str, str2, z), 10001);
    }

    public void identityVerify(Fragment fragment, String str, String str2) {
        identityVerify(fragment, str, str2, false);
    }

    public void identityVerify(Fragment fragment, String str, String str2, boolean z) {
        fragment.startActivityForResult(a(fragment.getActivity(), str, str2, z), 10001);
    }

    public void simpleBindCard(Activity activity, String str) {
        if (activity != null) {
            String a2 = a(str);
            Intent intent = new Intent(activity, MipayWebActivity.class);
            intent.putExtra(Constants.KEY_URL, a2);
            intent.setPackage(activity.getPackageName());
            activity.startActivityForResult(intent, 10000);
            return;
        }
        throw new IllegalArgumentException("activity should not be null");
    }

    public void simpleBindCard(Fragment fragment, String str) {
        if (fragment != null) {
            String a2 = a(str);
            Intent intent = new Intent(fragment.getActivity(), MipayWebActivity.class);
            intent.putExtra(Constants.KEY_URL, a2);
            intent.setPackage(fragment.getActivity().getPackageName());
            fragment.startActivityForResult(intent, 10000);
            return;
        }
        throw new IllegalArgumentException("activity should not be null");
    }
}
