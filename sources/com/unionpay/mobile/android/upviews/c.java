package com.unionpay.mobile.android.upviews;

import com.unionpay.mobile.android.upviews.b;
import java.util.TimerTask;

final class c extends TimerTask {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b.d f9710a;

    c(b.d dVar) {
        this.f9710a = dVar;
    }

    public final void run() {
        if (!b.this.e) {
            b.this.b.sendEmptyMessage(3);
        }
        b.this.d.cancel();
        b.this.d.purge();
    }
}
