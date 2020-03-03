package com.xiaomi.payment.recharge;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.channel.VoucherFragment;

public class VoucherPayRecharge implements Recharge {
    public String a() {
        return "VOUCHER";
    }

    public boolean a(Context context) {
        return true;
    }

    public boolean c() {
        return false;
    }

    public RechargeMethodParser b() {
        return new VoucherPayRechargeMethodParser();
    }

    public Class<? extends BaseFragment> a(boolean z) {
        return VoucherFragment.class;
    }
}
