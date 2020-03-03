package com.mipay.ucashier.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.mipay.common.base.StepActivity;
import com.mipay.common.base.l;
import com.mipay.ucashier.component.PayTypeGridItem;
import com.mipay.ucashier.data.PayType;

class c implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ChoosePayTypeFragment f8190a;

    c(ChoosePayTypeFragment choosePayTypeFragment) {
        this.f8190a = choosePayTypeFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        PayType payType = ((PayTypeGridItem) view).getPayType();
        Bundle bundle = new Bundle();
        bundle.putSerializable("payType", payType);
        bundle.putString("tradeId", this.f8190a.h);
        bundle.putString("deviceId", this.f8190a.i);
        this.f8190a.a((Class<? extends l>) PayFragment.class, bundle, (String) null, (Class<? extends StepActivity>) CounterActivity.class);
    }
}
