package com.unionpay.mobile.android.pro.views;

import android.os.Bundle;
import android.os.Handler;
import com.unionpay.mobile.android.model.c;
import com.unionpay.mobile.android.pro.pboc.engine.b;
import java.util.HashMap;

final class ad implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9680a;
    final /* synthetic */ c b;
    final /* synthetic */ String c;
    final /* synthetic */ HashMap d;
    final /* synthetic */ y e;

    ad(y yVar, b bVar, c cVar, String str, HashMap hashMap) {
        this.e = yVar;
        this.f9680a = bVar;
        this.b = cVar;
        this.c = str;
        this.d = hashMap;
    }

    public final void run() {
        Bundle a2 = this.f9680a.a(this.b, this.c, this.e.f9608a.p, this.d, this.e.f9608a.m);
        Handler v = this.e.D;
        Handler v2 = this.e.D;
        if (a2 == null) {
            a2 = null;
        }
        v.sendMessage(v2.obtainMessage(0, a2));
    }
}
