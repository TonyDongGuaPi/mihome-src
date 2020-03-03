package com.mobikwik.sdk;

import android.content.Intent;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.model.GenerateOTPResponse;
import com.mobikwik.sdk.lib.model.SavedCardResponse;
import com.mobikwik.sdk.lib.model.UserBalanceResponse;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;
import com.mobikwik.sdk.ui.data.b;
import java.util.List;

class m implements WalletResponseCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f8371a;
    final /* synthetic */ PaymentActivity b;

    m(PaymentActivity paymentActivity, b bVar) {
        this.b = paymentActivity;
        this.f8371a = bVar;
    }

    /* renamed from: a */
    public void onTaskCompleted(UserBalanceResponse userBalanceResponse, GenerateOTPResponse generateOTPResponse) {
        if (SDKErrorCodes.SUCCESS.getErrorCode().equals(userBalanceResponse.getStatuscode())) {
            if (this.b.f8357a != null && this.b.f8357a.isShowing()) {
                this.b.f8357a.dismiss();
            }
            Double valueOf = Double.valueOf(userBalanceResponse.getBalance());
            if (userBalanceResponse.getStoredCards() != null) {
                List storedCards = userBalanceResponse.getStoredCards();
                this.f8371a.a((SavedCardResponse.CardDetails[]) storedCards.toArray(new SavedCardResponse.CardDetails[storedCards.size()]));
            }
            this.b.a(valueOf);
        } else if (!SDKErrorCodes.INVALID_TOKEN.getErrorCode().equals(userBalanceResponse.getStatuscode())) {
            if (this.b.f8357a != null && this.b.f8357a.isShowing()) {
                this.b.f8357a.dismiss();
            }
            PaymentActivity paymentActivity = this.b;
            paymentActivity.a((Intent) null, "" + userBalanceResponse.getStatuscode(), userBalanceResponse.getStatusdescription());
        } else if (generateOTPResponse == null) {
            this.b.a(false);
        } else {
            if (this.b.f8357a != null && this.b.f8357a.isShowing()) {
                this.b.f8357a.dismiss();
            }
            if (SDKErrorCodes.SUCCESS.getErrorCode().equals(generateOTPResponse.getStatuscode())) {
                this.b.a(false, generateOTPResponse.getStatusdescription());
                return;
            }
            PaymentActivity paymentActivity2 = this.b;
            paymentActivity2.a((Intent) null, "" + generateOTPResponse.getStatuscode(), generateOTPResponse.getStatusdescription());
        }
    }

    public void onError(String str, String str2) {
        if (this.b.f8357a != null && this.b.f8357a.isShowing()) {
            this.b.f8357a.dismiss();
        }
        this.b.a((Intent) null, str, str2);
    }
}
