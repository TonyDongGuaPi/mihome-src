package com.unionpay.mobile.android.pboctransaction.remoteapdu;

import android.os.Handler;
import android.os.Message;

final class b implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9651a;

    b(a aVar) {
        this.f9651a = aVar;
    }

    public final boolean handleMessage(Message message) {
        if (message.what != 3000 || this.f9651a.f9650a == null) {
            return false;
        }
        this.f9651a.f9650a.b();
        return false;
    }
}
