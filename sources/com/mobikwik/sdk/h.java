package com.mobikwik.sdk;

import android.content.DialogInterface;
import android.content.Intent;
import com.mobikwik.sdk.lib.SDKErrorCodes;
import com.mobikwik.sdk.lib.utils.Utils;

class h implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PGWebView f8366a;

    h(PGWebView pGWebView) {
        this.f8366a = pGWebView;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Utils.sendResultBack(this.f8366a, (Intent) null, SDKErrorCodes.USER_CANCELLED_TRANSACTION.getErrorCode(), SDKErrorCodes.USER_CANCELLED_TRANSACTION.getErrorDescription());
    }
}
