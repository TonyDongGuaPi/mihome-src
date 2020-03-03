package com.xiaomi.payment.recharge;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.channel.PaytoolFragment;
import com.xiaomi.payment.channel.PaytoolTransitFragment;

public class WXRecharge implements Recharge {

    /* renamed from: a  reason: collision with root package name */
    private final String f12412a = "WXPAY";

    public String a() {
        return "WXPAY";
    }

    public boolean a(Context context) {
        return true;
    }

    public boolean c() {
        return true;
    }

    public RechargeMethodParser b() {
        return new WXRechargeMethodParser();
    }

    public Class<? extends BaseFragment> a(boolean z) {
        if (z) {
            return PaytoolTransitFragment.class;
        }
        return PaytoolFragment.class;
    }
}
