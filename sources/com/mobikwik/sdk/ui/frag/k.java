package com.mobikwik.sdk.ui.frag;

import android.app.Activity;
import android.content.Intent;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.model.DebitWalletResponse;
import com.mobikwik.sdk.lib.model.GenerateOTPResponse;
import com.mobikwik.sdk.lib.utils.UIFunctions;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;
import com.mobikwik.sdk.ui.data.a;
import com.mobikwik.sdk.ui.frag.f;

class k implements WalletResponseCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f8401a;

    k(f fVar) {
        this.f8401a = fVar;
    }

    /* renamed from: a */
    public void onTaskCompleted(DebitWalletResponse debitWalletResponse, GenerateOTPResponse generateOTPResponse) {
        Activity c;
        String str;
        if (this.f8401a.j != null && this.f8401a.j.isShowing()) {
            this.f8401a.j.dismiss();
        }
        if (SDKErrorCodes.SUCCESS.getErrorCode().equals(debitWalletResponse.getStatuscode())) {
            a.a(this.f8401a.f, this.f8401a.i.getUser(), debitWalletResponse.getToken());
            c = this.f8401a.f;
            str = "0";
        } else if (SDKErrorCodes.INVALID_OTP.getErrorCode().equals(debitWalletResponse.getStatuscode())) {
            UIFunctions.showToastLong(this.f8401a.getActivity(), "Please Enter correct OTP");
            return;
        } else if (SDKErrorCodes.WALLET_INSUFFICIENT_BALANCE.getErrorCode().equals(debitWalletResponse.getStatuscode())) {
            a.a(this.f8401a.f, this.f8401a.i.getUser(), debitWalletResponse.getToken());
            if (this.f8401a.f instanceof f.a) {
                Intent intent = new Intent();
                intent.putExtra("balance", debitWalletResponse.getBalanceamount());
                intent.putExtra("NEWUSER", "false");
                ((f.a) this.f8401a.f).a(-1, intent);
                return;
            }
            return;
        } else {
            c = this.f8401a.f;
            str = debitWalletResponse.getStatuscode() + "";
        }
        Utils.sendResultBack(c, (Intent) null, str, debitWalletResponse.getStatusdescription());
    }

    public void onError(String str, String str2) {
        if (this.f8401a.j != null && this.f8401a.j.isShowing()) {
            this.f8401a.j.dismiss();
        }
        Utils.sendResultBack(this.f8401a.f, (Intent) null, str, str2);
    }
}
