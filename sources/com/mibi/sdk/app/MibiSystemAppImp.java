package com.mibi.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.mibi.sdk.IMibi;
import com.mibi.sdk.IMibiAccountProvider;
import java.security.InvalidParameterException;

public class MibiSystemAppImp implements IMibi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7601a = "com.xiaomi.payment";

    public void a() {
    }

    public void a(Activity activity, IMibiAccountProvider iMibiAccountProvider) {
        if (activity != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage("com.xiaomi.payment");
            intent.setData(Uri.parse("https://app.mibi.xiaomi.com?id=mibi.milicenter"));
            activity.startActivity(intent);
            return;
        }
        throw new InvalidParameterException("activity cannot be null");
    }

    public void b(Activity activity, IMibiAccountProvider iMibiAccountProvider) {
        a(activity, 0, iMibiAccountProvider, (Bundle) null);
    }

    public void a(Activity activity, long j, IMibiAccountProvider iMibiAccountProvider, Bundle bundle) {
        if (activity != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage("com.xiaomi.payment");
            intent.setData(Uri.parse("https://app.mibi.xiaomi.com?id=mibi.recharge"));
            Bundle bundle2 = new Bundle();
            if (bundle == null) {
                bundle = bundle2;
            }
            bundle.putLong("rechargeAmount", j);
            intent.putExtra("payment_fragment_arguments", bundle);
            activity.startActivityForResult(intent, 425);
            return;
        }
        throw new InvalidParameterException("activity cannot be null");
    }

    public void a(Activity activity, String str, IMibiAccountProvider iMibiAccountProvider, Bundle bundle) {
        if (activity == null) {
            throw new InvalidParameterException("activity cannot be null");
        } else if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent("com.xiaomi.action.DEDUCT");
            intent.putExtra("deductSignOrder", str);
            int i = -1;
            if (bundle != null) {
                i = bundle.getInt("deductRequestCode", -1);
            }
            activity.startActivityForResult(intent, i);
        } else {
            throw new InvalidParameterException("the value of order can not be empty!");
        }
    }

    public void b(Activity activity, String str, IMibiAccountProvider iMibiAccountProvider, Bundle bundle) {
        if (activity != null) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage("com.xiaomi.payment");
            intent.setData(Uri.parse("https://app.mibi.xiaomi.com?id=mibi.pay"));
            Bundle bundle2 = new Bundle();
            if (bundle == null) {
                bundle = bundle2;
            }
            bundle.putString("order", str);
            intent.putExtra("payment_fragment_arguments", bundle);
            activity.startActivityForResult(intent, 424);
            return;
        }
        throw new InvalidParameterException("activity cannot be null");
    }
}
