package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

final class ay implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ at f9606a;

    ay(at atVar) {
        this.f9606a = atVar;
    }

    public final void onClick(View view) {
        this.f9606a.B.d();
        ((InputMethodManager) this.f9606a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.f9606a.getWindowToken(), 0);
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9606a.q);
        sb.append("_open_user_protocol");
        this.f9606a.a(this.f9606a.y.d(), this.f9606a.y.c());
    }
}
