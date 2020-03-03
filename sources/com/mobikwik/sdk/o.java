package com.mobikwik.sdk;

import android.content.DialogInterface;
import android.content.Intent;
import com.mobikwik.sdk.lib.SDKErrorCodes;

class o implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PaymentActivity f8373a;

    o(PaymentActivity paymentActivity) {
        this.f8373a = paymentActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f8373a.a((Intent) null, SDKErrorCodes.USER_CANCELLED_TRANSACTION.getErrorCode(), SDKErrorCodes.USER_CANCELLED_TRANSACTION.getErrorDescription());
    }
}
