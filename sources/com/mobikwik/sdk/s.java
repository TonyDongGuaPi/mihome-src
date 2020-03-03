package com.mobikwik.sdk;

import android.view.View;

class s implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PaymentOptions f8377a;

    s(PaymentOptions paymentOptions) {
        this.f8377a = paymentOptions;
    }

    public void onClick(View view) {
        this.f8377a.payByCC(view);
    }
}
