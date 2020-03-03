package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.DialogInterface;

class f implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d f1092a;

    f(d dVar) {
        this.f1092a = dVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f1092a.f1090a.cancel();
        boolean unused = AuthActivity.this.g = false;
        g.a((Activity) AuthActivity.this, AuthActivity.this.d + "?resultCode=150");
        AuthActivity.this.finish();
    }
}
