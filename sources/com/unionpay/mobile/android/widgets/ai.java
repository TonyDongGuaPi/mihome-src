package com.unionpay.mobile.android.widgets;

import android.view.View;

final class ai implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ah f9770a;

    ai(ah ahVar) {
        this.f9770a = ahVar;
    }

    public final void onClick(View view) {
        if (this.f9770a.c != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f9770a.s());
            sb.append("_change_phoneNO");
            this.f9770a.c.e(this.f9770a.p);
        }
    }
}
