package com.mobikwik.sdk.ui.frag;

import android.content.DialogInterface;
import com.mobikwik.sdk.lib.model.SavedCardResponse;

class r implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SavedCardResponse.CardDetails f8409a;
    final /* synthetic */ o b;

    r(o oVar, SavedCardResponse.CardDetails cardDetails) {
        this.b = oVar;
        this.f8409a = cardDetails;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.b.a(this.f8409a);
    }
}
