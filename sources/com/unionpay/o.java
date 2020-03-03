package com.unionpay;

import android.content.DialogInterface;

final class o implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ n f9810a;

    o(n nVar) {
        this.f9810a = nVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        UPPayWapActivity.a(this.f9810a.f9809a, "cancel", (String) null);
    }
}
