package com.unionpay.mobile.android.widgets;

import android.view.View;

final class ak implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aj f9772a;

    ak(aj ajVar) {
        this.f9772a = ajVar;
    }

    public final void onClick(View view) {
        if (this.f9772a.p != null) {
            this.f9772a.p.dismiss();
        }
    }
}
