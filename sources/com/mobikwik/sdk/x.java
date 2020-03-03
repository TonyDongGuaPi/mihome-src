package com.mobikwik.sdk;

import android.content.DialogInterface;
import android.content.Intent;
import com.mobikwik.sdk.lib.SDKErrorCodes;

class x implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PaymentOptions f8431a;

    x(PaymentOptions paymentOptions) {
        this.f8431a = paymentOptions;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f8431a.a((Intent) null, SDKErrorCodes.USER_CANCELLED_TRANSACTION.getErrorCode(), SDKErrorCodes.USER_CANCELLED_TRANSACTION.getErrorDescription());
    }
}
