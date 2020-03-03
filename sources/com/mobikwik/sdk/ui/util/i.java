package com.mobikwik.sdk.ui.util;

import android.content.DialogInterface;
import com.mobikwik.sdk.ui.util.d;

class i implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d.b f8426a;

    i(d.b bVar) {
        this.f8426a = bVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f8426a.f != null) {
            this.f8426a.f.b();
        }
    }
}
