package com.xiaomi.jr.mipay.codepay.util;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.xiaomi.jr.common.utils.UrlUtils;
import com.xiaomi.jr.deeplink.DeeplinkUtils;
import com.xiaomi.jr.mipay.codepay.R;
import com.xiaomi.jr.mipay.codepay.ui.BaseFragment;
import com.xiaomi.jr.mipay.codepay.ui.ChoosePayMethodFragment;
import com.xiaomi.jr.mipay.codepay.ui.CodePayActivity;
import com.xiaomi.jr.mipay.codepay.ui.CodePayCheckSmsFragment;
import com.xiaomi.jr.mipay.codepay.ui.CodePayConfirmFragment;
import com.xiaomi.jr.mipay.codepay.ui.CodePayDialogActivity;
import com.xiaomi.jr.mipay.codepay.ui.CodePayFragment;
import com.xiaomi.jr.mipay.codepay.ui.FragmentStackActivity;

public class CodePayUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10935a = "CodePayUtils";

    public static Fragment a(String str) {
        String str2;
        try {
            str2 = Uri.parse(str).getQueryParameter("id");
        } catch (Exception unused) {
            str2 = null;
        }
        return b(str2);
    }

    public static Fragment b(String str) {
        if (TextUtils.equals(str, CodePayFragment.f10924a)) {
            return new CodePayFragment();
        }
        if (TextUtils.equals(str, ChoosePayMethodFragment.f10916a)) {
            return new ChoosePayMethodFragment();
        }
        if (TextUtils.equals(str, CodePayConfirmFragment.f10922a)) {
            return new CodePayConfirmFragment();
        }
        if (TextUtils.equals(str, CodePayCheckSmsFragment.f10918a)) {
            return new CodePayCheckSmsFragment();
        }
        return null;
    }

    public static void a(Fragment fragment, String str, Bundle bundle) {
        Fragment b;
        FragmentActivity activity = fragment.getActivity();
        if ((activity instanceof FragmentStackActivity) && (b = b(str)) != null) {
            b.setArguments(bundle);
            ((FragmentStackActivity) activity).startFragment(b);
        }
    }

    public static void b(Fragment fragment, String str, Bundle bundle) {
        DeeplinkUtils.openDeeplink(fragment, (String) null, CodePayActivity.DEEPLINK_PREFIX + str, (String) null, bundle);
    }

    public static void c(Fragment fragment, String str, Bundle bundle) {
        DeeplinkUtils.openDeeplink(fragment, (String) null, CodePayDialogActivity.DEEPLINK_PREFIX + str, (String) null, bundle);
    }

    public static void a(BaseFragment baseFragment, String str) {
        DeeplinkUtils.openDeeplink(baseFragment, baseFragment.getActivity().getString(R.string.jr_mipay_pay_result_title), UrlUtils.a("https://api.jr.mi.com/loan/loan.html#/loan/pay/result", "params", str));
        baseFragment.d();
    }
}
