package com.unionpay.mobile.android.nocard.views;

import android.view.View;

final class u implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ o f9635a;

    u(o oVar) {
        this.f9635a = oVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        String unused = this.f9635a.F = str;
        int unused2 = this.f9635a.B = 5;
        this.f9635a.d(str, "");
    }
}
