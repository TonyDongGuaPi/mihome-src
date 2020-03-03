package com.unionpay.mobile.android.views.order;

import android.view.View;

final class f implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f9754a;

    f(b bVar) {
        this.f9754a = bVar;
    }

    public final void onClick(View view) {
        if (this.f9754a.e != null) {
            this.f9754a.e.a(b.a(this.f9754a.g, "title"), b.a(this.f9754a.g, "href"));
        }
    }
}
