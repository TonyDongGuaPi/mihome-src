package com.mobikwik.sdk.ui.frag;

import android.text.Editable;
import android.text.TextWatcher;

class q implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ o f8408a;

    q(o oVar) {
        this.f8408a = oVar;
    }

    public void afterTextChanged(Editable editable) {
        boolean unused = this.f8408a.a(true);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
