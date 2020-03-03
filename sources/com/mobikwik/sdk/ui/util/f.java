package com.mobikwik.sdk.ui.util;

import android.content.DialogInterface;
import com.mobikwik.sdk.ui.util.d;

class f implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d.b f8423a;

    f(d.b bVar) {
        this.f8423a = bVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f8423a.f();
        this.f8423a.b();
        if (this.f8423a.f != null) {
            this.f8423a.f.c();
        }
    }
}
