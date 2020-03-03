package com.unionpay.mobile.android.nocard.views;

import android.view.View;

final class m implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9627a;
    final /* synthetic */ l b;

    m(l lVar, String str) {
        this.b = lVar;
        this.f9627a = str;
    }

    public final void onClick(View view) {
        this.b.c(this.f9627a);
    }
}
