package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.nocard.utils.c;
import com.unionpay.mobile.android.views.order.l;

final class ax implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ at f9605a;

    ax(at atVar) {
        this.f9605a = atVar;
    }

    public final void onClick(View view) {
        if (c.a(this.f9605a.d, this.f9605a.f9608a) == l.c.intValue()) {
            this.f9605a.m();
            this.f9605a.m();
            return;
        }
        this.f9605a.m();
        if (this.f9605a.f9608a.J) {
            this.f9605a.m();
            this.f9605a.f9608a.J = false;
        }
        this.f9605a.f9608a.aP = l.c.intValue();
        this.f9605a.d(2);
    }
}
