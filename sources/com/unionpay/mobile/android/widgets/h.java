package com.unionpay.mobile.android.widgets;

import android.view.View;

final class h implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f9793a;

    h(g gVar) {
        this.f9793a = gVar;
    }

    public final void onClick(View view) {
        if (this.f9793a.r != null) {
            this.f9793a.r.dismiss();
        }
    }
}
