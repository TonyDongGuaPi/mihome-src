package com.unionpay.mobile.android.views.order;

import android.view.View;

final class k implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ i f9758a;

    k(i iVar) {
        this.f9758a = iVar;
    }

    public final void onClick(View view) {
        if (this.f9758a.e != null) {
            this.f9758a.e.a(i.a(this.f9758a.h, "label"), i.a(this.f9758a.h, "href"));
        }
    }
}
