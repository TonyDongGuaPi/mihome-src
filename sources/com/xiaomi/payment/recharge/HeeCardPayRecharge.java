package com.xiaomi.payment.recharge;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.channel.PrepaidCardTypeFragment;

public class HeeCardPayRecharge implements Recharge {
    public String a() {
        return "HEECARDPAY";
    }

    public boolean a(Context context) {
        return true;
    }

    public boolean c() {
        return false;
    }

    public RechargeMethodParser b() {
        return new PrepaidCardRechargeMethodParser();
    }

    public Class<? extends BaseFragment> a(boolean z) {
        return PrepaidCardTypeFragment.class;
    }
}
