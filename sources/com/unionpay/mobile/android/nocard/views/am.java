package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

final class am implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ak f9595a;

    am(ak akVar) {
        this.f9595a = akVar;
    }

    public final void onClick(View view) {
        this.f9595a.v.d();
        ((InputMethodManager) this.f9595a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.f9595a.getWindowToken(), 0);
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9595a.q);
        sb.append("_open_user_protocol");
        this.f9595a.a(this.f9595a.t.d(), this.f9595a.t.c());
    }
}
