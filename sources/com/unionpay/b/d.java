package com.unionpay.b;

import android.util.Log;
import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.utils.j;

final class d implements UPTsmAddon.UPTsmConnectionListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9544a;

    d(b bVar) {
        this.f9544a = bVar;
    }

    public final void onTsmConnected() {
        j.b("uppay", "TsmService connected.");
        this.f9544a.b();
    }

    public final void onTsmDisconnected() {
        Log.e("uppay", "TsmService disconnected.");
        this.f9544a.a(this.f9544a.d, this.f9544a.e, UPSEInfoResp.ERROR_NONE, "Tsm service disconnect");
    }
}
