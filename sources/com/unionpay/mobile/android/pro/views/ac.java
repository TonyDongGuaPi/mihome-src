package com.unionpay.mobile.android.pro.views;

import android.view.View;
import com.unionpay.mobile.android.nocard.utils.c;
import com.unionpay.mobile.android.views.order.l;

final class ac implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ y f9679a;

    ac(y yVar) {
        this.f9679a = yVar;
    }

    public final void onClick(View view) {
        if (c.a(this.f9679a.d, this.f9679a.f9608a) == l.c.intValue()) {
            this.f9679a.m();
            this.f9679a.m();
            return;
        }
        this.f9679a.m();
        if (this.f9679a.f9608a.J) {
            this.f9679a.m();
            this.f9679a.f9608a.J = false;
        }
        this.f9679a.f9608a.aP = l.c.intValue();
        this.f9679a.d(2);
    }
}
