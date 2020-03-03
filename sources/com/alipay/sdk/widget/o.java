package com.alipay.sdk.widget;

import android.content.DialogInterface;

class o implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ n f1154a;

    o(n nVar) {
        this.f1154a = nVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        boolean unused = this.f1154a.b.w = true;
        this.f1154a.f1153a.proceed();
        dialogInterface.dismiss();
    }
}
