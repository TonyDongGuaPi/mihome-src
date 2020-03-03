package com.unionpay.mobile.android.nocard.views;

import android.view.View;

final class q implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ o f9631a;

    q(o oVar) {
        this.f9631a = oVar;
    }

    public final void onClick(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9631a.q);
        sb.append("_open_user_protocol");
        this.f9631a.a(this.f9631a.C.d(), this.f9631a.C.c());
    }
}
