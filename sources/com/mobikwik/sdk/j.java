package com.mobikwik.sdk;

import android.content.Context;
import android.content.Intent;
import com.mobikwik.sdk.lib.model.GenerateOTPResponse;
import com.mobikwik.sdk.lib.model.TxnHashResponse;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;
import com.mobikwik.sdk.ui.data.b;

class j implements WalletResponseCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PaymentActivity f8368a;

    j(PaymentActivity paymentActivity) {
        this.f8368a = paymentActivity;
    }

    /* renamed from: a */
    public void onTaskCompleted(TxnHashResponse txnHashResponse, GenerateOTPResponse generateOTPResponse) {
        if (txnHashResponse.getTxnHash() != null) {
            b.b((Context) this.f8368a).b(txnHashResponse.getTxnHash());
            this.f8368a.h();
            return;
        }
        if (this.f8368a.f8357a != null && this.f8368a.f8357a.isShowing()) {
            this.f8368a.f8357a.dismiss();
        }
        this.f8368a.a((Intent) null, txnHashResponse.getStatuscode(), txnHashResponse.getStatusdescription());
    }

    public void onError(String str, String str2) {
        if (this.f8368a.f8357a != null && this.f8368a.f8357a.isShowing()) {
            this.f8368a.f8357a.dismiss();
        }
        this.f8368a.a((Intent) null, str, str2);
    }
}
