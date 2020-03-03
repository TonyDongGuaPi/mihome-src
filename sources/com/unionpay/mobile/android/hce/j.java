package com.unionpay.mobile.android.hce;

import android.os.Bundle;
import android.os.Handler;

final class j implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f9569a;

    j(f fVar) {
        this.f9569a = fVar;
    }

    public final void run() {
        f fVar = this.f9569a;
        String h = this.f9569a.l;
        int unused = this.f9569a.f;
        int unused2 = this.f9569a.h;
        Bundle b = fVar.d(h);
        f.k(this.f9569a);
        if (this.f9569a.y != null) {
            Handler d = this.f9569a.y;
            Handler d2 = this.f9569a.y;
            if (b == null) {
                b = null;
            }
            d.sendMessage(d2.obtainMessage(2001, b));
        }
    }
}
