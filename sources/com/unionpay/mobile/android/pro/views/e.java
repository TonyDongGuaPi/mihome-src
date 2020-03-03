package com.unionpay.mobile.android.pro.views;

import android.view.View;
import com.unionpay.mobile.android.nocard.utils.c;
import com.unionpay.mobile.android.views.order.l;

final class e implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9687a;

    e(a aVar) {
        this.f9687a = aVar;
    }

    public final void onClick(View view) {
        int a2 = c.a(this.f9687a.d, this.f9687a.f9608a);
        this.f9687a.m();
        this.f9687a.m();
        if (a2 != l.c.intValue()) {
            this.f9687a.f9608a.aP = l.c.intValue();
            this.f9687a.d(2);
        }
    }
}
