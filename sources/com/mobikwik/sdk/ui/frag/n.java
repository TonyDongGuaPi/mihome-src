package com.mobikwik.sdk.ui.frag;

import android.view.View;
import android.widget.Spinner;

class n implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Spinner f8404a;
    final /* synthetic */ m b;

    n(m mVar, Spinner spinner) {
        this.b = mVar;
        this.f8404a = spinner;
    }

    public void onClick(View view) {
        this.b.a(this.f8404a.getSelectedItemPosition() + 1);
    }
}
