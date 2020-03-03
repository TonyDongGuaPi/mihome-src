package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

final class r implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ k f9697a;

    r(k kVar) {
        this.f9697a = kVar;
    }

    public final void onClick(View view) {
        this.f9697a.y.d();
        ((InputMethodManager) this.f9697a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.f9697a.getWindowToken(), 0);
        Context unused = this.f9697a.d;
        StringBuilder sb = new StringBuilder();
        sb.append(this.f9697a.q);
        sb.append("_open_user_protocol");
        this.f9697a.a(this.f9697a.L.d(), this.f9697a.L.c());
    }
}
