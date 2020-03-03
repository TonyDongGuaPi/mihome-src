package com.unionpay.mobile.android.pro.pboc.engine;

import com.unionpay.mobile.android.pboctransaction.b;

final class e implements b {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9671a;

    e(b bVar) {
        this.f9671a = bVar;
    }

    public final void a() {
        this.f9671a.b(4);
    }

    public final void b() {
        if (this.f9671a.h != null) {
            this.f9671a.h.sendMessage(this.f9671a.h.obtainMessage(4, (Object) null));
        }
    }
}
