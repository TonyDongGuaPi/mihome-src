package com.unionpay.mobile.android.nocard.utils;

import android.content.Context;
import android.text.TextUtils;
import com.unionpay.mobile.android.model.b;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.views.order.l;

public final class c {
    public static int a(Context context, b bVar) {
        Integer num;
        String b = PreferenceUtils.b(context);
        int intValue = l.b.intValue();
        if (!TextUtils.isEmpty(b) && TextUtils.isDigitsOnly(b)) {
            intValue = Integer.valueOf(b).intValue();
        }
        boolean equalsIgnoreCase = "0".equalsIgnoreCase(bVar.an);
        if (bVar.aw && (bVar.ao & 69889) == 0) {
            equalsIgnoreCase = true;
        }
        if (equalsIgnoreCase) {
            num = l.f9759a;
        } else {
            if (!TextUtils.isEmpty(bVar.u)) {
                intValue = l.b.intValue();
            }
            if (!f.a(bVar.t)) {
                return intValue;
            }
            num = l.b;
        }
        return num.intValue();
    }
}
