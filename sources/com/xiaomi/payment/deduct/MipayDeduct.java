package com.xiaomi.payment.deduct;

import android.content.Context;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.recharge.Recharge;
import com.xiaomi.payment.recharge.RechargeMethodParser;

public class MipayDeduct implements Recharge {

    /* renamed from: a  reason: collision with root package name */
    private final String f12245a = "MIPAYDEDUCT";

    public Class<? extends BaseFragment> a(boolean z) {
        return null;
    }

    public String a() {
        return "MIPAYDEDUCT";
    }

    public boolean c() {
        return false;
    }

    public RechargeMethodParser b() {
        return new MipayDeductMethodParser();
    }

    public boolean a(Context context) {
        return !Utils.b();
    }
}
