package com.xiaomi.payment.recharge;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.channel.MessageChannelFragment;

public class TyUnicomMsgPayRecharge implements Recharge {

    /* renamed from: a  reason: collision with root package name */
    private final String f12410a = "TYUNICOMMSGPAY";

    public String a() {
        return "TYUNICOMMSGPAY";
    }

    public boolean a(Context context) {
        return true;
    }

    public boolean c() {
        return false;
    }

    public RechargeMethodParser b() {
        return new TyUnicomMsgPayRechargeMethodParser();
    }

    public Class<? extends BaseFragment> a(boolean z) {
        return MessageChannelFragment.class;
    }
}
