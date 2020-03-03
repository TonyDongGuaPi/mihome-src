package com.unionpay.mobile.android.widgets;

import android.view.View;

final class b implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ a f9783a;

    b(a aVar) {
        this.f9783a = aVar;
    }

    public final void onClick(View view) {
        if (this.f9783a.r != null) {
            this.f9783a.r.dismiss();
        }
    }
}
