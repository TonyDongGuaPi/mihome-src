package com.unionpay.mobile.android.nocard.views;

import android.view.View;

final class i implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f9624a;

    i(g gVar) {
        this.f9624a = gVar;
    }

    public final void onClick(View view) {
        this.f9624a.v.d();
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9624a.q);
        sb.append("_open_user_protocol");
        this.f9624a.a(this.f9624a.t.d(), this.f9624a.t.c());
    }
}
