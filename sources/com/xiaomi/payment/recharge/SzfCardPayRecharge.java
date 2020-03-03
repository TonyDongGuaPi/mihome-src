package com.xiaomi.payment.recharge;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.channel.PrepaidCardTypeFragment;

public class SzfCardPayRecharge implements Recharge {
    public String a() {
        return "SZFCARDPAY";
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
