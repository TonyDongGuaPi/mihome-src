package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import com.unionpay.mobile.android.nocard.views.o;

final class ac implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ o.b f9588a;

    ac(o.b bVar) {
        this.f9588a = bVar;
    }

    public final void onClick(View view) {
        if (this.f9588a.b != null) {
            this.f9588a.b.dismiss();
        }
    }
}
