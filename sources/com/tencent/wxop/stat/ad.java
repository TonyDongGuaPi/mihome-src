package com.tencent.wxop.stat;

import android.content.Context;

final class ad implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9285a;
    final /* synthetic */ int b;

    ad(Context context, int i) {
        this.f9285a = context;
        this.b = i;
    }

    public final void run() {
        try {
            StatServiceImpl.i(this.f9285a);
            au.a(this.f9285a).a(this.b);
        } catch (Throwable th) {
            StatServiceImpl.q.b(th);
            StatServiceImpl.a(this.f9285a, th);
        }
    }
}
