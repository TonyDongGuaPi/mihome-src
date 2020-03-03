package com.mobikwik.sdk.ui.frag;

import android.app.Activity;
import android.content.Intent;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.model.DebitWalletResponse;
import com.mobikwik.sdk.lib.model.GenerateOTPResponse;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;

class ah implements WalletResponseCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ y f8389a;

    ah(y yVar) {
        this.f8389a = yVar;
    }

    /* renamed from: a */
    public void onTaskCompleted(DebitWalletResponse debitWalletResponse, GenerateOTPResponse generateOTPResponse) {
        Activity f;
        String str;
        if (this.f8389a.d != null && this.f8389a.d.isShowing()) {
            this.f8389a.d.dismiss();
        }
        if (SDKErrorCodes.SUCCESS.getErrorCode().equals(debitWalletResponse.getStatuscode())) {
            f = this.f8389a.f8416a;
            str = "0";
        } else if (!SDKErrorCodes.INVALID_TOKEN.getErrorCode().equals(debitWalletResponse.getStatuscode())) {
            if (this.f8389a.d != null && this.f8389a.d.isShowing()) {
                this.f8389a.d.dismiss();
            }
            f = this.f8389a.f8416a;
            str = debitWalletResponse.getStatuscode() + "";
        } else if (generateOTPResponse == null) {
            this.f8389a.a(false);
            return;
        } else if (SDKErrorCodes.SUCCESS.getErrorCode().equals(generateOTPResponse.getStatuscode())) {
            this.f8389a.a(false, generateOTPResponse.getStatusdescription());
            return;
        } else {
            Utils.sendResultBack(this.f8389a.f8416a, (Intent) null, generateOTPResponse.getStatuscode() + "", generateOTPResponse.getStatusdescription());
            return;
        }
        Utils.sendResultBack(f, (Intent) null, str, debitWalletResponse.getStatusdescription());
    }

    public void onError(String str, String str2) {
        if (this.f8389a.d != null && this.f8389a.d.isShowing()) {
            this.f8389a.d.dismiss();
        }
        Utils.sendResultBack(this.f8389a.f8416a, (Intent) null, str, str2);
    }
}
