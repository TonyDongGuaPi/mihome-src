package com.mobikwik.sdk.ui.frag;

import android.view.View;
import com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType;

class v implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ t f8413a;

    v(t tVar) {
        this.f8413a = tVar;
    }

    public void onClick(View view) {
        this.f8413a.c.a(PaymentInstrumentType.CREDIT_CARD);
    }
}
