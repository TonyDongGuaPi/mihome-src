package com.mobikwik.sdk.ui.util;

import android.content.DialogInterface;
import com.mobikwik.sdk.ui.util.d;

class k implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d.b f8428a;

    k(d.b bVar) {
        this.f8428a = bVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f8428a.f != null) {
            this.f8428a.f.c();
        }
    }
}
