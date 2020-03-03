package com.unionpay.mobile.android.pro.views;

import android.view.View;

final class d implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9686a;

    d(a aVar) {
        this.f9686a = aVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        String unused = this.f9686a.E = str;
        int unused2 = this.f9686a.D = 5;
        this.f9686a.e(str, "");
    }
}
