package com.alipay.sdk.app;

import android.content.DialogInterface;

class e implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c f1072a;

    e(c cVar) {
        this.f1072a = cVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f1072a.b.cancel();
        boolean unused = this.f1072a.c.b = false;
        j.a(j.c());
        this.f1072a.f1070a.finish();
    }
}
