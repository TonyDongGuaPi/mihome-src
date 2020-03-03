package com.unionpay;

import android.app.AlertDialog;
import android.view.View;
import com.unionpay.utils.k;

final class n implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ UPPayWapActivity f9809a;

    n(UPPayWapActivity uPPayWapActivity) {
        this.f9809a = uPPayWapActivity;
    }

    public final void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.f9809a);
        AlertDialog unused = this.f9809a.d = builder.create();
        builder.setMessage(k.a().f9847a);
        builder.setTitle(k.a().d);
        builder.setPositiveButton(k.a().b, new o(this));
        builder.setNegativeButton(k.a().c, new p(this));
        builder.create().show();
    }
}
