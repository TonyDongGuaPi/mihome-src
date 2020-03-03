package com.mobikwik.sdk.ui.frag;

import android.view.View;
import com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType;

class w implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ t f8414a;

    w(t tVar) {
        this.f8414a = tVar;
    }

    public void onClick(View view) {
        this.f8414a.c.a(PaymentInstrumentType.DEBIT_CARD);
    }
}
