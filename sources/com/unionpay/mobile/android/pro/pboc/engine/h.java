package com.unionpay.mobile.android.pro.pboc.engine;

import com.unionpay.mobile.android.model.c;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9674a;

    h(b bVar) {
        this.f9674a = bVar;
    }

    public final void run() {
        synchronized (this.f9674a) {
            k.c("UPCardEngine", " cmcc_return : 2");
            ArrayList<c> b = this.f9674a.q.b();
            if (this.f9674a.h != null) {
                this.f9674a.h.sendMessage(this.f9674a.h.obtainMessage(2, b));
            }
        }
    }
}
