package com.mobikwik.sdk.ui.frag;

import android.text.Editable;
import android.text.TextWatcher;

class ae implements TextWatcher {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ y f8386a;

    ae(y yVar) {
        this.f8386a = yVar;
    }

    public void afterTextChanged(Editable editable) {
        this.f8386a.a();
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
