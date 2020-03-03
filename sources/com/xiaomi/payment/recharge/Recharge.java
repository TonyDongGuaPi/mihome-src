package com.xiaomi.payment.recharge;

import android.content.Context;
import com.mibi.common.base.BaseFragment;

public interface Recharge {
    Class<? extends BaseFragment> a(boolean z);

    String a();

    boolean a(Context context);

    RechargeMethodParser b();

    boolean c();
}
