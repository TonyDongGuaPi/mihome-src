package com.mobikwik.sdk;

import android.content.Intent;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.model.GenerateOTPResponse;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;

class l implements WalletResponseCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f8370a;
    final /* synthetic */ PaymentActivity b;

    l(PaymentActivity paymentActivity, boolean z) {
        this.b = paymentActivity;
        this.f8370a = z;
    }

    /* renamed from: a */
    public void onTaskCompleted(GenerateOTPResponse generateOTPResponse, GenerateOTPResponse generateOTPResponse2) {
        if (this.b.f8357a != null && this.b.f8357a.isShowing()) {
            this.b.f8357a.dismiss();
        }
        if (SDKErrorCodes.SUCCESS.getErrorCode().equals(generateOTPResponse.getStatuscode())) {
            this.b.a(this.f8370a, generateOTPResponse.getStatusdescription());
            return;
        }
        PaymentActivity paymentActivity = this.b;
        paymentActivity.a((Intent) null, "" + generateOTPResponse.getStatuscode(), generateOTPResponse.getStatusdescription());
    }

    public void onError(String str, String str2) {
        if (this.b.f8357a != null && this.b.f8357a.isShowing()) {
            this.b.f8357a.dismiss();
        }
        this.b.a((Intent) null, str, str2);
    }
}
