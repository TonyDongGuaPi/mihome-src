package com.unionpay.mobile.android.pro.views;

import android.view.View;

final class o implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k f9694a;

    o(k kVar) {
        this.f9694a = kVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        String unused = this.f9694a.P = str;
        int unused2 = this.f9694a.Q = 5;
        this.f9694a.d(str, "");
    }
}
