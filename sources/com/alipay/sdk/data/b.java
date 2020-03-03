package com.alipay.sdk.data;

import android.content.Context;
import com.alipay.sdk.util.c;

class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f1106a;
    final /* synthetic */ a b;

    b(a aVar, Context context) {
        this.b = aVar;
        this.f1106a = context;
    }

    public void run() {
        try {
            com.alipay.sdk.packet.b a2 = new com.alipay.sdk.packet.impl.b().a(this.f1106a);
            if (a2 != null) {
                this.b.b(a2.b());
                this.b.i();
            }
        } catch (Throwable th) {
            c.a(th);
        }
    }
}
