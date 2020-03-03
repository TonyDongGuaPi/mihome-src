package com.mobikwik.sdk;

import android.content.Intent;
import com.mobikwik.sdk.lib.Constants;

class p implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Intent f8374a;
    final /* synthetic */ PaymentActivity b;

    p(PaymentActivity paymentActivity, Intent intent) {
        this.b = paymentActivity;
        this.f8374a = intent;
    }

    public void run() {
        this.b.a(Double.valueOf(Double.parseDouble(this.f8374a.getStringExtra(Constants.WALLET_BALANCE))));
    }
}
