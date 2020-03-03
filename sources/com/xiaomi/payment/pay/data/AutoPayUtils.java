package com.xiaomi.payment.pay.data;

import android.text.TextUtils;
import com.xiaomi.payment.recharge.AlipayRecharge;
import com.xiaomi.payment.recharge.RechargeType;
import java.util.ArrayList;
import java.util.Iterator;

public class AutoPayUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f12368a = "MORE";
    public static final String b = new AlipayRecharge().a();

    public static RechargeType a(ArrayList<RechargeType> arrayList, String str) {
        if (arrayList != null) {
            Iterator<RechargeType> it = arrayList.iterator();
            while (it.hasNext()) {
                RechargeType next = it.next();
                if (TextUtils.equals(next.mType, str)) {
                    return next;
                }
            }
            return null;
        }
        throw new IllegalArgumentException("mRechargeTypes should not be null here");
    }
}
