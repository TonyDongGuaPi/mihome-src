package com.unionpay.mobile.android.views.order;

import android.view.View;

final class d implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9752a;

    d(b bVar) {
        this.f9752a = bVar;
    }

    public final void onClick(View view) {
        if (this.f9752a.m != null) {
            this.f9752a.m.dismiss();
        }
    }
}
