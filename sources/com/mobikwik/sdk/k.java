package com.mobikwik.sdk;

import android.content.Intent;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.User;
import com.mobikwik.sdk.lib.model.GenerateOTPResponse;
import com.mobikwik.sdk.lib.model.QueryWalletResponse;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;

class k implements WalletResponseCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PaymentActivity f8369a;

    k(PaymentActivity paymentActivity) {
        this.f8369a = paymentActivity;
    }

    /* renamed from: a */
    public void onTaskCompleted(QueryWalletResponse queryWalletResponse, GenerateOTPResponse generateOTPResponse) {
        PaymentActivity paymentActivity;
        StringBuilder sb;
        PaymentActivity paymentActivity2;
        boolean z;
        if (this.f8369a.f8357a != null && this.f8369a.f8357a.isShowing()) {
            this.f8369a.f8357a.dismiss();
        }
        if (SDKErrorCodes.SUCCESS.getErrorCode().equals(queryWalletResponse.getStatuscode())) {
            this.f8369a.d.d().updateUser(new User(queryWalletResponse.getEmail(), queryWalletResponse.getCell()));
            if (SDKErrorCodes.SUCCESS.getErrorCode().equals(generateOTPResponse.getStatuscode())) {
                paymentActivity2 = this.f8369a;
                z = false;
            } else {
                paymentActivity = this.f8369a;
                sb = new StringBuilder();
                sb.append("");
                sb.append(generateOTPResponse.getStatuscode());
                paymentActivity.a((Intent) null, sb.toString(), generateOTPResponse.getStatusdescription());
                return;
            }
        } else if (!SDKErrorCodes.USER_NOT_REGISTERED.getErrorCode().equals(queryWalletResponse.getStatuscode())) {
            PaymentActivity paymentActivity3 = this.f8369a;
            paymentActivity3.a((Intent) null, "" + queryWalletResponse.getStatuscode(), queryWalletResponse.getStatusdescription());
            return;
        } else if (SDKErrorCodes.SUCCESS.getErrorCode().equals(generateOTPResponse.getStatuscode())) {
            paymentActivity2 = this.f8369a;
            z = true;
        } else {
            paymentActivity = this.f8369a;
            sb = new StringBuilder();
            sb.append("");
            sb.append(generateOTPResponse.getStatuscode());
            paymentActivity.a((Intent) null, sb.toString(), generateOTPResponse.getStatusdescription());
            return;
        }
        paymentActivity2.a(z, generateOTPResponse.getStatusdescription());
    }

    public void onError(String str, String str2) {
        if (this.f8369a.f8357a != null && this.f8369a.f8357a.isShowing()) {
            this.f8369a.f8357a.dismiss();
        }
        this.f8369a.a((Intent) null, str, str2);
    }
}
