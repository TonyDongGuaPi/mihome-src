package com.alipay.sdk.auth;

import android.content.DialogInterface;

class e implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d f1091a;

    e(d dVar) {
        this.f1091a = dVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        boolean unused = AuthActivity.this.g = true;
        this.f1091a.f1090a.proceed();
        dialogInterface.dismiss();
    }
}
