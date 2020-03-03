package com.alipay.sdk.widget;

import android.content.DialogInterface;
import com.alipay.sdk.app.j;

class p implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ n f1155a;

    p(n nVar) {
        this.f1155a = nVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f1155a.f1153a.cancel();
        boolean unused = this.f1155a.b.w = false;
        j.a(j.c());
        this.f1155a.b.f1148a.finish();
    }
}
