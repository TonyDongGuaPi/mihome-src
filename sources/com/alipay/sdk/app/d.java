package com.alipay.sdk.app;

import android.content.DialogInterface;

class d implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c f1071a;

    d(c cVar) {
        this.f1071a = cVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        boolean unused = this.f1071a.c.b = true;
        this.f1071a.b.proceed();
        dialogInterface.dismiss();
    }
}
