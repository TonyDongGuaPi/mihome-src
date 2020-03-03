package com.xiaomi.payment.recharge;

import android.content.Context;
import android.os.Build;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.channel.MessageChannelFragment;

public class WoUnicomMsgPayRecharge implements Recharge {

    /* renamed from: a  reason: collision with root package name */
    private final String f12413a = "WOUNICOMMSGPAY";

    public String a() {
        return "WOUNICOMMSGPAY";
    }

    public boolean c() {
        return false;
    }

    public RechargeMethodParser b() {
        return new WoUnicomMsgPayRechargeMethodParser();
    }

    public Class<? extends BaseFragment> a(boolean z) {
        return MessageChannelFragment.class;
    }

    public boolean a(Context context) {
        return Build.VERSION.SDK_INT < 24;
    }
}
