package com.unionpay.mobile.android.views.order;

import android.view.View;
import com.unionpay.mobile.android.utils.k;

final class h implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f9756a;
    final /* synthetic */ CViewMethods b;

    h(CViewMethods cViewMethods, int i) {
        this.b = cViewMethods;
        this.f9756a = i;
    }

    public final void onClick(View view) {
        k.c("uppay", " touched " + this.f9756a);
        if (this.b.j != null) {
            this.b.j.c(this.f9756a);
        }
    }
}
