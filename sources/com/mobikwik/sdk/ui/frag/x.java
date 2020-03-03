package com.mobikwik.sdk.ui.frag;

import android.view.View;
import com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType;

class x implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ t f8415a;

    x(t tVar) {
        this.f8415a = tVar;
    }

    public void onClick(View view) {
        this.f8415a.c.a(PaymentInstrumentType.NETBANKING);
    }
}
