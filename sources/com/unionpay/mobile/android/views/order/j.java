package com.unionpay.mobile.android.views.order;

import android.view.View;

final class j implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ i f9757a;

    j(i iVar) {
        this.f9757a = iVar;
    }

    public final void onClick(View view) {
        if (this.f9757a.e != null) {
            this.f9757a.e.a(i.a(this.f9757a.g, "label"), i.a(this.f9757a.g, "href"));
        }
    }
}
