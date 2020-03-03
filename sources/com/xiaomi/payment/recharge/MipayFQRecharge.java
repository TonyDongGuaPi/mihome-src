package com.xiaomi.payment.recharge;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.channel.PaytoolFragment;
import com.xiaomi.payment.channel.PaytoolTransitFragment;

public class MipayFQRecharge implements Recharge {

    /* renamed from: a  reason: collision with root package name */
    private final String f12406a = "MIPAYFQ";

    public String a() {
        return "MIPAYFQ";
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
