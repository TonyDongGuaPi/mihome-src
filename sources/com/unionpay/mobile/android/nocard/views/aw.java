package com.unionpay.mobile.android.nocard.views;

import android.view.View;

final class aw implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ at f9604a;

    aw(at atVar) {
        this.f9604a = atVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        String unused = this.f9604a.E = str;
        int unused2 = this.f9604a.w = 5;
        this.f9604a.d(str, "");
    }
}
