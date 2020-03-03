package com.unionpay.mobile.android.pro.pboc.engine;

import com.unionpay.mobile.android.pboctransaction.b;

final class d implements b {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9670a;

    d(b bVar) {
        this.f9670a = bVar;
    }

    public final void a() {
        this.f9670a.b(2);
    }

    public final void b() {
        if (this.f9670a.h != null) {
            this.f9670a.h.sendMessage(this.f9670a.h.obtainMessage(2, (Object) null));
        }
    }
}
