package com.unionpay.mobile.android.upwidget;

import android.view.View;

final class n implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ j f9728a;

    n(j jVar) {
        this.f9728a = jVar;
    }

    public final void onClick(View view) {
        this.f9728a.m.setEnabled(false);
        this.f9728a.l.setVisibility(8);
        this.f9728a.k.setVisibility(0);
    }
}
