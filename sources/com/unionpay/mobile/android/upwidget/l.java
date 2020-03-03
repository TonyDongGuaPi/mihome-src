package com.unionpay.mobile.android.upwidget;

import android.view.View;
import java.util.Iterator;

final class l implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ j f9726a;

    l(j jVar) {
        this.f9726a = jVar;
    }

    public final void onClick(View view) {
        Iterator it = this.f9726a.u.iterator();
        while (it.hasNext()) {
            ((View.OnClickListener) it.next()).onClick(view);
        }
    }
}
