package com.unionpay.mobile.android.pboctransaction.samsung;

import android.os.Handler;
import android.os.Message;

final class c implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9655a;

    c(b bVar) {
        this.f9655a = bVar;
    }

    public final boolean handleMessage(Message message) {
        if (message.what == 1) {
            this.f9655a.a(false);
        }
        return true;
    }
}
