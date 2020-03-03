package com.unionpay.mobile.android.pro.views;

import android.view.View;

final class m implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f9692a;
    final /* synthetic */ k b;

    m(k kVar, boolean z) {
        this.b = kVar;
        this.f9692a = z;
    }

    public final void onClick(View view) {
        this.b.i();
        if (this.f9692a) {
            this.b.j();
            return;
        }
        boolean unused = this.b.M = true;
        this.b.S.removeAllViews();
        this.b.A.removeAllViews();
        this.b.F.setBackgroundColor(-16686660);
        this.b.F.a(0);
        this.b.c();
        this.b.a(this.b.R);
    }
}
