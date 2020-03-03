package com.unionpay.mobile.android.pboctransaction.simapdu;

import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.pboctransaction.b;

final class c implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9664a;

    c(b bVar) {
        this.f9664a = bVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (this.f9664a.c != null) {
                    this.f9664a.c.a();
                    break;
                } else {
                    return true;
                }
            case 2:
                if (this.f9664a.c != null) {
                    this.f9664a.c.b();
                    break;
                } else {
                    return true;
                }
            default:
                return true;
        }
        b unused = this.f9664a.c = null;
        return true;
    }
}
