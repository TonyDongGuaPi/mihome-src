package com.unionpay;

import android.content.Context;
import android.content.DialogInterface;

final class h implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9552a;

    h(Context context) {
        this.f9552a = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        UPPayAssistEx.a(this.f9552a, UPPayAssistEx.W, UPPayAssistEx.r());
        dialogInterface.dismiss();
    }
}
