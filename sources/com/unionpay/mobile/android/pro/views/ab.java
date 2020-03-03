package com.unionpay.mobile.android.pro.views;

import android.view.View;

final class ab implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ y f9678a;

    ab(y yVar) {
        this.f9678a = yVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        String unused = this.f9678a.B = str;
        int unused2 = this.f9678a.v = 5;
        this.f9678a.d(str, "");
    }
}
