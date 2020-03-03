package com.unionpay.mobile.android.pro.pboc.engine;

import com.unionpay.mobile.android.pboctransaction.b;
import com.unionpay.mobile.android.utils.k;

final class c implements b {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9669a;

    c(b bVar) {
        this.f9669a = bVar;
    }

    public final void a() {
        this.f9669a.b(1);
    }

    public final void b() {
        if (this.f9669a.h != null) {
            k.c("UPCardEngine", "mSDInitCallback.initFailed!!!!");
            this.f9669a.h.sendMessage(this.f9669a.h.obtainMessage(1, (Object) null));
        }
    }
}
