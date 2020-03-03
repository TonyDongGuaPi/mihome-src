package com.huawei.hms.update.e;

import android.content.DialogInterface;

class c implements DialogInterface.OnCancelListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ b f5922a;

    c(b bVar) {
        this.f5922a = bVar;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.f5922a.d();
    }
}
