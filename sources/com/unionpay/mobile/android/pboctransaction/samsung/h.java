package com.unionpay.mobile.android.pboctransaction.samsung;

import android.util.Log;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.UPTsmAddon;

final class h implements UPTsmAddon.UPTsmConnectionListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f9660a;

    h(f fVar) {
        this.f9660a = fVar;
    }

    public final void onTsmConnected() {
        k.c("uppay", "TsmService connected.");
        this.f9660a.f();
    }

    public final void onTsmDisconnected() {
        Log.e("uppay", "TsmService disconnected.");
        this.f9660a.a(false);
    }
}
