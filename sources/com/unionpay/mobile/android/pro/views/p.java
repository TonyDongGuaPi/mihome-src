package com.unionpay.mobile.android.pro.views;

import android.view.View;
import com.unionpay.mobile.android.nocard.utils.c;
import com.unionpay.mobile.android.views.order.l;

final class p implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k f9695a;

    p(k kVar) {
        this.f9695a = kVar;
    }

    public final void onClick(View view) {
        if (c.a(this.f9695a.d, this.f9695a.f9608a) == l.c.intValue()) {
            this.f9695a.m();
            this.f9695a.m();
            return;
        }
        this.f9695a.m();
        if (this.f9695a.f9608a.J) {
            this.f9695a.m();
            this.f9695a.f9608a.J = false;
        }
        this.f9695a.f9608a.aP = l.c.intValue();
        this.f9695a.d(2);
    }
}
