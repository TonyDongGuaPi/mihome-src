package com.unionpay.mobile.android.pro.pboc.engine;

import com.unionpay.mobile.android.model.c;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9673a;

    g(b bVar) {
        this.f9673a = bVar;
    }

    public final void run() {
        k.c("UPCardEngine", " sd_return : 1");
        ArrayList<c> b = this.f9673a.n.b();
        if (this.f9673a.h != null) {
            this.f9673a.h.sendMessage(this.f9673a.h.obtainMessage(1, b));
        }
    }
}
