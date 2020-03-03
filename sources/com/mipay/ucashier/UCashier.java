package com.mipay.ucashier;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.mipay.common.data.d;
import com.mipay.sdk.IMipayAccountProvider;
import com.mipay.ucashier.ui.CounterActivity;
import com.mipay.ucashier.ui.CreateTradeFragment;

public class UCashier {
    public static final int ERROR_CODE_CANCELED = 2;
    public static final int ERROR_CODE_EXCEPTION = 1;
    public static final int ERROR_CODE_OK = 0;
    public static final String KEY_CODE = "code";
    public static final String KEY_EXTRA = "extra";
    public static final String KEY_FULL_RESULT = "fullResult";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_ORDER = "order";
    public static final String KEY_RESULT = "result";
    public static final String KEY_USER_ID = "userId";
    public static final int REQUEST_PAY = 810;

    /* renamed from: a  reason: collision with root package name */
    private static IMipayAccountProvider f8177a;

    public static UCashier get() {
        return new UCashier();
    }

    public static UCashier get(IMipayAccountProvider iMipayAccountProvider) {
        if (iMipayAccountProvider != null) {
            f8177a = iMipayAccountProvider;
        }
        return new UCashier();
    }

    public static IMipayAccountProvider getAccountProvider() {
        return f8177a;
    }

    public static void release() {
        f8177a = null;
    }

    public void pay(Activity activity, String str, Bundle bundle) {
        if (activity == null) {
            throw new IllegalArgumentException("activity cannot be null");
        } else if (!TextUtils.isEmpty(str)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("order", str);
            bundle2.putBundle("extra", bundle);
            Intent intent = new Intent(activity, CounterActivity.class);
            intent.putExtra("fragment", CreateTradeFragment.class.getName());
            intent.putExtra(d.KEY_FRAGMENT_ARGUMENTS, bundle2);
            activity.startActivityForResult(intent, 810);
        } else {
            throw new IllegalArgumentException("order cannot be empty");
        }
    }

    public void pay(Fragment fragment, String str, Bundle bundle) {
        if (fragment == null) {
            throw new IllegalArgumentException("activity cannot be null");
        } else if (!TextUtils.isEmpty(str)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("order", str);
            bundle2.putBundle("extra", bundle);
            Intent intent = new Intent(fragment.getActivity(), CounterActivity.class);
            intent.putExtra("fragment", CreateTradeFragment.class.getName());
            intent.putExtra(d.KEY_FRAGMENT_ARGUMENTS, bundle2);
            fragment.startActivityForResult(intent, 810);
        } else {
            throw new IllegalArgumentException("order cannot be empty");
        }
    }
}
