package com.mipay.ucashier.ui;

import android.os.Bundle;
import android.view.View;
import com.mipay.common.base.StepActivity;
import com.mipay.common.base.l;
import com.mipay.ucashier.data.UCashierConstants;

class a implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CheckTradeInfoFragment f8188a;

    a(CheckTradeInfoFragment checkTradeInfoFragment) {
        this.f8188a = checkTradeInfoFragment;
    }

    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("tradeId", this.f8188a.h);
        bundle.putString("deviceId", this.f8188a.i);
        bundle.putSerializable(UCashierConstants.KEY_PAY_TYPES, this.f8188a.g);
        this.f8188a.a((Class<? extends l>) ChoosePayTypeFragment.class, bundle, (String) null, (Class<? extends StepActivity>) null);
    }
}
