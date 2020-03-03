package com.mobikwik.sdk.ui.frag;

import android.app.Activity;
import android.content.Intent;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.model.GenerateOTPResponse;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;

class ai implements WalletResponseCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f8390a;
    final /* synthetic */ y b;

    ai(y yVar, boolean z) {
        this.b = yVar;
        this.f8390a = z;
    }

    /* renamed from: a */
    public void onTaskCompleted(GenerateOTPResponse generateOTPResponse, GenerateOTPResponse generateOTPResponse2) {
        if (this.b.d != null && this.b.d.isShowing()) {
            this.b.d.dismiss();
        }
        if (SDKErrorCodes.SUCCESS.getErrorCode().equals(generateOTPResponse.getStatuscode())) {
            this.b.a(this.f8390a, generateOTPResponse.getStatusdescription());
            return;
        }
        Activity f = this.b.f8416a;
        Utils.sendResultBack(f, (Intent) null, generateOTPResponse.getStatuscode() + "", generateOTPResponse.getStatusdescription());
    }

    public void onError(String str, String str2) {
        if (this.b.d != null && this.b.d.isShowing()) {
            this.b.d.dismiss();
        }
        Utils.sendResultBack(this.b.f8416a, (Intent) null, str, str2);
    }
}
