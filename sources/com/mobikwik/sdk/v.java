package com.mobikwik.sdk;

import android.view.View;

class v implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PaymentOptions f8429a;

    v(PaymentOptions paymentOptions) {
        this.f8429a = paymentOptions;
    }

    public void onClick(View view) {
        this.f8429a.payByMkWallet(view);
    }
}
