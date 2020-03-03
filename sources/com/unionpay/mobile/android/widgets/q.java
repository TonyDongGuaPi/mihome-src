package com.unionpay.mobile.android.widgets;

import android.view.View;

final class q implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ p f9801a;

    q(p pVar) {
        this.f9801a = pVar;
    }

    public final void onClick(View view) {
        if (this.f9801a.q != null) {
            this.f9801a.q.dismiss();
        }
    }
}
