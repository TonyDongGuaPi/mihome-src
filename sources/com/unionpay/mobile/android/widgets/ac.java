package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

final class ac implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aa f9766a;

    ac(aa aaVar) {
        this.f9766a = aaVar;
    }

    public final void afterTextChanged(Editable editable) {
        this.f9766a.a(editable);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.f9766a.b.hasFocus() && TextUtils.isEmpty(this.f9766a.b.b())) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f9766a.s());
            sb.append(this.f9766a.d());
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.f9766a.c != null) {
            this.f9766a.c.a(this.f9766a.b, charSequence.toString());
        }
    }
}
