package com.unionpay.mobile.android.upwidget;

import android.view.View;
import java.util.Iterator;

final class d implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c f9717a;

    d(c cVar) {
        this.f9717a = cVar;
    }

    public final void onClick(View view) {
        Iterator it = this.f9717a.l.iterator();
        while (it.hasNext()) {
            ((View.OnClickListener) it.next()).onClick(view);
        }
    }
}
