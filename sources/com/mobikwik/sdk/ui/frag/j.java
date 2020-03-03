package com.mobikwik.sdk.ui.frag;

import android.content.Intent;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.model.CreateWalletResponse;
import com.mobikwik.sdk.lib.model.GenerateOTPResponse;
import com.mobikwik.sdk.lib.utils.UIFunctions;
import com.mobikwik.sdk.lib.wallet.WalletResponseCallback;
import com.mobikwik.sdk.ui.data.a;
import com.mobikwik.sdk.ui.frag.f;
import com.xiaomi.stat.b;

class j implements WalletResponseCallback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f8400a;

    j(f fVar) {
        this.f8400a = fVar;
    }

    /* renamed from: a */
    public void onTaskCompleted(CreateWalletResponse createWalletResponse, GenerateOTPResponse generateOTPResponse) {
        if (this.f8400a.j != null && this.f8400a.j.isShowing()) {
            this.f8400a.j.dismiss();
        }
        if (this.f8400a.f instanceof f.a) {
            f.a aVar = (f.a) this.f8400a.f;
            if (SDKErrorCodes.SUCCESS.getErrorCode().equals(createWalletResponse.getStatuscode())) {
                a.a(this.f8400a.f, this.f8400a.i.getUser(), createWalletResponse.getToken());
                Intent intent = new Intent();
                intent.putExtra("balance", Double.parseDouble(b.m));
                intent.putExtra("NEWUSER", "true");
                aVar.a(-1, intent);
            } else if (SDKErrorCodes.INVALID_OTP.getErrorCode().equals(createWalletResponse.getStatuscode())) {
                UIFunctions.showToastLong(this.f8400a.getActivity(), "Please Enter correct OTP");
            } else {
                Intent intent2 = new Intent();
                intent2.putExtra(Constants.STATUS_CODE, createWalletResponse.getStatuscode() + "");
                intent2.putExtra(Constants.STATUS_MSG, createWalletResponse.getStatusdescription());
                aVar.a(0, intent2);
            }
        }
    }

    public void onError(String str, String str2) {
        if (this.f8400a.j != null && this.f8400a.j.isShowing()) {
            this.f8400a.j.dismiss();
        }
        if (this.f8400a.f instanceof f.a) {
            Intent intent = new Intent();
            intent.putExtra(Constants.STATUS_CODE, str);
            intent.putExtra(Constants.STATUS_MSG, str2);
            ((f.a) this.f8400a.f).a(0, intent);
        }
    }
}
