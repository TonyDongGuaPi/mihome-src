package com.unionpay;

import android.content.Context;
import android.content.DialogInterface;

final class f implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9550a;

    f(Context context) {
        this.f9550a = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        UPPayAssistEx.a(this.f9550a, UPPayAssistEx.W, UPPayAssistEx.r());
        dialogInterface.dismiss();
    }
}
