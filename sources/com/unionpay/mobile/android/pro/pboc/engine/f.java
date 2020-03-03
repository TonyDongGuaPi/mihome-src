package com.unionpay.mobile.android.pro.pboc.engine;

import com.unionpay.mobile.android.pboctransaction.b;
import com.unionpay.mobile.android.utils.k;

final class f implements b {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9672a;

    f(b bVar) {
        this.f9672a = bVar;
    }

    public final void a() {
        k.c("uppay-spay", "tsmservice  init success");
        com.unionpay.mobile.android.model.b.bn = false;
        if (this.f9672a.y == null) {
            this.f9672a.b(8);
        } else if (!this.f9672a.y.e()) {
            com.unionpay.mobile.android.model.b.aB = false;
            this.f9672a.b(8);
        }
    }

    public final void b() {
        k.c("UPCardEngine", "mSE init failed");
        k.c("uppay-spay", "tsmservice  init fail");
        if (this.f9672a.h != null) {
            this.f9672a.h.sendMessage(this.f9672a.h.obtainMessage(8, (Object) null));
        }
    }
}
