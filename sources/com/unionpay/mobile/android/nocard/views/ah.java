package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.languages.c;

final class ah implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9592a;
    final /* synthetic */ af b;

    ah(af afVar, String str) {
        this.b = afVar;
        this.f9592a = str;
    }

    public final void onClick(View view) {
        this.b.a(c.bD.s, this.f9592a);
    }
}
