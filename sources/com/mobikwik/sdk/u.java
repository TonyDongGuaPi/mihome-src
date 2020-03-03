package com.mobikwik.sdk;

import android.view.View;

class u implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PaymentOptions f8379a;

    u(PaymentOptions paymentOptions) {
        this.f8379a = paymentOptions;
    }

    public void onClick(View view) {
        this.f8379a.payByNetbanking(view);
    }
}
