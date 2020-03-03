package com.xiaomi.payment.deduct;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.xiaomi.payment.recharge.Recharge;
import com.xiaomi.payment.recharge.RechargeMethodParser;

public class WxpayDeduct implements Recharge {

    /* renamed from: a  reason: collision with root package name */
    private final String f12247a = "WXPAYDEDUCT";

    public Class<? extends BaseFragment> a(boolean z) {
        return null;
    }

    public String a() {
        return "WXPAYDEDUCT";
    }

    public boolean a(Context context) {
        return true;
    }

    public boolean c() {
        return false;
    }

    public RechargeMethodParser b() {
        return new WxpayDeductMethodParaser();
    }
}
