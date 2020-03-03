package com.huawei.hms.b;

import android.content.DialogInterface;

class d implements DialogInterface.OnCancelListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f5865a;

    d(a aVar) {
        this.f5865a = aVar;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.f5865a.c();
    }
}
