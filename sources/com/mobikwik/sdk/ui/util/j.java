package com.mobikwik.sdk.ui.util;

import android.content.DialogInterface;
import com.mobikwik.sdk.ui.util.d;

class j implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d.b f8427a;

    j(d.b bVar) {
        this.f8427a = bVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.f8427a.f != null) {
            this.f8427a.f.a();
        }
    }
}
