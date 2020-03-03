package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;

final class k extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9182a;
    final /* synthetic */ Handler b;

    k(Context context, Handler handler) {
        this.f9182a = context;
        this.b = handler;
    }

    public void run() {
        if (am.a().a(true, this.f9182a) == 0) {
            am.a().b(this.f9182a, true);
        }
        o.a(true).a(this.f9182a, false, false);
        bt a2 = bt.a();
        a2.a(this.f9182a);
        boolean b2 = a2.b();
        this.b.sendEmptyMessage(3);
        if (!b2) {
            this.b.sendEmptyMessage(2);
        } else {
            this.b.sendEmptyMessage(1);
        }
    }
}
