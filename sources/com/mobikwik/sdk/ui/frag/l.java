package com.mobikwik.sdk.ui.frag;

import android.content.Intent;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.model.GenerateOTPResponse;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;
import com.mobikwik.sdk.ui.frag.f;

class l implements WalletResponseCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f8402a;

    l(f fVar) {
        this.f8402a = fVar;
    }

    /* renamed from: a */
    public void onTaskCompleted(GenerateOTPResponse generateOTPResponse, GenerateOTPResponse generateOTPResponse2) {
        if (this.f8402a.j != null && this.f8402a.j.isShowing()) {
            this.f8402a.j.dismiss();
        }
        if (SDKErrorCodes.SUCCESS.getErrorCode().equals(generateOTPResponse.getStatuscode())) {
            String unused = this.f8402a.h = generateOTPResponse.getStatusdescription();
            this.f8402a.d();
        } else if (this.f8402a.f instanceof f.a) {
            Intent intent = new Intent();
            intent.putExtra(Constants.STATUS_CODE, generateOTPResponse.getStatuscode() + "");
            intent.putExtra(Constants.STATUS_MSG, generateOTPResponse.getStatusdescription());
            ((f.a) this.f8402a.f).a(0, intent);
        }
    }

    public void onError(String str, String str2) {
        if (this.f8402a.j != null && this.f8402a.j.isShowing()) {
            this.f8402a.j.dismiss();
        }
        if (this.f8402a.f instanceof f.a) {
            Intent intent = new Intent();
            intent.putExtra(Constants.STATUS_CODE, str);
            intent.putExtra(Constants.STATUS_MSG, str2);
            ((f.a) this.f8402a.f).a(0, intent);
        }
    }
}
