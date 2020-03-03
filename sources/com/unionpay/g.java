package com.unionpay;

import android.content.Context;
import android.content.DialogInterface;

final class g implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f9551a;

    g(Context context) {
        this.f9551a = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        UPPayAssistEx.b(this.f9551a);
    }
}
