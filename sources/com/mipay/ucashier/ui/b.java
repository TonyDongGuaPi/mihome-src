package com.mipay.ucashier.ui;

import android.os.Bundle;
import android.view.View;
import com.mipay.common.base.StepActivity;
import com.mipay.common.base.l;

class b implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CheckTradeInfoFragment f8189a;

    b(CheckTradeInfoFragment checkTradeInfoFragment) {
        this.f8189a = checkTradeInfoFragment;
    }

    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("payType", this.f8189a.j);
        bundle.putString("tradeId", this.f8189a.h);
        bundle.putString("deviceId", this.f8189a.i);
        this.f8189a.a((Class<? extends l>) PayFragment.class, bundle, (String) null, (Class<? extends StepActivity>) CounterActivity.class);
    }
}
