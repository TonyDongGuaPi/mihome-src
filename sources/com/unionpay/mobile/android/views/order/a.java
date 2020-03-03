package com.unionpay.mobile.android.views.order;

import android.view.View;

final class a implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AbstractMethod f9749a;

    a(AbstractMethod abstractMethod) {
        this.f9749a = abstractMethod;
    }

    public final void onClick(View view) {
        if (this.f9749a.f != null) {
            this.f9749a.f.a(this.f9749a.b(), this.f9749a.c() == null, this.f9749a.d(), this.f9749a.c());
        }
    }
}
