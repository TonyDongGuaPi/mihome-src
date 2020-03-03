package com.unionpay.mobile.android.upwidget;

import android.view.View;
import java.util.Iterator;

final class i implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ g f9722a;

    i(g gVar) {
        this.f9722a = gVar;
    }

    public final void onClick(View view) {
        Iterator it = this.f9722a.d.iterator();
        while (it.hasNext()) {
            ((View.OnClickListener) it.next()).onClick(view);
        }
    }
}
