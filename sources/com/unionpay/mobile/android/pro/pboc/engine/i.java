package com.unionpay.mobile.android.pro.pboc.engine;

import com.unionpay.mobile.android.model.c;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9675a;

    i(b bVar) {
        this.f9675a = bVar;
    }

    public final void run() {
        synchronized (this.f9675a) {
            k.c("UPCardEngine", " ic_return : 4");
            ArrayList<c> b = this.f9675a.t.b();
            if (this.f9675a.h != null) {
                this.f9675a.h.sendMessage(this.f9675a.h.obtainMessage(4, b));
            }
        }
    }
}
