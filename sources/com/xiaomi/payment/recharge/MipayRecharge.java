package com.xiaomi.payment.recharge;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.mipay.ucashier.data.PayType;
import com.xiaomi.payment.channel.PaytoolFragment;
import com.xiaomi.payment.channel.PaytoolTransitFragment;

public class MipayRecharge implements Recharge {

    /* renamed from: a  reason: collision with root package name */
    private final String f12407a = PayType.PAY_NAME_MIPAY;

    public String a() {
        return PayType.PAY_NAME_MIPAY;
    }

    public boolean a(Context context) {
        return true;
    }

    public boolean c() {
        return true;
    }

    public RechargeMethodParser b() {
        return new MipayRechargeMethodParser();
    }

    public Class<? extends BaseFragment> a(boolean z) {
        if (z) {
            return PaytoolTransitFragment.class;
        }
        return PaytoolFragment.class;
    }
}
