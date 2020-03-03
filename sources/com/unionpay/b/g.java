package com.unionpay.b;

import android.util.Log;
import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.mi.UPTsmAddon;
import com.unionpay.utils.j;

final class g implements UPTsmAddon.UPTsmConnectionListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ e f9547a;

    g(e eVar) {
        this.f9547a = eVar;
    }

    public final void onTsmConnected() {
        j.b("uppay", "mi TsmService connected.");
        this.f9547a.b();
    }

    public final void onTsmDisconnected() {
        Log.e("uppay", "mi TsmService disconnected.");
        this.f9547a.a(this.f9547a.d, this.f9547a.e, UPSEInfoResp.ERROR_NONE, "Tsm service disconnect");
    }
}
