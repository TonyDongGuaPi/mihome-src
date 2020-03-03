package com.mobikwik.sdk.ui.frag;

import android.text.Editable;
import android.text.TextWatcher;

class c implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f8393a;

    c(a aVar) {
        this.f8393a = aVar;
    }

    public void afterTextChanged(Editable editable) {
        boolean unused = this.f8393a.a(true);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
