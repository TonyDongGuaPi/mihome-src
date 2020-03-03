package com.mobikwik.sdk;

import android.view.View;

/* renamed from: com.mobikwik.sdk.r  reason: case insensitive filesystem */
class C0094r implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PaymentOptions f8376a;

    C0094r(PaymentOptions paymentOptions) {
        this.f8376a = paymentOptions;
    }

    public void onClick(View view) {
        this.f8376a.payBySC(view);
    }
}
