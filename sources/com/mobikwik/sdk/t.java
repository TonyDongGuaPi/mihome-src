package com.mobikwik.sdk;

import android.view.View;

class t implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PaymentOptions f8378a;

    t(PaymentOptions paymentOptions) {
        this.f8378a = paymentOptions;
    }

    public void onClick(View view) {
        this.f8378a.payByDC(view);
    }
}
