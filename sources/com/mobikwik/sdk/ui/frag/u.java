package com.mobikwik.sdk.ui.frag;

import android.view.View;
import com.mobikwik.sdk.lib.payinstrument.PaymentInstrumentType;

class u implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ t f8412a;

    u(t tVar) {
        this.f8412a = tVar;
    }

    public void onClick(View view) {
        this.f8412a.c.a(PaymentInstrumentType.SAVED_CARD);
    }
}
