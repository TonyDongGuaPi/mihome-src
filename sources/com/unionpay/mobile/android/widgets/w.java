package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;

final class w implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ u f9807a;

    w(u uVar) {
        this.f9807a = uVar;
    }

    public final void afterTextChanged(Editable editable) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        ImageView a2;
        int i4;
        if (this.f9807a.c != null) {
            if (!u.b(this.f9807a) || !this.f9807a.b.isFocused()) {
                a2 = this.f9807a.c;
                i4 = 8;
            } else {
                a2 = this.f9807a.c;
                i4 = 0;
            }
            a2.setVisibility(i4);
        }
    }
}
