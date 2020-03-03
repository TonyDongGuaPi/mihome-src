package com.unionpay.mobile.android.nocard.views;

import android.view.View;

final class d implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f9620a;
    final /* synthetic */ b b;

    d(b bVar, boolean z) {
        this.b = bVar;
        this.f9620a = z;
    }

    public final void onClick(View view) {
        this.b.i();
        if (this.f9620a) {
            this.b.j();
        }
    }
}
