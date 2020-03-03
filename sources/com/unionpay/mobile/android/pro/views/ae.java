package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

final class ae implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ y f9681a;

    ae(y yVar) {
        this.f9681a = yVar;
    }

    public final void onClick(View view) {
        this.f9681a.A.d();
        ((InputMethodManager) this.f9681a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.f9681a.getWindowToken(), 0);
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9681a.q);
        sb.append("_open_user_protocol");
        this.f9681a.a(this.f9681a.x.d(), this.f9681a.x.c());
    }
}
