package com.xiaomi.payment.recharge;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.channel.MessageChannelFragment;

public class UmMsgPayRecharge implements Recharge {

    /* renamed from: a  reason: collision with root package name */
    private final String f12411a = "UMMSGPAY";

    public String a() {
        return "UMMSGPAY";
    }

    public boolean a(Context context) {
        return true;
    }

    public boolean c() {
        return false;
    }

    public RechargeMethodParser b() {
        return new UmMsgPayRechargeMethodParser();
    }

    public Class<? extends BaseFragment> a(boolean z) {
        return MessageChannelFragment.class;
    }
}
