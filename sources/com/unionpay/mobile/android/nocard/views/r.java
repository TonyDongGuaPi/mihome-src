package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.languages.c;

final class r implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9632a;
    final /* synthetic */ o b;

    r(o oVar, String str) {
        this.b = oVar;
        this.f9632a = str;
    }

    public final void onClick(View view) {
        this.b.a(c.bD.s, this.f9632a);
    }
}
